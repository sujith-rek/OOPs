import javax.swing.*;
import java.awt.*;
import java.util.TreeMap;

class LoginApp{
    private final TreeMap<String,String> LoginDatabase,UserDatabase;
    JFrame PageA;
    JButton SignIn = new JButton(" Sign In"),
            SignUp = new JButton(" Sign Up");
    LoginApp(){
        PageA = new JFrame(" Login ");
        LoginDatabase = new TreeMap<>();
        UserDatabase = new TreeMap<>();
        PageA.setLocationRelativeTo(null);
        PageA.setSize(350,200);
        PageA.setLayout(new FlowLayout(FlowLayout.CENTER,50,50));
        PageA.add(SignUp);
        PageA.add(SignIn);
    }

    void start(){
        LoginApp obj = new LoginApp();
        PageA.setVisible(true);

        SignIn.addActionListener(e -> {
            SignInPage USerSignIn = new SignInPage();
            USerSignIn.start(obj);
        });
        SignUp.addActionListener(e -> {
            SignUpPage UserSignUp = new SignUpPage();
            UserSignUp.start(obj);
        });
    }
    boolean checkUsername(String u){return LoginDatabase.containsKey(u);}
    boolean validateCreds(String u,String p){
        if(checkUsername(u)) return (LoginDatabase.get(u)).equals(p);
        return false;
    }
    void addLoginApp(String name,String username,String password){
        LoginDatabase.put(username,password);
        UserDatabase.put(username,name);
    }
    String NameOfUser(String u){return UserDatabase.get(u);}
}

class SignUpPage {
    JFrame SignUp;
    private final JTextField txt1 = new JTextField(),
            txt2 = new JTextField(),
            txt3 = new JTextField();
    private final JButton submit = new JButton(" Submit "),
            okay = new JButton(" OK ");

    private String Username1,Password1,Name1;
    JDialog status = new JDialog(SignUp,"Sign Up Status");
    JPanel statPanel = new JPanel();
    JLabel SignUpStatus = new JLabel();
    SignUpPage(){
        SignUp = new JFrame(" Sign Up");
        SignUp.setSize(350,300);
        SignUp.setLocationRelativeTo(null);
        SignUp.setLayout(new GridLayout(4,1,10,20));

        JPanel p1 = new JPanel(new GridLayout(1,2));
        JLabel lp1 = new JLabel(" Name : ");
        txt1.setFont(new Font(Font.SERIF,Font.PLAIN,17));
        p1.add(lp1);
        p1.add(txt1);

        JPanel p2 = new JPanel(new GridLayout(1,2));
        JLabel lp2 = new JLabel(" User Name : ");
        txt2.setFont(new Font(Font.SERIF,Font.PLAIN,17));

        p2.add(lp2);
        p2.add(txt2);

        JPanel p3 = new JPanel(new GridLayout(1,2));
        JLabel lp3 = new JLabel(" Password : ");
        txt3.setFont(new Font(Font.SERIF,Font.PLAIN,17));

        p3.add(lp3);
        p3.add(txt3);

        JPanel Submit = new JPanel();
        Submit.add(submit,BorderLayout.CENTER);

        SignUp.add(p1);
        SignUp.add(p2);
        SignUp.add(p3);
        SignUp.add(Submit);

        status.setLayout(new BorderLayout());
        status.setSize(300,300);

        status.setLocationRelativeTo(SignUp);

        statPanel.setLayout(new GridLayout(2,1));
        statPanel.add(SignUpStatus,BorderLayout.CENTER);
        statPanel.add(okay,BorderLayout.CENTER);
        status.add(statPanel,BorderLayout.CENTER);
    }

    void start(LoginApp loginApp){

        SignUp.setVisible(true);
        submit.addActionListener(e -> {
            Name1 = txt1.getText();
            Username1 = txt2.getText();
            Password1 = txt3.getText();

            status.setVisible(true);
            boolean stat = loginApp.checkUsername(Username1);
            if (stat) SignUpStatus.setText("               "+Name1+" already exists in system");
            else{
                loginApp.addLoginApp(Name1,Username1,Password1);
                SignUpStatus.setText("               "+Name1+" have successfully registered ");
            }
            okay.addActionListener(e1 -> {
                status.dispose();
                if(!stat) SignUp.dispose();
            });
        });
    }
}

class SignInPage{
    JFrame SignIn;
    private final JButton Submit = new JButton(" Submit "),
         okay = new JButton(" OK ");
    JPanel Password = new JPanel(new GridLayout(1,2)),
            UserName = new JPanel(new GridLayout(1,2)),
            statPanel = new JPanel(),
            submit = new JPanel();

    JLabel password = new JLabel(" Password : "),
            UName = new JLabel(" User Name : "),
            LoginStatus = new JLabel();

    private final JTextField PassWord = new JTextField(),
            username = new JTextField();
    JDialog status = new JDialog(SignIn,"Sign in status");

    private String Username1,Password1;

    SignInPage(){
        SignIn = new JFrame(" Sign In");
        SignIn.setSize(350,200);
        SignIn.setLocationRelativeTo(null);
        SignIn.setLayout(new GridLayout(3,1,10,20));
        username.setFont(new Font(Font.SERIF,Font.PLAIN,17));
        UserName.add(UName);
        UserName.add(username);
        PassWord.setFont(new Font(Font.SERIF,Font.PLAIN,17));
        Password.add(password);
        Password.add(PassWord);
        SignIn.add(UserName);
        SignIn.add(Password);
        submit.add(Submit,BorderLayout.CENTER);
        SignIn.add(submit);

        status.setLayout(new BorderLayout());
        status.setSize(300,300);

        status.setLocationRelativeTo(SignIn);

        statPanel.setLayout(new GridLayout(2,1));
        statPanel.add(LoginStatus,BorderLayout.CENTER);
        statPanel.add(okay,BorderLayout.CENTER);
        status.add(statPanel,BorderLayout.CENTER);

    }
    void start(LoginApp obj){
        SignIn.setVisible(true);
        Submit.addActionListener(e -> {
            Username1= username.getText();
            Password1=PassWord.getText();
            status.setVisible(true);
            if(obj.validateCreds(Username1,Password1))
                LoginStatus.setText("               "+"Welcome "+obj.NameOfUser(Username1));
            else LoginStatus.setText("               "+"Wrong Username / Password");
            okay.addActionListener(e1 -> {
                SignIn.dispose();
                status.dispose();
            });
        });
    }
}

public class Login {
    public static void main(String[] args) {
        LoginApp StartApp = new LoginApp();
        StartApp.start();
    }
}