package client;

import java.io.File;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import win.Win;

public class SendThread extends Thread {
	Socket socket;
	Win win;

	public SendThread(Socket socket, Win win) {
		this.socket = socket;
		this.win = win;
	}

	public void run() {
		try {
			while (true) {
				while (win.isFile == null)
					sleep(10);
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeInt(Integer.parseInt(win.textField_id.getText()));
				
				String history=win.textArea_receive.getText();
				if(history.length()!=0)history+='\n';
				history+=win.id+" :\n";
				if (win.isFile) {
					File file=new File(win.pathName);
					oos.writeObject(file);
					history+=file.getName();
				} else {
					oos.writeObject(win.textArea_send.getText());
					history+=win.textArea_send.getText();
				}
				oos.flush();
				win.textArea_receive.setText(history);
				win.textArea_send.setText("");//发送完毕后将发送去清空
				win.isFile = null;// 发送完毕后将标志设为空
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
