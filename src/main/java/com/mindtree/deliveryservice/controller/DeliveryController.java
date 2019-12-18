package com.mindtree.deliveryservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryController {
	
	@GetMapping("/deliver/{id}")
	public String deliverOder(@PathVariable long id) {
		String message="Order with id: "+id+" is delivered";
		return message;
		
	}

}
