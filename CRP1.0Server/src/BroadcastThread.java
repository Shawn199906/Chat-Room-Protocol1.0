import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class BroadcastThread implements Runnable
{
	private Vector<String> messages;
	private ArrayList<BufferedWriter> connections;
	//private HashMap<String,BufferedWriter> map;
	public BroadcastThread(Vector<String> messages,ArrayList<BufferedWriter> connections) {
		this.messages=messages;
		this.connections=connections;
	}
    public void run() {
    	//this.map=map;
        while (true) {
            // sleep for 1/10th of a second
            try { Thread.sleep(100); } catch (InterruptedException ignore) { }

            /**
             * check if there are any messages in the Vector. If so, remove them
             * and broadcast the messages to the chatroom
             */
            while(messages.isEmpty()==false) {
            	String save=messages.remove(0);
//            	String[]parts=save.split(" ");
//            	if(parts[0].equals("CRP1.0JOIN")) {
//            		map.put(parts[1], null);
//            	}else if(parts[0].equals("CRP1.0LEAVE")) {
//            		map.remove(parts[1], null);
//            	}
            	for(int i=0;i<connections.size();i++) {
            		BufferedWriter connect=connections.get(i);
            		try {
						connect.write(save+"\r\n");
						connect.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						connections.remove(i);
					}
            	}
            }
        }
    }
} 
