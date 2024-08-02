package com.itheima;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;
@Slf4j
@SpringBootTest
public class TestEmpMaper {


    @Autowired
    private EmpMapper empMapper;

        @Test
        public void empSave() {
            Emp emp = new Emp();
            emp.setUsername("广泛的衮");
            emp.setPassword("66866");
            emp.setName("球");
            emp.setGender((int) 1);
            emp.setImage("86.jpg");
            emp.setJob((int) 2);
            emp.setPassword("123456");
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            log.info("保存前的emp对象的id：{}",emp.getId());
            empMapper.addEmpMapper(emp);
            log.info("保存后的emp对象的id：{}",emp.getId());

        }
//    @Test
//    public void testDelectEmpMaper() {
//        empMapper.deleteEmpByIdMapper(Collections.singletonList(1));
//    }
}
