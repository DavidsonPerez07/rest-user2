package com.davidsonperez.practica.restuser.service;

import com.davidsonperez.practica.restuser.data.entity.User;
import com.davidsonperez.practica.restuser.data.repository.UserRepository;
import com.davidsonperez.practica.restuser.web.dto.UserDto;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    
    public UserDto save(UserDto user) throws Exception{
        //Validate information
        if (user == null) {
            throw new Exception("Parámtero no válido");
        }
        else if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new Exception("Hace falta el nombre de usuario");
        }
        else if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new Exception("Hace falta el email");
        }
        
        //Process information
        User myUser = modelMapper.map(user, User.class);
        log.info("Usuario Mapeado: " + myUser);
        myUser = userRepository.save(myUser);
        log.info("Usuario obtenido: " + myUser);
        UserDto resp = modelMapper.map(myUser, UserDto.class);
        log.info("Usuario devuelto: " + resp);
        return resp;
    }
    
    public List<UserDto> listAll() {
        return null;
    }
    
    public UserDto findOne(Long id) {
        return null;
    }
}
