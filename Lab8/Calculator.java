import javax.swing.*;
import java.awt.*;

public class Calculator {
    public static void main(String[] args) {
        JFrame calc = new JFrame(" Calculator ");
        calc.setVisible(true);
        calc.setSize(350,400);
        calc.setLayout(new BorderLayout());
        JPanel ButtonPanel = new JPanel(new GridLayout(4,4));

        JButton b0 = new JButton(" 0 ");
        JButton b1 = new JButton(" 1 ");
        JButton b2 = new JButton(" 2 ");
        JButton b3 = new JButton(" 3 ");
        JButton b4 = new JButton(" 4 ");
        JButton b5 = new JButton(" 5 ");
        JButton b6 = new JButton(" 6 ");
        JButton b7 = new JButton(" 7 ");
        JButton b8 = new JButton(" 8 ");
        JButton b9 = new JButton(" 9 ");
        JButton b10 = new JButton(" + ");
        JButton b11 = new JButton(" - ");
        JButton b12 = new JButton(" * ");
        JButton b13 = new JButton(" / ");
        JButton b14 = new JButton(" = ");
        JButton b15 = new JButton(" . ");

        JTextField Output = new JTextField(" ");
        Output.setFont(new Font("Serif", Font.PLAIN, 40));

        ButtonPanel.add(b7);
        ButtonPanel.add(b8);
        ButtonPanel.add(b9);
        ButtonPanel.add(b13);
        ButtonPanel.add(b4);
        ButtonPanel.add(b5);
        ButtonPanel.add(b6);
        ButtonPanel.add(b12);
        ButtonPanel.add(b1);
        ButtonPanel.add(b2);
        ButtonPanel.add(b3);
        ButtonPanel.add(b11);
        ButtonPanel.add(b0);
        ButtonPanel.add(b15);
        ButtonPanel.add(b14);
        ButtonPanel.add(b10);

        calc.add(Output,BorderLayout.NORTH);
        calc.add(ButtonPanel,BorderLayout.CENTER);
    }
}