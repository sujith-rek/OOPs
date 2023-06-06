import javax.swing.*;
import java.awt.*;

public class CheckerBoard {
    public static void main(String[] args) {
        JFrame checkerboard = new JFrame("Checker Board");
        checkerboard.setVisible(true);
        checkerboard.setSize(400,400);
        checkerboard.setLayout(new GridLayout(8,8));
        int stat;
        for(int i=0;i<8;i++){
            if(i%2==1) stat=1;
            else stat=0;
            for(int j=0;j<8;j++){
                JButton square = new JButton();
                if(stat==0) {
                    stat=1;
                    square.setBackground(Color.WHITE);
                }else{
                    stat=0;
                    square.setBackground(Color.BLACK);
                }
                checkerboard.add(square);
            }
        }
    }
}
