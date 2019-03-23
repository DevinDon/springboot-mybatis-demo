package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserMapper mapper;

  @Autowired
  private UserService service;

  @Test
  public void signIn() {
    mapper.deleteAll(); // Empty
    Assert.assertEquals(0, mapper.count());
    UserEntity user = new UserEntity("name", "email");
    UserEntity wrong = new UserEntity("no name", "no email");
    Assert.assertTrue(mapper.insert(user)); // Insert
    Assert.assertFalse(service.signIn(wrong));
    Assert.assertTrue(service.signIn(user));
  }

  @Test
  public void signUp() {
    mapper.deleteAll(); // Empty
    Assert.assertEquals(0, mapper.count());
    UserEntity user = new UserEntity("name", "email");
    Assert.assertTrue(service.signUp(user));
    Assert.assertFalse(service.signUp(user));
    Assert.assertEquals(1, mapper.count());
  }

}
