package client;

import java.net.Socket;

public class Client {
	public static void main(String args[]) {
		try {
			Socket socket = new Socket("127.0.0.1", 4700);
			new SendThread(socket).start();
			new ReceiveThread(socket).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
