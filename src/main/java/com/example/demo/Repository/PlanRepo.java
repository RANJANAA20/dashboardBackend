package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DataPlans;
import com.example.demo.model.Dataset;


@Repository

public interface PlanRepo extends JpaRepository<Dataset, Integer> 
{

	// to get no.of.customers based on location
	@Query(value="SELECT location, COUNT(location) FROM Dataset GROUP BY location",nativeQuery = true)
    List<Object[]> countLocations();
    
    // to get no.of.customers based on plan
    @Query(value="SELECT plan, COUNT(plan) FROM Dataset GROUP BY plan",nativeQuery = true)
    List<Object[]> countPlans();

    // no.of.customers
    @Query("select count(*) from Dataset")
    long count();
    
    // revenue
    @Query("select sum(bill) from Dataset")
    long revenue();
    
    // active users
    @Query(value="select count(status) from Dataset where status = 'Active'",nativeQuery=true)
    long activeCustomer();
    
    // inactive users 
    @Query(value="select count(status) from Dataset where status ='Inactive'",nativeQuery=true)
    long inactiveCustomer();
    
    // revenue based on month
    @Query(value = "SELECT month(date_of_issue) as month, SUM(bill) as total_amount FROM dataset GROUP BY month order by month;",nativeQuery = true)
    List<Object[]> monthRevenue();
    
    // to get no.of customers based on mode of payment
    @Query(value="select payment_mode,count(payment_mode) from Dataset GROUP BY payment_mode",nativeQuery =true)
    List<Object[]> modeOfPayment();
    
    // to get payment status
    @Query(value="select pay_status,count(pay_status) from Dataset GROUP BY pay_status",nativeQuery = true)
    List<Object[]> payStatus();
    
    // to get new customers
    @Query(value="select count(*) from dataset where month(date_of_issue) = month(current_date()) and year(date_of_issue) = year(date_of_issue)",nativeQuery = true)
    long subnew();
    
    // active / inactive based on month
    @Query(value="select monthname(date_of_issue ) as month, sum(CASE WHEN status = 'Active' THEN 1 ELSE 0 end),sum(CASE WHEN status = 'Inactive' THEN 1 ELSE 0 end) from dataset GROUP BY MONTH ORDER BY monthname(date_of_issue)",nativeQuery = true)
    List<Object[]> custStatus();
}
