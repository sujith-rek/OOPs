import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter n : ");
        int n = input.nextInt();
        int max=input.nextInt();
        for(int i=1;i<n;i++){
            int k = input.nextInt();
            if(k>max) max=k;
        }
        System.out.println("Maximum of given values is "+max);

    }
}
