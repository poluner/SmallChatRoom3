package win;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Win extends JFrame implements ActionListener {
	public Boolean isFile = null;
	public String pathName;
	public int id;

	
	public JTextField textField_id;
	public JTextArea textArea_send = new JTextArea();
	public JTextArea textArea_receive = new JTextArea();
	public JButton button_send = new JButton("\u53D1\u9001");
	public JButton button_file = new JButton("\u6587\u4EF6");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Win(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param id
	 */
	public Win(int id) {
		this.id = id;
		setTitle("hello:" + id);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 351);
		JPanel contentPane = new JPanel(){
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon ii = new ImageIcon("image\\background.jpg");
				g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(getHeight()/2);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);

		scrollPane.setViewportView(textArea_receive);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0, 0));
		splitPane.setRightComponent(panel);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);

		JLabel lblId = new JLabel("id:");
		panel_1.add(lblId);

		textField_id = new JTextField();
		panel_1.add(textField_id);
		textField_id.setColumns(10);

		panel_1.add(button_send);

		panel_1.add(button_file);

		JScrollPane scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1, BorderLayout.CENTER);

		scrollPane_1.setViewportView(textArea_send);

		button_send.addActionListener(this);
		button_file.addActionListener(this);
		setVisible(true);
		
		textArea_receive.setEditable(false);//接收框不可编辑
		//透明
		panel.setOpaque(false);
		panel_1.setOpaque(false);
		lblId.setOpaque(false);
		splitPane.setOpaque(false);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane_1.setOpaque(false);
		scrollPane_1.getViewport().setOpaque(false);
		textArea_receive.setOpaque(false);
		textArea_send.setOpaque(false);
		textField_id.setOpaque(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (textField_id.getText().length() == 0)
			return;

		if (e.getSource() == button_send) {
			if (textArea_send.getText().length() == 0)
				return;
			isFile = false;
		}
		if (e.getSource() == button_file) {
			FileDialog fd = new FileDialog(this, "选择文件", FileDialog.LOAD);
			fd.setVisible(true);

			pathName = fd.getDirectory();
			if (pathName == null)
				return;
			pathName += fd.getFile();
			isFile = true;
		}
	}
}
