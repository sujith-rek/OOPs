import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEvent_ex extends JFrame implements KeyListener {
    static JLabel jl = new JLabel("K");
    @Override
    public void keyTyped(KeyEvent e) {
        int keyp = e.getKeyCode();
        switch(keyp) {
            case KeyEvent.VK_UP -> {
                jl.setBounds(100,80,30,20);
            }
            case KeyEvent.VK_DOWN -> {
                jl.setBounds(100,120,30,20);
            }
            case KeyEvent.VK_RIGHT -> {
                jl.setBounds(130,100,30,20);
            }
            case KeyEvent.VK_LEFT -> {
                jl.setBounds(70,100,30,20);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyp = e.getKeyCode();
        switch(keyp) {
            case KeyEvent.VK_UP -> {
                jl.setBounds(100,80,30,20);
            }
            case KeyEvent.VK_DOWN -> {
                jl.setBounds(100,120,30,20);
            }
            case KeyEvent.VK_RIGHT -> {
                jl.setBounds(130,100,30,20);
            }
            case KeyEvent.VK_LEFT -> {
                jl.setBounds(70,100,30,20);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyp = e.getKeyCode();
        switch(keyp) {
            case KeyEvent.VK_UP -> {
                jl.setBounds(100,80,30,20);
            }
            case KeyEvent.VK_DOWN -> {
                jl.setBounds(100,120,30,20);
            }
            case KeyEvent.VK_RIGHT -> {
                jl.setBounds(130,100,30,20);
            }
            case KeyEvent.VK_LEFT -> {
                jl.setBounds(70,100,30,20);
            }
        }
    }
    public void init(){
        addKeyListener(this);
        requestFocus(true);
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame("letter Movement");
        jf.setLayout(null);
        jf.setSize(300,300);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
        jl.setBounds(100,100,30,20);
        jf.add(jl);
        jl.addKeyListener(new KeyEvent_ex());
    }
}
