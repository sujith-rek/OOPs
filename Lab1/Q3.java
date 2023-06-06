import java.util.Scanner;

public class Q3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the employee salary : ");
        int salary = input.nextInt();

        if(salary <= 50000){
            System.out.println("Income Tax is nil");
        } else if (salary <= 60000) {
            System.out.println("Income Tax is " + (salary - 50000)/10);
        } else if (salary <= 150000) {
            System.out.println("Income Tax is " + (1000 + (salary - 60000)*2/10));
        } else{
            System.out.println("Income Tax is " + (1000 + 18000 + (salary - 150000)*3/10));
        }
    }
}
