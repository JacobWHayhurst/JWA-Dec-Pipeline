package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Food;
import com.example.service.FoodService;

@RestController
@RequestMapping(value="/food")
@CrossOrigin(origins="*")//CORS - cross origin resource sharing, it is a mechinism that can restrict access for resource from external server
//requests(aka requests outside of the servers domain) if you set the origin to *, it will allow any domain to request the server
public class FoodController {

	private FoodService fServ;
	
	public FoodController() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public FoodController(FoodService fServ) {
		super();
		this.fServ = fServ;
	}
	
	@GetMapping("/init")
	public ResponseEntity<List<Food>> insertInitialValues(){
		List<Food> fList = new ArrayList<Food>(Arrays.asList(new Food("Ramen", 1000), new Food("Pizza", 2500), new Food("Tomato Soup", 300),
				new Food("Sandwich", 400)));
		for(Food food: fList) {
			fServ.insertFood(food);
		}
		
		return ResponseEntity.status(201).body(fServ.getAllFood());
		
	}
	
	@GetMapping()
	public ResponseEntity<List<Food>> getAllFood(){
		return ResponseEntity.status(200).body(fServ.getAllFood());
	}
	
	@GetMapping("/{foodname}")
	public ResponseEntity<Food> getFoodName(@PathVariable("foodname") String name){
		Optional<Food> foodOpt = Optional.ofNullable(fServ.test(name));
		if(foodOpt.isPresent()) {
			return ResponseEntity.status(200).body(foodOpt.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{foodname}")
	public ResponseEntity<Food> deleteFood(@PathVariable("foodname") String name){
		Optional<Food> foodOpt = Optional.ofNullable(fServ.getFoodByName(name));
		if(foodOpt.isPresent()) {
			fServ.deleteFood(foodOpt.get());
			return ResponseEntity.status(200).body(foodOpt.get());
		}
		return ResponseEntity.notFound().build();
		
	}
	
	
	@PostMapping()
	public ResponseEntity<Food> insertFood(@RequestBody Food food){
		Optional<Food> foodOpt = Optional.ofNullable(fServ.getFoodByName(food.getFoodName()));
		if(foodOpt.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		fServ.insertFood(food);
		return ResponseEntity.status(201).body(fServ.getFoodByName(food.getFoodName()));
	}
	
	@PostMapping(value="/testing")
	public ResponseEntity<String>test(){
		return ResponseEntity.status(200).body("success");
	}
	
	
	
}
