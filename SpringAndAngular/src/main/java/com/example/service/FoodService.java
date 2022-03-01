package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Food;
import com.example.repository.FoodRepository;

@Service
public class FoodService {
	
	private FoodRepository fRepo;
	
	public FoodService() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public FoodService(FoodRepository fRepo) {
		super();
		this.fRepo = fRepo;
	}



	public List<Food> getAllFood(){
		return fRepo.findAll();
	}
	
	public void insertFood(Food food) {
		fRepo.save(food);
	}
	
	public Food getFoodByName(String name) {
		return fRepo.findByFoodName(name);
	}
	
	public List<Food> getFoodByCalories(int cal){
		return fRepo.findByCalories(cal);
	}
	
	public Food getFoodByNameAndCal(String foodName, int cal) {
		return fRepo.findByFoodNameAndCalories(foodName, cal);
	}
	
	public Food getFoodById(int id) {
		return fRepo.findByFoodId(id);
	}
	
	public void deleteFood(Food food) {
		fRepo.delete(food);
	}
	
	public Food test(String name) {
		return this.fRepo.getFoodByFoodName(name);
	}
	

}
