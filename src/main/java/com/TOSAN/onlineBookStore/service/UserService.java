package com.TOSAN.onlineBookStore.service;

import com.TOSAN.onlineBookStore.dto.UserDto;
import com.TOSAN.onlineBookStore.exception.DuplicateUsernameException;
import com.TOSAN.onlineBookStore.exception.UserInfoNullException;
import com.TOSAN.onlineBookStore.exception.UserNotFoundException;
import com.TOSAN.onlineBookStore.model.User;

import java.util.List;

public interface UserService {
    public User addUser(User userInfo) throws DuplicateUsernameException, UserInfoNullException;
    public UserDto updateUser(UserDto userDto) throws UserNotFoundException;
    public void deleteUser(String username) throws UserNotFoundException;
    public List<User> getAllUsers(int pageNo, int pageSize, String sort);
}
