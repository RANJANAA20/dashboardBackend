package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.feedback;

public interface feedbackRepo extends JpaRepository<feedback, Integer> 
{
	@Query(value= "select feedback,count(feedback) from feedback group by feedback",nativeQuery = true)
    List<Object[]> feedback();
}
