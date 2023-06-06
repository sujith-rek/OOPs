import java.util.Scanner;
public class Q5{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n,prod=1;
        System.out.print("Enter the size of array : ");
        n = input.nextInt();
        int[] left = new int[n+1];
        int[] arr = new int[n];
        int[] right = new int[n+1];
        int[] result = new int[n];
        left[0] = 1;
        right[n-1] = 1;
        for(int i=0;i<n;i++){
            arr[i] = (int) (Math.random()*100);
             left[i+1] = left[i]*arr[i];
        }
        for(int i=n-1;i>0;i--) right[i-1] = arr[i]*right[i];
        for(int i=0;i<n;i++) result[i]=right[i]*left[i];

        System.out.print("Generated array : ");
        for(int i:arr) System.out.print(i+" ");

        System.out.print("\nResults : ");
        for(int i:result) System.out.print(i+" ");


    }
}