package com.datingsuggestion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;
import com.datingsuggestion.entity.User;
import com.datingsuggestion.repo.UserRepository;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DatingSuggestionsApplicationTests {

    @Autowired
    private Recommendation recommendationEngine;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testRecommendationEngine() {
        // Create test users
        User user1 = new User(1L, "User1", "Female", 25, Arrays.asList("Cricket", "Chess"));
        User user2 = new User(2L, "User2", "Male", 27, Arrays.asList("Cricket", "Football", "Movies"));
        List<User> allUsers = Arrays.asList(user1, user2);

        // Mock repository response
        Mockito.when(userRepository.findAll()).thenReturn(allUsers);

        // Test the recommendation engine
        List<User> matches = recommendationEngine.getTopMatches(user2, allUsers);
        
        // Assert the expected result
        assertEquals(1, matches.size());
        assertEquals(user1, matches.get(0)); // User1 should be the closest match for User2
    }
}
