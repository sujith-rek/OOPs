import java.util.Scanner;

public class Q3{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        double[] arr = new double[10];
        System.out.print("Enter 10 Double numbers : ");
        for(int i=0;i<10;i++) arr[i] = input.nextDouble();
        insertSort(arr);
        printSortedArray(arr);
    }

    public static void insertSort(double[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            double key = arr[i];
            int j = i - 1;
              while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public static void printSortedArray(double[] arr){
        for (double v : arr) System.out.print(v + " ");
    }
}