package org.example.myproject.RecipeManagement;

import org.example.myproject.DAO.RecipeDAO;
import org.example.myproject.Model.Recipe;
import org.example.myproject.Model.User;
import org.example.myproject.TopLevelMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;

// view recipe form which is the main page shows the overview of the recipes in database
public class ViewRecipeForm extends JFrame implements ActionListener {
    private final JPanel mainPane;
    private JPanel[] recipePanes;
    private JScrollPane[] recipeScrollPanes;
    private final JTabbedPane recipeTabbedPane;
    private final JButton mainMenuButton;
    private final User user;
    private LinkedList<Recipe> recipeLinkedList;

    public ViewRecipeForm(User user) {
        //set up view recipe form frame
        Color backgroundColor = new Color(250, 255, 237 );
        mainPane = new JPanel();
        mainPane.setLayout(new BorderLayout());
        mainPane.setSize(800,600);

        recipeTabbedPane = new JTabbedPane();
        recipeTabbedPane.setTabPlacement(JTabbedPane.LEFT);
        recipeTabbedPane.setBackground(Color.WHITE);

        // Get the default toolkit
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        // Get the screen size
        Dimension screenSize = toolkit.getScreenSize();

        // Display the screen width and height
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        setContentPane(mainPane);
        setTitle("Recipes");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(screenWidth/2 + 14, screenHeight));
        setLocation(0,0);
        setResizable(false);
        setVisible(true);

        this.user = user;

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

        // set up main menu button
        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(this);
        mainMenuButton.setBackground(Color.WHITE);
        mainMenuButton.setForeground(new Color(139,195,74));
        Dimension buttonSize = new Dimension(30, 30);
        mainMenuButton.setSize(buttonSize);
        mainPane.add(mainMenuButton, BorderLayout.SOUTH);

        setupRecipeColumns();
    }

    //to setup and refresh recipe columns
    public void setupRecipeColumns(){
        recipeTabbedPane.removeAll();
        Color backgroundColor = new Color(250, 255, 237 );
        recipeLinkedList = RecipeDAO.getRecipeList(user);

        // initialize recipe pane for each category and set the layout
        recipePanes = new JPanel[6];
        for (int i = 0; i < 6; i++) {
            recipePanes[i] = new JPanel();
            recipePanes[i].setLayout(new BoxLayout(recipePanes[i], BoxLayout.Y_AXIS));
            recipePanes[i].setBackground(backgroundColor);
        }
        setRecipesPane();

        // initialize recipe scroll pane for each category from recipe panes
        recipeScrollPanes = new JScrollPane[6];
        for (int i = 0; i < 6; i++) {
            recipeScrollPanes[i] = new JScrollPane(recipePanes[i]);
            recipeScrollPanes[i].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        }

        // add each recipe scroll pane to TabbedPane
        recipeTabbedPane.add("Appetizers", recipeScrollPanes[0]);
        recipeTabbedPane.add("Main Dishes", recipeScrollPanes[1]);
        recipeTabbedPane.add("Side Dishes", recipeScrollPanes[2]);
        recipeTabbedPane.add("Soups", recipeScrollPanes[3]);
        recipeTabbedPane.add("Desserts", recipeScrollPanes[4]);
        recipeTabbedPane.add("Baking", recipeScrollPanes[5]);

        mainPane.add(recipeTabbedPane, BorderLayout.CENTER);
    }

    // set up recipePanes for each category
    public void setRecipesPane() {
        Iterator<Recipe> iterator = recipeLinkedList.iterator();
        while (iterator.hasNext()) {
            Recipe currentRecipe = iterator.next();
            Recipe.Categories category = currentRecipe.getCategory();
            switch (category) {
                case Appetizer:
                    recipePanes[0].add(new RecipeColumn(user, currentRecipe));
                    break;
                case Main_Dish:
                    recipePanes[1].add(new RecipeColumn(user, currentRecipe));
                    break;
                case Side_Dish:
                    recipePanes[2].add(new RecipeColumn(user, currentRecipe));
                    break;
                case Soup:
                    recipePanes[3].add(new RecipeColumn(user, currentRecipe));
                    break;
                case Dessert:
                    recipePanes[4].add(new RecipeColumn(user, currentRecipe));
                    break;
                case Baking:
                    recipePanes[5].add(new RecipeColumn(user, currentRecipe));
                    break;
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == mainMenuButton){
            dispose();
        }
    }
}
