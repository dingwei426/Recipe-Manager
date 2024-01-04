package org.example.myproject.RecipeManagement;

import javax.swing.*;
import java.awt.*;

// panel to fill in each ingredient
public class IngredientForm extends JPanel {
    private final JLabel title;
    private final JLabel ingredientNameLabel;
    private final JLabel ingredientQuantityLabel;
    private final JLabel ingredientUnitLabel;
    public JTextField nameTextField;
    public JTextField quantityTextField;
    public JComboBox<String> unitComboBox;

    public IngredientForm(int num) {
        setLayout(new GridBagLayout());

        title = new JLabel("Ingredient " + num);
        title.setForeground(new Color(111, 156, 59));
        title.setFont(title.getFont().deriveFont(Font.BOLD));

        ingredientNameLabel = new JLabel("Name");
        ingredientNameLabel.setForeground(new Color(111, 156, 59));

        ingredientQuantityLabel = new JLabel("Quantity");
        ingredientQuantityLabel.setForeground(new Color(111, 156, 59));

        ingredientUnitLabel = new JLabel("Unit");
        ingredientUnitLabel.setForeground(new Color(111, 156, 59));

        nameTextField = new JTextField();
        quantityTextField = new JTextField();
        String[] units = new String[]{"grams (g)", "kilograms (kg)", "litre (L)", "millilitre (mL)", "ounces (oz)", "pieces", "slides"};
        unitComboBox = new JComboBox<>(units);
        unitComboBox.setBackground(Color.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        add(title, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE; // Prevent horizontal expansion
        c.weightx = 0.0; // Do not take up extra horizontal space
        add(ingredientNameLabel, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE; // Prevent horizontal expansion
        c.weightx = 0.0; // Do not take up extra horizontal space
        add(ingredientQuantityLabel, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE; // Prevent horizontal expansion
        c.weightx = 0.0; // Do not take up extra horizontal space
        add(ingredientUnitLabel, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0; // Allow horizontal expansion for input fields
        c.insets = new Insets(5, 5, 5, 5);
        add(nameTextField, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0; // Allow horizontal expansion for input fields
        c.insets = new Insets(5, 5, 5, 5);
        add(quantityTextField, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0; // Allow horizontal expansion for input fields
        c.insets = new Insets(5, 5, 5, 5);
        add(unitComboBox, c);
    }
}