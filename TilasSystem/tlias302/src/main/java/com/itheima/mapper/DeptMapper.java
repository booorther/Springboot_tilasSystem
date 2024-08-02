package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /*List<Brand> list();*/
    @Select("select * from dept")
    List<Dept> findAll();
/*//    @Delete("delete from tb_brand where id=#{id}  ")
    void deleteById(Integer id);*/
    @Delete("delete from dept where id=#{id}")
    void deleteDeptById(Integer id);

    @Select("select * from dept where name=#{name}")
    Dept findDeptByName(Dept dept);

/*//    @Insert("insert into tb_brand values (#{id},#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void save(Brand brand);*/
//    @Insert("insert into dept values (#{id},#{name},#{new },#{updateTime},)")
    @Insert("insert into dept values (#{id},#{name},#{creatTime},#{updateTime})")
    void addDept(Dept dept);
/*    Brand findById(Integer id);*/
    @Select("select * from dept where id=#{id}")
    Dept findDeptById(Integer id);
    /*//    @Update("update emp set name=#{name} where id=#{id}")*/
    @Update(" update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void updateDept(Dept dept);

    @Select("select * from dept where  name=#{name} and id != #{id}")
    Dept findDeptConditionByName(Dept dept);
}
