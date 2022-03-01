Feature: users can view and individual food and delete it

		Background: A user is logs in on the food page
			Given The user is on the login page
			When the user inputs "JacobWHayhurst" into the username field
			And the user inputs "password" in to password field
			When the user clicks login
			Then the user is directed the food page
			
		Scenario: A user can view Test food item and Delete it
			When The user inputs "Test" into the food name field
			When The user inputs "1500" into the calorie input field
			When the user clicks submit new food
			When the new food appears on the table the user can click the "Test" link
			When the user is on the Test food item page
			When the user clicks the delete food button
			Then the user will taken to the Food table page without the Test food