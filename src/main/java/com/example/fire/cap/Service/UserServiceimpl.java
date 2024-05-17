package com.example.fire.cap.Service;


import com.example.fire.cap.Dao.UserDao;
import com.example.fire.cap.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class UserServiceimpl implements UserService {

    private final UserDao userDao;

    @Override
    public List<User> getUsers() throws ExecutionException, InterruptedException {
        return userDao.getUsers();
    }

    @Override
    public User getUserById(String id) throws ExecutionException, InterruptedException {
        return userDao.getUserById(id);
    }
}
