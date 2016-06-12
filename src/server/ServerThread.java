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
		try {
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());// 告知客户端编号
			oos.writeInt(id);
			oos.flush();// 这个必须刷新，别的好像不是必须刷新
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			while (true) {
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(
						MultServer.sockets.elementAt(ois.readInt()).getOutputStream());
				oos.writeInt(id);
				oos.writeObject(ois.readObject());
				oos.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
