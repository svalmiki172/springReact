package com.example.intern;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Intern {
	
	@Id
	public String id;
	private String name;
	private String city;
}
