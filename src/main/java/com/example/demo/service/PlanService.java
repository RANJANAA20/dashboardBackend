package com.example.demo.service;
import java.math.BigDecimal;
import java.time.Month;
import java.time.format.TextStyle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.PlanRepo;
import com.example.demo.model.DataPlans;
import com.example.demo.model.Dataset;

import java.util.*;

@Service
public class PlanService 
{
	@Autowired
	PlanRepo pr;
	
	// add customer
//	public void addData(Dataset ds) 
//	{		pr.save(ds);
//		
//	}
	
    public void addData(List<Dataset> datasets) {
        for (Dataset ds : datasets) {
            pr.save(ds);
        }
    }


	//view customer
	public List<Dataset> viewData() 
	{
		// TODO Auto-generated method stub
		List<Dataset> ds = pr.findAll();
		return ds;
		
	}
	
	// to get no.of.customers based on location
	public Map<String, Long> getLocationCounts() 
	{
	        List<Object[]> locationCounts = pr.countLocations();
	        
	        Map<String, Long> locationCountMap = new HashMap<>();
	        for (Object[] row : locationCounts) {
	            String location = (String) row[0];
	            Long count = (Long) row[1];
	            locationCountMap.put(location, count);
	        }
	        
	        return locationCountMap;	
	}
	
	// total customer
	public long count() 
	{
		long count = pr.count();
//		System.out.println(count);
		return count;
	}

	// revenue
	public long billRevenue() 
	{
		long rev = pr.revenue();
//		System.out.println(rev);
		return rev;
	}
	
	// to get no.of.customers based on plans
	public Map<String, Long> getPlanCount() 
	{
		// this list has objects of plan with count
		List<Object[]> planList = pr.countPlans();
		
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

	// active users
	public long active() 
	{
		long active = pr.activeCustomer();
//		System.out.println(active);
		return active;
	}

	// inactive users
	public long inactive() 
	{
		long inactive = pr.inactiveCustomer();
		return inactive;
	}

	// month based revenue
	public Map<String, Long> monthRevenue() 
	{
	    List<Object[]> revList = pr.monthRevenue();
	
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

	    return sortedMap;
	}

	// to get customer by id
	public Optional<Dataset> getCustomerById(Integer id) 
	{
		return pr.findById(id);
	}

	// to get no.of.customers based on mode of payment
	public Map<String, Long> paymentMode() 
	{
		List<Object[]> payList = pr.modeOfPayment();
		
		Map<String,Long> payMap = new HashMap<>();
		
		for(Object[] object : payList)
		{
			String payment = (String) object[0];
			Long count = (Long) object[1];
			payMap.put(payment, count);
		}
		return payMap;
	}

	// payment status
	public Map<String, Long> paymentStatus() 
	{
		List<Object[]> pList = pr.payStatus();
		
		Map<String,Long> mList = new HashMap<>();
		
		for(Object[] object : pList)
		{
			String payLabel = (String) object[0];
			Long payCount = (Long) object[1];
			
			mList.put(payLabel, payCount);
		}
		return mList;
	}

	// to get count of new customers
	public long custNew() 
	{
		long cNew = pr.subnew();
		return cNew;
	}


//	public Map<String, Map<String, Long>> statusCount() 
//	{
//		// TODO Auto-generated method stub
//		List<Object[]> statusList = pr.custStatus();
//		System.out.println(statusList);
//		Map<String,Map<String,Long>> statusMap = new HashMap<>();
//		
//		for(Object[] object : statusList)
//		{
//			String month = (String)object[0];
//			Long activeCount = (Long) object[1];
//			Long inactiveCount = (Long) object[2];
//			
//			Map<String,Long> innerMap = new HashMap<>();
//			
//			innerMap.put("active", activeCount);
//			innerMap.put("inactive", inactiveCount);
//			statusMap.put(month, innerMap);
//		}
//		return statusMap;
//	}
	
//	
	public Map<String, Map<String, BigDecimal>> statusCount() {
	    List<Object[]> statusList = pr.custStatus();
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

	// revenue based on plan
//	public Map<String, Long> planRev() 
//	{
//		List<Object[]> prList = pr.planRevenue();
//		
//		Map<String,Long> prMap = new HashMap<>();
//		
//		for(Object[] object : prList)
//		{
//			String prLabel = (String)object[0];
//			
//			Long prValues = (Long)object[1];
//			
//			prMap.put(prLabel, prValues);
//			
//		}
//		return prMap;
//	}
	
	public Map<String, BigDecimal> planRev() {
	    List<Object[]> prList = pr.planRevenue();
	    
	    Map<String, BigDecimal> prMap = new HashMap<>();
	    
	    for(Object[] object : prList) {
	        String prLabel = (String) object[0];
	        BigDecimal prValues = (BigDecimal) object[1];
	        prMap.put(prLabel, prValues);
	    }
	    return prMap;
	}

	// to count no.of.plans
	public Long plan() 
	{
		return pr.plan();
		
	}

}

	
	


