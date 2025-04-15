package repository;

import model.*;
import java.util.*;

public class UserRepository {

    private Map<Long, User> userMap = new HashMap<>();
    private long id = 1;

    public User save(User user) {
        if(user.getId()==0) {
            user.setId(id++);
        }
        userMap.put(user.getId(), user);
        return user;
    }

    public Optional<User> findById(long id) {
        return Optional.ofNullable(userMap.get(id));
    }
}
