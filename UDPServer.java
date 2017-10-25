import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class UDPServer{
	JFrame frame;
	JPanel panel;
	JButton button1,button2;
	JTextArea area;
	JScrollPane pane;
	Thread thread;
	DatagramSocket socket;

	public static void main(String[] args) {
		UDPServer u = new UDPServer();
	}
	public UDPServer(){
		frame = new JFrame("Server");
		panel = new JPanel();
		panel.setLayout(null);
		area = new JTextArea();
		button1 = new JButton("Start");
		button1.setBounds(210, 10, 75, 40);
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				new StartThread();
			}
		});
		panel.add(button1);
		button2 = new JButton("Stop");
		button2.setBounds(300, 10, 75, 40);
		button2.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent ae){
				thread.interrupted();
				socket.close();
				area.append("=======Server closed=====\n");
				button1.setEnabled(true);
				button2.setEnabled(false);
			}
		});
		button2.setEnabled(false);
		panel.add(button2);
		pane = new JScrollPane(area);
		pane.setBounds(80, 80, 200, 180);
		panel.add(pane);
		frame.add(panel);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
	public class StartThread implements Runnable{
		StartThread(){
			thread = new Thread(this);
			thread.start();
			button1.setEnabled(false);
			button2.setEnabled(true);
		}
	public void run(){
			try{
				byte[] buffer = new byte[1024];
				int port = 43435;
				try{
				socket = new DatagramSocket(port);
				while(true){
				try{
					area.append("Server Connected\n");
					DatagramPacket packet = new DatagramPacket(buffer, buffer.length );
					socket.receive(packet);
					InetAddress client = packet.getAddress();
					int client_port = packet.getPort();
					area.append(new String(buffer)+  " <-client\n");
					
				}
				catch(UnknownHostException ue){}
				}
				}
				catch(java.net.BindException b){}
			}
			catch (IOException e){
				System.err.println(e);
			}
		}
	}
}
