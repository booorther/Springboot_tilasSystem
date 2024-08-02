package com.itheima;

import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.management.snmp.jvmmib.JVM_MANAGEMENT_MIBOidTable;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class TestDeptMapper {

    @Autowired
    private DeptMapper deptMapper;
    @Test
    public void test(){
        List<Dept> deptList=deptMapper.findAll();
        for(Dept dept:deptList){
            System.out.println(dept);
        }
    }

    @Test
    public void deleteDeptTest(){
        deptMapper.deleteDeptById(1);
    }
    /*    @Test
    public void empSave() {
        Emp emp = new Emp();
        emp.setUsername("广泛的衮");
        emp.setPassword("66866");
        emp.setName("球");
        emp.setGender((short)1);
        emp.setImage("86.jpg");
        emp.setJob((short)2);
        log.info("保存前的emp对象的id：{}",emp.getId());
        empMapper.empSave(emp);
        log.info("保存后的emp对象的id：{}",emp.getId());

    }*/
    @Test
    public void addDeptTest(){
        Dept dept=new Dept();
        dept.setName("坤部");
        dept.setCreatTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.addDept(dept);
    }
    @Test
    public void updateDeptTest(){
        Dept dept=new Dept();
        dept.setId(12);
        dept.setName("好号部");
        dept.setCreatTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDept(dept);
    }
}
