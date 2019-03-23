package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements Controller {

  private static final long serialVersionUID = 1L;

  @Autowired
  private UserService userService;

  @PostMapping("/signin")
  public boolean signIn(@RequestParam("name") String name, @RequestParam("email") String email) {
    UserEntity user = new UserEntity(name, email);
    return userService.signIn(user) ? true : false;
  }

  @PostMapping("/signup")
  public boolean signUp(@RequestParam("name") String name, @RequestParam("email") String email) {
    UserEntity user = new UserEntity(name, email);
    return userService.signUp(user);
  }

}
