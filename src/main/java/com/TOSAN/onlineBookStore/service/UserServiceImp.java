package com.TOSAN.onlineBookStore.service;

import com.TOSAN.onlineBookStore.dto.UserDto;
import com.TOSAN.onlineBookStore.exception.BookNotFoundException;
import com.TOSAN.onlineBookStore.exception.DuplicateUsernameException;
import com.TOSAN.onlineBookStore.exception.UserInfoNullException;
import com.TOSAN.onlineBookStore.exception.UserNotFoundException;
import com.TOSAN.onlineBookStore.model.Book;
import com.TOSAN.onlineBookStore.model.User;
import com.TOSAN.onlineBookStore.repository.UserRepository;
import com.TOSAN.onlineBookStore.security.Config;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> customer = userRepository.findByUsername(username);
        return customer.map(Config.CustomerDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public User addUser(User userInfo) throws DuplicateUsernameException, UserInfoNullException {
        if (userInfo.getUsername() != null && userInfo.getPassword() != null){
            if (userRepository.findByUsername(userInfo.getUsername()).isEmpty()){
                userInfo.setPassword(encoder.encode(userInfo.getPassword()));
                userRepository.save(userInfo);
            } else {
                throw new DuplicateUsernameException(userInfo.getUsername());
            }
        } else {
            throw new UserInfoNullException();
        }
        return userInfo;
    }

    public UserDto updateUser(UserDto userDto) throws UserNotFoundException {
        Optional<User> user = userRepository.findByUsername(userDto.getUsername());
        if (user.isPresent()) {
            user.get().setPassword(encoder.encode(userDto.getPassword()));
            userRepository.save(user.get());
        } else {
            throw new UserNotFoundException(userDto.getUsername());
        }
        return userDto;
    }

    public void deleteUser(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        userRepository.delete(user);
    }

    public List<User> getAllUsers(int pageNo, int pageSize, String sort){
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(sort).ascending());
        return userRepository.findAll(pageRequest).getContent();
    }





}
