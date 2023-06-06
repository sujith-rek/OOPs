//import java.util.Scanner;
//
//class circle{
//    double radius;
//    String color ;
//
//    circle(){
//        this.radius = 1.0;
//        this.color = "red";
//    }
//    circle(double radius){
//        this.color = "red";
//        this.radius = radius;
//    }
//
//    circle(double radius,String color){
//        this.radius = radius;
//        this.color = color;
//    }
//
//    double getRadius(){
//        return radius;
//    }
//
//    void setRadius(double radius){
//        this.radius = radius;
//    }
//
//    String getColor(){
//        return color;
//    }
//
//    void setColor(String color){
//        this.color = color;
//    }
//
//    double getArea(){
//        return (radius*radius*Math.PI);
//    }
//
//    String tostring() {
//        return "circle[radius="+radius+", color="+color+"]";
//    }
//}
//
//class Cylinder extends circle{
//    double height;
//    Cylinder(){
//        height= 1.0;
//    }
//
//    Cylinder(double height){
//        this.height = height;
//    }
//
//    Cylinder(double radius, double height){
//        this.radius=radius;
//        this.height=height;
//    }
//
//    Cylinder(double radius,double height,String color){
//        this.height = height;
//        this.color=color;
//        this.radius=radius;
//    }
//
//    double getHeight(){
//        return height;
//    }
//
//    void setHeight(double height){
//        this.height=height;
//    }
//
//    double getVolume(){
//        return Math.PI*radius*radius*height;
//    }
//
//    @Override
//    String tostring(){
//        return "Cylinder[radius="+radius+", height="+height+", color="+color+"]";
//    }
//}
//
//public class Q4 {
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        System.out.print("Enter radius for Cylinder : ");
//        double r = input.nextDouble();
//        System.out.print("Enter height for Cylinder : ");
//        double h = input.nextDouble();
//        System.out.print("Enter the colour for cylinder : ");
//        String c=input.next();
//        Cylinder cy1 = new Cylinder(r,h,c);
//        System.out.printf("Volume of the Cylinder is %.2f \n",cy1.getVolume());
//        System.out.println("Created Cylinder details => "+cy1.tostring());
//    }
//}
