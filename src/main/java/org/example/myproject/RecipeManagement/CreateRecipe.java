package org.example.myproject.RecipeManagement;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.example.myproject.Model.NutritionFact;
import org.example.myproject.Model.Recipe;
import org.example.myproject.Model.User;
import org.example.myproject.TopLevelMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Objects;

// create recipe frame that allow user to add new recipe to the database
public class CreateRecipe extends JFrame implements ActionListener {
    private JTextField fatsTextField, proteinTextField, carbsTextField, caloriesTextField, nameTextField, timeTakenTextField, yieldTextField;
    private JComboBox categoryComboBox;
    private JTextArea stepsTextArea, tipsTextArea, equipmentsTextArea;
    private JLabel imageLabel, fatsLabel, proteinLabel, carbsLabel, caloriesLabel, nutritionFactsLabel, equipmentsLabel, titleLabel, nameLabel, categoryLabel, timeTakenLabel, yieldLabel, stepsLabel, tipsLabel;
    private JPanel CreateRecipePane;
    private JButton mainMenuButton, setIngredientButton, addImageButton;
    private User user;
    private Recipe recipe;
    private final JFileChooser fileChooser;
    private File imageStream;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public CreateRecipe(User user) {
        // set up create recipe frame
        setContentPane(CreateRecipePane);
        setTitle("Create Recipe");
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(600, 700));
        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(false);

        this.user = user;

        // add action listener
        fileChooser = new JFileChooser();
        mainMenuButton.addActionListener(this);
        addImageButton.addActionListener(this);
        setIngredientButton.addActionListener(this);

        //  set up each element attributes
        categoryComboBox.setBackground(Color.WHITE);

        equipmentsTextArea.setLineWrap(true);
        equipmentsTextArea.setWrapStyleWord(true);

        tipsTextArea.setLineWrap(true);
        tipsTextArea.setWrapStyleWord(true);

        stepsTextArea.setLineWrap(true);
        stepsTextArea.setWrapStyleWord(true);

