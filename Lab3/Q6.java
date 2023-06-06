//import java.util.Scanner;
//
//class Person{
//    String name;
//    String address;
//    String email;
//    int phone_number;
//
//    Person(){
//        name = "";
//        address = "";
//        email = "";
//        phone_number = 0000000000;
//    }
//
//    Person(String name,String address,String email,int phone_number){
//        this.name = name;
//        this.phone_number = phone_number;
//        this.email = email;
//        this.address = address;
//    }
//
//    void setName(String name){
//        this.name = name;
//    }
//
//    void setAddress(String address){
//        this.address=address;
//    }
//
//    void setEmail(String email){
//        this.email = email;
//    }
//
//    void setPhone_number(int phone_number){
//        this.phone_number=phone_number;
//    }
//
//    String getName(){
//        return name;
//    }
//
//    String getAddress(){
//        return address;
//    }
//
//    String getEmail(){
//        return email;
//    }
//
//    int getPhone_number(){
//        return phone_number;
//    }
//
//    String tostring(){
//        return "Person " + name;
//    }
//
//}
//
//class Student extends Person{
//     String status;
//
//     Student(){
//         this.status = "";
//     }
//
//     Student(String status){
//         this.status = status;
//     }
//
//     String getStatus(){
//         return  status;
//     }
//
//     @Override
//     String tostring(){
//         return "Student " + name;
//     }
//}
//
//class Employee extends Person{
//    String office;
//    int salary;
//    String date_hired;
//
//    Employee(){
//        this.office = "";
//        this.salary=0;
//        this.date_hired = "";
//    }
//
//    Employee(String office,int salary,String date_hired){
//        this.office = office;
//        this.salary = salary;
//        this.date_hired = date_hired;
//    }
//
//    void setOffice(String office){
//        this.office=office;
//    }
//
//    void setSalary(int salary){
//        this.salary = salary;
//    }
//
//    void setDate_hired(String date_hired){
//        this.date_hired = date_hired;
//    }
//
//    String getOffice(){
//        return  office;
//    }
//
//    String getDate_hired(){
//        return date_hired;
//    }
//
//    int getSalary(){
//        return salary;
//    }
//
//    @Override
//    String tostring(){
//        return "Employee " + name;
//    }
//}
//
//class Faculty extends Employee{
//    int office_hours;
//    int rank;
//
//    Faculty(){
//        this.office_hours =0;
//        this.rank =0;
//    }
//
//    Faculty(int office_hours,int rank){
//        this.rank=rank;
//        this.office_hours = office_hours;
//    }
//
//    int getOffice_hours(){
//        return office_hours;
//    }
//
//    int getRank(){
//        return rank;
//    }
//
//    @Override
//    String tostring(){
//        return "Faculty " + name;
//    }
//}
//
//class Staff extends Employee{
//    String title;
//
//    Staff(){
//        this.title = "";
//    }
//
//    Staff(String title){
//        this.title = title;
//    }
//
//    String getTitle(){
//        return title;
//    }
//
//    @Override
//    String tostring(){
//        return "Staff " + name;
//    }
//}
//
//
//public class Q6 {
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        System.out.println("1.Create person");
//        System.out.println("2.Create student ");
//        System.out.println("3.Create Employee");
//        System.out.println("4.Create Faculty");
//        System.out.println("5.Create staff");
//        System.out.println("Any other number to exit");
//
//        int n=0;
//        while (n<6){
//            System.out.println("******************************************");
//            System.out.print("Enter your option : ");
//            n = input.nextInt();
//            switch (n){
//                case 1 ->{
//                    System.out.println("------------------------------------------");
//                    System.out.print("Enter name for Person class : ");
//                    String name = input.next();
//                    System.out.print("Enter Address : ");
//                    String add = input.next();
//                    System.out.print("Enter email : ");
//                    String mail = input.next();
//                    System.out.print("Enter phone number : ");
//                    int num = input.nextInt();
//                    Person person = new Person(name,add,mail,num);
//                    System.out.println("Invoking toString method");
//                    System.out.println(person.tostring());
//                    System.out.println("------------------------------------------");
//                }
//                case 2->{
//                    System.out.println("------------------------------------------");
//                    System.out.print("Enter name for Student : ");
//                    String name = input.next();
//                    System.out.print("Enter student status i.e freshman, sophomore, junior, or senior : ");
//                    String stat = input.next();
//                    Student student = new Student(stat);
//                    student.setName(name);
//                    System.out.println("Invoking toString method");
//                    System.out.println(student.tostring());
//                    System.out.println("------------------------------------------");
//                }
//                case 3->{
//                    System.out.println("------------------------------------------");
//                    System.out.print("Enter name for Employee : ");
//                    String name = input.next();
//                    System.out.print("Enter office,date hired and salary : ");
//                    String off = input.next();
//                    String date = input.next();
//                    int sal = input.nextInt();
//                    Employee employee = new Employee(off,sal,date);
//                    employee.setName(name);
//                    System.out.println("Invoking toString method");
//                    System.out.println(employee.tostring());
//                    System.out.println("------------------------------------------");
//                }
//                case 4->{
//                    System.out.println("------------------------------------------");
//                    System.out.print("Enter name for Faculty : ");
//                    String name = input.next();
//                    System.out.print("Enter office hours and rank : ");
//                    int offHrs = input.nextInt();
//                    int rank = input.nextInt();
//                    Faculty faculty = new Faculty(offHrs,rank);
//                    faculty.setName(name);
//                    System.out.println("Invoking toString method");
//                    System.out.println(faculty.tostring());
//                    System.out.println("------------------------------------------");
//                }
//                case 5->{
//                    System.out.println("------------------------------------------");
//                    System.out.print("Enter name for Staff : ");
//                    String name = input.next();
//                    System.out.print("Enter Title of staff : ");
//                    String title = input.next();
//                    Staff staff = new Staff(title);
//                    staff.setName(name);
//                    System.out.println("Invoking toString method");
//                    System.out.println(staff.tostring());
//                    System.out.println("------------------------------------------");
//                }
//            }
//        }
//    }
//}