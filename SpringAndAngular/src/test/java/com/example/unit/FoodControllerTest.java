package com.example.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.controller.FoodController;
import com.example.model.Food;
import com.example.service.FoodService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)// this allows the test to run with the IoC Container
@WebMvcTest(FoodController.class)//this allows us to mock MVC request to the foodcontroller
public class FoodControllerTest {
	
	@MockBean //this will tell the IoC container to initialize the mocked food service instance, and 
	//inject it to our controller
	FoodService fServ;
	
	@Autowired
	MockMvc mock;//this allows us to make requests from tests to the Foodcontroller, and control every of aspect
	//of the request
	
	Food food;
	
	@BeforeEach
	public void setUp() throws Exception{
		food = new Food(3, "Pizza", 1500);
		doNothing().when(this.fServ).insertFood(food);// we use this to mock a void return type method
		doNothing().when(this.fServ).deleteFood(food);
	}
	
	@Test
	public void testFoodInsertSuccessfully() throws Exception {
		when(this.fServ.getFoodByName(food.getFoodName())).thenReturn(null).thenReturn(food); //this is how we mock a method that does have a return value
		this.mock.perform(post("/food").contentType(MediaType.APPLICATION_JSON).content(
				new ObjectMapper().writeValueAsString(food))).andExpect(status().isCreated()); //we are mocking a request, sending that request to
			//POST to localhost:9015/food and JSON body of  {"foodname":"Pizza", "calories":"1500"}
	}
	
	@Test
	public void testFoodInsertUnSuccessfully() throws Exception {
		when(this.fServ.getFoodByName(food.getFoodName())).thenReturn(food); 
		this.mock.perform(post("/food").contentType(MediaType.APPLICATION_JSON).content(
				new ObjectMapper().writeValueAsString(food))).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testGetAllFood() throws Exception {
		List<Food> fList = new ArrayList<Food>();
		fList.add(food);
		when(this.fServ.getAllFood()).thenReturn(fList);
		this.mock.perform(get("/food").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(jsonPath("$[0].foodName", is(food.getFoodName())))
		.andExpect(jsonPath("$[0].calories", is(1500)));
		//The jsonPath allows use to look through the JSOn that is being sent back as a response from our method, we
		//can use the $ in the expression to select the whole json and use . operators to inspect the attributes 
		//of the json
	}
	
//	@Test
//	public void testGetFoodByNameSuccess() throws Exception {
//		when(this.fServ.getFoodByName(food.getFoodName())).thenReturn(food);
//		this.mock.perform(get("/food/"+food.getFoodName()).contentType(MediaType.APPLICATION_JSON))
//		.andExpect(status().isOk()).andExpect(jsonPath("$.foodName", is(food.getFoodName())));
//	}
	
	@Test
	public void testputNotAllowed() throws Exception {
		this.mock.perform(put("/food")).andExpect(status().isMethodNotAllowed());
	}
	

}
