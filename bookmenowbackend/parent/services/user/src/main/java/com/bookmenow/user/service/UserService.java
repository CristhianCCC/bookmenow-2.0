package com.bookmenow.user.service;

import com.bookmenow.user.dto.UserDTO;

import java.util.List;

public interface UserService {

    public List<UserDTO> getAllUsers ();

    public UserDTO getUserById(Long id);

    public UserDTO postUser(UserDTO userDTO);

    public UserDTO putUser(UserDTO userDTO, Long id);

    public void deleteUser(Long id);

}
