package com.hmwl.pojo;

import lombok.*;

import java.util.Date;
@Setter@Getter@NoArgsConstructor@AllArgsConstructor@ToString
public class Sys_User {
    private Integer user_id;
    private String username;
    private String password;
    private String sex;
    private Integer age;
    private String email;
    private String phone;
    private Integer dept_id;
    private Date register_date;
    private Integer is_valid;
}
