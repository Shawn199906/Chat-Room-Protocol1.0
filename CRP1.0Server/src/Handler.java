/**
 * Handler class containing the logic for echoing results back
 * to the client. 
 *
 * This conforms to RFC 862 for echo servers.
 *
 * @author Greg Gagne 
 */

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Vector;

public class Handler 
{
	public static final int BUFFER_SIZE = 256;
	public static final int NUMBER_OF_BYTES = 2048;
	
	/**
	 * this method is invoked by a separate thread
	 */
	public void process(Socket client,Vector<String> messages) throws java.io.IOException {
		BufferedReader fromClient = null;
		String line=null;
		try {
			fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
			while ( (line = fromClient.readLine()) != null) {
				System.out.println(line);
				System.out.println(messages.add(line));
			}
		}
		catch (UnknownHostException uhe) {
			
		}
	}
}
