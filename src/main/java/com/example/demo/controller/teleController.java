package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.teleRepo;
import com.example.demo.model.Dataset;
import com.example.demo.model.telecom;
import com.example.demo.service.teleService;

@RestController
public class teleController 
{
	@Autowired
	teleRepo tr;
	
	@Autowired
	teleService ts;
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/addTeleData")
	public String addData(@RequestBody List<telecom> tele) 
	{
        ts.addTele(tele);
        return "added";
    }
	
	// list of customers
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/teleData")
	public List<telecom> view()
	{
		return ts.teleList();
	}
	
	// total customers
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/countTele")
	public long countTeleCust()
	{
		return ts.countTeleCust();
	}
	
	// active customers
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/countActive")
	public long countA()
	{
		return ts.countA();
	}
	
	// inactive customers
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/countInactive")
	public long countInA()
	{
		return ts.countInA();
	}
	
	// new customers
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/countNew")
	public long countNew()
	{
		return ts.countNew();
	}
	
	// to get no.of.customers based on location
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/getLocation")
	public Map<String, Long> getLocation() 
	{
		return ts.getLocation();
	}
	
	// to get no.of.customers based on plan
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/getPlan")
	public Map<String, Long> plan() 
	{
		return ts.plan();
	}
	
	// to get no.of.customers based on mode of payment
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/Payment")
	public Map<String,Long> paymentMode()
	{
		return ts.payment();
	}
	
	// active/inactive
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/countStatus")
	public Map<String,Map<String,BigDecimal>> statusCount()
	{
		return ts.statusCount();
	}
	
	// plan based revenue
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/planRevenue")
	public Map<String,BigDecimal> planRevenue()
	{
		return ts.planRevenue();
	}
	
	// revenue by month
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/monthRev")
	public Map<String,Long> monthRev()
	{
		return ts.monthRev();
	}
	
	// billing anamoly
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/anamoly")
	public Map<Integer,Long> billAnamoly()
	{
		return ts.billAnamoly();
	}
	
	// to get the revenue
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/rev")
	public long billRev()
	{
		return ts.billRev();
	}
	
	// location
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/loc")
	public long loaction()
	{
		return ts.location();
	}
	
	// search
//	@CrossOrigin(origins="http://localhost:4200")
//	@GetMapping("/search")
//	public Optional<telecom> search(@RequestParam("Cust_009") String CustomerID)
//	{
//		return ts.search(CustomerID);
//	}
	
	// Search
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/search")
	public List<telecom> search(@RequestParam String customerId) {
	    return ts.search(customerId);
	}
	
	// view by location
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/getTeleDataByLocation")
	public List<telecom> viewByLocation(@RequestParam("location") String location) {
	    if (location.equalsIgnoreCase("all")) {
	        return ts.teleList();
	    } else {
	        // Assuming 'location' is the field in your 'telecom' entity class
	        return tr.findByLocation(location);
	    }
	}
	
}
