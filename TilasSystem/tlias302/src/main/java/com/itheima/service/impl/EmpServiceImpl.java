package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

     public void findCountDeptById(Integer id){
         empMapper.findCountDeptById(id);
    }

    @Override
    public List<Emp> findAllEmps() {
        List<Emp> empList=empMapper.findAllEmps();
        return empList;
    }
/*1.查询得到分页集合List<Emp>
* 2.查询员工总记录数
* 3.将1.和2.封装到pageBean中*/
    @Override
    public PageBean page(String name, Integer gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
//        //查询分页集合：select * from emp limit 0,10
//        // List<Emp> empList= empMapper.page(startPage,pageSize);
//        Integer startPage=(page-1)*pageSize;
//        //带条件的分页查询
//        List<Emp> empList= empMapper.page(name,gender,begin,end,startPage,pageSize);
//        //查询员工总数 Long total=empMapper.count();
//        //有参数的name,gender,begin,end，员工统计
//        Long total=empMapper.count(name,gender,begin,end);
//        //存储员工集合List<Emp>和记录总数total分装到PageBean对象中，并返回给controller
//        PageBean pageBean=new PageBean();
//        pageBean.setTotal(total);
//        pageBean.setRows(empList);



//设置分页参数：底层根据page页码计算起始索引，生成sql片段
        //tartPage(page,pageSize)=》limit #{page},#{pageSize}
        //import com.github.pagehelper.Page;包内的分页类
        Page pageInfo= PageHelper.startPage(page,pageSize);
        //2.直接调用一个条件查询，无需分页sql。基于下列查询sql
        //2.1改造出一条count求总数的sql（select count(0) from emp）
        //2.2改造出一条分页limit的sql语句（select * from emp oder by update_time limit 5,5
        //2.3把上列改造好的sql语句执行结果，自动封装到pageInfo对象的total和result中
        empMapper.page(name,gender,begin,end);
        PageBean pageBean=new PageBean();
        /*public class PageBean {
          private Long total; //总记录数
          private List rows; //当前页数据列表
          }*/
        pageBean.setTotal(pageInfo.getTotal());
        pageBean.setRows(pageInfo.getResult());

        return pageBean;
    }

    @Override
    public Emp findEmpByIdService(Integer id) {
        Emp emp=empMapper.findEmpByIdMapper(id);
        return emp;
    }

    @Override
    public void deleteEmpByIdService(List<Integer> ids) {

        empMapper.deleteEmpByIdMapper(ids);
    }
/*业务
* 1.完善数据 password createTime updateTime
* 2.*/
    @Override
    public void addEmpService(Emp emp) {
        Emp empNameForMysql=empMapper.findEmpByNameMapper(emp.getUsername());
        if(empNameForMysql!=null){
            throw  new RuntimeException(emp.getUsername()+"已存在，请重新添加！");
        }
        //添加用户信息时
        //添加设置默认密码明文
        String password="123456";
        //明文密码MD5加密，得到密文密码passwordMD5
        String passwordMD5= DigestUtils.md5DigestAsHex(password.getBytes());
        //生成随机盐。即随机函数
        String randomSalt= UUID.randomUUID().toString().replaceAll("-","");
        //密文密码passwordMD5 + 随机盐函数----->再次MD5加密  + 随机函数盐
        String saltMD5= DigestUtils.md5DigestAsHex((passwordMD5+randomSalt).getBytes()) +randomSalt;
        //添加密码经过加密的复合函数值
        emp.setPassword(saltMD5);
        //密码密文（不规则字符串）存放到数据库中
        // emp.setPassword(passwordMD5);
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.addEmpMapper(emp);
    }

    @Override
    public void updateEmpService(Emp emp) {

//        Emp empNameForMysql=empMapper.findEmpByNameMapper(emp.getUsername());
        Emp empNameForMysql=empMapper.findEmpByConditionMapper(emp);
        if(empNameForMysql!=null){
            throw  new RuntimeException(emp.getUsername()+"已存在，请重新修改！");
        }
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateEmpService(emp);
    }

    @Override
    public Emp findByUsernamePasswordService(Emp emp) {
/*        //emp从controller层中获取明文的usersname和password:123456
        //emp.getPassword()明文密码，加密
        String password=DigestUtils.md5DigestAsHex(emp.getPassword().getBytes());
        //设置emp的密码为密文:e10adc3949ba59abbe56e057f20f883e
        emp.setPassword(password);
        //emp中的usersname为明文查找，password为密文查找
        //实际都是字符串查找
        Emp usernamePassword=empMapper.findByUsernameMapper(emp);
 */
        //查询用户名
        //1.emp从controller层中获取明文的usersname,并于数据库比较
       Emp empNameForMysql=empMapper.findEmpByNameMapper(emp.getUsername());
       if(empNameForMysql==null){
           throw new RuntimeException("用户名错误");
       }

       //判断密码
        //2.emp从controller层中获取明文的usersname查询数据库，获取所有数据username，password，name...
        //获取所有数据username，password，name...的值赋给empNameForMysql，且password在数据库中已经固定
        //empNameForMysql调取数据库赋给password的值，并截取到第32位
        String sqlSalt=empNameForMysql.getPassword().substring(32);
        //emp从controller层中获取明文的password:123456，进行DM5加密
        String passwordMD5= DigestUtils.md5DigestAsHex(emp.getPassword().getBytes());
        //从浏览器获取password密码MD5加密+截取数据库密码盐salt---->再次MD5加密  +  截取数据库密码盐salt
        String passwordSaltMD5=DigestUtils.md5DigestAsHex((passwordMD5+sqlSalt).getBytes()) +sqlSalt;
        //拼接出来的密码盐passwordsalt在与数据库中的密码再次匹配
        if (!passwordSaltMD5.equals(empNameForMysql.getPassword())) {
           throw new RuntimeException("密码错误");
        }
        return empNameForMysql;
    }
}
