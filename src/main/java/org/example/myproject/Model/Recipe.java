package org.example.myproject.Model;

import java.awt.image.BufferedImage;
import java.io.File;

public class Recipe {
    public enum Categories{
        Appetizer, Main_Dish, Side_Dish, Soup, Dessert, Baking
    }

    private String name, steps, equipments, tips;
    private Ingredient[] ingredients;
    private int timeTaken;
    private Categories category;
    private double yield;
    private NutritionFact nutrition;
    private File imageStream;
    private BufferedImage image;

    public Recipe(){}
    public Recipe(String name, String equipments, Ingredient[] ingredients, String steps, int timeTaken, Categories category, double yield, String tips, NutritionFact nutrition, File imageStream) {
        this.name = name;
        this.equipments = equipments;
        this.ingredients = ingredients;
        this.steps = steps;
        this.timeTaken = timeTaken;
        this.category = category;
        this.yield = yield;
        this.tips = tips;
        this.nutrition = nutrition;
        this.imageStream = imageStream;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquipments() {
        return equipments;
    }

    public void setEquipments(String equipments) {
        this.equipments = equipments;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public int getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(int timeTaken) {
        this.timeTaken = timeTaken;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public double getYield() {
        return yield;
    }

    public void setYield(double yield) {
        this.yield = yield;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public NutritionFact getNutrition() {
        return nutrition;
    }

    public void setNutrition(NutritionFact nutrition) {
        this.nutrition = nutrition;
    }

    public File getImageStream() {
        return imageStream;
    }

    public void setImageStream(File imageStream) {
        this.imageStream = imageStream;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
