import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class UDPClient{
	JFrame frame;
	JPanel panel;
	JTextField field1, field2;
	JTextArea area;
	JScrollPane pane;
	JLabel label;
	JButton button;
	public static void main(String[] args) {
		UDPClient u = new UDPClient();
	}
	public UDPClient(){
		frame = new JFrame("Client");
		panel = new JPanel();
		panel.setLayout(null);
		area = new JTextArea();
		pane = new JScrollPane(area);
		pane.setBounds(100, 60, 150,120 );
		panel.add(pane);
		button = new JButton("Send");
		button.setBounds(215, 280, 75, 30);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				new SendRequest();
			}
		});
		panel.add(button);
		frame.add(panel);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
	public class SendRequest{
		SendRequest(){
			try{
				DatagramSocket socket;
				DatagramPacket packet;
				InetAddress address;
				socket = new DatagramSocket();
				String dip = "127.0.0.1";
				address = InetAddress.getByName(dip);
				String port = "43435";
				int pnum = Integer.parseInt(port);
				String mess = area.getText();
				byte message[] = mess.getBytes();
				packet = new DatagramPacket(message, message.length, address, pnum);
				socket.send(packet);
				area.setText("");
				socket.close();
			}
			catch(IOException io){}
		}
	}
}
