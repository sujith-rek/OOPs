//class Circle2D{
//    double x;
//    double y;
//    double radius;
//    Circle2D(){
//        this.x =0;
//        this.y=0;
//        this.radius = 1.0;
//    }
//
//    Circle2D(double x,double y,double radius){
//        this.x = x;
//        this.y=y;
//        this.radius=radius;
//    }
//
//    void setRadius(double radius){
//        this.radius = radius;
//    }
//
//    double getArea(){
//        return Math.PI*radius*radius;
//    }
//
//    double getPerimeter(){
//        return 2*radius*Math.PI;
//    }
//
//    double distance(double a,double b){
//        double A = (x-a)*(x-a);
//        double B = (y-b)*(y-b);
//        return Math.sqrt(A+B);
//    }
//
//    boolean contains(double x,double y){
//        return !(distance(x, y) > radius);
//    }
//
//    boolean contains(Circle2D circle){
//        double dis = distance(circle.x,circle.y);
//        return dis + circle.radius < this.radius;
//    }
//
//    boolean overlaps(Circle2D circle){
//        double dis = distance(circle.x,circle.y);
//        return dis < this.radius + circle.radius;
//    }
//
//}
//
//public class Q5 {
//    public static void main(String[] args) {
//        Circle2D c1 = new Circle2D(2,2,5.5);
//        System.out.printf("Perimeter of the circle C1 is %.2f \n",c1.getPerimeter());
//        System.out.printf("Area of the circle C1 is %.2f \n",c1.getArea());
//        if(c1.contains(3,3)) System.out.println("3,3 is inside the circle C1");
//        else System.out.println("3,3 is outside the circle C1");
//
//        if(c1.contains(new Circle2D(4,5,10.5))) System.out.println("New circle is inside C1");
//        else System.out.println("New circle isn't inside C1");
//
//        if(c1.overlaps(new Circle2D(3,5,2.3))) System.out.println("New circle Overlaps C1");
//        else System.out.println("New circle don't overlap C1");
//    }
//}
