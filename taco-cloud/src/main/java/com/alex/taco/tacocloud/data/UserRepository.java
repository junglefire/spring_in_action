package com.alex.taco.tacocloud;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  	User findByUsername(String username);
}