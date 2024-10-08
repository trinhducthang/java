package com.ducthang.ManagerUsers.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.ducthang.ManagerUsers.model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    boolean existsByNameAndAddressAndDob(String name, String address, String dob);
    Optional<Users> findByUsername(String username);

    @Query("SELECT u FROM Users u JOIN u.banks b WHERE b.bankNumber = :bankNumber")
    Users findUserByBankNumber(@Param("bankNumber") String bankNumber);

    Users findByEmail(String email);

    Users findUsersByUsername(String username);

}
