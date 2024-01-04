package org.example.myproject.RecipeManagement;

import org.example.myproject.DAO.NutritionFactDAO;
import org.example.myproject.DAO.RecipeDAO;
import org.example.myproject.Model.Ingredient;
import org.example.myproject.Model.NutritionFact;
import org.example.myproject.Model.Recipe;
import org.example.myproject.Model.User;
import org.example.myproject.TopLevelMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

//Page to edit the recipe
public class EditRecipePage extends RecipePage implements ActionListener {
    public EditRecipePage(User user, Recipe recipe) {
        super(user, recipe);
        setTitle("Edit Recipe");
        JTextField[] tfs = {nameTextField, timeTakenTextField, yieldTextField, caloriesTextField, carbsTextField, proteinTextField, fatsTextField};
        JTextArea[] tas = {equipmentsTextArea, stepsTextArea, tipsTextArea};

        editButton.addActionListener(this);
        editButton.setVisible(true);
        addImageButton.addActionListener(this);
        addImageButton.setVisible(true);

        for(JTextField tf: tfs){
            tf.setEditable(true);
        }

        for(JTextArea ta: tas){
            ta.setEditable(true);
        }

        ingredientsPanel.setColumnsEditable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton){
            LinkedList<Recipe> recipes = RecipeDAO.getRecipeList(user);
            int duplicateNums = 0;
            if (recipes != null) {
                for(Recipe recipe1: recipes){
                    if(recipe1.getName().equals(nameTextField.getText()))
                        duplicateNums++;
                    if(duplicateNums > 1){
                        JOptionPane.showMessageDialog(this, "The recipe is duplicated.");
                        return;
                    }
                }
            }

            try {
                Ingredient[] ingredients = new Ingredient[ingredientsPanel.ingredientColumns.size()];
                for (int i = 0; i < ingredientsPanel.ingredientColumns.size(); i++) {
                    ingredients[i] = new Ingredient(ingredientsPanel.ingredientColumns.get(i).nameTextField.getText(),
                            (String) ingredientsPanel.ingredientColumns.get(i).unitComboBox.getSelectedItem(),
                            Double.parseDouble(ingredientsPanel.ingredientColumns.get(i).quantityTextField.getText()));
                }

                NutritionFact nutrition = new NutritionFact(Double.parseDouble(caloriesTextField.getText()), Double.parseDouble(fatsTextField.getText()), Double.parseDouble(carbsTextField.getText()), Double.parseDouble(proteinTextField.getText()));
                String category = Objects.requireNonNull(categoryComboBox.getSelectedItem()).toString().replace(' ', '_');
                Recipe newRecipe = new Recipe(nameTextField.getText(), equipmentsTextArea.getText(), ingredients, stepsTextArea.getText(), Integer.parseInt(timeTakenTextField.getText()), Recipe.Categories.valueOf(category), Double.parseDouble(yieldTextField.getText()), tipsTextArea.getText(),
                        nutrition, imageStream);
                RecipeDAO.editRecipe(RecipeDAO.getRecipeID(user, recipe), newRecipe);
                NutritionFactDAO.editNutritionFact(RecipeDAO.getRecipeID(user, recipe), newRecipe);
                RecipeDAO.editIngredients(RecipeDAO.getRecipeID(user, recipe), newRecipe);
                dispose();
                JOptionPane.showMessageDialog(TopLevelMenu.viewRecipeForm, "The recipe is edited successfully.");
                TopLevelMenu.viewRecipeForm.setupRecipeColumns();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "The recipe is not edited successfully.");
            }
        } else if (e.getSource() == addImageButton) {
            JFileChooser fileChooser = new JFileChooser();

            int result = fileChooser.showOpenDialog(EditRecipePage.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                try {
                    // Read the selected image file and set it to the imageLabel
                    image = ImageIO.read(selectedFile).getScaledInstance(
                            imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);

                    ImageIcon imageIcon = new ImageIcon(image);
                    imageLabel.setIcon(imageIcon);

                    // Store the selected image file in imageStream
                    imageStream = selectedFile;
                } catch (IOException | NullPointerException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error loading the selected image.",
                            "Image Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
