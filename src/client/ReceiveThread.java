package client;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ReceiveThread extends Thread {
	Socket socket;

	public ReceiveThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			while (true) {
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				int myid = ois.readInt();
				String receiveMessage = (String) ois.readObject();
				System.out.println(myid + ":" + receiveMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
