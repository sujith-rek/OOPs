import java.util.Scanner;

public class l1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean[] seats = new boolean[10];
        int first=0,economy=5;
        boolean full=false;
        System.out.println("======== Flight Booking System =========");
        System.out.println("1. To Pick seat in first class");
        System.out.println("2. To Pick seat in economy class");
        System.out.println("3. To exit");
        System.out.println("========================================");
        while(!full){
            System.out.print("Enter your option : ");
            int op = input.nextInt();
            switch(op){
                case 1 :{
                    if(first<5){
                        seats[first] = true;
                        boardingPass(first,"First Class");
                        first++;
                    }else{
                        if(economy<10){
                            System.out.println("First class is full, are you ok with Economy?");
                            System.out.println("1. Accept");
                            System.out.println("2. Deny");
                            System.out.print("Enter your option ; ");
                            int option = input.nextInt();
                            if(option==1){

                            }else{
                                System.out.println("Next Fight Leaves in 3 hours");
                            }
                        }else{
                            System.out.println("Flight is full");
                            full=true;
                        }
                    }
                    break;
                }
                case 2:{
                    if(economy<10){
                        seats[economy]=true;
                        boardingPass(economy,"Economy");
                        economy++;
                    }else{
                        if(first<5){
                            System.out.println("Economy class is full, are you ok with First class?");
                            System.out.println("1. Accept");
                            System.out.println("2. Deny");
                            System.out.print("Enter your option ; ");
                            int option = input.nextInt();
                            if(option==1){
                                seats[first] = true;
                                boardingPass(first,"First Class");
                                first++;
                            }else{
                                System.out.println("Next Fight Leaves in 3 hours");
                            }
                        }else{
                            System.out.println("Flight is full");
                            full=true;
                        }
                    }
                    break;
                }
                case 3:{
                    full = true;
                    break;
                }
            }
        }
    }

    static void boardingPass(int s,String c){
        System.out.println("\nSeat Number "+(s+1));
        System.out.println("Class "+c+"\n");
    }

}
