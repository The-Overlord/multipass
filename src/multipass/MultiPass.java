/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multipass;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author Overlord
 */
public class MultiPass extends javax.swing.JFrame {

    private static final String ALGORITHM = "MD5", ERROR = "Error";

    /** Creates new form SitePassGUI */
    public MultiPass() {
        initComponents();
    }

    /**
     * Generates a password from a master password and an identifier.
     * @param master a secure password
     * @param identifier an identifier for the use of the password
     * @param length the password length, 0 to 32
     * @return the MD5 hash of master + ' ' + identifier
     */
    private String generatePassword(char[] master, String identifier, int length) {
        byte[] prehash = new byte[master.length + identifier.length() + 1];
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("System does not support the "
                    + ALGORITHM + " hash.");
            return ERROR;
        }
        for (int i = 0; i < master.length; i++) {
            prehash[i] = (byte) master[i];
            master[i] = '\0';
        }
        prehash[master.length] = ' ';
        for (int i = 0; i < identifier.length(); i++) {
            prehash[i + master.length + 1] = (byte) identifier.charAt(i);
        }
        byte[] posthash = digest.digest(prehash);
        Arrays.fill(prehash, (byte) 0);
        String password = new BigInteger(1, posthash).toString(16);
        while (password.length() < 32)
            password = "0" + password;
        return password.substring(0, length).
                replace('a', 'A').replace('c', 'C').replace('e', 'E');
    }

    /**
     * Generates a 4-digit PIN from a master password and an identifier.
     * @param master a secure password
     * @param identifier an identifier for the use of the password
     * @param length the password length, 0 to 32
     * @return the MD5 hash of master + ' ' + identifier
     */
    private String generatePIN(char[] master, String identifier) {
        byte[] prehash = new byte[master.length + identifier.length() + 1];
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("System does not support the "
                    + ALGORITHM + " hash.");
            return ERROR;
        }
        for (int i = 0; i < master.length; i++) {
            prehash[i] = (byte) master[i];
            master[i] = '\0';
        }
        prehash[master.length] = ' ';
        for (int i = 0; i < identifier.length(); i++) {
            prehash[i + master.length + 1] = (byte) identifier.charAt(i);
        }
        byte[] posthash = digest.digest(prehash);
        Arrays.fill(prehash, (byte) 0);
        String password = new BigInteger(1, posthash).toString(10);
        while (password.length() < 32)
            password = "0" + password;
        return password.substring(0, 4);
    }

    /** This method is called from within the constructor to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        masterField = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        identifierField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        generatePasswordButton = new javax.swing.JButton();
        copyToClipButton = new javax.swing.JButton();
        showPasswordButton = new javax.swing.JButton();
        showPasswordField = new javax.swing.JTextField();
        clearClipButton = new javax.swing.JButton();
        aboutButton = new javax.swing.JButton();
        lengthSlider = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        lengthField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        confirmMasterField = new javax.swing.JPasswordField();
        confirmCheckBox = new javax.swing.JCheckBox();
        clearAllFieldsButton = new javax.swing.JButton();
        pinCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SitePass");

        jLabel1.setText("Master Password:");

        masterField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masterFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Identifier:");

        identifierField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                identifierFieldActionPerformed(evt);
            }
        });

        passwordField.setEditable(false);

        generatePasswordButton.setText("Generate Password");
        generatePasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generatePasswordButtonActionPerformed(evt);
            }
        });

        copyToClipButton.setText("Copy Password To Clipboard");
        copyToClipButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyToClipButtonActionPerformed(evt);
            }
        });

        showPasswordButton.setText("Show Password");
        showPasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordButtonActionPerformed(evt);
            }
        });

        showPasswordField.setEditable(false);

        clearClipButton.setText("Clear Clipboard");
        clearClipButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearClipButtonActionPerformed(evt);
            }
        });

        aboutButton.setText("About");
        aboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutButtonActionPerformed(evt);
            }
        });

        lengthSlider.setMajorTickSpacing(4);
        lengthSlider.setMaximum(32);
        lengthSlider.setMinimum(8);
        lengthSlider.setMinorTickSpacing(2);
        lengthSlider.setPaintLabels(true);
        lengthSlider.setPaintTicks(true);
        lengthSlider.setValue(16);
        lengthSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                lengthSliderStateChanged(evt);
            }
        });

        jLabel3.setText("Password Length:");

        lengthField.setColumns(2);
        lengthField.setText(Integer.toString(lengthSlider.getValue()));
        lengthField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                lengthFieldFocusLost(evt);
            }
        });
        lengthField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lengthFieldActionPerformed(evt);
            }
        });

        jLabel4.setText("Confirm Master Password:");

        confirmMasterField.setEditable(false);

        confirmCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmCheckBoxActionPerformed(evt);
            }
        });

        clearAllFieldsButton.setText("Clear All Fields");
        clearAllFieldsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAllFieldsButtonActionPerformed(evt);
            }
        });

        pinCheckBox.setText("4-digit PIN");
        pinCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(clearClipButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(confirmCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addComponent(copyToClipButton)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pinCheckBox)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(generatePasswordButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(showPasswordButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(identifierField)
                            .addComponent(masterField)
                            .addComponent(passwordField)
                            .addComponent(showPasswordField)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lengthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                                .addComponent(lengthSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addComponent(confirmMasterField)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clearAllFieldsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aboutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(masterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(confirmCheckBox, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(confirmMasterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(identifierField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lengthSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lengthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pinCheckBox))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generatePasswordButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showPasswordButton)
                    .addComponent(showPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(copyToClipButton)
                    .addComponent(clearAllFieldsButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearClipButton)
                    .addComponent(aboutButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void masterFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masterFieldActionPerformed
        identifierField.requestFocusInWindow();
    }//GEN-LAST:event_masterFieldActionPerformed

    /**
     * Checks whether the master password fields are equal. If not, pops up a
     * message to inform the user.
     * @return whether the master password fields are equal
     */
    private boolean confirmMasterPassword() {
        if (!confirmCheckBox.isSelected()) return true;
        char[] master = masterField.getPassword(),
                confirm = confirmMasterField.getPassword();
        boolean equal = Arrays.equals(master, confirm);
        Arrays.fill(master, '\0');
        Arrays.fill(confirm, '\0');
        if (!equal) {
            passwordField.setText("");
            showPasswordField.setText("");
            JOptionPane.showMessageDialog(this,
                    "Please make sure master passwords match.",
                    "Confirm Password", JOptionPane.ERROR_MESSAGE);
        }
        return equal;
    }

    /**
     * Generates a password (or PIN) and displays it in passwordField.
     */
    private void generatePassword() {
        if (confirmMasterPassword()) {
            String generated = pinCheckBox.isSelected() ? generatePIN(
                    masterField.getPassword(), identifierField.getText())
                    : generatePassword(masterField.getPassword(),
                            identifierField.getText(), lengthSlider.getValue());
            passwordField.setText(generated);
            if (generated.equals(ERROR)) {
                showPasswordField.setText(ERROR);
            } else if (!showPasswordField.getText().equals(passwordField.
                    getText())) {
                showPasswordField.setText("");
            }
        }
    }

    private void identifierFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_identifierFieldActionPerformed
        generatePassword();
    }//GEN-LAST:event_identifierFieldActionPerformed

    private void generatePasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generatePasswordButtonActionPerformed
        generatePassword();
    }//GEN-LAST:event_generatePasswordButtonActionPerformed

    private void showPasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordButtonActionPerformed
        showPasswordField.setText(passwordField.getText());
    }//GEN-LAST:event_showPasswordButtonActionPerformed

    /**
     * Sets the contents of the system clipboard to content.
     * @param content
     */
    private void copyToClipboard(String content) {
        Toolkit.getDefaultToolkit().getSystemClipboard().
                setContents(new StringSelection(content), null);
    }

    @Override
    /**
     * Gives user the option to clear clipboard.
     */
    public void dispose() {
        switch (JOptionPane.showConfirmDialog(this, "Clear clipboard?", null,
                JOptionPane.YES_NO_OPTION)) {
            case JOptionPane.YES_OPTION:
                copyToClipboard("");
            case JOptionPane.NO_OPTION:
                super.dispose();
        }
    }

    private void copyToClipButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyToClipButtonActionPerformed
        copyToClipboard(passwordField.getText());
    }//GEN-LAST:event_copyToClipButtonActionPerformed

    private void clearClipButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearClipButtonActionPerformed
        copyToClipboard("");
    }//GEN-LAST:event_clearClipButtonActionPerformed

    private void aboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutButtonActionPerformed
        final Object[] message = {
            "Multipass is a tool that allows you to easily generate a secure",
            "password from a master password and an identfier (e.g. a website name).",
            "Just enter your master password and the identifier, then click",
            "\"Generate Password\". To see the generated password, click",
            "\"Show Password\". You can also copy the generated password to",
            "the clipboard without showing it by clicking \"Copy Password To",
            "Clipboard\". For security, it is recommended that you clear the",
            "password from the clipboard afterwards, either by copying something",
            "else or by clicking \"Clear Clipboard\".",
            "\n", "How it works:", "\n",
            "To generate passwords, SitePass concatenates the master password",
            "and the site name, separated by a space. It then uses the "
            + ALGORITHM,
            "algorithm to make a hash of the concatenation. The generated",
            "password is the first 12 digits of the hexadecimal representation",
            "of that hash, capitalizing the letters a, c, and e in order to",
            "fulfill requirements that the password has a capital letter.",
            "\n", "SitePass was written by Ari Zerner."
        };
        JOptionPane.showMessageDialog(this, message, "About SitePass",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_aboutButtonActionPerformed

    private void lengthSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_lengthSliderStateChanged
        lengthField.setText(Integer.toString(lengthSlider.getValue()));
    }//GEN-LAST:event_lengthSliderStateChanged

    /**
     * Sets the length slider to the value in the length text field if the text
     * is a valid integer, constraining the value to the range of the slider.
     */
    private void applyLengthFieldValue() {
        try {
            int val = Integer.parseInt(lengthField.getText());
            // constrain val to range of length slider
            val = Integer.max(lengthSlider.getMinimum(),
                    Integer.min(lengthSlider.getMaximum(), val));
            lengthField.setText(Integer.toString(val));
            lengthSlider.setValue(val);
        } catch (NumberFormatException e) {
            lengthField.setText(Integer.toString(lengthSlider.getValue()));
        }
    }

    private void clearAllFields() {
        masterField.setText("");
        confirmMasterField.setText("");
        identifierField.setText("");
        passwordField.setText("");
        showPasswordField.setText("");
    }

    private void lengthFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lengthFieldActionPerformed
        applyLengthFieldValue();
    }//GEN-LAST:event_lengthFieldActionPerformed

    private void lengthFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lengthFieldFocusLost
        applyLengthFieldValue();
    }//GEN-LAST:event_lengthFieldFocusLost

    private void confirmCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmCheckBoxActionPerformed
        confirmMasterField.setEditable(confirmCheckBox.isSelected());
    }//GEN-LAST:event_confirmCheckBoxActionPerformed

    private void clearAllFieldsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAllFieldsButtonActionPerformed
        clearAllFields();
    }//GEN-LAST:event_clearAllFieldsButtonActionPerformed

    private void pinCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinCheckBoxActionPerformed
        boolean isChecked = pinCheckBox.isSelected();
        lengthField.setEnabled(!isChecked);
        lengthSlider.setEnabled(!isChecked);
        generatePasswordButton.setText(isChecked ? "Generate PIN"
                : "Generate Password");
        showPasswordButton.setText(isChecked ? "Show PIN" : "Show Password");
    }//GEN-LAST:event_pinCheckBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MultiPass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutButton;
    private javax.swing.JButton clearAllFieldsButton;
    private javax.swing.JButton clearClipButton;
    private javax.swing.JCheckBox confirmCheckBox;
    private javax.swing.JPasswordField confirmMasterField;
    private javax.swing.JButton copyToClipButton;
    private javax.swing.JButton generatePasswordButton;
    private javax.swing.JTextField identifierField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField lengthField;
    private javax.swing.JSlider lengthSlider;
    private javax.swing.JPasswordField masterField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JCheckBox pinCheckBox;
    private javax.swing.JButton showPasswordButton;
    private javax.swing.JTextField showPasswordField;
    // End of variables declaration//GEN-END:variables
}
