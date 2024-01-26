package com.TOSAN.onlineBookStore.controller;

import com.TOSAN.onlineBookStore.dto.ResponseDto;
import com.TOSAN.onlineBookStore.dto.UserDto;
import com.TOSAN.onlineBookStore.exception.UserNotFoundException;
import com.TOSAN.onlineBookStore.model.User;
import com.TOSAN.onlineBookStore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(@RequestParam(defaultValue = "0", required = false)  Integer pageNo,
                                  @RequestParam(defaultValue = "10", required = false)  Integer pageSize,
                                  @RequestParam(defaultValue = "username", required = false)  String sort){
        return userService.getAllUsers(pageNo, pageSize, sort);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> updateUser(@RequestBody UserDto userDto) {
        try {
            userService.updateUser(userDto);
            return ResponseEntity.ok().body(new ResponseDto("User has been updated successfully"));
        } catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(new ResponseDto(e.getMessage()));
        }

    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteUser(@RequestBody UserDto userDto)  {
        try {
            userService.deleteUser(userDto.getUsername());
            return ResponseEntity.ok().body(new ResponseDto("User has been deleted successfully"));
        } catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(new ResponseDto(e.getMessage()));
        }

    }


}
