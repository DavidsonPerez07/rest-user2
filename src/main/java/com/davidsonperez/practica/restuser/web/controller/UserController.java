package com.davidsonperez.practica.restuser.web.controller;

import com.davidsonperez.practica.restuser.service.UserService;
import com.davidsonperez.practica.restuser.web.dto.UserDto;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     
@RequestMapping("user")
public class UserController {
    
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping() //POST http://localhost:8080/user
    public ResponseEntity<?> insert(@RequestBody UserDto user) throws Exception {
        //Validate
        if (user == null) {
            //TODO Devolver codigo 400 Bad Request con mensaje "Datos de usuario inválidos"
            return ResponseEntity.badRequest().body("Datos de usuario inválidos");
        }
        
        //Execute
        UserDto resp;
        try {
            resp = userService.save(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
    
    @GetMapping
    public List<UserDto> getAll() {
        return userService.listAll();
    }
    
    @GetMapping("/{id}")
    public UserDto getOne(@PathVariable("id") Long id) {
        return userService.findOne(id);
    }
    
    @PutMapping("/{id}")
    public UserDto updateOne(@PathVariable("id") Long id, @RequestBody UserDto user) {
        return null;
    }
    
    @DeleteMapping("/{id}")
    public UserDto removeOne(@PathVariable("id") Long id) {
        return null;
    }
    
    /*@PostMapping("/{user}/rol") //POST http://localhost:8080/user/(id)/rol
    public UserDto saveUserRol(@PathVariable("user") String idUser, UserDto user) {
        return null;
    } //Esto es un ejemplo*/
}
