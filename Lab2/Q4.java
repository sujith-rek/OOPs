import java.util.Scanner;

public class Q4{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter size of array : ");
        int n,key;

        n = input.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter elements of array : ");
        for(int i=0;i<n;i++) arr[i] = input.nextInt();

        insertSort(arr);
        System.out.print("The Sorted array is : ");
        for(int i:arr) System.out.print(i+" ");
        System.out.print("\nEnter the value to search in the array : ");
        key = input.nextInt();
        int index = binarySearch(arr,key);

        if(index != -1)System.out.println("Index of the value "+key+" is "+(index+1));
        else System.out.println("The value "+key+" Don't exist in the array");
    }

    public static int binarySearch(int[] arr,int key){
        int size = arr.length,start = 0,end = size - 1,mid;
        while(end >= start){
            mid = (end + start)/2;
            if(arr[mid] == key) return mid;
            if(key > arr[mid]) start = mid+1;
            else end = mid-1;
        }
        return -1;
    }

    public static void insertSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
}