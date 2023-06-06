import java.util.Scanner;

public class Q1{
    public static void main(String[] args){
        System.out.print("Enter a number N : ");
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for(int i=1;i<=n;i++){
            for(int j=0;j<i;j++) System.out.print(i+" ");
            System.out.println();
        }
    }
}