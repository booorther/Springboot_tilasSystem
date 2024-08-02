package com.itheima.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*@Data
@AllArgsConstructor
@NoArgsConstructor*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer gender;
    private String image;
    private Integer job;
    private LocalDate entrydate;
    private Integer deptId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}
