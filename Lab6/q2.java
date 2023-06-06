import java.util.Scanner;

class CustomException extends Exception{
    CustomException(String error){
        super(error);
    }
    CustomException(){
    }
}

public class q2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int option = 0;
        while(option < 4){

            System.out.println("1. Division\n2. Square Root\n3. Factorial\n4. Exit\n");
            System.out.print("Enter your arithmetic operation : ");
            option=input.nextInt();
            switch (option){
                case 1 ->{
                    System.out.print("Enter Dividend and Divisor : ");
                    int dividend = input.nextInt();
                    int divisor = input.nextInt();
                    try{
                        double quotient = division(dividend,divisor);
                        System.out.println("Quotient of "+dividend+" / "+divisor+" is "+quotient);
                    }catch (CustomException exception) {
                        System.out.println(exception + "\n");
                    }
                }

                case 2 ->{
                    System.out.print("Enter Number to find Square Root : ");
                    int SquareRoot = input.nextInt();
                    try{
                        double root = squareRoot(SquareRoot);
                        System.out.println("Square Root of given number is "+root);
                    }catch (CustomException exception){
                        System.out.println(exception+"\n");
                    }
                }

                case 3 ->{
                    System.out.print("Enter number to get its factorial : ");
                    int fac = input.nextInt();
                    try {
                        int Factorial = factorial(fac);
                        System.out.println("Factorial of given number is "+Factorial);
                    }catch (CustomException exception){
                        System.out.println(exception+"\n");
                    }
                }
            }
        }
    }

    public static double division(int dividend,int divisor) throws CustomException {
        if(divisor == 0) throw new CustomException("Divisor is 0");
        return (double) dividend/divisor;
    }

    public static double squareRoot(int n) throws CustomException{
        if(n<0) throw new CustomException("Number shouldn't be less than 0");
        return Math.sqrt(n);
    }

    public static int factorial(int n) throws CustomException{
        if(n<0) throw new CustomException("Number shouldn't be less than 0");
        int fac=1;
        for(int i=1;i<=n;i++) fac*=i;
        return fac;
    }
}