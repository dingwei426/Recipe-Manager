package org.example.myproject.RecipeManagement;

import org.example.myproject.DAO.NutritionFactDAO;
import org.example.myproject.DAO.RecipeDAO;
import org.example.myproject.Model.Ingredient;
import org.example.myproject.Model.Recipe;
import org.example.myproject.Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// add ingredients frame to create recipe
public class AddIngredients extends JFrame implements ActionListener {
    private final JPanel addIngredientsPane;
    private final JLabel titleLabel;
    private final JScrollPane ingredientScrollPane;
    private final JButton addIngredientButton;
    private final JButton extraIngredientButton;
    private final JButton previousButton;
    private final User user;
    private final Recipe recipe;
    private final JPanel ingredientPanelContainer; // Container for ingredient panels
    private final ArrayList<IngredientForm> ingredientForms;
    private final JFrame createRecipe;

    public AddIngredients(CreateRecipe createRecipe) {
        this.createRecipe = createRecipe;
        this.user = createRecipe.getUser();
        this.recipe = createRecipe.getRecipe();

        // Use BorderLayout for the main frame
        addIngredientsPane = new JPanel(new BorderLayout());
        setContentPane(addIngredientsPane);
        setTitle("Add Ingredients");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        setVisible(true);

        titleLabel = new JLabel("Set Recipe Ingredients");
        titleLabel.setForeground(new Color(139, 195, 74));
        titleLabel.setFont(new Font("Cooper Black", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addIngredientsPane.add(titleLabel, BorderLayout.NORTH);

        // initialize arraylist to store ingredients info
        ingredientForms = new ArrayList<>();

        Dimension buttonSize = new Dimension(200, 30);

        addIngredientButton = new JButton("Add Ingredients");
        addIngredientButton.setPreferredSize(buttonSize);
        addIngredientButton.setBackground(new Color(139, 195, 74));
        addIngredientButton.addActionListener(this);

        extraIngredientButton = new JButton("Add Extra Column");
        extraIngredientButton.setPreferredSize(buttonSize);
        extraIngredientButton.setBackground(Color.WHITE);
        extraIngredientButton.addActionListener(this);

        previousButton = new JButton("Previous");
        previousButton.setPreferredSize(buttonSize);
        previousButton.setBackground(Color.WHITE);
        previousButton.addActionListener(this);

        // Create a JPanel with GridBagLayout for ingredient panels
        ingredientPanelContainer = new JPanel(new GridBagLayout());

        // Create a JScrollPane and set its preferred size
        ingredientScrollPane = new JScrollPane(ingredientPanelContainer);
        ingredientScrollPane.setPreferredSize(new Dimension(800, 400));
        addIngredientsPane.add(ingredientScrollPane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(previousButton);
        buttonsPanel.add(extraIngredientButton);
        buttonsPanel.add(addIngredientButton);
        addIngredientsPane.add(buttonsPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == extraIngredientButton) {
            IngredientForm ip = new IngredientForm(ingredientForms.size() + 1);
            ingredientForms.add(ip);

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
            c.gridy = ingredientForms.size();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            c.insets = new Insets(5, 5, 5, 5);
            viewportPanel.add(ip, c);
            viewportPanel.revalidate();
        } else if (e.getSource() == addIngredientButton) {
            try {
                Ingredient[] ingredients = new Ingredient[ingredientForms.size()];
                int i = 0;
                for (IngredientForm ip : ingredientForms) {
                    ingredients[i++] = new Ingredient(ip.nameTextField.getText(), ip.unitComboBox.getSelectedItem().toString(), Double.parseDouble(ip.quantityTextField.getText()));
                }
                recipe.setIngredients(ingredients);

                if(RecipeDAO.insertRecipe(user, recipe)){
                    NutritionFactDAO.insertNutritionFact(recipe, recipe.getNutrition());
                    JOptionPane.showMessageDialog(this, "The recipe is added successfully");
                    dispose();
                } else JOptionPane.showMessageDialog(this, "The recipe is duplicated.", "Duplicated Recipe", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException numberFormatException){
                JOptionPane.showMessageDialog(this, "The recipe information is incomplete.", "Recipe Information Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == previousButton) {
            createRecipe.setVisible(true);
            dispose();
        }
    }
}
