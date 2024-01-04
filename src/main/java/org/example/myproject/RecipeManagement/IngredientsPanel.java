package org.example.myproject.RecipeManagement;

import org.example.myproject.Model.Ingredient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// panel that shows ingredients information within the ViewRecipe frame
public class IngredientsPanel implements ActionListener {
    protected ArrayList<IngredientColumn> ingredientColumns;
    private final JButton addIngredientButton;
    public JScrollPane ingredientScrollPane;
    public JPanel ingredientsPanel;

    public IngredientsPanel(Ingredient[] ingredients) {
        // Create a JPanel with GridBagLayout for ingredient panels
        ingredientScrollPane = new JScrollPane();
        ingredientColumns = new ArrayList<>();
        ingredientsPanel = new JPanel(new BorderLayout());

        addIngredientButton = new JButton("Add Ingredients");
        addIngredientButton.setPreferredSize(new Dimension(200,30));
        addIngredientButton.setBackground(new Color(139, 195, 74));
        addIngredientButton.addActionListener(this);
        addIngredientButton.setVisible(false);

        // Get the viewport of the scroll pane
        JViewport viewport = ingredientScrollPane.getViewport();

        // Get the view (content) of the viewport
        JPanel viewportPanel = (JPanel) viewport.getView();

        if (viewportPanel == null) {
            viewportPanel = new JPanel(new GridBagLayout());
            viewport.setView(viewportPanel);
        }

        int gridY = 0;
        for (Ingredient ingredient : ingredients) {
            if (ingredient != null) {
                IngredientColumn ingredientColumn = new IngredientColumn(ingredient, ingredientColumns.size() + 1);
                ingredientColumns.add(ingredientColumn);

                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = gridY;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.weightx = 1.0;
                c.insets = new Insets(5, 5, 5, 5);
                viewportPanel.add(ingredientColumn.getIngredientColumn(), c);

                gridY++;
            }
        }

        // Create a JScrollPane and set its preferred size
        ingredientScrollPane.setViewportView(viewportPanel);
        viewportPanel.revalidate();
        ingredientsPanel.add(ingredientScrollPane, BorderLayout.CENTER);
        ingredientsPanel.add(addIngredientButton, BorderLayout.SOUTH);
    }

    public void setColumnsEditable(){
        for(IngredientColumn ingredientColumn: ingredientColumns){
            ingredientColumn.setEditable();
        }
        addIngredientButton.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addIngredientButton) {
            IngredientColumn ingredientColumn = new IngredientColumn(ingredientColumns.size() + 1);
            ingredientColumns.add(ingredientColumn);

            // Get the viewport of the scroll pane
            JViewport viewport = ingredientScrollPane.getViewport();

            // Get the view (content) of the viewport
            JPanel viewportPanel = (JPanel) viewport.getView();

            if (viewportPanel == null) {
                viewportPanel = new JPanel(new GridBagLayout());
                viewport.setView(viewportPanel);
            }

            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = ingredientColumns.size() + 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            c.insets = new Insets(5, 5, 5, 5);
            viewportPanel.add(ingredientColumn.getIngredientColumn(), c);
            viewportPanel.revalidate();
            setColumnsEditable();
        }
    }

    public JPanel getIngredientsPanel() {
        return ingredientsPanel;
    }
}
