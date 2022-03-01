package com.example.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * There is an @Data annotation that will provide implementation of getter, setter, toString,
 * 	@RequiredArgsConstructor(a constructor only with the fields marked by the @NotNull and values that 
 * are final) and @EqualsAndHashCode
 */


@Entity
@Table(name="food")
public class Food {
	
	@Id
	@Column(name="food_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int foodId;
	
	@Column(name="food_name", unique=true, nullable=false)
	private String foodName;
	
	@Column(name="calories")
	private int calories;
	
	public Food() {
		// TODO Auto-generated constructor stub
	}
	
	public Food(String foodName, int calories) {
		super();
		this.foodName = foodName;
		this.calories = calories;
	}

	public Food(int foodId, String foodName, int calories) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.calories = calories;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getFoodId() {
		return foodId;
	}

	@Override
	public String toString() {
		return "Food [foodId=" + foodId + ", foodName=" + foodName + ", calories=" + calories + "]";
	}

}
