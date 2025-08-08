package com.bookmenow.user.service.serviceImpl;
import com.bookmenow.dto.UserDTO;
import com.bookmenow.user.exceptions.exceptions.BusinessRuleException;
import com.bookmenow.user.exceptions.validators.UserValidators;
import com.bookmenow.user.model.User;
import com.bookmenow.user.repository.UserRepository;
import com.bookmenow.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    /*Methods for DTO's ----------------------------------------------------------------------------------------------*/

    //convert DTO to Entity --------------------------------------------------------------------------------------------
    private User toEntity (UserDTO dto){
        User user = new User();
        user.setName(dto.getName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        //validating the password with passwordencoder previously implemented as a bean in securityconfig class
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setUserRoles(dto.getUserRoles());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        user.setCreatedAt(dto.getCreatedAt());
        user.setUpdatedAt(dto.getUpdatedAt());
        return user;
    }

    //convert entity to dto --------------------------------------------------------------------------------------------
    private UserDTO toDTO (User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setUserRoles(user.getUserRoles());
        dto.setPhone(user.getPhone());
        dto.setAddress(user.getAddress());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }


    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());    }

    @Override
    public UserDTO getUserById(Long id) {
        User userFound = userRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("2001", "user with the ID " + id + " Was not found", HttpStatus.BAD_REQUEST));
        return toDTO(userFound);
    }

    @Override
    public UserDTO postUser(UserDTO userDTO) {
        //validations --------------------------------------------------------------------------------------------------
        UserValidators.validate(userDTO, true, userRepository);

        User user = toEntity(userDTO);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        User userSaved = userRepository.save(user);
        return toDTO(userSaved);
    }

    @Override
    public UserDTO putUser(UserDTO userDTO, Long id) {
        //validations --------------------------------------------------------------------------------------------------
        UserValidators.validate(userDTO, false, userRepository);

        userDTO.setId(id);
          return userRepository.findById(id).map(userFound -> {
              userFound.setName(userDTO.getName());
              userFound.setLastName(userDTO.getLastName());
              userFound.setEmail(userDTO.getEmail());
              if (!passwordEncoder.matches(userDTO.getPassword(), userFound.getPassword())) {
                  userFound.setPassword(passwordEncoder.encode(userDTO.getPassword()));
              }
              userFound.setUserRoles(userDTO.getUserRoles());
              userFound.setPhone(userDTO.getPhone());
              userFound.setAddress(userDTO.getAddress());
              userFound.setUpdatedAt(LocalDateTime.now());
            User userFinal = userRepository.save(userFound);
            return toDTO(userFinal);
        }).orElseThrow(() -> new RuntimeException("User not found with the ID: " + id));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)){
            throw new RuntimeException("User with ID " + id + " was not found");
        }
        userRepository.deleteById(id);
    }
    @Override
    public UserDTO getUserByEmail (String email){
        User userFound = userRepository.getUserByEmail(email);
        return toDTO(userFound);
    }
}
