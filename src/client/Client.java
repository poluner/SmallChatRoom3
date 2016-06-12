package client;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String args[]) {
		try {
			Socket socket = new Socket("127.0.0.1", 4700);
			Scanner sin = new Scanner(System.in);
			while (true) {
				String yourid = sin.nextLine();
				String sendMessage = sin.nextLine();

				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeInt(Integer.parseInt(yourid));
				oos.writeObject(sendMessage);
				System.out.println("sended!");

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
