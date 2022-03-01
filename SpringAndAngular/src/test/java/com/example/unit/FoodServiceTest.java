package com.example.unit;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.model.Food;
import com.example.repository.FoodRepository;
import com.example.service.FoodService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class FoodServiceTest {
	
	@InjectMocks //this will inject our mocks into the food service, with is the mocked repo.
	FoodService service;
	@MockBean
	FoodRepository mockRepo;
	
	Food food;
	

	//test setup and when method to pervent the repository logic from actually executing
	//doNothing mockRepo.save etc.
	//when(mockRepo.findByFoodName( is(String)).thenResturn(food);
	
	@BeforeEach
	public void setUp() {
		food = new Food(1, "Taco", 123);
	}
	
	@Test
	public void testGetFoodByName() {
		System.out.println("test");
		when(this.mockRepo.findByFoodName("Taco")).thenReturn(food);
		assertEquals(food, service.getFoodByName(food.getFoodName()));
	}
	
	@Test
	public void testInsertFoodSuccess() {
		when(this.mockRepo.save(food)).thenReturn(food);
		this.service.insertFood(food);
		verify(this.mockRepo, times(1)).save(food);
	}
	
	//test to test the service method with logic

}
