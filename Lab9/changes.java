//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//class keyEvent extends JApplet implements KeyListener{
//    @Override
//    public void init() {
//        addKeyListener(this);
//        requestFocus();
//    }
//
//    @Override
//    public void keyTyped(KeyEvent e) {
//        System.out.println("lol");
//        int i = e.getKeyCode();
//        switch (i){
//            case KeyEvent.VK_UP -> Y[0]--;
//            case KeyEvent.VK_DOWN -> Y[0]++;
//            case KeyEvent.VK_LEFT -> X[0]--;
//            case KeyEvent.VK_RIGHT -> X[0]++;
//        }
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        System.out.println("lol1");
//
//        int i = e.getKeyCode();
//        switch (i){
//            case KeyEvent.VK_UP -> Y[0]--;
//            case KeyEvent.VK_DOWN -> Y[0]++;
//            case KeyEvent.VK_LEFT -> X[0]--;
//            case KeyEvent.VK_RIGHT -> X[0]++;
//        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        System.out.println("lol2");
//
//        int i = e.getKeyCode();
//        switch (i){
//            case KeyEvent.VK_UP -> Y[0]--;
//            case KeyEvent.VK_DOWN -> Y[0]++;
//            case KeyEvent.VK_LEFT -> X[0]--;
//            case KeyEvent.VK_RIGHT -> X[0]++;
//        }
//    }
//}
//
//public class changes{
//    public static void main(String[] args) {
//        final int[] X = {20};
//        final int[] Y = { 20 };
//        JFrame frame = new JFrame("Trying keys");
//        frame.setVisible(true);
//        frame.setLocationRelativeTo(null);
//        frame.setSize(300,300);
//        JPanel panel = new JPanel();
//        panel.setLocation(X[0], Y[0]);
//        frame.add(panel);
//        JLabel kay = new JLabel("K");
//        panel.add(kay,BorderLayout.CENTER);
//        panel.addKeyListener(new KeyListener() {
//            @Override
//
//        });
//    }
//}