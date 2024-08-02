package com.itheima.service;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeptService {
/*List<Brand> list();*/
    List<Dept> findAll();

    void deleteDeptById(Integer id);

    void addDept(Dept dept);

    Dept findDeptById(Integer id);

    void updateDept(Dept dept);

//    Dept findDeptByName(Dept dept);
}
