package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.nio.channels.FileChannel;

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
				File file = (File) ois.readObject();
				FileChannel fci = new FileInputStream(file).getChannel();
				FileChannel fco = new FileOutputStream(file.getName()).getChannel();
				fci.transferTo(0, fci.size(), fco);
				System.out.println(myid + ":" + file.getName());
				fci.close();
				fco.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
