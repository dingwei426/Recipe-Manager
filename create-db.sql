CREATE DATABASE  IF NOT EXISTS `RecipeManager`;
USE `RecipeManager`;

-- Create User Table
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create Recipe Table
CREATE TABLE `recipe` (
  `recipe_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `steps` text NOT NULL,
  `equipments` text,
  `time_taken` int DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `yield` double DEFAULT NULL,
  `tips` text,
  `image` longblob,
  `fk_recipe_user` int DEFAULT NULL,
  PRIMARY KEY (`recipe_id`),
  KEY `recipe_ibfk1` (`fk_recipe_user`),
  CONSTRAINT `recipe_ibfk1` FOREIGN KEY (`fk_recipe_user`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create Ingredient Table
CREATE TABLE `ingredient` (
  `ingredient_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `unit` varchar(50) DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `fk_ingredient_recipe` int DEFAULT NULL,
  PRIMARY KEY (`ingredient_id`),
  KEY `ingredient_ibfk1` (`fk_ingredient_recipe`),
  CONSTRAINT `ingredient_ibfk1` FOREIGN KEY (`fk_ingredient_recipe`) REFERENCES `recipe` (`recipe_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create Nutrition Fact Table
CREATE TABLE `nutrition_fact` (
  `nutrition_fact_id` int NOT NULL AUTO_INCREMENT,
  `calories` double DEFAULT NULL,
  `fat` double DEFAULT NULL,
  `carbs` double DEFAULT NULL,
  `protein` double DEFAULT NULL,
  `fk_nutrition_fact_recipe` int DEFAULT NULL,
  PRIMARY KEY (`nutrition_fact_id`),
  KEY `nutrition_fact_ibfk1` (`fk_nutrition_fact_recipe`),
  CONSTRAINT `nutrition_fact_ibfk1` FOREIGN KEY (`fk_nutrition_fact_recipe`) REFERENCES `recipe` (`recipe_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Stored Procedure to Insert User
DELIMITER //
CREATE PROCEDURE InsertUser(IN p_username VARCHAR(255), IN p_password VARCHAR(255))
BEGIN
    INSERT INTO user(username, password) VALUES (p_username, p_password);
END //
DELIMITER ;

-- Stored Procedure to Insert Recipe
DELIMITER //
CREATE PROCEDURE InsertRecipe(
    IN p_name VARCHAR(255), 
    IN p_steps TEXT, 
    IN p_equipments TEXT, 
    IN p_time_taken INT, 
    IN p_category VARCHAR(255), 
    IN p_yield DOUBLE, 
    IN p_tips TEXT, 
    IN p_image BLOB, 
    IN p_fk_recipe_user INT)
BEGIN
    INSERT INTO recipe(name, steps, equipments, time_taken, category, yield, tips, image, fk_recipe_user)
    VALUES (p_name, p_steps, p_equipments, p_time_taken, p_category, p_yield, p_tips, p_image, p_fk_recipe_user);
END //
DELIMITER ;

-- Stored Procedure to Insert Ingredient
DELIMITER //
CREATE PROCEDURE InsertIngredient(
    IN p_name VARCHAR(255), 
    IN p_unit VARCHAR(50), 
    IN p_quantity DOUBLE, 
    IN p_fk_ingredient_recipe INT)
BEGIN
    INSERT INTO ingredient(name, unit, quantity, fk_ingredient_recipe)
    VALUES (p_name, p_unit, p_quantity, p_fk_ingredient_recipe);
END //
DELIMITER ;

-- Stored Procedure to Insert Nutrition Fact
DELIMITER //
CREATE PROCEDURE InsertNutritionFact(
    IN p_calories DOUBLE, 
    IN p_fat DOUBLE, 
    IN p_carbs DOUBLE, 
    IN p_protein DOUBLE, 
    IN p_fk_nutrition_fact_recipe INT)
BEGIN
    INSERT INTO nutrition_fact(calories, fat, carbs, protein, fk_nutrition_fact_recipe)
    VALUES (p_calories, p_fat, p_carbs, p_protein, p_fk_nutrition_fact_recipe);
END //
DELIMITER ;

-- Stored Procedure to Delete Recipe
DELIMITER //
CREATE PROCEDURE DeleteRecipe(IN p_recipe_id INT)
BEGIN
    DELETE FROM recipe WHERE recipe_id = p_recipe_id;
END //
DELIMITER ;

-- Stored Procedure to Delete Ingredients
DELIMITER //
CREATE PROCEDURE DeleteIngredients(IN p_recipe_id INT)
BEGIN
    DELETE FROM ingredient WHERE fk_ingredient_recipe = p_recipe_id;
END //
DELIMITER ;

-- Stored Procedure to Change User Password
DELIMITER //
CREATE PROCEDURE ChangeUserPassword(IN p_username VARCHAR(255), IN p_password VARCHAR(255), OUT p_result VARCHAR(255))
BEGIN
    UPDATE user SET password = p_password WHERE username = p_username;
    SET p_result = 'Password changed successfully.';
END //
DELIMITER ;

-- Stored Procedure to Get User ID
DELIMITER //
CREATE PROCEDURE getUserID(IN p_username VARCHAR(255))
BEGIN
    SELECT user_id FROM user WHERE username = p_username;
END //
DELIMITER ;
