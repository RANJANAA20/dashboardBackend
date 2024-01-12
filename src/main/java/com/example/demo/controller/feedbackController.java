package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.feedbackRepo;
import com.example.demo.model.feedback;

@RestController
public class feedbackController 
{
	@Autowired
	feedbackRepo fr;
	
	@GetMapping("/viewFeedback")
	public List<feedback> view()
	{
		return fr.findAll(); 
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/feedbackCount")
	public Map<String, BigDecimal> feedStatus() {
	    List<Object[]> fList = fr.feedback();

	    Map<String, BigDecimal> fMap = new HashMap<>();

	    for (Object[] object : fList) {
	        String label = (String) object[0];
	        BigDecimal values;
	        if (object[1] instanceof Long) {
	            values = BigDecimal.valueOf((Long) object[1]);
	        } else if (object[1] instanceof BigDecimal) {
	            values = (BigDecimal) object[1];
	        } else {
	            // Handle the case when the type is not Long or BigDecimal.
	            throw new IllegalArgumentException("Unsupported data type found in the feedback list");
	        }

	        fMap.put(label, values);
	    }
	    return fMap;
	}

}
