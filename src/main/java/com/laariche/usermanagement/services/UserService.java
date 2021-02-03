package com.laariche.usermanagement.services;

import com.laariche.usermanagement.entities.User;
import com.laariche.usermanagement.exception.ApiRequestException;
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

    public Optional<User> findById(Long id){
        return iUserRepository.findById(id);
    }

    public User create(User user){
       return iUserRepository.save(user);
    }

    public User update(Long id, User _user){
        final Optional<User> optUser = iUserRepository.findById(id);
        if(optUser.isPresent())
        {
            User user = optUser.get();
            user.setFirstname(_user.getFirstname());
            user.setLastname(_user.getLastname());
            user.setUsername(_user.getUsername());
            user.setEmail(_user.getEmail());

            return iUserRepository.save(user);
        }else {
            throw new ApiRequestException("User not exist !");
        }
    }

    public void delete(User user){
        iUserRepository.delete(user);
    }


}
