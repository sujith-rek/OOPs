import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number to know its factorial : ");
        int n = input.nextInt();
        System.out.println("Factorial of " + n + " is " + factorial(n));
    }
    public static int factorial(int n){
        if(n==1) return 1;
        else return n * factorial(n-1);
    }
}
