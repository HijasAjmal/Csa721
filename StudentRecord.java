import java.sql.*;
import java.io.*;
import java.util.Scanner;

public class StudentRecord {

    static String DB_URL = "jdbc:mysql://localhost/Student_Record";

    static String USER = "virus";
    static String PASS = ".virusdb";

    Scanner sc;
    Connection con;
    Statement st;

    String Name;
    int Rollno;
    String Department;
    int Marks;

    public StudentRecord() {
        sc = new Scanner(System.in);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,USER,PASS);
            st = con.createStatement();
        } catch (SQLException e) {
            System.out.println("please check database connection, parameters");
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.out.println("please check you have correct CLASSPATH");
            System.exit(1);
        }
    }

    public StudentRecord(ResultSet rs) {
        try {
            Name = rs.getString("Name");
            Rollno = rs.getInt("Rollno");
            Department = rs.getString("Department");
            Marks = rs.getInt("Marks");
        } catch(Exception e) {
        }
    }

    public static void main(String[] args) {

        StudentRecord tt = new StudentRecord();

        int choice;


        while(true) {

            System.out.println("1. New Record \n"
                    + "2. List All Records \n"
                    + "3. Quit\n");

            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();

            switch(choice) {
                case 1:
                    StudentRecord st = new StudentRecord();
                    st.readNewRecord();
                    break;
                case 2:
                    tt.listStudents();
                    break;
                case 3:
                   System.exit(0);
                    break;
                default:
                    System.out.println("=>Your entry is Wrong");
            }
        }

    }

    void readNewRecord() {
        System.out.println("Student_Name: ");
        Name = sc.next();
        System.out.println("Student_Roll_No: ");
        Rollno = sc.nextInt();
        System.out.println("Department: ");
        Department = sc.next();
        System.out.println("Total_Marks: ");
        Marks = sc.nextInt();
        insertRecord();
    }

    void insertRecord() {
        String sql = "insert into Users values('"+Name+"', '"+Rollno+"','"+Department+"','"+Marks+"');";
        try {
            int f = st.executeUpdate(sql);
        } catch(SQLException e) {
            System.out.println("Error in sql");
            return;
        }
    }

    void print() {
       
	System.out.println(Name+"\t\t   "+Rollno+"\t\t\t    "+Department+"\t\t\t "+Marks);
    }

    void listStudents() {
        String sql = "SELECT * FROM Users";
        try {
            ResultSet rs = st.executeQuery(sql);
		 System.out.println("Student_Name:\tStudent_Roll_No:\t Department:\t Total_Marks:");
            while(rs.next())
            {
                StudentRecord st = new StudentRecord(rs);
                st.print();
            }
        } catch(Exception e) {
            return;
        }
    }

    
}
