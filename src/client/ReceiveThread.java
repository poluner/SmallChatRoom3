package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.nio.channels.FileChannel;

import win.Win;

public class ReceiveThread extends Thread {
	Socket socket;
	Win win;

	public ReceiveThread(Socket socket, Win win) {
		this.socket = socket;
		this.win = win;
	}

	public void run() {
		try {
			while (true) {
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				int myid = ois.readInt();
				Object o = ois.readObject();
				String history=win.textArea_receive.getText();
				if(history.length()!=0)history+="\n";
				history+=myid+" :\n";
				if (o instanceof File) {// �����ļ�
					File file = (File) o;
					history+=file.getName();
					FileChannel fci = new FileInputStream(file).getChannel();
					FileChannel fco = new FileOutputStream(file.getName()).getChannel();
					fci.transferTo(0, fci.size(), fco);
					fci.close();
					fco.close();
				} else {// �����ı�
					history+=(String)o;
				}
				win.textArea_receive.setText(history);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
