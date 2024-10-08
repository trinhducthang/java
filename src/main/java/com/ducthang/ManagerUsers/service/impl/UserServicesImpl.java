package com.ducthang.ManagerUsers.service.impl;


import com.ducthang.ManagerUsers.model.Users;
import com.ducthang.ManagerUsers.repository.UsersRepository;
import com.ducthang.ManagerUsers.service.UserServices;
import com.ducthang.ManagerUsers.ultil.Role;
import com.ducthang.ManagerUsers.dto.UserDTO;
import com.ducthang.ManagerUsers.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {

    private final UserMapper userMapper;
    private final UsersRepository usersRepository;


    public Users addUser(UserDTO userDTO){

        if(checkOverlap(userDTO)){
            Users users = UserMapper.INSTANCE.userDTOToUser(userDTO);
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            users.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            users.setRole(Role.USER);
            return usersRepository.save(users);
        }
        else{
            throw new RuntimeException("User was exist");
        }
    }

    @Override
    public boolean checkOverlap(UserDTO userDTO) {
        List<Users> users = usersRepository.findAll();
        for(Users users1 : users){
            if(users1.getUsername().equals(userDTO.getUsername())){
                return false;
            }

        }
        return true;
    }


    @Override
    public Optional<Users> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }




    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public boolean deleteUser(int id){
        Users users = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        usersRepository.delete(users);
        return true;
    }



    @Override
    public List<UserDTO> getUser() {
        List<Users> users = usersRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for(Users user : users)
            userDTOs.add(userMapper.INSTANCE.userToUserDTO(user));
        return userDTOs;
    }

    @Override
    public UserDTO getUserById(int id) {
        Users users = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        return userMapper.INSTANCE.userToUserDTO(users);
    }


    public Users updateUser(Integer id, UserDTO userDTO){
            Users users = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
            users.setPhone(userDTO.getPhone());
            users.setName(userDTO.getName());
            users.setEmail(userDTO.getEmail());
            users.setAddress(userDTO.getAddress());
            users.setDob(userDTO.getDob());
            users.setGender(userDTO.getGender());
            return usersRepository.save(users);

    }

    @Override
    public String getNameByNumber(String number) {
        Users users = usersRepository.findUserByBankNumber(number);
        if(users == null) throw new RuntimeException("Bank not found, please retype!");
        return users.getName();
    }

}
