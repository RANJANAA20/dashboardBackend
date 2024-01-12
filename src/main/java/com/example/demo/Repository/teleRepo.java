package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.telecom;

public interface teleRepo extends JpaRepository<telecom, String> 
{

	// total count of customer
	@Query(value="select count(*) from telecom ",nativeQuery=true)
	long countTele();
	
	// count of active customers
	@Query(value="select count(*) from telecom where status = 'Active' ",nativeQuery=true)
	long countActive();
	
	// count of inactive customers
	@Query(value="select count(*) from telecom where status = 'Inactive' ",nativeQuery=true)
	long countInactive();
	
	// to get new customers
	//select count(*) from telecom where month(date) = month(current_date()) and year(date) = year(date);
    @Query(value="SELECT COUNT(*) FROM telecom WHERE MONTH(date) = 10 AND YEAR(date) = YEAR(CURRENT_DATE);",nativeQuery = true)
    long telenew();
    
    // location
    @Query(value="SELECT location, COUNT(location) FROM telecom GROUP BY location;",nativeQuery = true)
    List<Object[]> teleLoc();
    
    // to get no.of.customers based on plan
    @Query(value="SELECT subscription_plan, COUNT(subscription_plan) FROM telecom GROUP BY subscription_plan;",nativeQuery = true)
    List<Object[]> countPlans();
    
    // to get no.of customers based on mode of payment
    @Query(value="select paymode,count(paymode) from telecom GROUP BY paymode",nativeQuery =true)
    List<Object[]> Payment();
    
    // active / inactive based on month
    @Query(value="select monthname(date) as month, sum(CASE WHEN status = 'Active' THEN 1 ELSE 0 end),sum(CASE WHEN status = 'Inactive' THEN 1 ELSE 0 end) from telecom GROUP BY MONTH ORDER BY monthname(date);",nativeQuery = true)
    List<Object[]> statusTelecom();
    
    // to get no.of.customers based on plan
    @Query(value="SELECT subscription_plan, COUNT(subscription_plan) FROM telecom GROUP BY subscription_plan",nativeQuery = true)
    List<Object[]> Plancount();
    
    // revenue based on plan
    @Query(value= "select  subscription_plan , sum(bill) from telecom group by subscription_plan",nativeQuery = true)
    List<Object[]> planRev();
    
    // revenue based on month
    @Query(value = "SELECT month(date) as month, SUM(bill) as total_amount FROM telecom GROUP BY month order by month;",nativeQuery = true)
    List<Object[]> monthRev();
    
    // billing anamoly
    @Query(value= "SELECT is_billing_anomaly, COUNT(is_billing_anomaly) FROM telecom GROUP BY is_billing_anomaly;",nativeQuery = true)
    List<Object[]> anamoly();
    
    // revenue
    @Query("select sum(bill) from telecom")
    long rev();
    
    // location count
    @Query(value ="SELECT COUNT(DISTINCT location) AS unique_location_count FROM telecom;",nativeQuery = true)
    long locCount();

    // search
    @Query(value = "SELECT * FROM telecom t WHERE t.customerID = ?1",nativeQuery = true)
	List<telecom> findByCustomerID(String customerID);
	
	// location by category
	@Query(value="SELECT * FROM telecom t WHERE t.location = :location",nativeQuery = true)
    List<telecom> findByLocation(@Param("location") String location);
}


