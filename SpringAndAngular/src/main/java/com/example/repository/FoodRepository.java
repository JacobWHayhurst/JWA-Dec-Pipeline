package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Food;


@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
	
	public List<Food> findAll();
	public Food getFoodByFoodName(String name);
	public Food findByFoodName(String foodName);
	public List<Food> findByCalories(int cal);
	public Food findByFoodNameAndCalories(String foodName, int cal);
	public Food findByFoodId(int id);
	//if you have a stored function all you would need to do is make a method in this interface of the same name as the function 
	// in the database annotated with @Procedure
	/*
	 * @Procedure
	 * int countTotalFoodEntries();
	 */

}