        //  read cake icon
        InputStream cakeImgInputStream = TopLevelMenu.class.getClassLoader().getResourceAsStream("loginIconCake.png");
        Image cakeImage = null;
        try {
            cakeImage = ImageIO.read(cakeImgInputStream).getScaledInstance(30, 30,
                    Image.SCALE_SMOOTH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon imageCakeIcon = new ImageIcon(cakeImage);

        //  set icon of the window
        setIconImage(imageCakeIcon.getImage());

        imageLabel.setSize(150, 120);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);

        InputStream nullImgInputStream = TopLevelMenu.class.getClassLoader().getResourceAsStream("nullRecipeImage.png");
        Image nullRecipeImage = null;
        try {
            nullRecipeImage = ImageIO.read(nullImgInputStream).getScaledInstance(100, 100,
                    Image.SCALE_SMOOTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageIcon nullRecipeImageIcon = new ImageIcon(nullRecipeImage);
        imageLabel.setIcon(nullRecipeImageIcon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == setIngredientButton) {
            try {
                NutritionFact nutritionFact = new NutritionFact(Double.parseDouble(caloriesTextField.getText()), Double.parseDouble(fatsTextField.getText()), Double.parseDouble(carbsTextField.getText()), Double.parseDouble(proteinTextField.getText()));
                recipe = new Recipe();
                recipe.setName(nameTextField.getText());
                recipe.setEquipments(equipmentsTextArea.getText());
                recipe.setSteps(stepsTextArea.getText());
                recipe.setTimeTaken(Integer.parseInt(timeTakenTextField.getText()));

                String category = Objects.toString(categoryComboBox.getSelectedItem(), "");
                switch (category) {
                    case "Appetizer":
                        recipe.setCategory(Recipe.Categories.Appetizer);
                        break;
                    case "Main Dish":
                        recipe.setCategory(Recipe.Categories.Main_Dish);
                        break;
                    case "Side Dish":
                        recipe.setCategory(Recipe.Categories.Side_Dish);
                        break;
                    case "Dessert":
                        recipe.setCategory(Recipe.Categories.Dessert);
                        break;
                    case "Soup":
                        recipe.setCategory(Recipe.Categories.Soup);
                        break;
                    case "Baking":
                        recipe.setCategory(Recipe.Categories.Baking);
                        break;
                }

                if (!yieldTextField.getText().isEmpty())
                    recipe.setYield(Double.parseDouble(yieldTextField.getText()));
                if (!tipsTextArea.getText().isEmpty())
                    recipe.setTips(tipsTextArea.getText());
                recipe.setNutrition(nutritionFact);
                if (imageStream != null) {
                    recipe.setImageStream(imageStream);
                    new AddIngredients(this);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "The recipe image is not inserted.",
                            "Image Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(this, "The recipe is incomplete.", "Recipe Input Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == mainMenuButton) {
            dispose();
        } else if (e.getSource() == addImageButton) {
            JFileChooser fileChooser = new JFileChooser();

            int result = fileChooser.showOpenDialog(CreateRecipe.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                try {
                    // Read the selected image file and set it to the imageLabel
                    Image image = ImageIO.read(selectedFile).getScaledInstance(
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        CreateRecipePane = new JPanel();
        CreateRecipePane.setLayout(new GridLayoutManager(3, 5, new Insets(0, 0, 0, 0), -1, -1));
        final Spacer spacer1 = new Spacer();
        CreateRecipePane.add(spacer1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        CreateRecipePane.add(panel1, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        titleLabel = new JLabel();
        Font titleLabelFont = this.$$$getFont$$$("Cooper Black", Font.BOLD, 20, titleLabel.getFont());
        if (titleLabelFont != null) titleLabel.setFont(titleLabelFont);
        titleLabel.setForeground(new Color(-7617718));
        titleLabel.setText("Create Recipe");
        panel1.add(titleLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        nameLabel = new JLabel();
        nameLabel.setEnabled(true);
        Font nameLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, nameLabel.getFont());
        if (nameLabelFont != null) nameLabel.setFont(nameLabelFont);
        nameLabel.setForeground(new Color(-9462725));
        nameLabel.setText("Name");
        panel4.add(nameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        categoryLabel = new JLabel();
        Font categoryLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, categoryLabel.getFont());
        if (categoryLabelFont != null) categoryLabel.setFont(categoryLabelFont);
        categoryLabel.setForeground(new Color(-9462725));
        categoryLabel.setText("Category");
        panel4.add(categoryLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        nameTextField = new JTextField();
        panel5.add(nameTextField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        categoryComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Appetizer");
        defaultComboBoxModel1.addElement("Main Dish");
        defaultComboBoxModel1.addElement("Side Dish");
        defaultComboBoxModel1.addElement("Soup");
        defaultComboBoxModel1.addElement("Dessert");
        defaultComboBoxModel1.addElement("Baking");
        categoryComboBox.setModel(defaultComboBoxModel1);
        panel5.add(categoryComboBox, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel6, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel6.add(panel7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        timeTakenLabel = new JLabel();
        Font timeTakenLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, timeTakenLabel.getFont());
        if (timeTakenLabelFont != null) timeTakenLabel.setFont(timeTakenLabelFont);
        timeTakenLabel.setForeground(new Color(-9462725));
        timeTakenLabel.setText("Time Taken (minutes)");
        panel7.add(timeTakenLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        yieldLabel = new JLabel();
        Font yieldLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, yieldLabel.getFont());
        if (yieldLabelFont != null) yieldLabel.setFont(yieldLabelFont);
        yieldLabel.setForeground(new Color(-9462725));
        yieldLabel.setText("Yield");
        panel7.add(yieldLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel6.add(panel8, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        timeTakenTextField = new JTextField();
        panel8.add(timeTakenTextField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        yieldTextField = new JTextField();
        panel8.add(yieldTextField, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(7, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel9, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        stepsLabel = new JLabel();
        Font stepsLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, stepsLabel.getFont());
        if (stepsLabelFont != null) stepsLabel.setFont(stepsLabelFont);
        stepsLabel.setForeground(new Color(-9462725));
        stepsLabel.setText("Steps");
        panel9.add(stepsLabel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tipsLabel = new JLabel();
        Font tipsLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, tipsLabel.getFont());
        if (tipsLabelFont != null) tipsLabel.setFont(tipsLabelFont);
        tipsLabel.setForeground(new Color(-9462725));
        tipsLabel.setText("Tips");
        panel9.add(tipsLabel, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        equipmentsLabel = new JLabel();
        Font equipmentsLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, equipmentsLabel.getFont());
        if (equipmentsLabelFont != null) equipmentsLabel.setFont(equipmentsLabelFont);
        equipmentsLabel.setForeground(new Color(-9462725));
        equipmentsLabel.setText("Equipments");
        panel9.add(equipmentsLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nutritionFactsLabel = new JLabel();
        Font nutritionFactsLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, nutritionFactsLabel.getFont());
        if (nutritionFactsLabelFont != null) nutritionFactsLabel.setFont(nutritionFactsLabelFont);
        nutritionFactsLabel.setForeground(new Color(-9462725));
        nutritionFactsLabel.setText("Nutrition Facts");
        panel9.add(nutritionFactsLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel9.add(panel10, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel10.add(panel11, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel11.add(panel12, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        caloriesLabel = new JLabel();
        Font caloriesLabelFont = this.$$$getFont$$$(null, -1, -1, caloriesLabel.getFont());
        if (caloriesLabelFont != null) caloriesLabel.setFont(caloriesLabelFont);
        caloriesLabel.setForeground(new Color(-9462725));
        caloriesLabel.setText("Calories");
        panel12.add(caloriesLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        carbsLabel = new JLabel();
        Font carbsLabelFont = this.$$$getFont$$$(null, -1, -1, carbsLabel.getFont());
        if (carbsLabelFont != null) carbsLabel.setFont(carbsLabelFont);
        carbsLabel.setForeground(new Color(-9462725));
        carbsLabel.setText("Carbs");
        panel12.add(carbsLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel13 = new JPanel();
        panel13.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel11.add(panel13, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        caloriesTextField = new JTextField();
        panel13.add(caloriesTextField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        carbsTextField = new JTextField();
        panel13.add(carbsTextField, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel14 = new JPanel();
        panel14.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel10.add(panel14, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel15 = new JPanel();
        panel15.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel14.add(panel15, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        proteinLabel = new JLabel();
        Font proteinLabelFont = this.$$$getFont$$$(null, -1, -1, proteinLabel.getFont());
        if (proteinLabelFont != null) proteinLabel.setFont(proteinLabelFont);
        proteinLabel.setForeground(new Color(-9462725));
        proteinLabel.setText("Protein");
        panel15.add(proteinLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fatsLabel = new JLabel();
        Font fatsLabelFont = this.$$$getFont$$$(null, -1, -1, fatsLabel.getFont());
        if (fatsLabelFont != null) fatsLabel.setFont(fatsLabelFont);
        fatsLabel.setForeground(new Color(-9462725));
        fatsLabel.setText("Fats");
        panel15.add(fatsLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel16 = new JPanel();
        panel16.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel14.add(panel16, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        proteinTextField = new JTextField();
        panel16.add(proteinTextField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        fatsTextField = new JTextField();
        panel16.add(fatsTextField, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panel9.add(separator1, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JSeparator separator2 = new JSeparator();
        panel9.add(separator2, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        addImageButton = new JButton();
        addImageButton.setBackground(new Color(-1));
        addImageButton.setForeground(new Color(-7617718));
        addImageButton.setText("Select Recipe Image");
        panel9.add(addImageButton, new GridConstraints(6, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel9.add(scrollPane1, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        equipmentsTextArea = new JTextArea();
        equipmentsTextArea.setRows(5);
        equipmentsTextArea.setText("");
        scrollPane1.setViewportView(equipmentsTextArea);
        final JScrollPane scrollPane2 = new JScrollPane();
        panel9.add(scrollPane2, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        stepsTextArea = new JTextArea();
        stepsTextArea.setRows(5);
        stepsTextArea.setText("");
        scrollPane2.setViewportView(stepsTextArea);
        final JScrollPane scrollPane3 = new JScrollPane();
        panel9.add(scrollPane3, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tipsTextArea = new JTextArea();
        tipsTextArea.setRows(5);
        scrollPane3.setViewportView(tipsTextArea);
        imageLabel = new JLabel();
        imageLabel.setText("");
        panel1.add(imageLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel17 = new JPanel();
        panel17.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        CreateRecipePane.add(panel17, new GridConstraints(2, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        mainMenuButton = new JButton();
        mainMenuButton.setBackground(new Color(-1));
        mainMenuButton.setForeground(new Color(-7617718));
        mainMenuButton.setText("Main Menu");
        panel17.add(mainMenuButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        setIngredientButton = new JButton();
        setIngredientButton.setBackground(new Color(-7617718));
        setIngredientButton.setForeground(new Color(-1));
        setIngredientButton.setText("Set Ingredients");
        panel17.add(setIngredientButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        CreateRecipePane.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        CreateRecipePane.add(spacer3, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return CreateRecipePane;
    }
}

