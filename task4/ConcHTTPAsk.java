import java.net.*;
import java.io.*;

public class ConcHTTPAsk {
  public static void main( String[] args) throws IOException {

    try{
      //skapa Create ServerSocket
      //skapa welcome sockaet po portnummret fron inputen
      ServerSocket w_Socket = new ServerSocket(Integer.parseInt(args[0]));



      while (true) {


        // vanta tills clienten kontaktar,
        //skapa ny socket
        Socket conn_Socket = w_Socket.accept();
       //accept
       //skapa ny thread
        new Thread(new MyRunnable(conn_Socket)).start(); //
            }


     //Exception
     //System.out.print("fail");

    } catch(Exception e){
      System.out.print("fail");
    }
  }
}