package com.ecommerce.blockchain.domain.users;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name="users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    int id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private Timestamp created_at;

    @Column
    private String password;

}