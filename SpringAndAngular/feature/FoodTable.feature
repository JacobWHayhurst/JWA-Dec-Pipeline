Feature: Users can view all Food items they created

		Background: A user is logs in on the food page
			Given The user is on the login page
			When the user inputs "JacobWHayhurst" into the username field
			And the user inputs "password" in to password field
			When the user clicks login
			Then the user is directed the food page

		Scenario: A User can view all Food in the Database
			When Page food Page Loads
			Then the user should see the All the food in the table.
			
		Scenario: A User can submit a new Food successfully
			When Page food Page Loads
			When The User inputs "Nachos" into the foodName Field
			When The User inputs "1000" into calories field
			When the User clicks submit food
			Then the table should show the new food 
			
		Scenario: A User can submit a new Food unsuccessfully
			When Page food Page Loads
			When The User inputs "Pizza" into the foodName Field
			When The User inputs "2500" into calories field
			When the User clicks submit food
			Then the table should show food already exists