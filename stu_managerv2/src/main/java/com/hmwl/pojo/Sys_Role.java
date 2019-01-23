package com.hmwl.pojo;

import lombok.*;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor@ToString
public class Sys_Role {
    private Integer role_id;
    private String role_name;
    private String role_code;
    private String role_desc;
    private Integer orders;
    private Integer is_valid;
}
