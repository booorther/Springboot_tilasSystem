package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    //普通分页参数请求
    public Result pageEpms(
            String name,
            Integer gender,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize
    ){
//        List<Emp> empList=empService.findAllEmps();
//        log.info("findAllEmps: {}", empList);
          log.info("翻页查询：姓名：{}，性别：{}，起始时间：{}，结束时间：{}，第{}页，页跨度：{}", name, gender, begin, end, page, pageSize);

        PageBean pageBean= empService.page(name,gender,begin,end,page,pageSize);
        return Result.success(pageBean);
    }
/*    @GetMapping("/{ids}")//路径请求
    public Result findDeptByIdController(@PathVariable Integer ids){
        log.info("查找删除员工编号：id={}，添加返回结果。",ids);
        Emp emp= empService.findEmpByIdService(ids);
        return Result.success(emp);
    }*/

    //单一和批量删除
    @DeleteMapping("/{ids}")
    public Result deleteEmpByIdsController(@PathVariable List<Integer>  ids){
        log.info("根据ID删除部门id:{}",ids);
        empService.deleteEmpByIdService(ids);
        return Result.success();
    }
    //员工添加
    /*    @PostMapping
    public Result addDept(@RequestBody Dept dept){
        log.info("部门已存在！");
        try{
            deptService.addDept(dept);
        }catch (Exception e){
            return Result.error(e.getMessage());
        }
        return Result.success();
    }*/
    @PostMapping
    public Result addEmpController(@RequestBody Emp emp){
        log.info("新增员工信息：",emp);
//        try{
            empService.addEmpService(emp);
//        }catch (Exception e){
//            return Result.error(e.getMessage());
//        }
        return Result.success();
    }
    /*根据ID查询员工*/
    @GetMapping("/{id}")
    public Result getEmpController(@PathVariable Integer id){
        Emp emp= empService.findEmpByIdService(id);
        return Result.success(emp);
    }
    @PutMapping
    public Result updateEmpController(@RequestBody Emp emp){
//        try{
            empService.updateEmpService(emp);

//        }catch (Exception e){
//            return Result.error(e.getMessage());
//        }
        return Result.success();
    }

}
