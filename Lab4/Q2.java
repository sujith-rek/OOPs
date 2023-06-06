import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        MinimumStack stack1 = new MinimumStack();
        MinimumStack stack2 = new MinimumStack();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the bracket sequence : ");
        String bracket = input.next();
        for(int i=0;i<bracket.length();i++) stack1.push(bracket.charAt(i));
        if(balanced(stack1,stack2)) System.out.println("Balanced String");
        else System.out.println("Unbalanced string");
    }

    static boolean balanced(MinimumStack a,MinimumStack b){
        boolean status = true;
        while(a.top!=null){
            if(b.top==null) b.push(a.top());
            else{
                if(a.peek()-b.peek() == '('-')' || a.peek()-b.peek() == '['-']' || a.peek()-b.peek() == '{'-'}'){
                    a.pop();
                    b.pop();
                }else b.push(a.top());
            }
            if(a.top==null && b.top!=null) status=false;
        }
        return status;
    }

}
