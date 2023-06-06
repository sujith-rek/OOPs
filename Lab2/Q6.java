import java.util.Scanner;

class bankAccount{
    String name;
    long accountNumber;
    String accountType;
    int accountBalance;

    void assignInitialValues(String Name,long AccountNumber,String AccountType,int AccountBalance){
        this.name=Name;
        this.accountNumber = AccountNumber;
        this.accountType = AccountType;
        this.accountBalance = AccountBalance;
    }

    void deposit(long DepositAmount){
        accountBalance += DepositAmount;
    }

    void withDraw(long WithdrawAmount){
        accountBalance -= WithdrawAmount;
    }

    void display(){
        System.out.println("Account Holder name => " + name);
        System.out.println("Account Balance => "+accountBalance);
    }

}

public class Q6 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        bankAccount newAccount = new bankAccount();
        int i=0;
        System.out.println("===================================================================");
        System.out.println("1.Assign Initial values ");
        System.out.println("2.Deposit ");
        System.out.println("3.Withdraw ");
        System.out.println("4.Display Account Holder Name and Respective Account Balance ");
        System.out.println("Any other number to exit ");
        System.out.println("===================================================================");
        while(i==0){
            System.out.print("\nEnter the option : ");
            int option = input.nextInt();
            switch (option) {
                case 1 -> {
                    System.out.print("Enter the name : ");
                    String name = input.next();
                    System.out.print("Enter your Account Number : ");
                    long accountNumber = input.nextLong();
                    System.out.print("Enter the Account Type : ");
                    String accountType = input.next();
                    System.out.print("Enter Account Balance : ");
                    int accountBalance = input.nextInt();
                    System.out.println("Account initialised successfully\n");
                    newAccount.assignInitialValues(name,accountNumber,accountType,accountBalance);
                }

                case 2 -> {
                    System.out.print("Enter deposit amount : ");
                    long depositAmount = input.nextLong();
                    newAccount.deposit(depositAmount);
                    System.out.println("Deposit successful!!");
                }

                case 3 -> {
                    System.out.print("Enter Withdraw amount : ");
                    long withdrawAmount = input.nextLong();
                    if(withdrawAmount < newAccount.accountBalance) {
                        newAccount.withDraw(withdrawAmount);
                        System.out.println("Withdraw successful!!");
                        }
                    else System.out.println("Not Sufficient Funds");
                }

                case 4 -> newAccount.display();
                default -> i=1;
                }
            }
        }
}