package com.example.fire.cap.Service;

import com.example.fire.cap.Entity.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface UserService{

        List<User> getUsers() throws ExecutionException, InterruptedException;

        User getUserById(String id) throws ExecutionException, InterruptedException;
}

