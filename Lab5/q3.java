import java.util.Scanner;

abstract class GameConsole{
    int allowedKeyStrokes;
    int currentKeyStrokes=0;
    abstract void start(int allowedKeyStrokes);
    abstract void right();
    abstract void left();
    abstract void up();
    abstract void down();
    abstract String status();
}

class FlightSimple extends GameConsole{
    int height,speed;
    void start(int key){
        super.allowedKeyStrokes=key;
        super.currentKeyStrokes=0;
        this.height=0;
        this.speed=0;
    }
    void right() {
        super.currentKeyStrokes++;
        speed++;
    }
    void left() {
        super.currentKeyStrokes++;
        speed--;
    }
    void up() {
        super.currentKeyStrokes++;
        if(speed>=2) height++;
    }
    void down() {
        super.currentKeyStrokes++;
        height--;
    }
    String status(){
        if(speed==0 && height==0 ) return "Game Won";
        else if(speed!=0 && height<=0) return "Game Lost";
        else return "Game Over";
    }
}

class WalkRandom extends GameConsole{
    int[][] grid;
    int posX=10,posY=10;
    void start(int key){
        super.allowedKeyStrokes=key;
        grid = new int[20][20];
    }

    void right() {
        super.currentKeyStrokes++;
            posY++;
    }
    void left() {
        super.currentKeyStrokes++;
        if(posY>0) posY--;
    }
    void down() {
        super.currentKeyStrokes++;
            posX--;
    }
    void up() {
        super.currentKeyStrokes++;
            posX++;
    }
    String status(){
        if(posX>18 || posY>18) return "Game Lost";
        else if(posX==0) return "Game Won";
        else return "Game Over";
    }
}

class Game{
    GameConsole game;
    void startGame(int op){
        if(op==1) game = new WalkRandom();
        else game = new FlightSimple();
    }

    String playGame(int allowed,String controls){
        game.start(allowed);

        for(int i=0;i<controls.length();i++){
            char op = controls.charAt(i);
            switch (op){
                case 'R' -> game.right();
                case 'L' -> game.left();
                case 'U' -> game.up();
                case 'D' -> game.down();
            }
        }
        return game.status();
    }
}

public class PlayGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Game game = new Game();
        String gameOption = input.next();
        while(!gameOption.equals("End")){
            if(gameOption.compareTo("RandomWalk")==0) game.startGame(1);
            else if(gameOption.compareTo("FlightSimple") == 0) game.startGame(2);

            int allowed = input.nextInt();
            input.nextLine();
            String controls = input.nextLine();
            controls = controls.replaceAll("\\s","");

            String gameStatus = game.playGame(allowed,controls);
            if(gameOption.compareTo("RandomWalk")==0) System.out.println("\nRandomWalk : "+gameStatus);
            else System.out.println("\nFlightSimple : "+gameStatus);
            gameOption = input.next();
        }
    }
}
