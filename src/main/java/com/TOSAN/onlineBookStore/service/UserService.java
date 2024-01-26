package com.TOSAN.onlineBookStore.service;

import com.TOSAN.onlineBookStore.exception.DuplicatedUsernameException;
import com.TOSAN.onlineBookStore.exception.UserInfoNullException;
import com.TOSAN.onlineBookStore.model.Customer;
import com.TOSAN.onlineBookStore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder encoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Customer> customer = customerRepository.findByUsername(username);
        return customer.map(CustomerDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(Customer userInfo) throws DuplicatedUsernameException, UserInfoNullException {
        if (userInfo.getUsername() != null && userInfo.getPassword() != null){
            if (customerRepository.findByUsername(userInfo.getUsername()).isEmpty()){
                userInfo.setPassword(encoder.encode(userInfo.getPassword()));
                customerRepository.save(userInfo);
            } else {
                throw new DuplicatedUsernameException(userInfo.getUsername());
            }
        } else {
            throw new UserInfoNullException();
        }
        return "User Added Successfully";
    }


}
