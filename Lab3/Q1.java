//import java.util.Scanner;
//
//public class Q1 {
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        System.out.print("Enter the size of array : ");
//        int size = input.nextInt();
//        System.out.print("Enter 1 to give integer input or 2 for String input : ");
//        int op = input.nextInt();
//        switch (op){
//            case 1 ->{
//                int[] arr = new int[size];
//                System.out.print("Enter the elements of array : ");
//                for(int i=0;i<size;i++) arr[i] = input.nextInt();
//                System.out.print("Do you want to reverse the sorting order? if yes type 1 : " );
//                int rev = input.nextInt();
//                if(rev==1) sort(arr,size,true);
//                else sort(arr,size);
//                System.out.println("Sorted array is : ");
//                for(int e:arr) System.out.print(e+" ");
//            }
//            case 2 ->{
//                String[] arr = new String[size];
//                System.out.print("Enter the elements of array : ");
//                for(int i=0;i<size;i++)   arr[i] = input.next();
//                sort(arr,size);
//                System.out.println("Sorted array is : ");
//                for(String e:arr) System.out.print(e+" ");
//            }
//        }
//    }
//
//    static void sort(int[] arr,int n){
//        for (int i = 1; i < n; ++i) {
//            int key = arr[i];
//            int j = i - 1;
//            while (j >= 0 && arr[j] > key) {
//                arr[j + 1] = arr[j];
//                j = j - 1;
//            }
//            arr[j + 1] = key;
//        }
//    }
//
//    static void sort(int[] arr,int n,boolean reverse){
//        sort(arr,n);
//        if(reverse){
//            int maxIndex = n - 1;
//            int halfLength = n / 2;
//            for (int i = 0; i < halfLength; i++) {
//                int temp = arr[i];
//                arr[i] = arr[maxIndex - i];
//                arr[maxIndex - i] = temp;
//            }
//        }
//    }
//
//    static void sort(String[] arr,int n){
//        for(int i=0;i<n;i++){
//            for(int j=0;j<n;j++){
//                if(arr[i].compareTo(arr[j]) < 0){
//                    String temp = arr[i];
//                    arr[i] = arr[j];
//                    arr[j] = temp;
//                }
//            }
//        }
//    }
//}
