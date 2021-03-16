package com.sampleproject.retailmart.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sampleproject.retailmart.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long > {
	
	@Query
	Optional<Login> findById(Long id);
	
	@Query
	Optional<Login> findByUserNameAndPassword(String userName, String password);

}
