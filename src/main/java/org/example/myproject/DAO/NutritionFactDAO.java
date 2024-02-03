package org.example.myproject.DAO;

import org.example.myproject.Model.NutritionFact;
import org.example.myproject.Model.Recipe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NutritionFactDAO {
    public static void insertNutritionFact(Recipe recipe, NutritionFact nutritionFact){

        try {
            Connection connection = DriverManager.getConnection(DbInfo.dbUrl, DbInfo.dbUser, DbInfo.dbPass);

            String getUserIDSql = "SELECT recipe_id FROM recipe WHERE name = ?";
            PreparedStatement preparedUserIDStatement = connection.prepareCall(getUserIDSql);
            preparedUserIDStatement.setString(1, recipe.getName());
            preparedUserIDStatement.execute();
            ResultSet recipeIDSet = preparedUserIDStatement.getResultSet();
            if (!recipeIDSet.next())
                return;
            int recipeID = recipeIDSet.getInt("recipe_id");

            //insert new account info into db
            String storedProcedureCall = "{call InsertNutritionFact(?, ?, ?, ?, ?)}";
            PreparedStatement preparedStatement = connection.prepareCall(storedProcedureCall);
            preparedStatement.setDouble(1, nutritionFact.getCalories());
            preparedStatement.setDouble(2, nutritionFact.getFat());
            preparedStatement.setDouble(3, nutritionFact.getCarbs());
            preparedStatement.setDouble(4, nutritionFact.getProtein());
            preparedStatement.setInt(5, recipeID);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean editNutritionFact(int recipe_id, Recipe newRecipe){

        try {
            Connection connection = DriverManager.getConnection(DbInfo.dbUrl, DbInfo.dbUser, DbInfo.dbPass);

            String editNutritionFactSql = "UPDATE nutrition_fact SET calories = ?, fat = ?, carbs = ?, protein = ? " +
                    "WHERE fk_nutrition_fact_recipe = ?";
            PreparedStatement preparedEditNutritionFactStatement = connection.prepareCall(editNutritionFactSql);
            preparedEditNutritionFactStatement.setDouble(1, newRecipe.getNutrition().getCalories());
            preparedEditNutritionFactStatement.setDouble(2, newRecipe.getNutrition().getFat());
            preparedEditNutritionFactStatement.setDouble(3, newRecipe.getNutrition().getCarbs());
            preparedEditNutritionFactStatement.setDouble(4, newRecipe.getNutrition().getProtein());
            preparedEditNutritionFactStatement.setInt(5, recipe_id);
            preparedEditNutritionFactStatement.execute();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
