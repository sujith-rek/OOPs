import java.sql.ClientInfoStatus;

class REQUEST{
    String first_name,last_name;
    int roll_no,uid;
    boolean status;
    REQUEST next;

    REQUEST(String f_n,String l_n,int r_n,int uid){
        this.roll_no=r_n;
        this.uid=uid;
        this.last_name=l_n;
        this.first_name=f_n;
        this.status = false;
        this.next=null;
    }
    REQUEST(){
        this.next=null;
    }
}

class List{
    REQUEST head;

    List(){
        this.head = null;
    }
    void insert(REQUEST node){
        REQUEST temp = head;
        if(head==null ) head=node;
        else{
            while(temp.next!=null ) temp = temp.next;
            temp.next = node;
        }
    }

    void delete(String f_n,String l_n){
        REQUEST temp = head;
        if(f_n.compareTo(temp.first_name)==0 && l_n.compareTo(temp.last_name)==0 ) {
            head=head.next;
            return;
        }
        while (temp.next!=null){
            if(f_n.compareTo(temp.next.first_name)==0 && l_n.compareTo(temp.next.last_name)==0 ){
                temp.next=temp.next.next;
            }
            temp=temp.next;
        }
    }

    void printList(String name){
        System.out.println(name+" List ");
        REQUEST temp = head;
        while(temp!=null){
            System.out.println(temp.uid+" "+temp.first_name+" "+temp.last_name+" ("+temp.roll_no+")");
            temp = temp.next;
        }
    }

}

public class Q3 {
    public static void main(String[] args) {
//        1.Add student to request list
//        2.cancel student request
//        3.print request list
//        4.print confirmed list
//        5.print waitlist
//        switch (op){
//            case 1->{
//
//            }
//            case 2->{
//
//            }
//            case 3->{
//
//            }
//            case 4->{
//
//            }
//            case 8->{
//
//            }
//
//        }
        List ne = new List();
        ne.insert(new REQUEST("suj5","fr",505,47));
        ne.insert(new REQUEST("suj1","fr",40,4));
        ne.insert(new REQUEST("suj2","fr",50,47));
        ne.insert(new REQUEST("suj3","fr",560,4));
        ne.insert(new REQUEST("suj4","fr",560,5));
        ne.insert(new REQUEST("suj6","fr",560,48));
        ne.delete("suj6","fr");

        ne.printList("REQUEST");

    }
}
