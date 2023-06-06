import java.util.Scanner;

public class Q2{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the size of array : ");
        int n = input.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter the elements of array : ");
        for(int i=0;i<n;i++) arr[i] = input.nextInt();
        printPrime(arr,n);
    }

    public static void printPrime(int[] arr,int n){
        System.out.print("Primes in given array are : ");
        for(int i=0;i<n;i++) if(isPrime(arr[i])) System.out.print(arr[i]+" ");
    }

    public static boolean isPrime(int n){
        if(n==1) return false;
        boolean flag = true;
        for(int i=2;i<=n/2;i++){
            if(n%i == 0){
                flag = false;
                break;
            }
        }
        return flag;
    }
}