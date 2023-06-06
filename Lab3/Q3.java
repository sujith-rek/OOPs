//import java.util.Scanner;
//
//class Object{
//    String name;
//    static int count = 0;
//
//    Object(){
//        name = "";
//        count++;
//    }
//    Object(String name){
//        this.name = name;
//        count++;
//    }
//
//    int show_count(){
//        return count;
//    }
//}
//
//public class Q3 {
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        System.out.println("1.Create new object with a name ");
//        System.out.println("2.To show count i.e number of objects created");
//        int n=0;
//        Object sample = new Object();
//        while(n<3){
//            System.out.print("Enter your option : ");
//            n = input.nextInt();
//            switch (n){
//                case 1 ->{
//                    System.out.print("Enter a name to give for object : ");
//                    String name = input.next();
//                    Object obj = new Object(name);
//                }
//                case 2 ->{
//                    System.out.println("Number of objects created : "+(sample.show_count()-1));
//                }
//            }
//        }
//    }
//}