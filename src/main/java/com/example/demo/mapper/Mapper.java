package com.example.demo.mapper;

import java.io.Serializable;
import java.util.List;

/** Basic mapper interface, with entity type T. */
public interface Mapper<T> extends Serializable {

  /**
   * Insert one or some record(s).
   * <p>
   * You should add
   * <code>@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")</code>
   * before it.
   *
   * @param entity {T} The record(s) to be inserted.
   * @return {boolean} Success or not.
   */
  public boolean insert(T entity);

  /**
   * Delete one or some record(s) that match the filter (WHERE).
   * <p>
   * You should add <code>@Param(value = "filter")</code> before param.
   *
   * @param filter {String} Conditional filter.
   * @return {boolean} Success or not.
   */
  public boolean delete(String filter);

  /**
   * Delete all records.
   *
   * @return {boolean} Success or not.
   */
  public void deleteAll();

  /**
   * Update one or some record(s) by ID.
   *
   * @param entity {T} The record(s) to be updated.
   * @return {boolean} Success or not.
   */
  public boolean update(T entity);

  /**
   * Select one or some record(s) that match the filter (WHERE).
   * <p>
   * You should add <code>@Param(value = "filter")</code> before param.
   *
   * @param filter {String} Conditional filter.
   * @return {List<T>} Record(s).
   */
  public List<T> select(String filter);

  /**
   * Select all records.
   *
   * @return {List<T>} All records.
   */
  public List<T> selectAll();

  /**
   * Count the number of records.
   *
   * @return {int} The number of records.
   */
  public int count();

}
