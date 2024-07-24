import java.util.Scanner;
public class Entrada{
  Scanner scanner = new Scanner(System.in);

  public int intIn(){
    try{
        System.out.println("Ingrese un numero: ");
      return scanner.nextInt();
    }catch(Exception e){
      System.out.println("Ingrese un numero entero como valor");
      scanner.next();
      return intIn();
    }
  }

    public void close(){
      scanner.close();
    }
  
}