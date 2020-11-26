package com.ktc.spit.controller;

import com.ktc.spit.pojo.Spit;
import com.ktc.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.*;
/**
 * Description:
 * date: 2020/11/26 17:12
 *
 * @author mxc
 */
@RestController
@RequestMapping("/spit")
@CrossOrigin
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 查询全部
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findAll());
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findById(id));
    }

    /**
     * 修改
     * @param spit
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable String id,@RequestBody Spit spit){
        spit.setId(id);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(@RequestBody String id){
        spitService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 增加
     * @param spit
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    /**
     根据吐槽id查询评论 
     * @return:entity.Result
     * @Author mxc
     * @Description comment 
     * @Date 2020/11/26 17:25
     **/
    @RequestMapping(value = "/comment/{parentid}/{page}/{size}", method = RequestMethod.GET)
    public Result comment(@PathVariable String parentid, @PathVariable int page, @PathVariable int size){
        Page<Spit> pageData = spitService.comment(parentid,page,size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageData.getTotalElements(),pageData.getContent()));
    }

    /**
     吐槽点赞
     * @return:entity.Result
     * @Author mxc
     * @Description thumbup
     * @Date 2020/11/26 17:33
     **/
    @RequestMapping(value = "/thumbup/{spitid}", method = RequestMethod.PUT)
    public Result thumbup(@PathVariable String spitid){
        String userid = "3";//暂时写死

        //key字符串可见
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        Object flag = redisTemplate.opsForValue().get("spit_" + userid + "_" +spitid);

        if (flag!=null){
            //删除redis中的标记
            redisTemplate.delete("spit_" + userid + "_" + spitid);
            //让原来MongoDB中的点赞记录数-1
            spitService.delthumbup(spitid);
            return new Result(true, StatusCode.OK, "已取消点赞");
        }
        //存入一个标记在redis中说明该用户已经点赞
        redisTemplate.opsForValue().set("spit_" + userid + "_" + spitid,"1");

        spitService.thumbup(spitid);
        return new Result(true, StatusCode.OK,"点赞成功");
    }
}
