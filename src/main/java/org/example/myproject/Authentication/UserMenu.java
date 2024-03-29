package org.example.myproject.Authentication;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.example.myproject.Model.User;
import org.example.myproject.TopLevelMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

// user menu frame that shows user profile and settings
public class UserMenu extends JFrame implements ActionListener {
    private JLabel logoPicLabel, recipeManagerLabel, profilePicLabel, usernameLabel;
    private JButton changePasswordButton, mainMenuButton;
    private JPanel userMenuPanel;
    private final ChangePasswordFrame changePasswordFrame;

    public UserMenu(User user) {
        // set up user menu (profile) frame
        setContentPane(userMenuPanel);
        setTitle("User Menu");
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(400, 400));
        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(false);

        changePasswordFrame = new ChangePasswordFrame(user);

        // add action listener
        changePasswordButton.addActionListener(this);
        mainMenuButton.addActionListener(this);
        usernameLabel.setText(user.getUsername());

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

        //  set image as logo
        logoPicLabel.setIcon(imageCakeIcon);


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
        profilePicLabel.setIcon(imageChefIcon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changePasswordButton) {
            dispose();
            changePasswordFrame.setVisible(true);
        } else if (e.getSource() == mainMenuButton) {
            dispose();
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
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        logoPicLabel = new JLabel();
        logoPicLabel.setText("");
        panel1.add(logoPicLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        recipeManagerLabel = new JLabel();
        Font recipeManagerLabelFont = this.$$$getFont$$$("Britannic Bold", Font.ITALIC, 14, recipeManagerLabel.getFont());
        if (recipeManagerLabelFont != null) recipeManagerLabel.setFont(recipeManagerLabelFont);
        recipeManagerLabel.setForeground(new Color(-10724260));
        recipeManagerLabel.setText("Recipe Manager");
        panel1.add(recipeManagerLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        userMenuPanel = new JPanel();
        userMenuPanel.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(userMenuPanel, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        profilePicLabel = new JLabel();
        profilePicLabel.setText("");
        userMenuPanel.add(profilePicLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        userMenuPanel.add(spacer2, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        usernameLabel = new JLabel();
        usernameLabel.setText("Username");
        userMenuPanel.add(usernameLabel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        userMenuPanel.add(spacer3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        userMenuPanel.add(spacer4, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        changePasswordButton = new JButton();
        changePasswordButton.setBackground(new Color(-7617718));
        changePasswordButton.setEnabled(true);
        changePasswordButton.setForeground(new Color(-1));
        changePasswordButton.setHideActionText(false);
        changePasswordButton.setHorizontalAlignment(0);
        changePasswordButton.setHorizontalTextPosition(10);
        changePasswordButton.setOpaque(true);
        changePasswordButton.setRequestFocusEnabled(true);
        changePasswordButton.setSelected(true);
        changePasswordButton.setText("Change Password");
        userMenuPanel.add(changePasswordButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mainMenuButton = new JButton();
        mainMenuButton.setBackground(new Color(-1));
        mainMenuButton.setEnabled(true);
        mainMenuButton.setForeground(new Color(-7617718));
        mainMenuButton.setHideActionText(false);
        mainMenuButton.setHorizontalAlignment(0);
        mainMenuButton.setHorizontalTextPosition(10);
        mainMenuButton.setOpaque(true);
        mainMenuButton.setRequestFocusEnabled(true);
        mainMenuButton.setSelected(true);
        mainMenuButton.setText("Main Menu");
        userMenuPanel.add(mainMenuButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
}
