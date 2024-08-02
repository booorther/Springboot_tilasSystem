package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    //业务层，就可以创建多个mapper,保证业务的正常经行

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> findAll() {
        List<Dept> deptList=deptMapper.findAll();
        return deptList;
    }
/*    @Override
    public void deleteById(Integer id) {
        brandMapper.deleteById(id);
    }*/
    /*
    * 删除业务处理
    *

       */
    @Override
    public void deleteDeptById(Integer id) {
        //查询当前部门员工的部门id
        long conut=empMapper.findCountDeptById(id);
        if (conut>0){
//            log.info("删除失败，部门关联员工");
            //直接抛出异常
            throw new RuntimeException("部门关联员工，删除失败！");
        }else {
            deptMapper.deleteDeptById(id);
        }
    }

    @Override
    public void addDept(Dept dept) {
        Dept deptForMysql=deptMapper.findDeptByName(dept);
        if (deptForMysql!=null){
            throw new RuntimeException("添加失败，"+dept.getName()+"已存在部门");
        }
        dept.setCreatTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.addDept(dept);
    }

    @Override
    public Dept findDeptById(Integer id) {
        Dept dept= deptMapper.findDeptById(id);
        return dept;
    }
/*业务：部门名称和修改时间*/
    @Override
    public void updateDept(Dept dept) {
        //若只修改更新时间，本身的部门名不修改。即无操作保存也要更新修改时间
        Dept deptForMysql1=deptMapper.findDeptConditionByName(dept);
        if (deptForMysql1!=null){
            throw new RuntimeException("修改失败，"+dept.getName()+"已存!");
        }
//        if (dept.getName() == null || dept.getName().isEmpty()) {
//            System.out.println("部门名称为空");
//        }
//
//        dept.setCreatTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDept(dept);
    }

}
