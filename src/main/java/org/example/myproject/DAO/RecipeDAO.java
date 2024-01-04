package org.example.myproject.DAO;

import org.example.myproject.Model.Ingredient;
import org.example.myproject.Model.NutritionFact;
import org.example.myproject.Model.Recipe;
import org.example.myproject.Model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class RecipeDAO {
    public static boolean insertRecipe(User user, Recipe recipe) {
        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String dbUser = "recipemanager";
        String dbPass = "manager1234";

        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            String getUserIDSql = "SELECT user_id FROM user WHERE username = ?";
            PreparedStatement preparedUserIDStatement = connection.prepareCall(getUserIDSql);
            preparedUserIDStatement.setString(1, user.getUsername());
            preparedUserIDStatement.execute();
            ResultSet userIDSet = preparedUserIDStatement.getResultSet();
            if (!userIDSet.next())
                return false;
            int userID = userIDSet.getInt("user_id");

            String getRecipeSql = "SELECT name FROM recipe WHERE fk_recipe_user = ? AND name = ?";
            PreparedStatement preparedRecipeStatement = connection.prepareCall(getRecipeSql);
            preparedRecipeStatement.setInt(1, userID);
            preparedRecipeStatement.setString(2, recipe.getName());
            preparedRecipeStatement.execute();
            ResultSet recipeSet = preparedRecipeStatement.getResultSet();
            if (recipeSet.next())
                return false;

            //insert new account info into db
            String storedProcedureCall = "{call InsertRecipe(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            PreparedStatement preparedStatement = connection.prepareCall(storedProcedureCall);
            preparedStatement.setString(1, recipe.getName());
            preparedStatement.setString(2, recipe.getSteps());
            preparedStatement.setString(3, recipe.getEquipments());
            preparedStatement.setInt(4, recipe.getTimeTaken());
            preparedStatement.setString(5, String.valueOf(recipe.getCategory()));
            preparedStatement.setDouble(6, recipe.getYield());
            preparedStatement.setString(7, recipe.getTips());
            FileInputStream imageStream = new FileInputStream(recipe.getImageStream());
            preparedStatement.setBinaryStream(8, imageStream, (int)recipe.getImageStream().length());
            preparedStatement.setInt(9, userID);
            preparedStatement.execute();
            insertIngredient(recipe, recipe.getIngredients());

            connection.close();
            preparedStatement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void insertIngredient(Recipe recipe, Ingredient[] ingredients) {
        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String dbUser = "recipemanager";
        String dbPass = "manager1234";

        for (Ingredient ingredient : ingredients) {
            try {
                Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);

                String getUserIDSql = "SELECT recipe_id FROM recipe WHERE name = ?";
                PreparedStatement preparedUserIDStatement = connection.prepareCall(getUserIDSql);
                preparedUserIDStatement.setString(1, recipe.getName());
                preparedUserIDStatement.execute();
                ResultSet recipeIDSet = preparedUserIDStatement.getResultSet();
                if (!recipeIDSet.next())
                    return;
                int recipeID = recipeIDSet.getInt("recipe_id");

                String storedProcedureCall = "{call InsertIngredient(?, ?, ?, ?)}";
                PreparedStatement preparedStatement = connection.prepareCall(storedProcedureCall);
                preparedStatement.setString(1, ingredient.getName());
                preparedStatement.setString(2, ingredient.getUnit());
                preparedStatement.setDouble(3, ingredient.getQuantity());
                preparedStatement.setInt(4, recipeID);
                preparedStatement.execute();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public static LinkedList<Recipe> getRecipeList(User user) {
        LinkedList<Recipe> recipeLinkedList = new LinkedList<>();

        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String dbUser = "recipemanager";
        String dbPass = "manager1234";

        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            String getUserIDSql = "SELECT user_id FROM user WHERE username = ?";
            PreparedStatement preparedUserIDStatement = connection.prepareCall(getUserIDSql);
            preparedUserIDStatement.setString(1, user.getUsername());
            preparedUserIDStatement.execute();
            ResultSet userIDSet = preparedUserIDStatement.getResultSet();
            if (!userIDSet.next())
                return null;
            int userID = userIDSet.getInt("user_id");

            String getRecipesSql = "SELECT * FROM recipe WHERE fk_recipe_user = ?";
            PreparedStatement preparedRecipesStatement = connection.prepareCall(getRecipesSql);
            preparedRecipesStatement.setInt(1, userID);
            preparedRecipesStatement.execute();
            ResultSet recipeResultSet = preparedRecipesStatement.getResultSet();
            while (recipeResultSet.next()) {
                int recipe_id = recipeResultSet.getInt("recipe_id");
                String getIngredientsSql = "SELECT * FROM ingredient WHERE fk_ingredient_recipe = ?";
                PreparedStatement preparedIngredientsStatement = connection.prepareStatement(getIngredientsSql);
                preparedIngredientsStatement.setInt(1, recipe_id);
                preparedIngredientsStatement.execute();
                ResultSet ingredientResultSet = preparedIngredientsStatement.getResultSet();

                Ingredient[] ingredients = new Ingredient[20];
                int i = 0;
                while (ingredientResultSet.next()) {
                    ingredients[i++] = new Ingredient(
                            ingredientResultSet.getString("name"),
                            ingredientResultSet.getString("unit"),
                            ingredientResultSet.getDouble("quantity")
                    );
                }

                String getNutritionFactsSql = "SELECT * FROM nutrition_fact WHERE fk_nutrition_fact_recipe = ?";
                PreparedStatement preparedNutritionFactsStatement = connection.prepareCall(getNutritionFactsSql);
                preparedNutritionFactsStatement.setInt(1, recipe_id);
                preparedNutritionFactsStatement.execute();
                ResultSet nutritionFactResultSet = preparedNutritionFactsStatement.getResultSet();
                NutritionFact nutritionFact = null;
                while (nutritionFactResultSet.next())
                    nutritionFact = new NutritionFact(nutritionFactResultSet.getDouble("calories"), nutritionFactResultSet.getDouble("fat"), nutritionFactResultSet.getDouble("carbs"), nutritionFactResultSet.getDouble("protein"));

                // Get the BLOB data
                byte[] blobBytes = recipeResultSet.getBytes("image");
                File file = null;

                if (blobBytes != null) {
                    // Generate a unique filename using a timestamp
                    String timestamp = String.valueOf(System.currentTimeMillis());
                        file = new File("src/main/java/recipeImageCache/" + "output_" + timestamp + ".jpg");
                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        fos.write(blobBytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                recipeLinkedList.add(new Recipe(recipeResultSet.getString("name"),
                        recipeResultSet.getString("equipments"),
                        ingredients, recipeResultSet.getString("steps"),
                        recipeResultSet.getInt("time_taken"),
                        Recipe.Categories.valueOf(recipeResultSet.getString("category")),
                        recipeResultSet.getDouble("yield"),
                        recipeResultSet.getString("tips"),
                        nutritionFact, file));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipeLinkedList;
    }

    public static int getRecipeID(User user, Recipe recipe){
        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String dbUser = "recipemanager";
        String dbPass = "manager1234";

        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            String getUserIDSql = "SELECT user_id FROM user WHERE username = ?";
            PreparedStatement preparedUserIDStatement = connection.prepareCall(getUserIDSql);
            preparedUserIDStatement.setString(1, user.getUsername());
            preparedUserIDStatement.execute();
            ResultSet userIDSet = preparedUserIDStatement.getResultSet();
            if (!userIDSet.next())
                return -1;
            int userID = userIDSet.getInt("user_id");

            String getRecipeSql = "SELECT recipe_id FROM recipe WHERE fk_recipe_user = ? AND name = ?";
            PreparedStatement preparedRecipeIDStatement = connection.prepareCall(getRecipeSql);
            preparedRecipeIDStatement.setInt(1, userID);
            preparedRecipeIDStatement.setString(2, recipe.getName());
            preparedRecipeIDStatement.execute();
            ResultSet recipeSet = preparedRecipeIDStatement.getResultSet();
            if (!recipeSet.next())
                return -1;
            return recipeSet.getInt("recipe_id");
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static void deleleRecipe(int recipe_id){
        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String dbUser = "recipemanager";
        String dbPass = "manager1234";

        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            String storedDeleteRecipeProcedure = "{call DeleteRecipe(?)}";
            PreparedStatement preparedStatement = connection.prepareCall(storedDeleteRecipeProcedure);
            preparedStatement.setInt(1, recipe_id);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean editRecipe(int recipe_id, Recipe newRecipe) {
        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String dbUser = "recipemanager";
        String dbPass = "manager1234";

        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            String editRecipeSql = "UPDATE recipe " +
                    "SET name = ?, steps = ?, equipments = ?, time_taken = ?, category = ?, yield = ?, tips = ?, image = ? " +
                    "WHERE recipe_id = ?";
            PreparedStatement preparedEditRecipeStatement = connection.prepareCall(editRecipeSql);
            preparedEditRecipeStatement.setString(1, newRecipe.getName());
            preparedEditRecipeStatement.setString(2, newRecipe.getSteps());
            preparedEditRecipeStatement.setString(3, newRecipe.getEquipments());
            preparedEditRecipeStatement.setInt(4, newRecipe.getTimeTaken());
            preparedEditRecipeStatement.setString(5, String.valueOf(newRecipe.getCategory()));
            preparedEditRecipeStatement.setDouble(6, newRecipe.getYield());
            preparedEditRecipeStatement.setString(7, newRecipe.getTips());
            FileInputStream imageStream = new FileInputStream(newRecipe.getImageStream());
            preparedEditRecipeStatement.setBinaryStream(8, imageStream, (int)newRecipe.getImageStream().length());
            preparedEditRecipeStatement.setInt(9, recipe_id);
            preparedEditRecipeStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void editIngredients(int recipe_id, Recipe newRecipe){
        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String dbUser = "recipemanager";
        String dbPass = "manager1234";

        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            String storedDeleteIngredientsProcedure = "{call DeleteIngredients(?)}";
            PreparedStatement preparedDeleteIngredientsStatement = connection.prepareCall(storedDeleteIngredientsProcedure);
            preparedDeleteIngredientsStatement.setInt(1, recipe_id);
            preparedDeleteIngredientsStatement.execute();

            String storedInsertIngredientsProcedure = "{call InsertIngredient(?,?,?,?)}";
            PreparedStatement preparedInsertIngredientsStatement = connection.prepareCall(storedInsertIngredientsProcedure);
            for(Ingredient ingredient: newRecipe.getIngredients()){
                preparedInsertIngredientsStatement.setString(1,ingredient.getName());
                preparedInsertIngredientsStatement.setString(2,ingredient.getUnit());
                preparedInsertIngredientsStatement.setDouble(3,ingredient.getQuantity());
                preparedInsertIngredientsStatement.setInt(4,recipe_id);
                preparedInsertIngredientsStatement.execute();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
