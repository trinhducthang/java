package com.ducthang.ManagerUsers.model;


import com.ducthang.ManagerUsers.ultil.Gender;
import com.ducthang.ManagerUsers.ultil.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)



public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String username;
    String password;
    String name;
    String dob;
    Gender gender;
    String phone;
    String email;
    String address;
    Role role;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "users",cascade = CascadeType.DETACH)
    @JsonManagedReference
    private Set<Bank> banks;

    @OneToMany(mappedBy = "users",cascade = CascadeType.DETACH)
    @JsonManagedReference
    private Set<Card> cards;

    @OneToMany(mappedBy = "users",cascade = CascadeType.DETACH)
    //Khong can thiet, se map qua response dto
    @JsonManagedReference
    private Set<TransactionHistory> transactionHistories;


}
