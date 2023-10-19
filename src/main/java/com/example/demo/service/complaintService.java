package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.ComplaintRepo;
import com.example.demo.model.Complaints;

@Service
public class complaintService 
{
	@Autowired
	ComplaintRepo cr;

	public void addComplaint(Complaints c) 
	{
		// TODO Auto-generated method stub
		cr.save(c);
	}

	public List<Complaints> viewComplaint() 
	{
		// TODO Auto-generated method stub
		List<Complaints> cl = cr.findAll();
		return cl;
		
	}

	
	
	
}
