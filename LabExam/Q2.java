class Queue{
    private int[] queue ;
    int lastIndex,firstIndex;
    Queue(){
        this.queue = new int[100];
        this.lastIndex=0;
        this.firstIndex=0;
    }

    int dequeue(){
        int n = queue[firstIndex];
        firstIndex++;
        return n;
    }

    void enqueue(int n){
        this.queue[lastIndex] = n;
        lastIndex++;
    }
    void printQueue(){
        for(int i=firstIndex;i<lastIndex;i++){
            System.out.print(queue[i]+" ");
        }
    }
}


public class Q2 {
    public static void main(String[] args) {
        Queue q1 = new Queue();
        q1.enqueue(5);
        q1.enqueue(6);
        q1.enqueue(2);
        q1.enqueue(15);
        q1.enqueue(456);
        q1.enqueue(456887);
        q1.enqueue(4846);
        q1.enqueue(468);
        q1.enqueue(789);
        System.out.print("\nPrinting the queue : ");
        q1.printQueue();
        q1.dequeue();
        q1.printQueue();


    }
}
