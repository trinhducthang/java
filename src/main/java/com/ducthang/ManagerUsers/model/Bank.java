package com.ducthang.ManagerUsers.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;


    @ManyToOne
    @JoinColumn(name = "users_id",nullable = false,referencedColumnName = "id")
    @JsonBackReference
    private Users users;


    // Khong dung trong nay, dung trong request dto
    @NumberFormat
    private String bankNumber;

    private String name;

    private long balance;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
