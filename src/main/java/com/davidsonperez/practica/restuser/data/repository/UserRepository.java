package com.davidsonperez.practica.restuser.data.repository;

import com.davidsonperez.practica.restuser.data.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    
}
