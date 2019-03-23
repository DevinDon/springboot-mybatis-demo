package com.example.demo.mapper;

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
public class UserMapperTest {

  @Autowired
  private UserMapper mapper;

  @Test
  public void insert() {
    // Empty
    mapper.deleteAll(); // Delete all users
    Assert.assertEquals(0, mapper.count());
    Assert.assertEquals(0, mapper.selectAll().size());
    // Insert
    UserEntity user = new UserEntity("name", "email");
    Assert.assertTrue(mapper.insert(user)); // Insert should success
    Assert.assertEquals(1, mapper.count());
    // Select
    UserEntity check = mapper.select("`id` = " + user.getId()).get(0); // Get the user just inserted
    Assert.assertNotNull(check); // It should not be null
    Assert.assertEquals(user.getEmail(), check.getEmail()); // It should be the same
    Assert.assertEquals(user.getName(), check.getName());
  }

  @Test
  public void delete() {
    // Empty
    mapper.deleteAll(); // Delete all users
    Assert.assertEquals(0, mapper.count()); // Table should be empty
    Assert.assertEquals(0, mapper.selectAll().size());
    // Insert
    UserEntity user = new UserEntity("test", "test");
    Assert.assertTrue(mapper.insert(user));
    // Delete
    Assert.assertTrue(mapper.delete("`id` = " + user.getId())); // It should success
    Assert.assertEquals(0, mapper.count());
  }

  @Test
  public void update() {
    // Empty
    mapper.deleteAll(); // Delete all users
    Assert.assertEquals(0, mapper.count()); // Table should be empty
    Assert.assertEquals(0, mapper.selectAll().size());
    // Insert
    UserEntity user = new UserEntity("test", "test");
    Assert.assertTrue(mapper.insert(user));
    // Update
    String name = "new name";
    String email = "new email";
    user.setName(name);
    user.setEmail(email);
    Assert.assertTrue(mapper.update(user)); // Update should success
    // Select
    user = mapper.select("`id` = " + user.getId()).get(0);
    Assert.assertEquals(name, user.getName()); // And it should be new
    Assert.assertEquals(email, user.getEmail());
  }

  @Test
  public void select() {
    // Empty
    mapper.deleteAll(); // Delete all users
    Assert.assertEquals(0, mapper.count()); // Table should be empty
    Assert.assertEquals(0, mapper.selectAll().size());
    // Insert
    UserEntity user = new UserEntity("test", "test");
    Assert.assertTrue(mapper.insert(user));
    // Select
    UserEntity check = mapper.select("`id` = " + user.getId()).get(0);
    Assert.assertEquals(user.getName(), check.getName()); // And it should be new
    Assert.assertEquals(user.getEmail(), check.getEmail());
  }

}
