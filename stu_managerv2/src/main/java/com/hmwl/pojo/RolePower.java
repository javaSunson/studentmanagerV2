package com.hmwl.pojo;

import lombok.*;

import java.util.List;
@Setter@Getter@NoArgsConstructor@AllArgsConstructor@ToString
public class RolePower {
    private Integer role_id;
    private String role_name;
    private String role_code;
    private String role_desc;
    private Integer orders;
    private Integer is_valid;
    private List<Sys_Auth> auths;

}
