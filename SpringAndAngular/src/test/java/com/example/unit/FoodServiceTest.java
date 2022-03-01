package com.example.unit;

<<<<<<< HEAD
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
=======
>>>>>>> 6b0e007e55349e16afadbcfb5bf6e2437ded8901
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
<<<<<<< HEAD
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.model.Food;
import com.example.repository.FoodRepository;
import com.example.service.FoodService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
=======

import com.example.repository.FoodRepository;
import com.example.service.FoodService;

@RunWith(MockitoJUnitRunner.class)
>>>>>>> 6b0e007e55349e16afadbcfb5bf6e2437ded8901
public class FoodServiceTest {
	
	@InjectMocks //this will inject our mocks into the food service, with is the mocked repo.
	FoodService service;
	
<<<<<<< HEAD
	@MockBean
	FoodRepository mockRepo;
	
	Food food;
	
=======
	@Mock
	FoodRepository mockRepo;
	
>>>>>>> 6b0e007e55349e16afadbcfb5bf6e2437ded8901
	//test setup and when method to pervent the repository logic from actually executing
	//doNothing mockRepo.save etc.
	//when(mockRepo.findByFoodName( is(String)).thenResturn(food);
	
<<<<<<< HEAD
	@BeforeEach
	public void setUp() {
		food = new Food(1, "Taco", 123);
	}
	
	@Test
	public void testGetFoodByName() {
		when(this.service.getFoodByName("Taco")).thenReturn(food);
		assertEquals(food, service.getFoodByName(food.getFoodName()));
	}
=======
>>>>>>> 6b0e007e55349e16afadbcfb5bf6e2437ded8901
	
	//test to test the service method with logic

}
