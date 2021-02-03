package com.laariche.usermanagement.controllers;

import com.laariche.usermanagement.entities.User;
import com.laariche.usermanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    // to be replaced by pagination
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable("id") Long id){ return userService.findById(id); }

    @PostMapping
    public User create(@RequestBody User user){
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable("id") Long id, @RequestBody User user){
        return userService.update(id, user);
    }

    @DeleteMapping
    public void delete(@RequestBody User user){
        userService.delete(user);
    }


}
