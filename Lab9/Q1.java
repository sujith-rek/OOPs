import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

class dataobj {
    private String Name;
    private String Password ;

    public void setName(String name) {
        this.Name = name;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }
}

public class Q1 {
    static HashMap<String,dataobj> mainmap = new HashMap<String,dataobj>();
    public static void main(String[] args) {
        JFrame frame1 = new JFrame("Welcome to my DataBase");
        frame1.setVisible(true);
        frame1.setSize(350,250);
        frame1.setLayout(new BorderLayout());
        frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
        JFrame frame2 = new JFrame("Sing Up Page");
        frame2.setSize(350,250);
        frame2.setLayout(new BorderLayout());
        JFrame frame3 = new JFrame("Sign in Page");
        frame3.setSize(350,250);
        frame3.setLayout(new BorderLayout());
        JFrame framesuccess = new JFrame();
        framesuccess.setSize(300,150);
        framesuccess.setLayout(new BorderLayout());
        JFrame framefailure = new JFrame();
        framefailure.setSize(300,150);
        framefailure.setLayout(new BorderLayout());
        JFrame framesuccess2 = new JFrame();
        framesuccess2.setSize(300,150);
        framesuccess2.setLayout(new BorderLayout());
        JFrame framefailure2 = new JFrame();
        framefailure2.setSize(300,150);
        framefailure2.setLayout(new BorderLayout());
        framefailure2.add(new JLabel("Failure, User don't exists"));
        framesuccess.setVisible(false);
        framesuccess2.setVisible(false);
        framefailure.setVisible(false);
        framefailure2.setVisible(false);
        JPanel frame1p1 = new JPanel();
        frame1p1.add(new JLabel("Click on the button"));
        JPanel frame1p2 = new JPanel(new FlowLayout(FlowLayout.CENTER,50,50));
        JButton sin = new JButton("Sign In");
        JButton sup = new JButton("Sign Up");

        frame1p2.add(sin);
        frame1p2.add(sup);
        frame1.add(frame1p1,BorderLayout.NORTH);
        frame1.add(frame1p2,BorderLayout.CENTER);
        JPanel frame2p1 = new JPanel(new GridLayout(3,2,20,10));
        JPanel frame2p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        frame2.add(frame2p1,BorderLayout.CENTER);
        frame2.add(frame2p2,BorderLayout.SOUTH);
        frame2p1.add(new JLabel("Name"));
        JTextField namefield = new JTextField();
        frame2p1.add(namefield);
        frame2p1.add(new JLabel("UserName"));
        JTextField userfield = new JTextField();
        frame2p1.add(userfield);
        frame2p1.add(new JLabel("Password"));
        JTextField passfield = new JTextField();
        frame2p1.add(passfield);
        JButton submitbut1 = new JButton("Submit");
        frame2p2.add(submitbut1);
        dataobj data = new dataobj();

        JPanel frame3p1 = new JPanel(new GridLayout(2,2,10,50));
        JPanel frame3p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        frame3.add(frame3p1,BorderLayout.CENTER);
        frame3.add(frame3p2,BorderLayout.SOUTH);
        frame3p1.add(new JLabel("UserName"));
        JTextField userfield2 = new JTextField();
        frame3p1.add(userfield2);
        frame3p1.add(new JLabel("Password"));
        JTextField passfield2 = new JTextField();
        frame3p1.add(passfield2);
        JButton submitbut2 = new JButton("Submit");
        frame3p2.add(submitbut2);
        dataobj data2 = new dataobj();

        sin.addActionListener(e -> {
            frame1.setVisible(false);
            frame3.setVisible(true);
        });
        sup.addActionListener(e -> {
            frame1.setVisible(false);
            frame2.setVisible(true);
        });

        submitbut1.addActionListener(e -> {
            data.setName(namefield.getText());
            data.setPassword(passfield.getText());
            if(!checksignup(data, userfield.getText())){
                namefield.setText("");
                userfield.setText("");
                passfield.setText("");
                frame2.setVisible(false);
                frame1.setVisible(true);
                framefailure.setVisible(true);
                framefailure.add(new JLabel("Failure," + data.getName()+" User already exists"));
            }
            else {
                namefield.setText("");
                userfield.setText("");
                passfield.setText("");
                frame2.setVisible(false);
                frame1.setVisible(true);
                framesuccess.setVisible(true);
                framesuccess.add(new JLabel(data.getName()+ " Successly added the user"));
            }
        });

        submitbut2.addActionListener(e -> {
            data2.setPassword(passfield2.getText());
            if(checksignin(data2, userfield2.getText())) {
                frame3.setVisible(false);
                frame1.setVisible(true);
                framesuccess2.setVisible(true);
                framesuccess2.add(new JLabel("Welcome "+mainmap.get(userfield2.getText()).getName()));
                userfield2.setText("");
                passfield2.setText("");
            }
            else {
                userfield2.setText("");
                passfield2.setText("");
                frame3.setVisible(false);
                frame1.setVisible(true);
                framefailure2.setVisible(true);
            }
        });
    }

    static boolean checksignin(dataobj obj , String username){
        return mainmap.containsKey(username);
    }
    static boolean checksignup(dataobj obj,String username){
        if(!mainmap.containsKey(username)) mainmap.put(username,obj);
        return !mainmap.containsKey(username);
    }
}