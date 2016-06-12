package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
	Socket socket;
	int id;

	public ServerThread() {
		socket = MultServer.sockets.lastElement();
		id = MultServer.sockets.size() - 1;
	}

	public void run() {
		try {
			while (true) {
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(
						MultServer.sockets.elementAt(ois.readInt()).getOutputStream());
				oos.writeInt(id);
				oos.writeObject(ois.readObject());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
