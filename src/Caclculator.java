

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/** GUI calculator */


class Calculator implements ActionListener {
    private int value = 1;
    private double n1, n2;
    private double check = 1;

    private JLabel label, number;
    private JTextField field;
    JButton buttonZero, buttonDot, buttonPlus, buttonMinus,
            buttonMultiplication,buttonDivision,
            buttonBack, buttonDelAll, buttonPercent,
            buttonPlusMinus, buttonSqrt, buttonFraction, buttonEquals;

    private boolean plus, minus, multiplication, division, equals, control;


    public void addButons() {
        // initial coordinates
        int x = 20;
        int y = 180;

        URL iconURL = getClass().getResource("calc.png");

        // iconURL is null when not found
        ImageIcon icon = new ImageIcon(iconURL);

        JFrame frame = new JFrame("Calculator");
        // adding buttons and text field into the frame

        label = new JLabel();
        label.setBounds(267,25,100,35);
        label.setVisible(true);

        number = new JLabel();
        number.setBounds(25,10,235,15);
        number.setVisible(true);

        field = new JTextField();
        field.setBounds(20, 25, 240, 35);
        field.setEditable(false);
        field.setDocument(new JTextFieldLimit(32));
        field.setBackground(Color.white);

        buttonZero = new JButton("0");
        buttonZero.setBounds(20, 215, 93, 30);
        buttonZero.addActionListener(this);

        buttonDot = new JButton(".");
        buttonDot.setBounds(116, 215, 45, 30);
        buttonDot.addActionListener(this);

        buttonPlus = new JButton("+");
        buttonPlus.setBounds(165, 215, 45, 30);
        buttonPlus.addActionListener(new Calculator.ButtonPlus());

        buttonMinus = new JButton("-");
        buttonMinus.setBounds(165, 180, 45, 30);
        buttonMinus.addActionListener(new Calculator.ButtonMinus());

        buttonMultiplication = new JButton("*");
        buttonMultiplication.setBounds(165, 145, 45, 30);
        buttonMultiplication.addActionListener(new Calculator.ButtonMultiplication());

        buttonDivision = new JButton("/");
        buttonDivision.setBounds(165, 110, 45, 30);
        buttonDivision.addActionListener(new Calculator.ButtonDivision());

        buttonBack = new JButton("←");
        buttonBack.setBounds(20, 75, 45, 30);
        buttonBack.addActionListener(new Calculator.ButtonBack());

        buttonDelAll = new JButton("C");
        buttonDelAll.setBounds(68, 75, 45, 30);
        buttonDelAll.addActionListener(new Calculator.ButtonDelAll());

        buttonPercent = new JButton("%");
        buttonPercent.setBounds(116, 75, 45, 30);
        buttonPercent.addActionListener(new Calculator.ButtonPercent());

        buttonPlusMinus = new JButton("±");
        buttonPlusMinus.setBounds(165, 75, 45, 30);
        buttonPlusMinus.addActionListener(new Calculator.ButtonPlusMinus());

        buttonSqrt = new JButton("√");
        buttonSqrt.setBounds(215, 75, 45, 30);
        buttonSqrt.addActionListener(new Calculator.ButtonSqrt());

        buttonFraction = new JButton("⅟ₓ");
        buttonFraction.setBounds(215, 110, 45, 30);
        buttonFraction.addActionListener(new Calculator.ButtonFraction());

        buttonEquals = new JButton("=");
        buttonEquals.setBounds(215, 145, 45, 100);
        buttonEquals.addActionListener(new Calculator.ButtonEquals());
        buttonEquals.setBackground(new Color(255, 171, 0));

        frame.getContentPane().add(label);
        frame.getContentPane().add(number);
        frame.getContentPane().add(field);
        frame.getContentPane().add(buttonZero);
        frame.getContentPane().add(buttonDot);
        frame.getContentPane().add(buttonPlus);
        frame.getContentPane().add(buttonMinus);
        frame.getContentPane().add(buttonMultiplication);
        frame.getContentPane().add(buttonDivision);
        frame.getContentPane().add(buttonBack);
        frame.getContentPane().add(buttonDelAll);
        frame.getContentPane().add(buttonPercent);
        frame.getContentPane().add(buttonPlusMinus);
        frame.getContentPane().add(buttonSqrt);
        frame.getContentPane().add(buttonFraction);
        frame.getContentPane().add(buttonEquals);
        // setting frame icon
        frame.setIconImage(icon.getImage());


        // buttons for numbers

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton(String.valueOf(value));
                button.addActionListener(this);
                frame.add(button);
                button.setBounds(x, y, 45, 30);
                x += 48;
                value++;
            }
            x = 20;
            y -= 35;
        }

        frame.setBounds(1000, 100, 300, 300);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public class ButtonPlus implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            label.setText("+");

            plus = true;
            minus = false;
            multiplication = false;
            division = false;
            control = true;
            firstNum();

        }
    }

    public class ButtonMinus implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            label.setText("-");

            plus = false;
            minus = true;
            multiplication = false;
            division = false;
            control = true;
            firstNum();
        }
    }

    public class ButtonMultiplication implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            label.setText("*");

            plus = false;
            minus = false;
            multiplication = true;
            division = false;
            control = true;
            firstNum();
        }
    }


    public class ButtonDivision implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            label.setText("/");

            plus = false;
            minus = false;
            multiplication = false;
            division = true;
            control = true;
            firstNum();
        }
    }

    public class ButtonPercent implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                n1 = Double.parseDouble(field.getText());
                n1 = n1 / 100;
                field.setText(Double.toString(n1));
                number.setText(Double.toString(n1));
            }catch (Exception ignored){}
        }
    }

    public class ButtonBack implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (field.getText().length() > 0){
                    field.setText(field.getText().substring(0,field.getText().length()-1));
                }
            }catch (Exception ignored){}
        }
    }

    public class ButtonDelAll implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                field.setText("");
                label.setText("");
                number.setText("");

                plus = false;
                minus = false;
                multiplication = false;
                division = false;
                control = false;

                n1 = 0.0;
                n2 = 0.0;
            }catch (Exception ignored){}
        }
    }

    // make number positive or negative
    public class ButtonPlusMinus implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (!control){
                    n1 = Double.parseDouble(field.getText());
                    n1 = -n1;
                    field.setText(Double.toString(n1));
                }else {
                    n2 = Double.parseDouble(field.getText());
                    n2 = -n2;
                    field.setText(Double.toString(n2));
                }
            }catch (Exception ignored){}
        }
    }

    public class ButtonSqrt implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {n1 = Double.parseDouble(field.getText());
                n1 = Math.sqrt(n1);
                field.setText(Double.toString(n1));
            }catch (Exception ignored){}
        }
    }

    // make the number 1/number
    public class ButtonFraction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                n1 = Double.parseDouble(field.getText());
                n1 = 1 / n1;

                field.setText(Double.toString(n1));
            } catch (Exception ignored) {}
        }
    }

    // To compute and prints the result
    public class ButtonEquals implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try{if (check == 1){
                n2 = Double.parseDouble(field.getText());
                check++;
            }
                if (plus){
                    n1 = n1 + n2;
                    field.setText(Double.toString(n1));

                }else if (minus){
                    n1 = n1 - n2;
                    field.setText(Double.toString(n1));
                }else if (multiplication){
                    n1 = n1 * n2;
                    field.setText(Double.toString(n1));
                }else if (division){
                    if (n2 == 0){
                        field.setText("Math Error");
                    }
                    else {
                        n1 = n1 / (n2);
                        field.setText(Double.toString(n1));
                    }
                }
            }catch (Exception ignored){}

            label.setText("");
            number.setText("");
            equals = true;
        }
    }

    // delete the first number from text field
    // the number will be shown above the text field

    public void firstNum(){
        try {
            if (plus || minus || multiplication || division){
                n1 = Double.parseDouble(field.getText());
                field.setText("");
                number.setText(Double.toString(n1));
            }
        }catch (Exception ignored){}
    }

    // main actionPerformed method to make everything work correctly
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (equals){
                field.setText("");
                check = 1;
                equals = false;
            }

            // printing all the numbers and buttons
            field.setText(field.getText() + e.getActionCommand());
        }catch (Exception ignored){}
    }

}
