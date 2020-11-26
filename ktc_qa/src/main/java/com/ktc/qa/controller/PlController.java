package com.ktc.qa.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ktc.qa.pojo.pl;
import com.ktc.qa.service.PlService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

/**
* @Description tb_pl 控制器层
* @author admin
* @date 2020-11-25 18:21:41
*/
@RestController
@CrossOrigin
@RequestMapping("/pl")
public class PlController {

@Autowired
private PlService plService;

/**
* 查询全部数据
* @return
*/
@RequestMapping(method= RequestMethod.GET)
public Result findAll(){
return new Result(true,StatusCode.OK,"查询成功",plService.findAll());
}

/**
* 根据ID查询
* @param id ID
* @return
*/
@RequestMapping(value="/{id}",method= RequestMethod.GET)
public Result findById(@PathVariable String id){
return new Result(true,StatusCode.OK,"查询成功",plService.findById(id));
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
Page<pl> pageList = plService.findSearch(searchMap, page, size);
return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<pl>(pageList.getTotalElements(), pageList.getContent()) );
}

/**
* 根据条件查询
* @param searchMap
* @return
*/
@RequestMapping(value="/search",method = RequestMethod.POST)
public Result findSearch( @RequestBody Map searchMap){
return new Result(true,StatusCode.OK,"查询成功", plService.findSearch(searchMap));
}

/**
* 增加
* @param pl
*/
@RequestMapping(method=RequestMethod.POST)
public Result add(@RequestBody  pl  pl  ){
plService.add( pl);
return new Result(true,StatusCode.OK,"增加成功");
}

/**
* 修改
* @param pl
*/
@RequestMapping(value="/{id}",method= RequestMethod.PUT)
public Result update(@RequestBody  pl  pl, @PathVariable String id ){
pl.setId(id);
plService.update( pl);
return new Result(true,StatusCode.OK,"修改成功");
}

/**
* 删除
* @param id
*/
@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
public Result delete(@PathVariable String id ){
plService.deleteById(id);
return new Result(true,StatusCode.OK,"删除成功");
}

}
