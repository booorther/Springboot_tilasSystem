package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    //员工关联部门查询
    @Select("select count(*) from emp where dept_id=#{id}")
    long findCountDeptById(Integer id);
/*    @Select("select * from dept")
    List<Dept> findAll();*/
    //员工所有查询          在controller中没使用
    @Select("select * from emp")

    List<Emp> findAllEmps();
//员工分页查询
//    @Select("select * from emp limit #{startPage},#{pageSize}")
//List<Emp> page(String name, Integer gender, LocalDate begin, LocalDate end, Integer startPage, Integer pageSize);

    //分页插件查询
    List<Emp> page(String name, Integer gender, LocalDate begin,LocalDate end);

//根据员工ID查询
    @Select("select * from emp where id=#{id}")
    Emp findEmpByIdMapper(Integer id);
/*    @Delete("delete from dept where id=#{id}")
    void deleteDeptById(Integer id);*/
//    @Delete("delete from emp where id in (#{id},#{id2})")
    void deleteEmpByIdMapper(List<Integer> ids);


    @Insert("insert into emp values (#{id},#{username},#{password},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void addEmpMapper(Emp emp);

    @Select("select * from emp where username=#{username}")
    Emp findEmpByNameMapper(String username);

    void updateEmpService(Emp emp);

    @Select("select * from emp where username=#{username}  and id!=#{id}" )
    Emp findEmpByConditionMapper(Emp emp);

    @Select("select  * from emp where username=#{username}  ")
   //emp的password为密文
    //查询密码password为密文匹配
    Emp findByUsernamePasswordMapper(Emp emp);
//员工总计查询
////    @Select("select  count(*) from emp")
//    Long count(String name, Integer gender, LocalDate begin,LocalDate end);
}
