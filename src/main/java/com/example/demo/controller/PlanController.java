package com.example.demo.controller;

import java.math.BigDecimal;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.DataPlans;
import com.example.demo.model.Dataset;
import com.example.demo.service.PlanService;

@RestController

public class PlanController
{
	@Autowired
	PlanService ps;
	
	// to add customer to dataset
	@PostMapping("/adddata")
//	@MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//	public String addData(@RequestBody Dataset ds) throws InterruptedException
//	{
//		ps.addData(ds);
////		Thread.sleep(2000);
//		return "added";
//	}
	
//	@MessageMapping("")
    public String addData(@RequestBody List<Dataset> datasets) throws InterruptedException {
        ps.addData(datasets);
//      Thread.sleep(2000);
        return "added";
    }
	
	
	// to get List of customers
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/viewData")
	public List<Dataset> viewData()
	{
		return ps.viewData();
	}
	
	// to get the total no.of.customers
	@ResponseBody
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/count")
    public long count() 
	{
        return ps.count();
        
    }
	
	// to get the revenue
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/revenue")
	public long billRevenue()
	{
		return ps.billRevenue();
	}
	
	
	// to get no.of.customers based on location
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/getLocationCounts")
    public Map<String, Long> getLocationCounts() {
        return ps.getLocationCounts();
    }
	
	// to get no.of.customers based on plans
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/getPlanCount")
	public Map<String,Long> getPlanCount()
	{
		return ps.getPlanCount();
	}
	
	// active customer
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/activeCustomer")
	public long active()
	{
		return ps.active();
	}
	
	// inactive customer
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/inactiveCustomer")
	public long Inactive()
	{
		return ps.inactive();
	}
	
	// new customer
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/newCustomer")
	public long custNew()
	{
		return ps.custNew();
	}
	
	// revenue by month
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/monthRevenue")
	public Map<String,Long> monthRevenue()
	{
		return ps.monthRevenue();
	}
	
	// individual customer by id
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{id}")
	public Optional<Dataset> getCustomerById(@PathVariable int id)
	{
		
		return ps.getCustomerById(id);
	}
	
	// to get no.of.customers based on mode of payment
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("modeOfPayment")
	public Map<String,Long> paymentMode()
	{
		return ps.paymentMode();
	}
	
	// payment status
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("paymentStatus")
	public Map<String,Long> paymentStatus()
	{
		return ps.paymentStatus();
	}
	
	// active/inactive
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/statusCount")
	public Map<String,Map<String,BigDecimal>> statusCount()
	{
		return ps.statusCount();
	}
	
	
}
