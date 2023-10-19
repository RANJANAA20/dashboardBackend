package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Complaints;
import com.example.demo.service.complaintService;

@RestController
public class complaintController 
{
	@Autowired
	complaintService cs;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/addComplaint")
	public String addComplaint(@RequestBody Complaints c)
	{
		cs.addComplaint(c);
		return "added";
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/viewComplaints")
	public List<Complaints> view()
	{
		return cs.viewComplaint();
		
	}
}
