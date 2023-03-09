package com.davidsonperez.practica.restuser.service;

import com.davidsonperez.practica.restuser.data.entity.User;
import com.davidsonperez.practica.restuser.data.repository.UserRepository;
import com.davidsonperez.practica.restuser.web.dto.UserDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.modelmapper.ModelMapper;

public class UserServiceTest {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    
    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class); 
        modelMapper = new ModelMapper();
    }
    
    @Test
    public void testAllDataOk_ResultOk() throws Exception {
        User resulted = new User(1L, "davidson", "123", "Davidson Pérez", "daperez@gmail.com");
        when(userRepository.save(any(User.class))).thenReturn(resulted);
        
        //Input
        UserDto user = new UserDto(null, "DAVIDSON", "123", "Davidson Pérez", "daperez@gmail.com");
        
        //Target
        UserService instance = new UserService(userRepository, modelMapper);
        
        //Expected
        UserDto expResult = new UserDto(1L, "davidson", "123", "Davidson Pérez", "daperez@gmail.com");
        
        //Test
        UserDto result = instance.save(user);
        
        //Validations
        assertEquals(expResult.getId(), result.getId());
        assertEquals(expResult.getUsername(), result.getUsername());
    }
    
    @Test()
    public void testParamNull_ResultException() {
        //Input
        UserDto user = null;
        
        //Target
        UserService instance = new UserService(userRepository, modelMapper);
        
        //Test
        assertThrows(Exception.class, () -> {
            UserDto result = instance.save(user);
        });
    }

}
