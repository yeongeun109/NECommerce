package com.ecommerce.blockchain.domain.user;

import com.ecommerce.blockchain.domain.wallet.Wallet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name="users")
public class User {

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

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Wallet> wallet;
}
