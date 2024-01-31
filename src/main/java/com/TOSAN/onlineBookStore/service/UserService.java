package com.TOSAN.onlineBookStore.service;

import com.TOSAN.onlineBookStore.dto.UserDto;
import com.TOSAN.onlineBookStore.exception.DuplicateUsernameException;
import com.TOSAN.onlineBookStore.exception.UserInfoNullException;
import com.TOSAN.onlineBookStore.exception.UserNotFoundException;
import com.TOSAN.onlineBookStore.model.User;

import java.util.List;

public interface UserService {
     User addUser(User userInfo) throws DuplicateUsernameException, UserInfoNullException;
     UserDto updateUser(UserDto userDto) throws UserNotFoundException;
     void deleteUser(String username) throws UserNotFoundException;
     List<User> getAllUsers(int pageNo, int pageSize, String sort);
      User findUserByUsername(String username) throws UserNotFoundException;
}
