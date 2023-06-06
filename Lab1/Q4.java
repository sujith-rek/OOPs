import java.util.Scanner;

public class Q4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter n : ");
        int n = input.nextInt();
        int sumEven=0,sumOdd=0,even=0,odd=0;
        for(int i=0;i<n;i++){
            System.out.print("Enter the "+(i+1)+" Input : ");
            int k = input.nextInt();
            if(k%2==0) {
                sumEven+=k;
                even++;
            }
            else {
                sumOdd+=k;
                odd++;
            }
        }
        System.out.println("Sum of Even numbers is "+sumEven+", Its average is "+sumEven/even);
        System.out.println("Sum of Odd numbers is "+sumOdd+", Its average is "+sumOdd/odd);
    }
}
