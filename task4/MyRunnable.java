import java.net.*;
import java.io.*;
import tcpclient.TCPClient;

public class MyRunnable implements Runnable {

  Socket conn_Socket;

  public MyRunnable(Socket conn_Socket) throws Exception {
    this.conn_Socket =  conn_Socket;
  }


  String h = null;
  String in_client = null;

  //port nummer
  //port =43
  String p = null;

  // data till att skicka till server
  String string = null;

  public void run() {

    try{

      BufferedReader input_Fron_Client = new BufferedReader(new InputStreamReader(conn_Socket.getInputStream()));

      DataOutputStream output_To_Client = new DataOutputStream(conn_Socket.getOutputStream());


      StringBuilder out = new StringBuilder();
      in_client = input_Fron_Client.readLine();

      if(in_client != null){



        String[] split = in_client.split("[/?=& ]");


        for(int i = 0; i < split.length; i++){
          if(split[i].equals("hostname")){
            h = split[i+1];
            i++;
        }
        else if(split[i].equals("port")){
          p = split[i+1];
          i++;
        }
        else if(split[i].equals("string")){
          string  = split[i+1];
          i++;

        }

        }


        if(split[2].equals("ask") && h != null && p != null){



          try{
			  // responsen fron servern
            String res = TCPClient.askServer(h,Integer.parseInt(p),string);

            ///"HTTP/1.1 200 OK\r\n\r\n";
            String HTTPOK = "HTTP/1.1 200 OK\r\n\r\n";
            output_To_Client.writeBytes(HTTPOK + res);


			//"HTTP/1.1 404 Not Found\r\n";
          } catch(IOException e){
            String HTTP_N_Found  = "HTTP/1.1 404 Not Found\r\n";
            output_To_Client.writeBytes(HTTP_N_Found);

          }

          //"HTTP/1.1 400 Bad Request\r\n";
        } else {
          String HTTP_B_Request = "HTTP/1.1 400 Bad Request\r\n";
          output_To_Client.writeBytes(HTTP_B_Request);

        }
      }

      conn_Socket.close();
    } catch(IOException e){
      System.exit(1);
    }
  }
}