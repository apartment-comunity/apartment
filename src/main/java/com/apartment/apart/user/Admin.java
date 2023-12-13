package com.apartment.apart.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Admin")
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Column(unique = true)
    private String adminname;
    private String password;
}
