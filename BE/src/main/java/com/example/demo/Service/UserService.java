package com.example.demo.Service;

import com.example.demo.DataModels.User;
import com.example.demo.Repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public List<User> getAll(){
        return userRepository.getAll();
    }

    public User addUser(User user){
        user.setId_user((long) userRepository.getAvailableUserId());
        return userRepository.addUser(user);
    }

    public void UpdateUser(User user, Integer id){
        User found = userRepository.findUserById(id);
        if ((user.getNume() != null)) {
            found.setNume(user.getNume());
        }
        if ((user.getPrenume() != null)) {
            found.setPrenume(user.getPrenume());
        }
        if ((user.getType() != null)) {
            found.setType(user.getType());
        }
        if ((user.getEmail() != null)) {
            found.setEmail(user.getEmail());
        }
        if ((user.getBudget() != null)) {
            found.setBudget(user.getBudget());
        }
        if ((user.getPassword() != null)) {
            found.setPassword(user.getPassword());
        }
        userRepository.UpdateUser(found);

    }

    public void DeleteUser(Long id){
        userRepository.DeleteUser(id);
    }

    public User getRole(User user) {
        return userRepository.getUser(user);
    }
}
