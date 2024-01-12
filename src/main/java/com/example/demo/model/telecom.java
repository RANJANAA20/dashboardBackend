package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class telecom 
{
	@Id
	private String CustomerID;
	private String SubscriptionPlan;
	private int IsBillingAnomaly;
	private String status;
	private Date date;
	private String paymode;
	private String location;
	private int bill;
}
