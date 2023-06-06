import java.util.Scanner;

public class Q6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of Years : ");
        int numberOfYears = input.nextInt();
        System.out.print("Enter monthly Interest Rate : ");
        float monthlyInterestRate = input.nextFloat();
        System.out.print("Enter Loan Amount : ");
        int loanAmount = input.nextInt();
        System.out.printf("Monthly interest is %.2f",monthlyInterest(loanAmount,monthlyInterestRate,numberOfYears));
    }

    public static float monthlyInterest(int loan, float rate,int years){
        float numerator1 = loan*rate/100;
        float denominator2 = (float)(Math.pow((1+(rate/100)),(12*years)));
        float denominator1 = 1 - (1/denominator2);
        return (numerator1/(1-denominator1));
    }
}
