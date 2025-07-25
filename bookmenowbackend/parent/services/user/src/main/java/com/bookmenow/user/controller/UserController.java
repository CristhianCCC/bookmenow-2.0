package com.bookmenow.user.controller;
import com.bookmenow.user.dto.UserDTO;
import com.bookmenow.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers (){
        List<UserDTO> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id){
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(userDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> postUser (@RequestBody UserDTO userDTO){
        UserDTO userCreated = userService.postUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> putUser (@RequestBody UserDTO userDTO, @PathVariable("id") Long id){
        UserDTO userEdited = userService.putUser(userDTO, id);
        return ResponseEntity.ok().body(userEdited);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
