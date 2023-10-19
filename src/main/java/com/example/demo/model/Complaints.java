package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Complaints 
{
	@Id
	private int cId;
	private String cName;
	private String location;
	private String plan;
	private String issue;
	private Date issueDate;
	private String status;
	
}
