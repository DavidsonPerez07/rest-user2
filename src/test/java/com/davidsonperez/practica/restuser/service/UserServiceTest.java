package com.davidsonperez.practica.restuser.service;

import com.davidsonperez.practica.restuser.data.entity.User;
import com.davidsonperez.practica.restuser.data.repository.UserRepository;
import com.davidsonperez.practica.restuser.web.dto.UserDto;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    @Autowired
    private UserDto userDto;
    @Autowired
    private Optional<User> user;
    private UserService userServ;
    
    
    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class); 
        modelMapper = new ModelMapper();
        userServ = new UserService(userRepository, modelMapper);
        User userMock = new User();
        userMock.setId(1L);
        userMock.setUsername("David12");
        userMock.setPassword("123");
        userMock.setName("David Perez");
        userMock.setEmail("asdasd@gmail.com");
        userDto = modelMapper.map(userMock, UserDto.class);
        Mockito.when(userServ.findOne(1L)).thenReturn(userDto);
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

    @Test()
    public void testFindOne() {
        userDto = userServ.findOne(1L);
        System.out.println(userDto.toString());
    }
}
