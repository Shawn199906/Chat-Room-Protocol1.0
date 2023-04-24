/**
 * This is the separate thread that services each
 * incoming echo client request.
 *
 * @author Greg Gagne 
 */

import java.net.*;
import java.util.Vector;
import java.io.*;

public class Connection implements Runnable
{
	private Socket	client;
	private Vector<String> messages;
	private static Handler handler = new Handler();
	
	public Connection(Socket client,Vector<String> messages) {
		this.client = client;
		this.messages=messages;
	}

    /**
     * This method runs in a separate thread.
     */	
	public void run() { 
		try {
			handler.process(client,messages);
		}
		catch (java.io.IOException ioe) {
			System.err.println(ioe);
		}
	}
}

