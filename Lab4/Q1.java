import java.util.Scanner;

class node{
    int data;
    node next;

    node(int data){
        this.data=data;
        this.next = null;
    }
}

class MinimumStack{
    static int min;
    node top;

    void push(int val){
        if(top==null) min = val;
        if(val<min) min = val;
        node Node = new node(val);
        if(top!=null) Node.next = top;
        top=Node;
    }

    void pop(){
        top = top.next;
    }

    int peek(){
        return this.top.data;
    }

    int top(){
        int n=this.top.data;
        top = top.next;
        return n;
    }

    int getMin(){
        return min;
    }
}

public class Q1 {
    public static void main(String[] args) {
        MinimumStack stack = new MinimumStack();
        Scanner input = new Scanner(System.in);
        boolean k = true;
        System.out.println("==============================================");
        System.out.println("1. Push");
        System.out.println("2. Pop");
        System.out.println("3. Peek");
        System.out.println("4. top");
        System.out.println("5. get Minimum");
        System.out.println("==============================================");

        while(k){
            System.out.print("Enter your choice : ");
            int op=input.nextInt();
            switch (op){
                case 1->{
                    System.out.print("Enter value to push : ");
                    int push = input.nextInt();
                    stack.push(push);
                }
                case 2->{
                    stack.pop();
                }
                case 3->{
                    System.out.println("Peeked value : "+stack.peek());
                }
                case 4->{
                    System.out.println("Top value : "+stack.top());
                }
                case 5->{
                    System.out.println("Minimum  value is : "+stack.getMin());
                }
                default -> k =false;
            }

        }

    }
}
