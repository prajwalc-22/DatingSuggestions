package com.datingsuggestion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datingsuggestion.Recommendation;
import com.datingsuggestion.entity.User;
import com.datingsuggestion.repo.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final Recommendation recommendationEngine = new Recommendation();

    public List<User> getTopMatches(Long userId) {
        User currentUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<User> allUsers = userRepository.findAll();
        return recommendationEngine.getTopMatches(currentUser, allUsers);
    }
    
//    public List<User> getTopMatches(Long userId) {
//        User currentUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        List<User> allUsers = userRepository.findAllTop2();
//        System.out.println(allUsers);
//        return recommendationEngine.getTopMatches(currentUser, allUsers);
//    }
    
    public User createUser(User user) {
    	return userRepository.save(user);
    }
}

