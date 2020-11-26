package com.ktc.base.controller;

import com.ktc.base.pojo.Label;
import com.ktc.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

/**
 * @author mxc
 * @date 2020/11/18 15:11
 * @descrition
 * @return
 */
@RestController
@RequestMapping("/label")
@CrossOrigin
public class LabelController {
    @Autowired
    private LabelService labelService;
    /**
     * 添加
     *
     * @param label
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true, StatusCode.OK, "添加成功");
    }
    /**
     * 根据id删除标签
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deleteByID(@PathVariable String id) {
        labelService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }
    /**
     * 修改标签
     * @param id
     * @param label
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable String id,@RequestBody Label label) {
        label.setId(id);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }
    /**
     * 根据标签id查询单个标签
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        Label label = labelService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", label);
    }

    /**
     * 查询全部标签
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Label> labelList = labelService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", labelList);
    }

    /**
     * 根据条件查询标签信息
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result search(@RequestBody Map searchMap){
        return new Result(true, StatusCode.OK,"添加成功", labelService.search(searchMap));
    }

    @RequestMapping(value = "search/{page}/{pageSize}", method = RequestMethod.POST)
    public Result searchPage(@RequestBody Map searchMap, @PathVariable Integer page, @PathVariable Integer pageSize){
        Page<Label> pageData = labelService.findPage(searchMap,page,pageSize);
        return new Result(true, StatusCode.OK, "查询成功",new PageResult<>(pageData.getTotalElements(),pageData.getContent()));
    }

}
