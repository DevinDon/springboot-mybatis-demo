package com.example.demo.mapper;

import java.util.List;

import com.example.demo.entity.UserEntity;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

  /**
   * Insert one user.
   *
   * @param user {User} User information.
   * @return {void} Nothing.
   */
  @Insert("INSERT INTO `database`.`user`(`name`, `email`) VALUES (#{name}, #{email})")
  public void insertOne(UserEntity user);

  /**
   * Delete all users.
   *
   * @return {void} Nothing.
   */
  @Delete("TRUNCATE `user`")
  public void deleteAll();

  /**
   * Select all users.
   *
   * @return {List<UserEntity>} All users.
   */
  @Select("SELECT * FROM `user`")
  public List<UserEntity> selectAll();

}
