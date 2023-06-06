//
//import java.util.*;
//
//class Sort {
//    void sort(String arr, int n) {
//        char[] array = arr.toCharArray();
//        for (int i = 1; i < n; ++i) {
//            char key = array[i];
//            int j = i - 1;
//            while (j >= 0 && array[j] > key) {
//                array[j + 1] = array[j];
//                j = j - 1;
//            }
//            array[j + 1] = key;
//        }
//        arr = array.toString();
//        System.out.println(array);
//    }
//
//    void sort(int arr[], int n, boolean reverse) {
//        for (int i = 1; i < n; ++i) {
//            int key = arr[i];
//            int j = i - 1;
//            while (j >= 0 && arr[j] > key) {
//                arr[j + 1] = arr[j];
//                j = j - 1;
//            }
//            arr[j + 1] = key;
//        }
//        if (reverse) {
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
//    void sort(int arr[], int n) {
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
//}
//
//public class Question4 {
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        Sort sorting = new Sort();
//        System.out.print("Press 1 for integer array and 2 for string :- ");
//        int option = sc.nextInt();
//        if (option == 1) {
//            System.out.print("Enter the size of the array :- ");
//            int n = sc.nextInt();
//            int[] array = new int[n];
//            System.out.print("Enter the elements of the array :- ");
//            for (int i = 0; i < n; i++) {
//                array[i] = sc.nextInt();
//            }
//            System.out.print("Do you want to reverse the array:- ");
//            boolean reverse = sc.nextBoolean();
//            if (reverse) {
//                sorting.sort(array, n, reverse);
//                System.out.print("Array after sorting and reversing is :- ");
//                for (int i = 0; i < n; i++) {
//                    System.out.print(array[i] + " ");
//                }
//                System.out.println();
//            } else {
//                sorting.sort(array, n);
//                System.out.print("Array after sorting is :- ");
//                for (int i = 0; i < n; i++) {
//                    System.out.print(array[i] + " ");
//                }
//                System.out.println();
//            }
//        } else if (option == 2) {
//            System.out.print("Enter the string :- ");
//            String s = sc.next();
//            System.out.print("The string after sorting is :- ");
//            sorting.sort(s, s.length());
//        }
//        sc.close();
//    }
//}