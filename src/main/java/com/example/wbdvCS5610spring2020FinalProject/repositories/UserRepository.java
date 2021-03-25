package com.example.wbdvCS5610spring2020FinalProject.repositories;

import com.example.wbdvCS5610spring2020FinalProject.models.user.User;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * An interface which extends CrudRepository for basic CRUD operations and also has methods to fetch
 * data using native query from user table.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

  @Query(value = "SELECT * FROM users WHERE handle =:handle", nativeQuery =
      true)
  User findUserByHandle(@Param("handle") String handle);

  @Query(value = "SELECT * FROM users WHERE handle LIKE %:handle%", nativeQuery =
      true)
  List<User> findUserByHandleForSearch(@Param("handle") String handle);


  @Query(value = "SELECT * FROM users  WHERE handle=:username "
      + "AND password=:password", nativeQuery = true)
  User findUserByCredentials(@Param("username") String username,
      @Param("password") String password);
}
