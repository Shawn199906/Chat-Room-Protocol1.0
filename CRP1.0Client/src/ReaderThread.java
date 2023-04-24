/**
 * This thread is passed a socket that it reads from. Whenever it gets input
 * it writes it to the ChatScreen text area using the displayMessage() method.
 */

import java.io.*;
import java.net.*;
import javax.swing.*;

public class ReaderThread implements Runnable
{
	Socket server;
	BufferedReader fromServer;
	ChatScreen screen;

	public ReaderThread(Socket server, ChatScreen screen) {
		this.server = server;
		this.screen = screen;
	}

	public void run() {
		try {
			fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));

			while (true) {
				String message = fromServer.readLine();
				String[] parts=message.split(" ");
				String show=parts[1]+": ";
				// now display it on the display area
				if (parts[0].equals("CRP1.0JOIN")){
					screen.displayMessage("Welcome "+parts[1]);
				}else if(parts[0].equals("CRP1.0SEND")) {
					for(int i=2;i<parts.length;i++){
						show=show+parts[i]+" ";
					}
					if(show!=null) {
						screen.displayMessage(show);
					}
				}else if(parts[0].equals("CRP1.0LEAVE")) {
					screen.displayMessage(parts[1]+" has left the call");
				}
			}
		}
		catch (IOException ioe) { System.out.println(ioe); }
	}
}
