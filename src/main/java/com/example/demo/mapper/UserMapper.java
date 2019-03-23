package com.example.demo.mapper;

import java.util.List;

import com.example.demo.entity.UserEntity;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper extends Mapper<UserEntity> {

  @Override
  @Select("SELECT COUNT(1) FROM `user`")
  public int count();

  @Override
  @Delete("DELETE FROM `user` WHERE ${filter}")
  public boolean delete(@Param(value = "filter") String filter);

  @Override
  @Delete("TRUNCATE `user`")
  public void deleteAll();

  @Override
  @Insert("INSERT INTO `user`(`name`, `email`) VALUES (#{name}, #{email})")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  public boolean insert(UserEntity entity);

  @Override
  @Select("SELECT * FROM `user` WHERE ${filter}")
  public List<UserEntity> select(@Param(value = "filter") String filter);

  @Override
  @Select("SELECT * FROM `user`")
  public List<UserEntity> selectAll();

  @Override
  @Update("UPDATE `user` SET `name` = #{name}, `email` = #{email} WHERE `id` = #{id}")
  public boolean update(UserEntity entity);

}
