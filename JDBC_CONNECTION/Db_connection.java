import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.net.*;
import java.sql.*;
import java.awt.event.*;


public class Db_connection
{
		//Initialization of objects
		public static JLabel ld;
		public static JFrame frame1;
		public static Connection conn;
		public static Statement stmt;
		JButton Register, Records, Delete,Search;
		JTextArea Student_Namet, Student_Rollnot, Student_Semestert, Student_Departmentt, Student_Markt;
		JLabel StandByl, Main_Headingl, Student_Namel, Student_Rollnol, Student_Semesterl, Student_Departmentl, Student_Markl;
		//Main function		
		public static void main(String [] args)
		{
			Configuration_d b = new Configuration_d();
			conn = null;
			try
			{
				//Register the jdbc driver
				Class.forName("com.mysql.jdbc.Driver");
				//Establish the Connection
				conn = DriverManager.getConnection(b.databaseURL, b.user, b.password);
				//System.out.println("Database connected");
				stmt = conn.createStatement();
			}catch(SQLException ae){
				ae.printStackTrace();
			}catch(ClassNotFoundException ae){
				ae.printStackTrace();
			}
			Db_connection o = new Db_connection();
		}
		//Gui modeling
		public Db_connection()
		{
			frame1 = new JFrame("Student Record");
			JPanel panel1 = new JPanel();
			ld = new JLabel("<===>Hijas Ajmal <===> Roll no::21 <===>S7-CSE-A <===> IWJ Assignment <===>");
			ld.setBounds(350,60,750,30);
			ld.setSize(3000,100);
			panel1.setBounds(200,20,900,700);
			JPanel panel2 = new JPanel();   
			panel2.setBounds(350,100,600,600);
			panel1.setBackground(Color.gray);
			//Labels for fields
			Student_Namel = new JLabel();
			Student_Rollnol = new JLabel();
			Student_Semesterl = new JLabel();
			Student_Departmentl = new JLabel();
			Student_Markl = new JLabel();
			Main_Headingl = new JLabel();
			//TextAresas
			Student_Namet = new JTextArea ();
			Student_Rollnot = new JTextArea ();
			Student_Semestert = new JTextArea ();
			Student_Departmentt = new JTextArea ();
			Student_Markt = new JTextArea ();
			//SetIcon for Labels
			Student_Namel.setIcon(new ImageIcon("R/Name.png"));
			Student_Rollnol.setIcon(new ImageIcon("R/Rollno.png"));
			Student_Semesterl.setIcon(new ImageIcon("R/Semester.png"));
			Student_Departmentl.setIcon(new ImageIcon("R/Department.png"));
			Student_Markl.setIcon(new ImageIcon("R/TotalMark.png"));
			//set TextField Position
			Student_Namet.setBounds(600,210,601,215);
			Student_Namet.setSize(300,40);
			Student_Rollnot.setBounds(600,280,601,310);
			Student_Rollnot.setSize(300,40);
			Student_Semestert.setBounds(600,360,601,390);
			Student_Semestert.setSize(300,40);
			Student_Departmentt.setBounds(600,440,601,460);
			Student_Departmentt.setSize(300,40);	
			Student_Markt.setBounds(600,510,601,520);
			Student_Markt.setSize(300,40);		
			//set labels position
			Student_Namel.setBounds(400,150,410,170);
			Student_Rollnol.setBounds(400,200,410,220);
			Student_Semesterl.setBounds(400,250,410,270);
			Student_Departmentl.setBounds(400,300,410,320);
			Student_Markl.setBounds(400,350,410,370);
			Main_Headingl.setIcon(new ImageIcon("R/Main_Heading.png"));
			Main_Headingl.setBounds(500,1,800,1);
			Main_Headingl.setSize(300,300);
			//Buttons
			Search = new JButton("Search by Rollno");
			Delete = new JButton("Delete All Records");
			Register = new JButton("Register");
			//Register.setIcon(new ImageIcon("R/Register.png"));
			Records = new JButton("Records");
			//Records.setIcon(new ImageIcon("R/Records.png"));
			Search.setBounds(550,570,600,580);
			Search.setSize(200,50);
			Delete.setBounds(500,640,750,650);
			Delete.setSize(300,50);
			Search.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				new Search_by_rollno();
			}
			});
			Delete.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				new dl();
			}
			});
			Records.setBounds(400,570,410,600);
			//Records.setBounds(400,600,410,620);
			Records.setSize(100,50);
			Register.setBounds(800,570,750,830);
			//Register.setBounds(730,600,750,630);
			Register.setSize(100,50);
			//Button click event start
			Register.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				new newRecord();
			}
			});
			Records.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				new Record_list();
			}
			});
			//Add all components to the frame
			frame1.add(ld);
			frame1.add(Search);
			frame1.add(Delete);
			frame1.add(Main_Headingl);
			frame1.add(Records);
			frame1.add(Register);
			frame1.add(Student_Markt);
			frame1.add(Student_Departmentt);
			frame1.add(Student_Semestert);
			frame1.add(Student_Rollnot);
			frame1.add(Student_Namet);
			frame1.add(Student_Markl);
			frame1.add(Student_Departmentl);
			frame1.add(Student_Semesterl);
			frame1.add(Student_Rollnol);
			frame1.add(Student_Namel);
			frame1.add(panel2);
			frame1.add(panel1);
			//Frame (size,visibility,layout)
			frame1.setSize(5000,5000);
			frame1.setLayout(null);
			frame1.setVisible(true);

		
	}		
	public class newRecord
	{
		newRecord()
		{
			try{
				String Name,Rollno,Semester,Dept;
				int Mark;
				Name = Student_Namet.getText();
				Rollno = Student_Rollnot.getText();				
				Semester = Student_Semestert.getText();				
				Dept = Student_Departmentt.getText();
				Mark = Integer.parseInt(Student_Markt.getText());
				//Execute the query				
				String sql = "insert into Users values('"+Name+"','"+Rollno+"', '"+Semester+"','"+Dept+"','"+Mark+"');";
				int rs=stmt.executeUpdate(sql);
				JOptionPane.showMessageDialog(frame1,"Record created successfully"); 
				Student_Namet.setText("");
				Student_Rollnot.setText("");
				Student_Semestert.setText("");
				Student_Departmentt.setText("");
				Student_Markt.setText("");
			} catch (SQLException ex) {
				System.out.println("An error occurred. Maybe user/password is invalid");
				ex.printStackTrace();
			}
		}
	}
	public class dl
	{
		//Function to clean all records
		dl()
		{
			try
			{
				String sql = "delete from Users";
				int rs=stmt.executeUpdate(sql);
				JOptionPane.showMessageDialog(frame1,"All records are deleted"); 
			}catch(SQLException e){
				e.printStackTrace();			
			}
		}
	}

}
