import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ButtonController extends JPanel {

    private JButton btnCheck;
    private JButton btnHint;
    private JButton btnNew;
    private JRadioButton rBtnEasy;
    private JRadioButton rBtnHard;
    private JRadioButton rBtnNormal;
    private ButtonGroup btnGroup;
    private SudokuBoard sudokuBoard;
    private JLabel labelTry;
    private int wrongTries;
    private GridBagConstraints gbc;

    public ButtonController(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
        gbc = new GridBagConstraints();

        btnCheck = new JButton("Check");
        btnHint = new JButton("Rules");
        btnNew = new JButton("New Game");
        labelTry = new JLabel("Number of wrong tries : " + wrongTries + "/3");
        setRadioButtons();
    }

    public String getSelectedRadio() {
        Enumeration<AbstractButton> allRadioButton = btnGroup.getElements();
        while (allRadioButton.hasMoreElements()) {
            JRadioButton temp = (JRadioButton) allRadioButton.nextElement();
            if (temp.isSelected()) {
                return temp.getText();
            }
        }
        return null;
    }

    private void setMenu() {
        setBorder(new EmptyBorder(4, 4, 4, 4));
        setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }

    private void checkButtonAction() {
        btnCheck.setFocusable(false);
        
        btnCheck.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = true;
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (!sudokuBoard.getFields()[i][j].getText().equals(String.valueOf(sudokuBoard.getSolution()[i][j]))) {
                            flag = false;
                            sudokuBoard.getFields()[i][j].setBackground(Color.RED);
                        } else {
                            if (sudokuBoard.getFields()[i][j].getBackground().getRGB() != -986896) {
                                sudokuBoard.getFields()[i][j].setBackground(Color.BLUE);
                            }
                        }
                    }
                }
                if (!flag) {
                    wrongTries++;
                    labelTry.setText("Number of wrong tries : " + wrongTries + "/3");
                    if(wrongTries >= 3){
                        JOptionPane.showMessageDialog(null, "Yanlış çözdünüz..", "Wrong Solution", JOptionPane.ERROR_MESSAGE);
                        btnNew.doClick();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Tebrikler, doğru çözdünüz..", "True Solution", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        add(btnCheck, gbc);
        gbc.gridy++;
    }

    private void hintButtonAction() {
        btnHint.setFocusable(false);
        btnHint.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "The Sudoku game involves a grid of 81 squares. " +
                        "The grid is divided into nine blocks, each containing nine squares.\n" +
                        "The rules of the game are simple: " +
                        "each of the nine blocks has to contain all the numbers 1-9 within its squares. " +
                        "Each number can only appear once in a row, column or box.", "Rules", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(btnHint, gbc);
        gbc.gridy++;
    }

    private void newButtonAction() {
        btnNew.setFocusable(false);
        btnNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sudokuBoard.createGame();
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if(sudokuBoard.getGame()[i][j] == 0 )
                        {
                            sudokuBoard.getFields()[i][j].setText("");
                            sudokuBoard.getFields()[i][j].setBackground(Color.WHITE);
                            sudokuBoard.getFields()[i][j].setEditable(true);     
                        }
                        else
                        {
                            sudokuBoard.getFields()[i][j].setText(String.valueOf(sudokuBoard.getGame()[i][j]));
                            sudokuBoard.getFields()[i][j].setBackground(new Color(-986896));
                            sudokuBoard.getFields()[i][j].setEditable(false);
                        }
                    }
                }
                wrongTries = 0;
                labelTry.setText("New game started!");
            }
        });
        add(btnNew, gbc);
        gbc.gridy++;
    }

    private void setRadioButtons() {
        rBtnEasy = new JRadioButton("Easy");
        rBtnNormal = new JRadioButton("Normal");
        rBtnHard = new JRadioButton("Hard");
        btnGroup = new ButtonGroup();
        btnGroup.add(rBtnEasy);
        btnGroup.add(rBtnNormal);
        btnGroup.add(rBtnHard);

        setSize(100,200);
        setLayout( new FlowLayout());
        
        rBtnEasy.setSelected(true);
        add(rBtnEasy, gbc);
        gbc.gridy++;
        add(rBtnNormal, gbc);
        gbc.gridy++;
        add(rBtnHard, gbc);
        gbc.gridy++;
    }

    public ButtonController createController() {
        setMenu();
        newButtonAction();
        hintButtonAction();
        checkButtonAction();
        add(labelTry, gbc);

        return this;
    }
}
