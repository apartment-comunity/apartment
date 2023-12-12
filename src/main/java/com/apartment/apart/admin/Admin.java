package com.apartment.apart.admin;

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
    private String username;
    private String password;
}
