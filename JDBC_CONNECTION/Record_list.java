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
import java.net.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.table.*;
public class Record_list
{
	public static JFrame f2;
	public static JTable jt;
	public static JLabel l1,l2,l3,l4,l5;
	public static ResultSet rs;
	public static Connection conn;
	public static DefaultTableModel model = new DefaultTableModel(new String[]{"Name","Rollno","Semester", "Department", "Mark"}, 0);
	Record_list()
	{
			f2 = new JFrame("Records List");
			jt = new JTable();
			l1 = new JLabel("Names");
			l2 = new JLabel("Roll No");
			l3 = new JLabel("Semester");
			l4 = new JLabel("Department");
			l5 = new JLabel("Marks");
			l1.setBounds(80,10,20,8);
			l2.setBounds(350,10,20,8);
			l3.setBounds(580,10,20,8);
			l4.setBounds(830,10,20,8);
			l5.setBounds(1100,10,20,8);
			l1.setSize(300,50);
			l2.setSize(300,50);
			l3.setSize(300,50);
			l4.setSize(300,50);
			l5.setSize(300,50);
			jt.setBounds(20,50,1300,650); 
			f2.add(l5);
			f2.add(l4);
			f2.add(l3);			
			f2.add(l2);
			f2.add(l1);       
			f2.add(jt);                 
			f2.setSize(2000,2000);
			f2.setLayout(null);
			f2.setVisible(true);
			try{
				Configuration_d o = new Configuration_d();
				model.setRowCount(0);
				//Establish the Connection
				conn = DriverManager.getConnection(o.databaseURL, o.user,o.password);
				Statement stmt = conn.createStatement();
				//Execute the query				
				String sql = "SELECT * FROM Users order by Name asc";
				rs=stmt.executeQuery(sql);
				while(rs.next()){
					
        					String d = rs.getString("Name");
    						String e = rs.getString("Rollno");
    						String f = rs.getString("Semester");
						String g = rs.getString("Department");
						String h = rs.getString("Mark");
    						model.addRow(new Object[]{d, e, f, g, h}); 
				}
				jt.setModel(model);
    				}catch (SQLException ex) {
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
		}
	}
		
