//package com.bank.repository;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.repository.CrudRepository;
//
//import com.bank.model.User;
//
//public interface UserRepo extends CrudRepository<User, Long> {//User and id(long)
//
//	//Username should be unique
//	Optional<User> findByUsername(String username);
//
//	Boolean existsByUsername(String username);
//
//	Boolean existsByEmail(String email);
//
//	//
//	List<User> findAll();
//}

package com.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bank.model.User;

public interface UserRepo extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	List<User> findAll();
	
	long count();
}