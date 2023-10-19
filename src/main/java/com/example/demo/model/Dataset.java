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
public class Dataset 
{
	@Id
	private int cust_id;
	private String cust_name;
	private String location;
	private String plan;
	private int bill;
	private Date date_of_issue;
	private String status;
	private String paymentMode;
	private Date endDate;
	private Date payDate;
	private String payStatus;
	
}
