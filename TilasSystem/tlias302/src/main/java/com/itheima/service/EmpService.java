package com.itheima.service;


import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    void findCountDeptById(Integer id);

    List<Emp> findAllEmps();

    PageBean page(String name, Integer gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

    Emp findEmpByIdService(Integer id);

    void deleteEmpByIdService(List<Integer> ids);

    void addEmpService(Emp emp);

    void updateEmpService(Emp emp);

    Emp findByUsernamePasswordService(Emp emp);
}
