import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class fCal{
    JFrame frame;
    JTextField investment,years,annual,fuVal;
    fCal(){
        frame = new JFrame(" Future Value Calculator");
        frame.setLayout(new BorderLayout());
        frame.setSize(400,300);
        frame.setVisible(true);
        JPanel topBox = new JPanel(new GridLayout(4,1,10,20));
        JPanel downBox = new JPanel();
        JPanel p1 = new JPanel(new GridLayout(1,2));
        JPanel p2 = new JPanel(new GridLayout(1,2));
        JPanel p3 = new JPanel(new GridLayout(1,2));
        JPanel p4 = new JPanel(new GridLayout(1,2));
        topBox.add(p1);
        topBox.add(p2);
        topBox.add(p3);
        topBox.add(p4);
        JLabel invAMT =new JLabel(" Investment Amount : ");
        JTextField investment = new JTextField("Enter amount");
        p1.add(invAMT);
        p1.add(investment);

        JLabel numYears =new JLabel(" Number of years : ");
        JTextField years = new JTextField("Enter years");
        p2.add(numYears);
        p2.add(years);

        JLabel annInt =new JLabel(" Annual Interest  : ");
        JTextField annual = new JTextField("Annual interest rate ");
        p3.add(annInt);
        p3.add(annual);

        JLabel futVal =new JLabel(" Future Value is : ");
        JTextField fuVal = new JTextField("Value in rupees ");
        p4.add(futVal);
        p4.add(fuVal);

        JButton submit = new JButton(" Calculate ");
        downBox.add(submit);

        frame.add(topBox,BorderLayout.CENTER);
        frame.add(downBox,BorderLayout.SOUTH);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButton(e);
            }
        });
    }
    void submitButton(ActionEvent e){
        String s = e.getActionCommand();
        if (s.equals(" Calculate ")){
        String time = years.getText();
        int y = Integer.parseInt(time);
            System.out.println(y);}

        System.out.println("lol + ");
    }
}

public class futureCal {
    public static void main(String[] args) {
        fCal cal = new fCal();
    }
}
