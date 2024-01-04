package org.example.myproject;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.example.myproject.Authentication.UserMenu;
import org.example.myproject.Model.User;
import org.example.myproject.RecipeManagement.CreateRecipe;
import org.example.myproject.RecipeManagement.ViewRecipeForm;

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

// top level menu frame, the user will reach this page after logging in
public class TopLevelMenu extends JFrame implements ActionListener {
    private JLabel decoPicLabel;
    private JButton viewRecipesButton, profileSettingButton, logoutButton, createRecipeButton;
    private JPanel TopLevelMenuPanel;
    private final User user;
    public static ViewRecipeForm viewRecipeForm;

    public TopLevelMenu(User user) {
        // set up top level menu frame
        setContentPane(TopLevelMenuPanel);
        setTitle("Main Menu");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setBackground(new Color(250, 255, 237));

        // set frame size and location
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        setSize(screenWidth + 28, screenHeight);
        setLocationRelativeTo(null); // Center the frame on the screen

        this.user = user;

        // add action listener
        logoutButton.addActionListener(this);
        profileSettingButton.addActionListener(this);
        viewRecipesButton.addActionListener(this);
        createRecipeButton.addActionListener(this);

        //  read cake icon
        InputStream cakeImgInputStream = TopLevelMenu.class.getClassLoader().getResourceAsStream("loginIconCake.png");
        Image cakeImage;
        try {
            assert cakeImgInputStream != null;
            cakeImage = ImageIO.read(cakeImgInputStream).getScaledInstance(30, 30,
                    Image.SCALE_SMOOTH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon imageCakeIcon = new ImageIcon(cakeImage);

        //  set icon of the window
        setIconImage(imageCakeIcon.getImage());

        //  read chef icon
        InputStream chefImgInputStream = TopLevelMenu.class.getClassLoader().getResourceAsStream("chefIcon.png");
        Image chefImage;
        try {
            assert chefImgInputStream != null;
            chefImage = ImageIO.read(chefImgInputStream).getScaledInstance(180, 180,
                    Image.SCALE_SMOOTH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon imageChefIcon = new ImageIcon(chefImage);

        //  set image chef for deco
        decoPicLabel.setIcon(imageChefIcon);
    }

    public static void clearDirectory(File directory) {
        // Get the list of files in the directory
        File[] files = directory.listFiles();

        // Delete each file in the directory
        if (files != null) {
            for (File file : files) {
                boolean deleted = file.delete();
                if (deleted) {
                    System.out.println("Deleted: " + file.getAbsolutePath());
                } else {
                    System.err.println("Failed to delete: " + file.getAbsolutePath());
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewRecipesButton) {
            viewRecipeForm = new ViewRecipeForm(user);
        } else if (e.getSource() == profileSettingButton) {
            new UserMenu(user);
        } else if (e.getSource() == logoutButton) {
            JOptionPane.showMessageDialog(this.TopLevelMenuPanel, "Logout Successfully.");
            clearDirectory(new File("src/main/java/recipeImageCache/"));
            System.exit(0);
        } else if (e.getSource() == createRecipeButton) {
            new CreateRecipe(user);
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
        TopLevelMenuPanel = new JPanel();
        TopLevelMenuPanel.setLayout(new GridLayoutManager(10, 4, new Insets(0, 0, 0, 0), -1, -1));
        TopLevelMenuPanel.setBackground(new Color(-327699));
        Font TopLevelMenuPanelFont = this.$$$getFont$$$("Britannic Bold", Font.ITALIC, 12, TopLevelMenuPanel.getFont());
        if (TopLevelMenuPanelFont != null) TopLevelMenuPanel.setFont(TopLevelMenuPanelFont);
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Cooper Black", Font.BOLD, 20, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-7617718));
        label1.setText("Embrace the art of culinary creation");
        TopLevelMenuPanel.add(label1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        TopLevelMenuPanel.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        decoPicLabel = new JLabel();
        Font decoPicLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, decoPicLabel.getFont());
        if (decoPicLabelFont != null) decoPicLabel.setFont(decoPicLabelFont);
        decoPicLabel.setForeground(new Color(-9462725));
        decoPicLabel.setText("");
        TopLevelMenuPanel.add(decoPicLabel, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-327699));
        TopLevelMenuPanel.add(panel1, new GridConstraints(0, 2, 10, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        viewRecipesButton = new JButton();
        viewRecipesButton.setBackground(new Color(-7617718));
        viewRecipesButton.setEnabled(true);
        viewRecipesButton.setForeground(new Color(-1));
        viewRecipesButton.setHideActionText(false);
        viewRecipesButton.setHorizontalAlignment(0);
        viewRecipesButton.setHorizontalTextPosition(10);
        viewRecipesButton.setOpaque(true);
        viewRecipesButton.setRequestFocusEnabled(true);
        viewRecipesButton.setSelected(true);
        viewRecipesButton.setText("View Recipe");
        panel1.add(viewRecipesButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 25), new Dimension(200, 25), new Dimension(200, 25), 0, false));
        profileSettingButton = new JButton();
        profileSettingButton.setBackground(new Color(-7617718));
        profileSettingButton.setEnabled(true);
        profileSettingButton.setForeground(new Color(-1));
        profileSettingButton.setHideActionText(false);
        profileSettingButton.setHorizontalAlignment(0);
        profileSettingButton.setHorizontalTextPosition(10);
        profileSettingButton.setOpaque(true);
        profileSettingButton.setRequestFocusEnabled(true);
        profileSettingButton.setSelected(true);
        profileSettingButton.setText("Profile Setting");
        panel1.add(profileSettingButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 25), new Dimension(200, 25), new Dimension(200, 25), 0, false));
        logoutButton = new JButton();
        logoutButton.setBackground(new Color(-7617718));
        logoutButton.setEnabled(true);
        logoutButton.setForeground(new Color(-1));
        logoutButton.setHideActionText(false);
        logoutButton.setHorizontalAlignment(0);
        logoutButton.setHorizontalTextPosition(10);
        logoutButton.setOpaque(true);
        logoutButton.setRequestFocusEnabled(true);
        logoutButton.setSelected(true);
        logoutButton.setText("Logout");
        panel1.add(logoutButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 25), new Dimension(200, 25), new Dimension(200, 25), 0, false));
        createRecipeButton = new JButton();
        createRecipeButton.setBackground(new Color(-7617718));
        createRecipeButton.setEnabled(true);
        createRecipeButton.setForeground(new Color(-1));
        createRecipeButton.setHideActionText(false);
        createRecipeButton.setHorizontalAlignment(0);
        createRecipeButton.setHorizontalTextPosition(10);
        createRecipeButton.setOpaque(true);
        createRecipeButton.setRequestFocusEnabled(true);
        createRecipeButton.setSelected(true);
        createRecipeButton.setText("Create Recipe");
        panel1.add(createRecipeButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 25), new Dimension(200, 25), new Dimension(200, 25), 0, false));
        final Spacer spacer4 = new Spacer();
        TopLevelMenuPanel.add(spacer4, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Britannic Bold", Font.ITALIC, 12, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-9462725));
        label2.setText("Every dish is a masterpiece waiting to be born, a story to be shared, and a memory to be savored.");
        TopLevelMenuPanel.add(label2, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Britannic Bold", Font.ITALIC, 12, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-9462725));
        label3.setText("Welcome to your kitchen, where inspiration and innovation meet at every recipe. Let's cook up some magic together.");
        TopLevelMenuPanel.add(label3, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        TopLevelMenuPanel.add(spacer5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        TopLevelMenuPanel.add(spacer6, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("");
        TopLevelMenuPanel.add(label4, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("");
        TopLevelMenuPanel.add(label5, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("");
        TopLevelMenuPanel.add(label6, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("");
        TopLevelMenuPanel.add(label7, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return TopLevelMenuPanel;
    }
}
