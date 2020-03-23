package tcpclient;
import java.net.*;
import java.io.*;

public class TCPClient {
    private static int BUFFERSIZE = 1024;

    public static String askServer(String hostname, int port, String ToServer) throws  IOException {
	if(ToServer == null)
	    return askServer(hostname, port);

	byte[] fromBuffer = new byte[BUFFERSIZE];
	byte[] fromServerBuffer = new byte[BUFFERSIZE];
	Socket clientsocket = new Socket(hostname, port);

	clientsocket.getOutputStream().write(ToServer.getBytes());
	clientsocket.getOutputStream().write('\n');

	int fromuserLängd = clientsocket.getInputStream().read(fromBuffer);

	StringBuilder output = new StringBuilder("");

	int i=0;
	while(i < fromuserLängd)
	{
		output.append((char)fromBuffer [i]);
		i++;
	}



	clientsocket.close();
	return output.toString();
    }

    public static String askServer(String hostname, int port) throws  IOException {

	byte[] fromuserBuffer = new byte[BUFFERSIZE];

	Socket clientsocket1 = new Socket(hostname, port);
	int outputLängd = clientsocket1.getInputStream().read(fromuserBuffer);
	StringBuilder output = new StringBuilder("");

	int i=0;
	while(i < outputLängd) {
		output.append((char)fromuserBuffer [i]);
		i++;
	}

	clientsocket1.close();
	return output.toString();
    }
}

