package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // Why not @Service? Because I already have one(laughing).
public class UserService implements Service {

  private static final long serialVersionUID = 1L;

  @Autowired
  public UserMapper mapper;

  /**
   * Sign in service.
   *
   * @param user {UserEntity} User information.
   * @return {boolean} Success or not.
   */
  public boolean signIn(UserEntity user) {
    return mapper.select("`name` = '" + user.getName() + "' AND `email` = '" + user.getEmail() + "'").size() != 0;
  }

  /**
   * Sign up service.
   *
   * @param user {UserEntity} User information.
   * @return {boolean} Success or not.
   */
  public boolean signUp(UserEntity user) {
    if (mapper.select("`name` = '" + user.getName() + "' AND `email` = '" + user.getEmail() + "'").size() == 0) {
      mapper.insert(user);
      return true;
    } else {
      return false;
    }
  }

}
