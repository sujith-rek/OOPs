import java.util.Scanner;

class TenureException extends Exception{
    TenureException(String error){
        super(error);
    }
    TenureException(){
    }
}

class DepositException extends Exception{
    DepositException(String error){
        super(error);
    }
    DepositException(){
    }
}

public class q1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int Deposit,Tenure,option=1;
        while(option==1){
            System.out.print("Enter 1. To deposit\nAny other number to quit\nEnter your choice : ");
            option = input.nextInt();
            if(option!=1) break;
            System.out.print("Enter Deposit amount and Tenure : ");
            Deposit=input.nextInt();
            Tenure=input.nextInt();
            try {
                double Returns = returns(Deposit,Tenure);
                System.out.println("Maturity Amount is "+Returns);
            }catch (DepositException | TenureException exception){
                System.out.println(exception);
            }

        }
    }

    public static double returns(int deposit,int tenure) throws TenureException,DepositException{
        if(deposit<50000) throw new DepositException("Deposit is under 50,000\n");
        if (tenure<12) throw new TenureException("Tenure shouldn't be less than 12 months\n");
        return deposit+(deposit*(tenure/12)*0.075);
    }
}