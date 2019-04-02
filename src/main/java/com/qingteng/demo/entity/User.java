package com.qingteng.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;
    private String email;
    private Date lastPasswordResetDate;


    @OneToMany(targetEntity=Role.class,
            fetch=FetchType.EAGER)
    private List<String> roles;


}
