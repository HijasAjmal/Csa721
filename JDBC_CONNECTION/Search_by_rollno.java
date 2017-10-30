import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.event.*;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.net.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.JButton;
public class Search_by_rollno
{
	public static JFrame f2;
	public static JButton b;
	public static JTextArea ta;
	public static int columns;
	public static JLabel l1,l2,l3,l4,l5,l6;
	public static ResultSet rs;
	public static int str,found=0;
	public static Connection conn;
	public static DefaultTableModel model = new DefaultTableModel(new String[]{"Name","Rollno","Semester", "Department", "Mark"}, 0);     
	public static JTable jt = new JTable(); 
	Search_by_rollno()
	{
			f2 = new JFrame("Records List");
			jt.setBounds(20,50,950,100);  
			ta = new JTextArea();  
			l1 = new JLabel("Name");
			l2 = new JLabel("Roll No");
			l3 = new JLabel("Semester");
			l4 = new JLabel("Department");
			l5 = new JLabel("Mark");
			l6 = new JLabel("Enter the roll no:=>");
			l1.setBounds(60,10,20,8);
			l2.setBounds(240,10,20,8);
			l3.setBounds(420,10,20,8);
			l4.setBounds(600,10,20,8);
			l5.setBounds(800,10,20,8);
			l6.setBounds(150,160,600,300);
			l6.setSize(200,100);
			l1.setSize(300,50);
			l2.setSize(300,50);
			l3.setSize(300,50);
			l4.setSize(300,50);
			l5.setSize(300,50);
			f2.add(l6);
			f2.add(l5);
			f2.add(l4);
			f2.add(l3);			
			f2.add(l2);
			f2.add(l1);       
			ta.setBounds(300,190,600,300);
			ta.setSize(200,50);
			b = new JButton("Submit");
			b.setBounds(340,260,500,350);
			b.setSize(100,50);
			f2.add(b); 
			f2.add(ta);       
			f2.add(jt);                 
			f2.setSize(1000,600);
			f2.setLayout(null);
			f2.setVisible(true);
			b.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e){ 
				try{
					Configuration_d a = new Configuration_d();
					model.setRowCount(0);
					conn = null;
					//Register the jdbc driver
					Class.forName("com.mysql.jdbc.Driver");
					//Establish the Connection
					conn = DriverManager.getConnection(a.databaseURL, a.user, a.password);
					Statement stmt = conn.createStatement();
					str = Integer.parseInt(ta.getText());
					rs=stmt.executeQuery("select * from Users where Rollno="+str);
					found = 0;
					while(rs.next()){
						found = 1;	
        						String d = rs.getString("Name");
    							String r = rs.getString("Rollno");
    							String f = rs.getString("Semester");
							String g = rs.getString("Department");
							int h = rs.getInt("Mark");
    							model.addRow(new Object[]{d, r, f, g, h}); 
					}
					if(found == 0)
					{
						JOptionPane.showMessageDialog(f2,"No Matches Found-----"); 
					}
				jt.setModel(model);
    				
				} catch (ClassNotFoundException ex) {
					System.out.println("Could not find database driver class");
					ex.printStackTrace();
				} catch (SQLException ex) {
					System.out.println("An error occurred. Maybe user/password is invalid");
					ex.printStackTrace();
				} finally {
				if (conn != null) {
					try {
						conn.close();
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
				}
			 }
		}});
	}
}
		
