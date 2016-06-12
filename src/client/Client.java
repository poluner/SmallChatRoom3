package client;

import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.UIManager;

import win.Win;

public class Client {
	public static void main(String args[]) {
		try {
			Socket socket = new Socket("127.0.0.1", 4700);

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());// 设置为系统风格
			Win win = new Win(new ObjectInputStream(socket.getInputStream()).readInt());
			new ReceiveThread(socket, win).start();
			new SendThread(socket, win).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
