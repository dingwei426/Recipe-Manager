package org.example.myproject.Authentication;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.example.myproject.DAO.UserDAO;
import org.example.myproject.Model.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Signup frame that allows new user to create account
public class SignUpFrame extends JFrame implements ActionListener, ItemListener {
    private JPanel loginPanel;
    private JLabel loginTitle;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;
    private JLabel imageLabel;
    private JButton signUpButton;
    private JButton mainMenuButton;

    public SignUpFrame() {
        // set up signup frame
        setContentPane(loginPanel);
        setTitle("Sign Up");
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(500, 400));
        setLocationRelativeTo(null); // Center the frame on the screen

        // add action listener
        signUpButton.addActionListener(this);
        mainMenuButton.addActionListener(this);
        passwordField.addActionListener(this);
        showPasswordCheckBox.addItemListener(this);

//      read cake icon
        InputStream cakeImgInputStream = LoginFrame.class.getClassLoader().getResourceAsStream("loginIconCake.png");
        Image cakeImage;
        try {
            assert cakeImgInputStream != null;
            cakeImage = ImageIO.read(cakeImgInputStream).getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(),
                    Image.SCALE_SMOOTH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon imageCakeIcon = new ImageIcon(cakeImage);

        //  set icon of the window
        setIconImage(imageCakeIcon.getImage());

        //  set image as logo
        imageLabel.setIcon(imageCakeIcon);
    }

    // check password validity
    public static boolean passwordChecker(String password) {
        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    // check username validity
    public static boolean usernameChecker(String username) {
        int MIN_LENGTH = 4;
        int MAX_LENGTH = 20;
//        String ALLOWED_CHARACTERS = "a-zA-Z0-9_";

        if (username.length() < MIN_LENGTH || username.length() > MAX_LENGTH) {
            return false;
        }

        String regex = "^[a-zA-Z0-9_]+$";
        return username.matches(regex);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == signUpButton) {
                if (!usernameChecker(usernameField.getText())) {
                    JOptionPane.showMessageDialog(SignUpFrame.this, "Username must contain alphabet and/or digit only.\nUsername can be made up from 4 to 20 characters.", "Sign Up Error", JOptionPane.ERROR_MESSAGE);
                } else if (!passwordChecker(passwordField.getText())) {
                    JOptionPane.showMessageDialog(SignUpFrame.this, "Password must be at least 8 characters long, contains at least one digit,\none lowercase letter, one uppercase letter, and one special character.\nPassword should not contain whitespace.", "Sign Up Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());

                    if (UserDAO.signup(new User(username, password))) {
                        JOptionPane.showMessageDialog(SignUpFrame.this, "Signup Successful");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(SignUpFrame.this, "The username was used.");
                    }
                }
            } else if (e.getSource() == passwordField) {
                signUpButton.doClick();
            } else if (e.getSource() == mainMenuButton) {
                dispose();
                new LoginFrame();
            }
        } catch (HeadlessException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    // set up showPasswordCheckBox
    public void itemStateChanged(ItemEvent e) {
        try {
            if (e.getSource() == showPasswordCheckBox) {
                int state = e.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    // Show the password
                    passwordField.setEchoChar((char) 0);
                } else {
                    // Hide the password (use the default password character, typically '*')
                    passwordField.setEchoChar('\u2022');
                }
            }
        } catch (Exception ae) {
            ae.printStackTrace();
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
        panel1.setLayout(new BorderLayout(0, 0));
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayoutManager(9, 3, new Insets(0, 0, 0, 0), -1, -1));
        loginPanel.setBackground(new Color(-855310));
        panel1.add(loginPanel, BorderLayout.CENTER);
        usernameLabel = new JLabel();
        Font usernameLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, usernameLabel.getFont());
        if (usernameLabelFont != null) usernameLabel.setFont(usernameLabelFont);
        usernameLabel.setForeground(new Color(-9462725));
        usernameLabel.setText("Username");
        loginPanel.add(usernameLabel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        usernameField = new JTextField();
        usernameField.setText("");
        loginPanel.add(usernameField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        passwordLabel = new JLabel();
        Font passwordLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, passwordLabel.getFont());
        if (passwordLabelFont != null) passwordLabel.setFont(passwordLabelFont);
        passwordLabel.setForeground(new Color(-9462725));
        passwordLabel.setText("Password");
        loginPanel.add(passwordLabel, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        passwordField = new JPasswordField();
        loginPanel.add(passwordField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        showPasswordCheckBox = new JCheckBox();
        Font showPasswordCheckBoxFont = this.$$$getFont$$$(null, Font.BOLD, -1, showPasswordCheckBox.getFont());
        if (showPasswordCheckBoxFont != null) showPasswordCheckBox.setFont(showPasswordCheckBoxFont);
        showPasswordCheckBox.setForeground(new Color(-9462725));
        showPasswordCheckBox.setText("show password");
        loginPanel.add(showPasswordCheckBox, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        signUpButton = new JButton();
        signUpButton.setBackground(new Color(-7617718));
        signUpButton.setForeground(new Color(-1));
        signUpButton.setText("Sign Up");
        loginPanel.add(signUpButton, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        imageLabel = new JLabel();
        imageLabel.setText("");
        loginPanel.add(imageLabel, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 100), null, 0, false));
        final Spacer spacer1 = new Spacer();
        loginPanel.add(spacer1, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        loginPanel.add(spacer2, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        loginTitle = new JLabel();
        Font loginTitleFont = this.$$$getFont$$$("Cooper Black", Font.BOLD, 20, loginTitle.getFont());
        if (loginTitleFont != null) loginTitle.setFont(loginTitleFont);
        loginTitle.setForeground(new Color(-7617718));
        loginTitle.setText("Recipe Manager Registration");
        loginPanel.add(loginTitle, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mainMenuButton = new JButton();
        mainMenuButton.setBackground(new Color(-1));
        mainMenuButton.setForeground(new Color(-7617718));
        mainMenuButton.setText("Main Menu");
        loginPanel.add(mainMenuButton, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
