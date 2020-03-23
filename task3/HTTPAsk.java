import java.net.*;
import java.io.*;
import tcpclient.TCPClient;

public class HTTPAsk {
  public static void main( String[] args) throws IOException{


     ServerSocket wel_Socket = new ServerSocket(Integer.parseInt(args[0]));



     String in;
     String h;
     String p;
     String string;

     while(true){
       try{


         h = null;
         p = null;
         string = null;
         in = null;

         Socket konekt_Socket = wel_Socket.accept();



         BufferedReader input_Fron_Client = new BufferedReader(new InputStreamReader(konekt_Socket.getInputStream()));

         DataOutputStream output_Till_Client = new DataOutputStream(konekt_Socket.getOutputStream());

         StringBuilder outpot = new StringBuilder();
         in = input_Fron_Client.readLine();

         if(in != null){

           String[] split = in.split("[/?=& ]");

           for(int i = 0; i < split.length; i++){
             if(split[i].equals("hostname")){
               h = split[i+1];
               i++;

              //system.out.println()
             }
             else if(split[i].equals("port")){
               p = split[i+1];
               i++;
               //for att
             }
             else if(split[i].equals("string")){
               string  = split[i+1];
               i++;
               //System.out.println(string);
             }
           }

           if(split[2].equals("ask") && h != null && p != null){


             try{

               String svar = TCPClient.askServer(h,Integer.parseInt(p),string);
               String HTTPOK = "HTTP/1.1 200 OK\r\n\r\n";
               //system.out.println("")
               output_Till_Client.writeBytes(HTTPOK + svar);


             } catch(IOException e){
               String HTTPNotFound  = "HTTP/1.1 404 Not Found\r\n";

               output_Till_Client.writeBytes(HTTPNotFound);

             }
           } else {
             String HTTPBadRequest = "HTTP/1.1 400 Bad Request\r\n";
             //hosten eller porten ar null eller no ask
             output_Till_Client.writeBytes(HTTPBadRequest);

           }
         }

         konekt_Socket.close();

       } catch (IOException e){
         System.exit(1);
       }
     }
   }
 }