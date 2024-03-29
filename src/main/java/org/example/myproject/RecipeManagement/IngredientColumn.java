package org.example.myproject.RecipeManagement;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.example.myproject.Model.Ingredient;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Locale;

// ingredientColumn that contain ingredient info of the recipe
public class IngredientColumn {
    private JLabel nameLabel;
    protected JTextField nameTextField;
    private JLabel unitLabel;
    private JLabel quantityLabel;
    protected JTextField quantityTextField;
    private JLabel title;
    private JPanel ingredientColumn;
    protected JComboBox unitComboBox;

    public IngredientColumn(int num) {
        Border border = new LineBorder(Color.GRAY, 1);
        ingredientColumn.setBorder(border);
        ingredientColumn.setVisible(true);
        ingredientColumn.setPreferredSize(new Dimension(-1, 100));
        ingredientColumn.setMaximumSize(new Dimension(-1, 100));
        ingredientColumn.setMinimumSize(new Dimension(-1, 100));

        title.setText("Ingredient " + num);
        title.setFont(title.getFont().deriveFont(Font.BOLD));
    }

    public IngredientColumn(Ingredient ingredient, int num) {
        Border border = new LineBorder(Color.GRAY, 1);
        ingredientColumn.setBorder(border);
        ingredientColumn.setVisible(true);
        ingredientColumn.setPreferredSize(new Dimension(-1, 100));
        ingredientColumn.setMaximumSize(new Dimension(-1, 100));
        ingredientColumn.setMinimumSize(new Dimension(-1, 100));

        title.setText("Ingredient " + num);
        title.setFont(title.getFont().deriveFont(Font.BOLD));

        nameTextField.setText(ingredient.getName());
        DecimalFormat decimalFormat = new DecimalFormat("#####"); // Use the pattern that suits your needs
        quantityTextField.setText(String.valueOf(decimalFormat.format(ingredient.getQuantity())));

        switch (ingredient.getUnit()) {
            case "grams (g)":
                unitComboBox.setSelectedIndex(0);
                break;
            case "kilograms (kg)":
                unitComboBox.setSelectedIndex(1);
                break;
            case "litre (L)":
                unitComboBox.setSelectedIndex(2);
                break;
            case "millilitre (mL)":
                unitComboBox.setSelectedIndex(3);
                break;
            case "ounces (oz)":
                unitComboBox.setSelectedIndex(4);
                break;
            case "pieces":
                unitComboBox.setSelectedIndex(5);
                break;
            case "slides":
                unitComboBox.setSelectedIndex(6);
                break;
        }
    }

    public JPanel getIngredientColumn() {
        return ingredientColumn;
    }

    public void setEditable() {
        nameTextField.setEditable(true);
        quantityTextField.setEditable(true);
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
        ingredientColumn = new JPanel();
        ingredientColumn.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        nameLabel = new JLabel();
        nameLabel.setEnabled(true);
        nameLabel.setForeground(new Color(-9462725));
        nameLabel.setText("Name");
        ingredientColumn.add(nameLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        quantityLabel = new JLabel();
        quantityLabel.setEnabled(true);
        quantityLabel.setForeground(new Color(-9462725));
        quantityLabel.setText("Quantity");
        ingredientColumn.add(quantityLabel, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        unitLabel = new JLabel();
        unitLabel.setEnabled(true);
        unitLabel.setForeground(new Color(-9462725));
        unitLabel.setText("Unit");
        ingredientColumn.add(unitLabel, new GridConstraints(1, 1, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameTextField = new JTextField();
        nameTextField.setBackground(new Color(-1));
        nameTextField.setEditable(false);
        nameTextField.setForeground(new Color(-7617718));
        ingredientColumn.add(nameTextField, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        quantityTextField = new JTextField();
        quantityTextField.setBackground(new Color(-1));
        quantityTextField.setEditable(false);
        quantityTextField.setForeground(new Color(-7617718));
        ingredientColumn.add(quantityTextField, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        title = new JLabel();
        title.setEnabled(true);
        Font titleFont = this.$$$getFont$$$(null, Font.BOLD, -1, title.getFont());
        if (titleFont != null) title.setFont(titleFont);
        title.setForeground(new Color(-12828863));
        title.setText("");
        ingredientColumn.add(title, new GridConstraints(1, 0, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        unitComboBox = new JComboBox();
        unitComboBox.setBackground(new Color(-1));
        unitComboBox.setForeground(new Color(-7617718));
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("grams (g)");
        defaultComboBoxModel1.addElement("kilograms (kg)");
        defaultComboBoxModel1.addElement("litre (L)");
        defaultComboBoxModel1.addElement("millilitre (mL)");
        defaultComboBoxModel1.addElement("ounces (oz)");
        defaultComboBoxModel1.addElement("pieces");
        defaultComboBoxModel1.addElement("slides");
        unitComboBox.setModel(defaultComboBoxModel1);
        ingredientColumn.add(unitComboBox, new GridConstraints(1, 2, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return ingredientColumn;
    }
}
