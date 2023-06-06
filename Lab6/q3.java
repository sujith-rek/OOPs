import java.util.Scanner;

class LegalAgeException extends Exception{
    int age;
    LegalAgeException(int n,String error){
        super(error);
        age=n;
    }
    LegalAgeException(){

    }
}

class WordANumberException extends Exception{
    String Word;

}

class BrandNotSameException extends Exception{
    boolean sameBrand=false;
    BrandNotSameException(String b1,String b2,String error){
        super(error);
        sameBrand=b1.equals(b2);
    }
    BrandNotSameException(){

    }
}

public class q3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

    }
}
