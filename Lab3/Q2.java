//import java.util.Scanner;
//
//class Shapes{
//    float area;
//    int dimension;
//
//    int get_dimension(){
//        return dimension;
//    }
//}
//
//class Circle extends Shapes{
//    float radius;
//
//    Circle(){
//        this.radius = 0;
//        dimension = 1;
//    }
//
//    Circle(float r){
//        dimension = 1;
//        this.radius = r;
//    }
//
//    float area(){
//        area = (float)(radius*radius*Math.PI);
//        return area;
//    }
//}
//
//class Rectangle extends Shapes{
//    float length;
//    float breadth;
//
//    Rectangle(){
//        this.length = 0;
//        this.breadth = 0;
//        dimension = 2;
//    }
//
//    Rectangle(float l,float b){
//        this.length = l;
//        this.breadth = b;
//        dimension = 2;
//    }
//
//    float area(){
//        area = length*breadth;
//        return area;
//    }
//}
//
//class Triangle extends Shapes{
//    float sideA;
//    float sideB;
//    float sideC;
//
//    Triangle(){
//        this.sideA=0;
//        this.sideB=0;
//        this.sideC=0;
//        dimension = 3;
//    }
//
//    Triangle(float a,float b,float c){
//        this.sideA=a;
//        this.sideB=b;
//        this.sideC=c;
//        dimension = 3;
//    }
//
//    float area(){
//        float S = (sideA+sideC+sideB)/2;
//        float prod = S*(S-sideA)*(S-sideB)*(S-sideC);
//        area = (float) Math.sqrt(prod);
//        return area;
//    }
//}
//
//public class Q2 {
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        System.out.println("1. Create a Circle and find the area");
//        System.out.println("2. Create a Rectangle and find the area");
//        System.out.println("3. Create a Triangle and find its area");
//        System.out.println("Enter any other number to exit");
//        int n = 0;
//        while(n<4){
//            System.out.print("Enter an option : ");
//            n = input.nextInt();
//            switch (n){
//                case 1 -> {
//                    System.out.print("Enter the radius of Circle : ");
//                    float radius = input.nextFloat();
//                    Circle shape1 = new Circle(radius);
//                    System.out.println("Area of the Circle is "+shape1.area());
//                }
//                case 2 -> {
//                    System.out.print("Enter the Length and breadth of rectangle : ");
//                    float length = input.nextFloat();
//                    float breadth = input.nextFloat();
//                    Rectangle shape2 = new Rectangle(length,breadth);
//                    System.out.println("Area of the rectangle is "+shape2.area());
//                }
//                case 3 ->{
//                    System.out.print("Enter the sides of triangle : ");
//                    float a = input.nextFloat();
//                    float b = input.nextFloat();
//                    float c= input.nextFloat();
//                    Triangle shape3 = new Triangle(a,b,c);
//                    System.out.println("Area of the triangle is "+shape3.area());
//                }
//            }
//        }
//    }
//}
