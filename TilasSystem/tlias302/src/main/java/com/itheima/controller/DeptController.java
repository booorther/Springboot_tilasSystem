package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j//打印日志
@RestController
@RequestMapping("/depts")
/*
  REST风格的API
  GET:查询
  POST:添加
  PUT:修改
  DELETE：删除
  */
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result findAll(){
        List<Dept> deptList=deptService.findAll();
        log.info("查询所有部门");
        return Result.success(deptList);
    }
/*
 删除部门 */
    @DeleteMapping("/{id}")//二级路径，占位符
//    @PathVariable Integer id：映射路径参数
    public Result deleteDeptById( @PathVariable Integer id){
        log.info("根据ID删除部门id={}",id);
        //删除成功则返回success,否则返回error
//        try{
            deptService.deleteDeptById(id);
//        }catch (Exception e){//try报错则执行
//            return Result.error(e.getMessage());
//        }
        return Result.success();
    }

    @PostMapping
    public Result addDept(@RequestBody Dept dept){
        log.info("部门已存在！");
//        try{
            deptService.addDept(dept);
//        }catch (Exception e){
//            return Result.error(e.getMessage());
//        }
        return Result.success();
    }
/*    @RequestMapping("/findById")
    public Result findById(Integer id){
        Brand brand=brandService.findById(id);
        return Result.success(brand);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Brand brand){
        brandService.update(brand);
        return Result.success();*/
    //根据Id查询部门
    @GetMapping("/{id}")//路径请求
    public Result findDeptById(@PathVariable Integer id){
        log.info("查询部门标号：id={}",id);
        Dept dept= deptService.findDeptById(id);
        return Result.success(dept);
    }

    @PutMapping//JSON请求
    public Result updateDept(@RequestBody Dept dept){
//已经被Service修改，得到的名称也为修改后的名称log.info("要修改前部门名称为：{}",dept.getName());

//        try{
            deptService.updateDept(dept);
//        }catch (Exception e){
//            return Result.error(e.getMessage());
//        }
        log.info("要修改后部门名称为：{}",dept.getName());
        return Result.success();
    }
}
