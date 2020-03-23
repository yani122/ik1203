import java.net.*;
import java.io.*;

public class HTTPEcho {
    public static void main( String[] args) throws IOException {


    	int User_input_port = Integer.parseInt(args[0]);



        ServerSocket S_Socket = new ServerSocket(User_input_port);



        while(true) {
        	try {





        	Socket C_Socket = S_Socket.accept();


        	BufferedReader data_från_klient = new BufferedReader(new InputStreamReader(C_Socket.getInputStream()));


        	DataOutputStream data_till_klient = new DataOutputStream(C_Socket.getOutputStream());


			String rad = null;

        	StringBuilder String_builder = new StringBuilder();
    		String resp_Header = "HTTP/1.1 200 OK\r\n\r\n";
    		String_builder.append(resp_Header);







			while(!(rad = data_från_klient.readLine()).isEmpty()) {
				String_builder .append(rad + "\n");
			}


			data_till_klient.writeBytes(String_builder.toString());
			C_Socket.close();
			//
			data_från_klient.close();
			data_till_klient.close();
			System.out.println("Closed one connection");
        	}



			//Exception
        	catch (IOException exc) {
        	System.out.println("Exception");
        }


     }
  }


}

