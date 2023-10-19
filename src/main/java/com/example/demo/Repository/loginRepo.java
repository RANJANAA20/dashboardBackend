package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Login;

public interface loginRepo extends JpaRepository<Login, Integer> 
{
	Login findByusername(String uname);
}
