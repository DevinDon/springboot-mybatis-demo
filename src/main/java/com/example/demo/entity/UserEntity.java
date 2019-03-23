package com.example.demo.entity;

public class UserEntity implements Entity {

  private static final long serialVersionUID = 1L;

  private int id;

  private String name;

  private String email;

  public UserEntity() {
    this(0, null, null);
  }

  public UserEntity(String name, String email) {
    this(0, name, email);
  }

  public UserEntity(int id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Name: " + this.name + "/nEmail: " + this.email + "/n";
  }

}
