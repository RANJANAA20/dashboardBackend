package com.example.demo.service;

import java.math.BigDecimal;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.teleRepo;
import com.example.demo.model.Dataset;
import com.example.demo.model.telecom;

@Service
public class teleService 
{
	@Autowired 
	teleRepo tr;

	public long countTeleCust() 
	{
		
		return tr.countTele();
	}

	public List<telecom> teleList() 
	{
		return tr.findAll();
	}

	public long countA() 
	{
		return tr.countActive();
	}

	public long countInA() 
	{
		return tr.countInactive();
	}

	public long countNew() 
	{
		
		return tr.telenew();
	}

	public Map<String, Long> getLocation()
	{
		List<Object[]> locationCounts = tr.teleLoc();
        
        Map<String, Long> locationCountMap = new HashMap<>();
        for (Object[] row : locationCounts) {
            String location = (String) row[0];
            Long count = (Long) row[1];
            locationCountMap.put(location, count);
        }
		return locationCountMap;
	}

	public Map<String, Long> plan() 
	{
		List<Object[]> planList = tr.countPlans();
		
		// map to have plan as key and count as value
		Map<String , Long> planCountMap = new HashMap<>();
		for(Object[] object : planList)
		{
			String plan = (String) object[0];
			Long planCount = (Long) object[1];
			planCountMap.put(plan, planCount);
		}
		return planCountMap;
	}

	public Map<String, Long> payment() 
	{
		// TODO Auto-generated method stub
		List<Object[]> payList = tr.Payment();
		
		Map<String,Long> payMap = new HashMap<>();
		
		for(Object[] object : payList)
		{
			String payment = (String) object[0];
			Long count = (Long) object[1];
			payMap.put(payment, count);
		}
		return payMap;
	}

	public Map<String, Map<String, BigDecimal>> statusCount() 
	{
		List<Object[]> statusList = tr.statusTelecom();
//	    System.out.println(statusList);
	    Map<String, Map<String, BigDecimal>> statusMap = new HashMap<>();

	    // Custom comparator to sort months chronologically
	    Comparator<String> monthComparator = (month1, month2) -> {
	        List<String> monthOrder = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
	        return Integer.compare(monthOrder.indexOf(month1), monthOrder.indexOf(month2));
	    };

	    for (Object[] object : statusList) {
	        String month = (String) object[0];
	        BigDecimal activeCount = (BigDecimal) object[1];
	        BigDecimal inactiveCount = (BigDecimal) object[2];

	        Map<String, BigDecimal> innerMap = new HashMap<>();

	        innerMap.put("active", activeCount);
	        innerMap.put("inactive", inactiveCount);
	        statusMap.put(month, innerMap);
	    }

	    // Sort the map by month
	    Map<String, Map<String, BigDecimal>> sortedMap = new TreeMap<>(monthComparator);
	    sortedMap.putAll(statusMap);

	    return sortedMap;

	}

	public Map<String, BigDecimal> planRevenue() 
	{
		List<Object[]> prList = tr.planRev();
	    
	    Map<String, BigDecimal> prMap = new HashMap<>();
	    
	    for(Object[] object : prList) {
	        String prLabel = (String) object[0];
	        BigDecimal prValues = (BigDecimal) object[1];
	        prMap.put(prLabel, prValues);
	    }
		return prMap;
	}

	public Map<String, Long> monthRev() 
	{
		List<Object[]> revList = tr.monthRev();
		
	    Map<String, Long> revMap = new LinkedHashMap<>();
	    // comparator to sort months 
	    Comparator<String> monthComparator = (m1, m2) -> {
	        Integer m1Index = Month.valueOf(m1.toUpperCase()).getValue();
	        Integer m2Index = Month.valueOf(m2.toUpperCase()).getValue();
	        return m1Index.compareTo(m2Index);
	    };

	    for (Object[] object : revList) 
	    {
	        int monthNumber = (int) object[0];
	        String monthName = Month.of(monthNumber).getDisplayName(TextStyle.FULL, Locale.ENGLISH).toUpperCase();
//	        String monthName = Month.of(month).getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
	        Long revenue = ((Number) object[1]).longValue();
	        revMap.put(monthName, revenue);
	    }

	    // Sort the map by month
	    Map<String, Long> sortedMap = new LinkedHashMap<>();
	    revMap.entrySet().stream()
	            .sorted(Map.Entry.comparingByKey(monthComparator))
	            .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

		return revMap;
	}

	public Map<Integer, Long> billAnamoly() 
	{
		List<Object[]> prList = tr.anamoly();
	    
	    Map<Integer, Long> prMap = new HashMap<>();
	    
	    for(Object[] object : prList) {
	    	Integer prLabel = (Integer) object[0];
	        Long prValues = (Long) object[1];
	        prMap.put(prLabel, prValues);
	    }
		return prMap;
	}

	public long billRev() 
	{
		long rev = tr.rev();
		return rev;
	}

	public long location() 
	{
		long l = tr.locCount();
		return l;
	}

	public void addTele(List<telecom> tele) 
	{
		for (telecom t : tele) 
		{
            tr.save(t);
        }
		
	}

//	public Optional<telecom> search(String customerID) 
//	{
//		// TODO Auto-generated method stub
//		return tr.findByCustomerID(customerID);
//		
//	}
	// search by Customer ID
	public List<telecom> search(String customerID) {
	    return tr.findByCustomerID(customerID);
	}
	
	public List<telecom> teleListByLocation(String location) {
	    return tr.findByLocation(location);
	}

	
	
	
}
