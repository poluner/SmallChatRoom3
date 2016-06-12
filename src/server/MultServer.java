package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MultServer {
	static Vector<Socket> sockets = new Vector<Socket>();

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(4700);
			while (true) {
				Socket socket = serverSocket.accept();
				sockets.add(socket);
				new ServerThread().start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
