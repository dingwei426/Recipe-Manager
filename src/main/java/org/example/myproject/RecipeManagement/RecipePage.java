package org.example.myproject.RecipeManagement;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.example.myproject.Authentication.LoginFrame;
import org.example.myproject.Model.Recipe;
import org.example.myproject.Model.User;
import org.example.myproject.TopLevelMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

//recipe page that shows all information of the recipe
public class RecipePage extends JFrame {
    protected JTextField nameTextField, timeTakenTextField, yieldTextField, caloriesTextField, carbsTextField, proteinTextField, fatsTextField;
    protected JTextArea equipmentsTextArea, stepsTextArea, tipsTextArea;
    protected JLabel imageLabel, nameLabel, timeTakenLabel, yieldLabel, categoryLabel, equipmentsLabel, stepsLabel, tipsLabel, caloriesLabel, carbsLabel, proteinLabel, fatsLabel;
    private JPanel recipePage, recipePanel;
    protected IngredientsPanel ingredientsPanel;
    private JTabbedPane dynamicPane;
    protected JComboBox categoryComboBox;
    protected JButton editButton;
    protected JButton addImageButton;
    protected User user;
    protected Recipe recipe;
    protected Image image;
    protected File imageStream;

    public RecipePage(User user, Recipe recipe) {
        this.user = user;
        this.recipe = recipe;

        // set up view recipe frame
        setTitle("Recipe Page");
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setBackground(Color.WHITE);
        setContentPane(recipePage);

        // set frame size and location
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        setSize(new Dimension(screenWidth / 2 + 14, screenHeight));
        setLocation(screenWidth / 2, 0); // Center the frame on the screen

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

        imageStream = recipe.getImageStream();
        try {
            if (recipe.getImageStream() != null) {
                image = ImageIO.read(recipe.getImageStream()).getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(),
                        Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(image);
                imageLabel.setIcon(imageIcon);
            } else {
                //      read cake icon
                InputStream nullRecipeImgInputStream = LoginFrame.class.getClassLoader().getResourceAsStream("nullRecipeImage.png");
                Image nullRecipeImage = null;
                try {
                    nullRecipeImage = ImageIO.read(nullRecipeImgInputStream).getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(),
                            Image.SCALE_SMOOTH);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ImageIcon nullRecipeImageIcon = new ImageIcon(nullRecipeImage);
                imageLabel.setIcon(nullRecipeImageIcon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // set up each text-field
        DecimalFormat decimalFormat = new DecimalFormat("#####"); // Use the pattern that suits your needs
        nameTextField.setText(recipe.getName().toUpperCase());
        timeTakenTextField.setText(String.valueOf(recipe.getTimeTaken()));
        yieldTextField.setText(decimalFormat.format(recipe.getYield()));
        caloriesTextField.setText(decimalFormat.format(recipe.getNutrition().getCalories()));
        carbsTextField.setText(decimalFormat.format(recipe.getNutrition().getCarbs()));
        proteinTextField.setText(decimalFormat.format(recipe.getNutrition().getProtein()));
        fatsTextField.setText(decimalFormat.format(recipe.getNutrition().getFat()));
        equipmentsTextArea.setText(recipe.getEquipments());
        stepsTextArea.setText(recipe.getSteps());
        tipsTextArea.setText(recipe.getTips());
        switch (String.valueOf(recipe.getCategory())) {
            case "Appetizer":
                categoryComboBox.setSelectedIndex(0);
                break;
            case "Main_Dish":
                categoryComboBox.setSelectedIndex(1);
                break;
            case "Side_Dish":
                categoryComboBox.setSelectedIndex(2);
                break;
            case "Soup":
                categoryComboBox.setSelectedIndex(3);
                break;
            case "Dessert":
                categoryComboBox.setSelectedIndex(4);
                break;
            case "Baking":
                categoryComboBox.setSelectedIndex(5);
                break;
        }

        editButton.setVisible(false);
        addImageButton.setVisible(false);
        ingredientsPanel = new IngredientsPanel(recipe.getIngredients());
        dynamicPane.add("Ingredient Information", ingredientsPanel.getIngredientsPanel());
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
        recipePage = new JPanel();
        recipePage.setLayout(new GridLayoutManager(4, 6, new Insets(0, 0, 0, 0), -1, -1));
        recipePage.setForeground(new Color(-7617718));
        imageLabel = new JLabel();
        imageLabel.setText("");
        recipePage.add(imageLabel, new GridConstraints(0, 3, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        recipePage.add(spacer1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        recipePage.add(spacer2, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        recipePage.add(panel1, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 150), new Dimension(-1, 150), new Dimension(-1, 150), 0, false));
        nameLabel = new JLabel();
        nameLabel.setEnabled(true);
        nameLabel.setForeground(new Color(-9462725));
        nameLabel.setText("Name:");
        panel1.add(nameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        timeTakenLabel = new JLabel();
        timeTakenLabel.setForeground(new Color(-9462725));
        timeTakenLabel.setText("Time Taken\n(min):");
        panel1.add(timeTakenLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        yieldLabel = new JLabel();
        yieldLabel.setForeground(new Color(-9462725));
        yieldLabel.setText("Yield:");
        panel1.add(yieldLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        categoryLabel = new JLabel();
        categoryLabel.setForeground(new Color(-9462725));
        categoryLabel.setText("Category:");
        panel1.add(categoryLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameTextField = new JTextField();
        nameTextField.setBackground(new Color(-1));
        nameTextField.setEditable(false);
        nameTextField.setEnabled(true);
        nameTextField.setForeground(new Color(-9462725));
        nameTextField.setText("");
        panel1.add(nameTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), new Dimension(150, -1), new Dimension(150, -1), 0, false));
        timeTakenTextField = new JTextField();
        timeTakenTextField.setBackground(new Color(-1));
        timeTakenTextField.setColumns(10);
        timeTakenTextField.setEditable(false);
        timeTakenTextField.setEnabled(true);
        timeTakenTextField.setForeground(new Color(-9462725));
        panel1.add(timeTakenTextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), new Dimension(150, -1), new Dimension(150, -1), 0, false));
        yieldTextField = new JTextField();
        yieldTextField.setBackground(new Color(-1));
        yieldTextField.setColumns(10);
        yieldTextField.setEditable(false);
        yieldTextField.setEnabled(true);
        yieldTextField.setForeground(new Color(-9462725));
        panel1.add(yieldTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), new Dimension(150, -1), new Dimension(150, -1), 0, false));
        categoryComboBox = new JComboBox();
        categoryComboBox.setBackground(new Color(-1));
        categoryComboBox.setEditable(false);
        categoryComboBox.setEnabled(true);
        categoryComboBox.setForeground(new Color(-7617718));
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Appetizer");
        defaultComboBoxModel1.addElement("Main Dish");
        defaultComboBoxModel1.addElement("Side Dish");
        defaultComboBoxModel1.addElement("Soup");
        defaultComboBoxModel1.addElement("Dessert");
        defaultComboBoxModel1.addElement("Baking");
        categoryComboBox.setModel(defaultComboBoxModel1);
        panel1.add(categoryComboBox, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), new Dimension(150, -1), new Dimension(150, -1), 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        recipePage.add(panel2, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 150), new Dimension(-1, 150), new Dimension(-1, 150), 0, false));
        caloriesLabel = new JLabel();
        caloriesLabel.setEnabled(true);
        caloriesLabel.setForeground(new Color(-9462725));
        caloriesLabel.setText("Calories");
        panel2.add(caloriesLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        caloriesTextField = new JTextField();
        caloriesTextField.setBackground(new Color(-1));
        caloriesTextField.setColumns(10);
        caloriesTextField.setEditable(false);
        caloriesTextField.setEnabled(true);
        caloriesTextField.setForeground(new Color(-9462725));
        caloriesTextField.setText("");
        panel2.add(caloriesTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), new Dimension(150, -1), new Dimension(150, -1), 0, false));
        fatsLabel = new JLabel();
        fatsLabel.setEnabled(true);
        fatsLabel.setForeground(new Color(-9462725));
        fatsLabel.setText("Fats");
        panel2.add(fatsLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fatsTextField = new JTextField();
        fatsTextField.setBackground(new Color(-1));
        fatsTextField.setColumns(10);
        fatsTextField.setEditable(false);
        fatsTextField.setEnabled(true);
        fatsTextField.setForeground(new Color(-9462725));
        fatsTextField.setText("");
        panel2.add(fatsTextField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), new Dimension(150, -1), new Dimension(150, -1), 0, false));
        proteinTextField = new JTextField();
        proteinTextField.setBackground(new Color(-1));
        proteinTextField.setColumns(10);
        proteinTextField.setEditable(false);
        proteinTextField.setEnabled(true);
        proteinTextField.setForeground(new Color(-9462725));
        proteinTextField.setText("");
        panel2.add(proteinTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), new Dimension(150, -1), new Dimension(150, -1), 0, false));
        carbsTextField = new JTextField();
        carbsTextField.setBackground(new Color(-1));
        carbsTextField.setColumns(10);
        carbsTextField.setEditable(false);
        carbsTextField.setEnabled(true);
        carbsTextField.setForeground(new Color(-9462725));
        carbsTextField.setText("");
        panel2.add(carbsTextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), new Dimension(150, -1), new Dimension(150, -1), 0, false));
        proteinLabel = new JLabel();
        proteinLabel.setEnabled(true);
        proteinLabel.setForeground(new Color(-9462725));
        proteinLabel.setText("Protein");
        panel2.add(proteinLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        carbsLabel = new JLabel();
        carbsLabel.setEnabled(true);
        carbsLabel.setForeground(new Color(-9462725));
        carbsLabel.setText("Carbs");
        panel2.add(carbsLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dynamicPane = new JTabbedPane();
        recipePage.add(dynamicPane, new GridConstraints(2, 1, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        dynamicPane.addTab("Recipe Information", panel3);
        recipePanel = new JPanel();
        recipePanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(recipePanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 250), new Dimension(-1, 250), new Dimension(-1, 250), 0, false));
        tipsLabel = new JLabel();
        tipsLabel.setForeground(new Color(-9462725));
        tipsLabel.setText("Tips:");
        recipePanel.add(tipsLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        stepsLabel = new JLabel();
        stepsLabel.setForeground(new Color(-9462725));
        stepsLabel.setText("Steps:");
        recipePanel.add(stepsLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        equipmentsLabel = new JLabel();
        equipmentsLabel.setForeground(new Color(-9462725));
        equipmentsLabel.setText("Equipments:");
        recipePanel.add(equipmentsLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        recipePanel.add(scrollPane1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        equipmentsTextArea = new JTextArea();
        equipmentsTextArea.setEditable(false);
        equipmentsTextArea.setForeground(new Color(-9462725));
        equipmentsTextArea.setLineWrap(true);
        equipmentsTextArea.setText("");
        scrollPane1.setViewportView(equipmentsTextArea);
        final JScrollPane scrollPane2 = new JScrollPane();
        recipePanel.add(scrollPane2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        stepsTextArea = new JTextArea();
        stepsTextArea.setEditable(false);
        stepsTextArea.setForeground(new Color(-9462725));
        stepsTextArea.setLineWrap(true);
        scrollPane2.setViewportView(stepsTextArea);
        final JScrollPane scrollPane3 = new JScrollPane();
        recipePanel.add(scrollPane3, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tipsTextArea = new JTextArea();
        tipsTextArea.setEditable(false);
        tipsTextArea.setForeground(new Color(-9462725));
        tipsTextArea.setLineWrap(true);
        scrollPane3.setViewportView(tipsTextArea);
        editButton = new JButton();
        editButton.setBackground(new Color(-7617718));
        editButton.setForeground(new Color(-1));
        editButton.setText("Edit");
        recipePage.add(editButton, new GridConstraints(3, 3, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(300, -1), new Dimension(300, -1), new Dimension(300, -1), 1, false));
        addImageButton = new JButton();
        addImageButton.setBackground(new Color(-1));
        addImageButton.setForeground(new Color(-7617718));
        addImageButton.setText("Select Recipe Image (Optional)");
        recipePage.add(addImageButton, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(300, -1), new Dimension(300, -1), new Dimension(300, -1), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return recipePage;
    }
}
