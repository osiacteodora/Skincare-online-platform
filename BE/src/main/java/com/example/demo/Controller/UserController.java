package com.example.demo.Controller;

import com.example.demo.DataModels.Order;
import com.example.demo.DataModels.Product;
import com.example.demo.DataModels.User;
import com.example.demo.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService  userService = new UserService();

    @CrossOrigin()
    @GetMapping("/users")
    public List<User> getAll(){
        return userService.getAll();
    }

    @CrossOrigin()
    @PostMapping("/users")
    public User AddUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping("/users/{id}")
    public void UpdateUser(@RequestBody User user, @PathVariable int id){
        user.setId_user((long) id);
        userService.UpdateUser(user, id);
    }

    @DeleteMapping("/users/{id}")
    public void DeleteUser(@PathVariable int id){
        userService.DeleteUser((long) id);
    }

    @CrossOrigin()
    @PostMapping("users/getrole")
    public User getRole(@RequestBody User user) {
        return userService.getRole(user);
    }


}
