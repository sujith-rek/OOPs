abstract class Shape{
    int dimensions;
    abstract double getArea();
    abstract String details();

    abstract double getVolume();

}

abstract class TwoDimensionalShape extends Shape{
    TwoDimensionalShape(){
        super.dimensions=2;
    }
    String details(){
        return "TwoDimensionalShape -> dimensions "+dimensions;
    }
}

class Circle extends TwoDimensionalShape{
    double radius;

    Circle(){
        this.radius=1;
    }

    Circle(double radius){
        this.radius=radius;
    }

    double getRadius(){
        return radius;
    }

    void setRadius(double radius){
        this.radius=radius;
    }

    double getArea(){
        return Math.PI*radius*radius;
    }


    double getVolume() {
        return 0;
    }

    String details(){
        return "Circle -> radius "+radius;
    }
}

class Square extends TwoDimensionalShape{
    double side;
    Square(){
        this.side=1;
    }

    Square(double side){
        this.side=side;
    }

    void setSide(double side){
        this.side=side;
    }

    double getSide(){
        return this.side;
    }

    double getArea(){
        return side*side;
    }
    double getVolume() {
        return 0;
    }
    String details(){
        return "Square -> side "+side;
    }

}

class Triangle extends TwoDimensionalShape{
    double side1,side2,side3;
    Triangle(){
        this.side1=1;
        this.side2=1;
        this.side3=1;
    }

    Triangle(double side1,double side2,double side3){
        this.side1=side1;
        this.side2=side2;
        this.side3=side3;
    }

    void setSide1(double side1){
        this.side1=side1;
    }

    void setSide2(double side2){
        this.side2=side2;
    }

    void setSide3(double side3){
        this.side3=side3;
    }

    double getSide1(){
        return this.side1;
    }

    double getSide2(){
        return this.side2;

    }
    double getSide3(){
        return this.side3;
    }

    double getVolume() {
        return 0;
    }

    double getArea(){
        double s = (side1+side3+side2)/2;
        double s1=(s-side1),s2=(s-side2),s3=(s-side3);
        return Math.sqrt(s*s1*s2*s3);
    }

    String details(){
        return "Triangle -> sides "+side1+" "+side2+" "+side3;
    }
}

abstract class ThreeDimensionalShape extends Shape{
    ThreeDimensionalShape(){
        super.dimensions=3;
    }
    String details(){
        return "ThreeDimensionalShape -> dimensions "+dimensions;
    }
}

class Cube extends ThreeDimensionalShape{
    double side;
    Cube(){
        this.side=1;
    }
    Cube(double side){
        this.side=side;
    }

    double getSide(){
        return side;
    }

    void setSide(double side){
        this.side=side;
    }

    double getArea(){
        return 6*side*side;
    }

    double getVolume(){
        return side*side*side;
    }

    String details(){
        return "Cube -> side "+side;
    }
}

class Sphere extends ThreeDimensionalShape{
    double radius;
    Sphere(){
        this.radius=1;
    }
    Sphere(double radius){
        this.radius=radius;
    }

    double getRadius(){
        return this.radius;
    }

    void setRadius(double radius){
        this.radius=radius;
    }

    double getArea(){
        return Math.PI*4*radius*radius;
    }

    double getVolume() {
        return Math.PI*radius*radius*radius*(4/3);
    }

    String details(){
        return "Sphere -> radius "+radius;
    }
}

class Tetrahedron extends ThreeDimensionalShape{
    double side;
    Tetrahedron(){
        this.side=1;
    }
    Tetrahedron(double side){
        this.side=side;
    }

    double getSide(){
        return side;
    }
    void setSide(double side){
        this.side=side;
    }

    double getArea(){
        return Math.sqrt(3)*side*side;
    }

    double getVolume(){
        return side*side*side/(6*Math.sqrt(2));
    }

    String details(){
        return "Tetrahedron -> side "+side;
    }
}

public class Q3 {
    public static void main(String[] args) {
        Shape[] ref = new Shape[12];
        Circle c1 = new Circle();
        Circle c2 = new Circle(7);
        Square s1 = new Square();
        Square s2 = new Square(2.3);
        Triangle t1 = new Triangle();
        Triangle t2 = new Triangle(1.5,2.5,3);
        Sphere sp1 = new Sphere();
        Sphere sp2 = new Sphere(2.6);
        Cube cu1 = new Cube();
        Cube cu2 = new Cube(9);
        Tetrahedron th1 = new Tetrahedron();
        Tetrahedron th2 = new Tetrahedron(9.4);

        ref[0]=c1;
        ref[1]=th1;
        ref[2]=s1;
        ref[3]=cu1;
        ref[4]=t1;
        ref[5]=sp1;
        ref[6]=s2;
        ref[7]=cu2;
        ref[8]=th2;
        ref[9]=t2;
        ref[10]=c2;
        ref[11]=sp2;

        for(int i=0;i<ref.length;i++){
            System.out.println("Array is pointing towards "+(i+1)+" element");
            System.out.println(ref[i].details());
            if(ref[i].dimensions==2){
                System.out.println("Area of this Two Dimensional Shape is "+ref[i].getArea()+"\n");
            }else{
                System.out.println("Surface Area of 3-D object is "+ref[i].getArea()+"\n");
                System.out.println("Volume of this 3-D object is "+ref[i].getVolume()+"\n");
            }
        }

    }
}
