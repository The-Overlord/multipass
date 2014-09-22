/*
 * Multipass v1.3
 * Copyright 2014 Ari Zerner.
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * The full text of the GNU General Public License can be found at
 * http://www.gnu.org/licenses/gpl.txt.
 */
package multipass;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class MultiPass extends javax.swing.JFrame {

    private static final String ALGORITHM = "MD5";
    private char defaultEchoChar = '*';

    /** Creates new form SitePassGUI */
    public MultiPass() {
        initComponents();
    }

    /**
     * Generates a hash from master and identifier.
     * @param master
     * @param identifier
     * @return the hash as a BigInteger, or null if there was an error
     */
    private BigInteger generateHash(char[] master, String identifier) {
        byte[] prehash = new byte[master.length + identifier.length() + 1];
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("System does not support the "
                    + ALGORITHM + " hash.");
            return null;
        }
        for (int i = 0; i < master.length; i++) {
            prehash[i] = (byte) master[i];
        }
        prehash[master.length] = ' ';
        for (int i = 0; i < identifier.length(); i++) {
            prehash[i + master.length + 1] = (byte) identifier.charAt(i);
        }
        byte[] posthash = digest.digest(prehash);
        Arrays.fill(prehash, (byte) 0);
        return new BigInteger(1, posthash);
    }

    /**
     * Generates a password from a master password and an identifier.
     * @param master a secure password
     * @param identifier an identifier for the use of the password
     * @param length the password length, 0 to 32
     */
    private String generatePassword(char[] master, String identifier, int length) {
        BigInteger hash = generateHash(master, identifier);
        if (hash == null) return null;
        String password = hash.toString(16);
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
     */
    private String generatePIN(char[] master, String identifier) {
        BigInteger hash = generateHash(master, identifier);
        if (hash == null) return null;
        String password = hash.toString(10);
        while (password.length() < 32)
            password = "0" + password;
        return password.substring(0, 4);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        masterField = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        identifierField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        defaultEchoChar = passwordField.getEchoChar();
        copyToClipButton = new javax.swing.JButton();
        clearClipButton = new javax.swing.JButton();
        aboutButton = new javax.swing.JButton();
        lengthSlider = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        lengthField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        confirmField = new javax.swing.JPasswordField();
        clearAllFieldsButton = new javax.swing.JButton();
        pinCheckBox = new javax.swing.JCheckBox();
        showCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Multipass");

        jLabel1.setText("Master Password:");

        masterField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masterFieldActionPerformed(evt);
            }
        });
        masterField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                masterFieldKeyReleased(evt);
            }
        });

        jLabel2.setText("Password Use Identifier (site name):");

        identifierField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                identifierFieldActionPerformed(evt);
            }
        });
        identifierField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                identifierFieldKeyReleased(evt);
            }
        });

        passwordField.setEditable(false);

        copyToClipButton.setText("Copy Password To Clipboard");
        copyToClipButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyToClipButtonActionPerformed(evt);
            }
        });

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

        jLabel4.setText("Confirm Master Password (optional):");

        confirmField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                confirmFieldKeyReleased(evt);
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

        showCheckBox.setText("Show");
        showCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(pinCheckBox)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel3))
                                .addComponent(jLabel2)
                                .addComponent(jLabel4))
                            .addGap(21, 21, 21))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(copyToClipButton)
                                .addComponent(clearClipButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(showCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(identifierField)
                    .addComponent(masterField)
                    .addComponent(passwordField)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lengthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lengthSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE))
                    .addComponent(confirmField)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clearAllFieldsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aboutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(masterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(confirmField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(identifierField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                    .addComponent(showCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearAllFieldsButton)
                    .addComponent(copyToClipButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearClipButton)
                    .addComponent(aboutButton))
                .addContainerGap(14, Short.MAX_VALUE))
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
        char[] master = masterField.getPassword(),
                confirm = confirmField.getPassword();
        boolean confirmed = confirm.length == 0 || Arrays.
                equals(master, confirm);
        Arrays.fill(master, '\0');
        Arrays.fill(confirm, '\0');
        if (confirmed) {
            confirmField.setForeground(Color.black);
        } else {
            passwordField.setText("");
            confirmField.setForeground(Color.red);
        }
        return confirmed;
    }

    /**
     * Sets whether the password is shown in plaintext.
     */
    private void setShowPassword(boolean show) {
        passwordField.setEchoChar(showCheckBox.isSelected() ? '\0'
                : defaultEchoChar);
    }

    /**
     * Sets show password according to the checkbox.
     */
    private void setShowPassword() {
        setShowPassword(showCheckBox.isSelected());
    }

    /**
     * Generates a password (or PIN) and displays it in passwordField.
     */
    private void generate() {
        if (confirmMasterPassword()) {
            char[] master = masterField.getPassword();
            String identifier = identifierField.getText();
            String generated = pinCheckBox.isSelected() ? generatePIN(master,
                    identifier) : generatePassword(master, identifier,
                            lengthSlider.getValue());
            Arrays.fill(master, '\0');
            if (generated == null) {
                passwordField.setText("Error");
                setShowPassword(true);
            } else {
                passwordField.setText(generated);
                setShowPassword();
            }
        }
    }

    private void identifierFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_identifierFieldActionPerformed
        generate();
    }//GEN-LAST:event_identifierFieldActionPerformed

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
            "Multipass v1.3 Copyright 2014 Ari Zerner.",
            "This program is free software: you can redistribute it and/or modify",
            "it under the terms of the GNU General Public License as published by",
            "the Free Software Foundation, either version 3 of the License, or",
            "(at your option) any later version.",
            "The full text of the GNU General Public License can be found at",
            "http://www.gnu.org/licenses/gpl.txt.", "\n",
            "Multipass is a tool that allows you to easily generate a secure",
            "password from a master password and an identfier (e.g. a website name).",
            "\n",
            "If you're creating a new password, it's recommended that you",
            "confirm your master password to ensure that it's correct.",
            "You can copy the generated password to the clipboard by clicking",
            "\"Copy Password To Clipboard\". For security, it is recommended",
            "that you clear the password from the clipboard afterwards, either",
            "by copying something else or by clicking \"Clear Clipboard\".",
            "\n", "How it works:", "\n",
            "To generate passwords, Multipass concatenates the master password",
            "and the identifier, separated by a space. It then uses the "
            + ALGORITHM,
            "algorithm to make a hash of the concatenation. The hash is",
            "represented as a hexadecimal number, padded with leading zeroes to",
            "make its length 32, and truncated to the specified length.",
            "Then, all instances of a, c, and e are capitalized.",
            "The same process is used to make a PIN, except that the hash is",
            "represented as a decimal number instead of a hexadecimal number,",
            "and always truncated to 4 digits instead of the specified length.",};
        JOptionPane.showMessageDialog(this, message, "About SitePass",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_aboutButtonActionPerformed

    private void lengthSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_lengthSliderStateChanged
        lengthField.setText(Integer.toString(lengthSlider.getValue()));
        generate();
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
        confirmField.setText("");
        identifierField.setText("");
        passwordField.setText("");
    }

    private void lengthFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lengthFieldActionPerformed
        applyLengthFieldValue();
    }//GEN-LAST:event_lengthFieldActionPerformed

    private void lengthFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lengthFieldFocusLost
        applyLengthFieldValue();
    }//GEN-LAST:event_lengthFieldFocusLost

    private void clearAllFieldsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAllFieldsButtonActionPerformed
        clearAllFields();
    }//GEN-LAST:event_clearAllFieldsButtonActionPerformed

    private void pinCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinCheckBoxActionPerformed
        boolean isChecked = pinCheckBox.isSelected();
        lengthField.setEnabled(!isChecked);
        lengthSlider.setEnabled(!isChecked);
        generate();
    }//GEN-LAST:event_pinCheckBoxActionPerformed

    private void showCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showCheckBoxActionPerformed
        setShowPassword();
    }//GEN-LAST:event_showCheckBoxActionPerformed

    private void masterFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_masterFieldKeyReleased
        generate();
    }//GEN-LAST:event_masterFieldKeyReleased

    private void confirmFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_confirmFieldKeyReleased
        generate();
    }//GEN-LAST:event_confirmFieldKeyReleased

    private void identifierFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_identifierFieldKeyReleased
        generate();
    }//GEN-LAST:event_identifierFieldKeyReleased

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
    private javax.swing.JPasswordField confirmField;
    private javax.swing.JButton copyToClipButton;
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
    private javax.swing.JCheckBox showCheckBox;
    // End of variables declaration//GEN-END:variables
}
