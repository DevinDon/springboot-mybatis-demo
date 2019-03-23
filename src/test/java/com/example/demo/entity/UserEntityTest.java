package com.example.demo.entity;

import com.example.demo.entity.UserEntity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEntityTest {

  @Test
  public void userWithNothing() {
    UserEntity user = new UserEntity();
    Assert.assertEquals(0, user.getId());
    Assert.assertNull(user.getName());
    Assert.assertNull(user.getEmail());
  }

  @Test
  public void userWithNameEmail() {
    String name = "name";
    String email = "email";
    UserEntity user = new UserEntity(name, email);
    Assert.assertEquals(0, user.getId());
    Assert.assertEquals(name, user.getName());
    Assert.assertEquals(email, user.getEmail());
  }

  @Test
  public void userWithIDNameEmail() {
    int id = 1;
    String name = "name";
    String email = "email";
    UserEntity user = new UserEntity(id, name, email);
    Assert.assertEquals(id, user.getId());
    Assert.assertEquals(name, user.getName());
    Assert.assertEquals(email, user.getEmail());
  }

  @Test
  public void userSetMethods() {
    int id = 1;
    String name = "name";
    String email = "email";
    UserEntity user = new UserEntity();
    user.setId(id);
    user.setName(name);
    user.setEmail(email);
    Assert.assertEquals(id, user.getId());
    Assert.assertEquals(name, user.getName());
    Assert.assertEquals(email, user.getEmail());
  }

}
