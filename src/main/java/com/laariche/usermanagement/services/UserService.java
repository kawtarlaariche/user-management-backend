package com.laariche.usermanagement.services;

import com.laariche.usermanagement.entities.User;
import com.laariche.usermanagement.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private IUserRepository iUserRepository;

    public List<User> findAll(){
        return iUserRepository.findAll();
    }

    public User findById(Long id){
        Optional<User> user = iUserRepository.findById(id);

        if(user.isPresent()) return user.get();
        else return null;
    }

    public User create(User user){
       return iUserRepository.save(user);
    }

    public User update(Long id, User user){
        return iUserRepository.save(user);
    }

    public void delete(User user){
        iUserRepository.delete(user);
    }


}
