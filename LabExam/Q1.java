//class Triangle{
//    double side1,side2,side3;
//    Triangle(){
//        this.side1=1;
//        this.side2=1;
//        this.side3=1;
//    }
//
//    Triangle(double side1,double side2,double side3){
//        this.side1=side1;
//        this.side2=side2;
//        this.side3=side3;
//    }
//
//    void setSide1(double side1){
//        this.side1=side1;
//    }
//
//    void setSide2(double side2){
//        this.side2=side2;
//    }
//
//    void setSide3(double side3){
//        this.side3=side3;
//    }
//
//    double getSide1(){
//        return this.side1;
//    }
//
//    double getSide2(){
//        return this.side2;
//
//    }double getSide3(){
//        return this.side3;
//    }
//    double getArea(){
//        double s = (side1+side3+side2)/2;
//        double s1=(s-side1),s2=(s-side2),s3=(s-side3);
//        return Math.sqrt(s*s1*s2*s3);
//    }
//
//    double getPerimeter(){
//        return side1+side2+side3;
//    }
//
//}
//public class Q1 {
//    public static void main(String[] args) {
//        Triangle t1 = new Triangle(4,5,6);
//        Triangle t2 = new Triangle(1.5,2.5,3.5);
//
//        System.out.println("Properties of Triangle 1");
//        System.out.println("side 1 is "+t1.getSide1()+" side 2 is "+t1.getSide2()+" side 3 is "+t1.getSide3());
//        System.out.println("Area is "+t1.getArea());
//        System.out.println("Perimeter is "+t1.getPerimeter());
//
//
//        System.out.println("\nProperties of Triangle 2");
//        System.out.println("side 1 is "+t2.getSide1()+" side 2 is "+t2.getSide2()+" side 3 is "+t2.getSide3());
//        System.out.println("Area is "+t2.getArea());
//        System.out.println("Perimeter is "+t2.getPerimeter());
//    }
//}
