import java.util.*;

interface ISavable {
    List<String> write();
    void read(List<String> savedValues);
    String toString();
}

class Players implements ISavable {
    List<String> values;
    @Override
    public List<String> write() {
        return values;
    }

    @Override
    public void read(List<String> savedValues) {
        values = savedValues;
    }

    @Override
    public String toString() {
        return "Players "+ values;
    }
}

class Monsters implements ISavable {
    List<String> values;
    @Override
    public List<String> write() {
        return values;
    }

    @Override
    public void read(List<String> savedValues) {
        values = savedValues;
    }

    @Override
    public String toString() {
        return "Monsters "+values;
    }
}

public class QuestionTwo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean quit = false;
        System.out.println("Press\n"+"1 to enter Players\n" + "2 to enter Monsters\n" + "0 to Quit\n");

        while (!quit) {
            System.out.print("Choose (Players or Monsters): ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                        case 1 ->{
                            Players player = new Players();
                            boolean back = false;
                            System.out.println("Press\n" + "1. Enter name of players\n" + "2. Display name of players");
                            System.out.println("3. Display toString \n" +"Any other to go back\n");
                            while (!back) {
                                System.out.print("Enter your Choice: ");
                                int option = input.nextInt();
                                input.nextLine();
                                switch (option) {
                                    case 1 ->{
                                        ArrayList<String> values = new ArrayList<String>();
                                        values = readValues();
                                        player.read(values);
                                    }
                                    case 2 -> {
                                        List<String> persons = player.values;
                                        System.out.println(persons);
                                    }
                                    case 3 -> System.out.println(player.toString()+"\n");

                                    default -> back = true;
                                }
                            }
                        }

                        case 2 ->{
                            Monsters monsters = new Monsters();
                            boolean back = false;
                            System.out.println("Press\n" + "1. Enter name of Monsters\n"+"2. Display name of monsters");
                            System.out.println("3. Display toString"+"Any other to go back\n");
                            while (!back) {
                                System.out.print("Enter your Choice: ");
                                int option = input.nextInt();
                                input.nextLine();
                                switch (option) {
                                    case 1 ->{
                                        ArrayList<String> values = new ArrayList<String>();
                                        values = readValues();
                                        monsters.read(values);
                                    }

                                    case 2 ->{
                                        List<String> persons = monsters.values;
                                        System.out.println(persons);
                                    }

                                    case 3 -> System.out.println(monsters.toString()+"\n");

                                    default -> back = true;
                                }
                            }
                        }
                        default -> quit = true;
            }
        }
    }

    public static ArrayList<String> readValues() {
        ArrayList<String> values = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        boolean quit = false;
        int index = 0;
        System.out.println("Choose\n"+"1 to enter string\n" + "0 to quit");
        while (!quit) {
            System.out.print("Choose (Enter string or quit): ");
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                default -> quit = true;

                case 1 ->{
                    System.out.print("Enter a string : ");
                    String stringInput = input.nextLine();
                    values.add(index , stringInput);
                    index++;
                }

            }
        }
        return values;
    }
}
