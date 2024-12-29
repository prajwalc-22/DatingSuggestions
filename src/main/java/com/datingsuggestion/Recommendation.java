package com.datingsuggestion;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.datingsuggestion.entity.User;

@Component
public class Recommendation {

    public List<User> getTopMatches(User currentUser, List<User> allUsers) {
        List<User> sortedUsers = allUsers.stream()
            .filter(user -> !user.getId().equals(currentUser.getId()))
            .sorted((user1, user2) -> {
                int genderScore = compareGender(currentUser, user1, user2);
                if (genderScore != 0) return genderScore;

                int ageScore = compareAge(currentUser, user1, user2);
                if (ageScore != 0) return ageScore;

                return compareInterests(currentUser, user1, user2);
            })
            .limit(2)
            .collect(Collectors.toList());
        
        return sortedUsers;
    }

    private int compareGender(User currentUser, User user1, User user2) {
        if (user1.getGender().equals(currentUser.getGender())) return 1;
        if (user2.getGender().equals(currentUser.getGender())) return -1;
        return 0; // Same gender
    }

    private int compareAge(User currentUser, User user1, User user2) {
        int ageDiff1 = Math.abs(currentUser.getAge() - user1.getAge());
        int ageDiff2 = Math.abs(currentUser.getAge() - user2.getAge());
        return Integer.compare(ageDiff1, ageDiff2);
    }

    private int compareInterests(User currentUser, User user1, User user2) {
        long commonInterests1 = currentUser.getInterests().stream()
            .filter(user1.getInterests()::contains)
            .count();
        long commonInterests2 = currentUser.getInterests().stream()
            .filter(user2.getInterests()::contains)
            .count();
        return Long.compare(commonInterests2, commonInterests1); // Higher common interest is preferred
    }
}

