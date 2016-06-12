package client;

import java.io.File;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SendThread extends Thread {
	Socket socket;

	public SendThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			Scanner sin = new Scanner(System.in);
			while (true) {
				String yourid = sin.nextLine();
				String pathName = sin.nextLine();

				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeInt(Integer.parseInt(yourid));
				oos.writeObject(new File(pathName));
				System.out.println("sended!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
