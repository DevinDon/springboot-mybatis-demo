package com.example.demo;

import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  public void allInOne() {
    userMapper.deleteAll(); // Delete all users
    Assert.assertEquals(0, userMapper.selectAll().size()); // Now, table `user` is empty
    UserEntity user = new UserEntity("test", "test@test.com");
    userMapper.insertOne(user); // Add new user
    UserEntity checkUser = userMapper.selectAll().get(0);
    Assert.assertNotNull(checkUser); // Now, table `user` should have 1 record
    Assert.assertEquals(user.getName(), checkUser.getName()); // The name should be the same
    Assert.assertEquals(user.getEmail(), checkUser.getEmail()); // Also the email
  }

}
