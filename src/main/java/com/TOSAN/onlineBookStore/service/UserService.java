package com.TOSAN.onlineBookStore.service;

import com.TOSAN.onlineBookStore.dto.UserDto;
import com.TOSAN.onlineBookStore.exception.DuplicateUsernameException;
import com.TOSAN.onlineBookStore.exception.UserInfoNullException;
import com.TOSAN.onlineBookStore.exception.UserNotFoundException;
import com.TOSAN.onlineBookStore.model.User;
import com.TOSAN.onlineBookStore.repository.CustomerRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder encoder;

    public UserService(CustomerRepository customerRepository, PasswordEncoder encoder) {
        this.customerRepository = customerRepository;
        this.encoder = encoder;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> customer = customerRepository.findByUsername(username);
        return customer.map(CustomerDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public User addUser(User userInfo) throws DuplicateUsernameException, UserInfoNullException {
        if (userInfo.getUsername() != null && userInfo.getPassword() != null){
            if (customerRepository.findByUsername(userInfo.getUsername()).isEmpty()){
                userInfo.setPassword(encoder.encode(userInfo.getPassword()));
                customerRepository.save(userInfo);
            } else {
                throw new DuplicateUsernameException(userInfo.getUsername());
            }
        } else {
            throw new UserInfoNullException();
        }
        return userInfo;
    }

    public UserDto updateUser(UserDto userDto) throws UserNotFoundException {
        Optional<User> user = customerRepository.findByUsername(userDto.getUsername());
        if (user.isPresent()) {
            user.get().setPassword(encoder.encode(userDto.getPassword()));
            customerRepository.save(user.get());
        } else {
            throw new UserNotFoundException(userDto.getUsername());
        }
        return userDto;
    }

    public void deleteUser(String username) throws UserNotFoundException {
        Optional<User> user = customerRepository.findByUsername(username);
        if (username != null && user.isPresent()) {
            customerRepository.delete(user.get());
        } else {
            throw new UserNotFoundException(username);
        }
    }

    public List<User> getAllUsers(int pageNo, int pageSize, String sort){
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(sort).ascending());
        return customerRepository.findAll(pageRequest).getContent();
    }





}
