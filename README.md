# :ramen: Recipe-Manager
A recipe management system to keep track the recipes for personal or commercial such as restaurant

## :loudspeaker: Problem Statement
- Difficult to manage recipe in a busy restaurant
- Difficult to remember all the recipes of the restaurant


## :books: Functional Requirement
1.	Create a new recipe.
2.	Search existing recipes.
3.	View existing recipes.
4.	Modify existing recipes.
5.	Delete existing recipes.
6.	Allow admin to create an account.
7.	Allow the admin to log in to the admin account.
8.	Allow the admin to log out to the admin account.

## :gear: Installation
1. Clone the project
2. Create a database for this project using create-db.sql
3. Change the mysql database information (you may need to create a new user, 'RecipeManager' in dbUrl is the schema name) ![DBInfo](https://github.com/dingwei426/Recipe-Manager/blob/main/screenshot/DbInfo.png)
4. Change the directory for recipeImageCache
   - change the path according to location of the recipeImageCache folder (you can find in src/main/java directory) ![RecipeDAO](https://github.com/dingwei426/Recipe-Manager/blob/main/screenshot/RecipeDAO.png) ![TopLevelMenu](https://github.com/dingwei426/Recipe-Manager/blob/main/screenshot/TopLevelMenu.png)
5. Run maven in command line (or you may use any IDE to run this project)
    ```
     mvn compile
     ```
    ```
     mvn test
     ```
    ```
     mvn install
     ```
    ```
     java -jar target/Recipe-Manager-1.0-SNAPSHOT.jar
     ```
   


## :framed_picture: Screenshot
### :memo: Signup
![Signup Page](https://github.com/dingwei426/Recipe-Manager/blob/main/screenshot/signup.png)

### :old_key: Login
![Login Page](https://github.com/dingwei426/Recipe-Manager/blob/main/screenshot/login.png)

### :clipboard:	Main Menu
![Main Menu Page](https://github.com/dingwei426/Recipe-Manager/blob/main/screenshot/main_menu.png)

### :receipt:	Create Recipe
![Create Recipe Page](https://github.com/dingwei426/Recipe-Manager/blob/main/screenshot/create_recipe.png)

### :paintbrush: Set Up Ingredients of the New Recipe
![Set Up Ingredients Page](https://github.com/dingwei426/Recipe-Manager/blob/main/screenshot/set_recipe_ingredients.png)

### :eye: View Recipes
![View Recipes Page](https://github.com/dingwei426/Recipe-Manager/blob/main/screenshot/view_recipes.png)

### :eyes: View Recipe Details
![View Recipe Details Page](https://github.com/dingwei426/Recipe-Manager/blob/main/screenshot/recipe_page.png)

### :black_nib:	Edit Recipe
![Edit Recipe Page](https://github.com/dingwei426/Recipe-Manager/blob/main/screenshot/edit_recipe.png)

### :technologist: User Profile
![User Profile Page](https://github.com/dingwei426/Recipe-Manager/blob/main/screenshot/user_profile.png)

### :closed_lock_with_key: Change Account Password
![Change Account Password Page](https://github.com/dingwei426/Recipe-Manager/blob/main/screenshot/change_password.png)

### :lock: Logout
![Logout Page](https://github.com/dingwei426/Recipe-Manager/blob/main/screenshot/logout.png)
