package org.example.myproject.RecipeManagement;

import org.example.myproject.DAO.RecipeDAO;
import org.example.myproject.Model.Recipe;
import org.example.myproject.Model.User;
import org.example.myproject.TopLevelMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

// recipeColumn that contain brief information of the recipe and has view, edit, and delete button to perform on the recipe
public class RecipeColumn extends JPanel implements ActionListener {
    private final JLabel imageLabel;
    private JLabel recipeNameLabel;
    private Image image;
    private final JButton viewButton;
    private final JButton editButton;
    private final JButton deleteButton;
    private final Recipe recipe;
    private final User user;

    public RecipeColumn(User user, Recipe recipe) {
        this.recipe = recipe;
        this.user = user;

        // set up recipe column panel
        Color backgroundColor = new Color(250, 255, 237 );
        setPreferredSize(new Dimension(400, 180));
        setBackground(backgroundColor);
        setLayout(new GridLayout(1,2));
        Border border = new LineBorder(Color.GRAY, 1);
        setBorder(border);

        // set up each label
        imageLabel = new JLabel();
        imageLabel.setSize(150, 150);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);

        Font nameFont = new Font("Segoe Print", Font.BOLD, 18);
        recipeNameLabel = new JLabel();
        recipeNameLabel = new JLabel(recipe.getName().toUpperCase());
        recipeNameLabel.setFont(nameFont);
        recipeNameLabel.setForeground(new Color(111, 156, 59));
        recipeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        recipeNameLabel.setVerticalAlignment(SwingConstants.BOTTOM);

        // set up each button
        viewButton = new JButton();
        viewButton.setSize(20,20);
        viewButton.addActionListener(this);
        viewButton.setBackground(Color.WHITE);
        viewButton.setToolTipText("View Recipe");

        editButton = new JButton();
        editButton.setSize(20,20);
        editButton.addActionListener(this);
        editButton.setBackground(Color.WHITE);
        editButton.setToolTipText("Edit Recipe");

        deleteButton = new JButton();
        deleteButton.setSize(20,20);
        deleteButton.addActionListener(this);
        deleteButton.setBackground(Color.WHITE);
        deleteButton.setToolTipText("Delete Recipe");

        try {
            if (recipe.getImageStream() != null) {
                image = ImageIO.read(recipe.getImageStream()).getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(),
                        Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(image);
                imageLabel.setIcon(imageIcon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // load image from resources directory
        InputStream viewInputStream = RecipeColumn.class.getClassLoader().getResourceAsStream("viewButtonIcon.png");
        Image viewImage = null;
        InputStream editInputStream = RecipeColumn.class.getClassLoader().getResourceAsStream("editButtonIcon.png");
        Image editImage = null;
        InputStream deleteInputStream = RecipeColumn.class.getClassLoader().getResourceAsStream("deleteButtonIcon.png");
        Image deleteImage = null;
        try {
            viewImage = ImageIO.read(viewInputStream).getScaledInstance(viewButton.getWidth(), viewButton.getHeight(),
                    Image.SCALE_SMOOTH);
            editImage = ImageIO.read(editInputStream).getScaledInstance(editButton.getWidth(), editButton.getHeight(),
                    Image.SCALE_SMOOTH);
            deleteImage = ImageIO.read(deleteInputStream).getScaledInstance(deleteButton.getWidth(), deleteButton.getHeight(),
                    Image.SCALE_SMOOTH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // set image for each icon
        ImageIcon viewIcon = new ImageIcon(viewImage);
        ImageIcon editIcon = new ImageIcon(editImage);
        ImageIcon deleteIcon = new ImageIcon(deleteImage);

        viewButton.setIcon(viewIcon);
        editButton.setIcon(editIcon);
        deleteButton.setIcon(deleteIcon);

        // set up buttonPanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setSize(30,30);
        buttonPanel.add(viewButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        JPanel subPanel = new JPanel(new GridLayout(2,1));
        subPanel.setBackground(backgroundColor);
        subPanel.add(recipeNameLabel);
        subPanel.add(buttonPanel);

        add(imageLabel);
        add(subPanel);
    }

     @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == editButton){
            new EditRecipePage(user, recipe);
        } else if(e.getSource() == viewButton){
            new RecipePage(user, recipe);
        } else if (e.getSource() == deleteButton) {
            RecipeDAO.deleleRecipe(RecipeDAO.getRecipeID(user, recipe));
            TopLevelMenu.viewRecipeForm.setupRecipeColumns();
        }
    }
}
