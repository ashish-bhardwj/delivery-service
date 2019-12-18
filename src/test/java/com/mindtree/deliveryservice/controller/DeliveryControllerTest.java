package com.mindtree.deliveryservice.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value= DeliveryController.class)
public class DeliveryControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void deliverOrderById_positive() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get(
				"/deliver/1")
				.accept(MediaType.ALL);
		mockMvc.perform(request)
		.andExpect(status().is(200))
		.andReturn();

	}
	
	@Test
	public void deliverOrderById_negative() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get(
				"/deliver/w")
				.accept(MediaType.ALL);
		mockMvc.perform(request)
		.andExpect(status().is(400))
		.andReturn();

	}
	
	@Test
	public void deliverOrderById_null() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get(
				"/deliver")
				.accept(MediaType.ALL);
		mockMvc.perform(request)
		.andExpect(status().is(404))
		.andReturn();

	}

}
