package com.ktc.gathering.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ktc.gathering.pojo.Usergath;
import com.ktc.gathering.service.UsergathService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

/**
* @Description 用户关注活动 控制器层
* @author admin
* @date 2020-11-26 10:33:26
*/
@RestController
@CrossOrigin
@RequestMapping("/usergath")
public class UsergathController{

@Autowired
private UsergathService usergathService;

/**
* 查询全部数据
* @return
*/
@RequestMapping(method= RequestMethod.GET)
public Result findAll(){
return new Result(true,StatusCode.OK,"查询成功",usergathService.findAll());
}

/**
* 根据ID查询
* @param id ID
* @return
*/
@RequestMapping(value="/{id}",method= RequestMethod.GET)
public Result findById(@PathVariable String id){
return new Result(true,StatusCode.OK,"查询成功",usergathService.findById(id));
}

/**
* 分页+多条件查询
* @param searchMap 查询条件封装
* @param page 页码
* @param size 页大小
* @return 分页结果
*/
@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
Page<Usergath> pageList = usergathService.findSearch(searchMap, page, size);
return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Usergath>(pageList.getTotalElements(), pageList.getContent()) );
}

/**
* 根据条件查询
* @param searchMap
* @return
*/
@RequestMapping(value="/search",method = RequestMethod.POST)
public Result findSearch( @RequestBody Map searchMap){
return new Result(true,StatusCode.OK,"查询成功", usergathService.findSearch(searchMap));
}

/**
* 增加
* @param usergath
*/
@RequestMapping(method=RequestMethod.POST)
public Result add(@RequestBody  Usergath  usergath  ){
usergathService.add( usergath);
return new Result(true,StatusCode.OK,"增加成功");
}

/**
* 修改
* @param usergath
*/
@RequestMapping(value="/{id}",method= RequestMethod.PUT)
public Result update(@RequestBody  Usergath  usergath, @PathVariable String id ){
usergath.setId(id);
usergathService.update( usergath);
return new Result(true,StatusCode.OK,"修改成功");
}

/**
* 删除
* @param id
*/
@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
public Result delete(@PathVariable String id ){
usergathService.deleteById(id);
return new Result(true,StatusCode.OK,"删除成功");
}

}
