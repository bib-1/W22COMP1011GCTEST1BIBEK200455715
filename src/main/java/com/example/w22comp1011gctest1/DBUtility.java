package com.example.w22comp1011gctest1;

import java.sql.*;
import java.util.ArrayList;

public class DBUtility {

    //database connection properties
    private static String userName = "Bibek200455715";
    private static String password = "TLY78N8G_4";
    private static String connectURL = "jdbc:mysql://172.31.22.43:3306/Bibek200455715";

    //This method will select data from the database.
    public static ArrayList<Student> getStudentsFromDB() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();

        String selectQuery = "SELECT * FROM students;";

        try (
                Connection conn = DriverManager.getConnection(connectURL, userName, password); //connects to the database
                Statement st = conn.createStatement(); //Creates connection object
                ResultSet rs = st.executeQuery(selectQuery) // Executes query and stores result
        ) {
//            ResultSetMetaData rsMetaData = rs.getMetaData();
//            int number = rsMetaData.getColumnCount();
//            for(int i=1; i< number; i++)
//            {
//                System.out.println(rsMetaData.getColumnName(i));
//
//            }

            while (rs.next()) {
                int studentNum = rs.getInt("studentNum");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String telephone = rs.getString("telephone");
                String address = rs.getString("homeAddress");
                String province = rs.getString("province");
                String major = rs.getString("major");
                int avgGrade = rs.getInt("avgGrade");

                //Creating new Student Object
                Student newStudent = new Student(studentNum, firstName, lastName,  telephone, address, province, avgGrade, major);

                //adding in the object list
                students.add(newStudent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static ArrayList<Student> getStudentsFromON() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();

        String selectQuery = "SELECT * FROM students WHERE province = 'On';";

        try (
                Connection conn = DriverManager.getConnection(connectURL, userName, password); //connects to the database
                Statement st = conn.createStatement(); //Creates connection object
                ResultSet rs = st.executeQuery(selectQuery) // Executes query and stores result
        ) {
//            ResultSetMetaData rsMetaData = rs.getMetaData();
//            int number = rsMetaData.getColumnCount();
//            for(int i=1; i< number; i++)
//            {
//                System.out.println(rsMetaData.getColumnName(i));
//
//            }

            while (rs.next()) {
                int studentNum = rs.getInt("studentNum");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String telephone = rs.getString("telephone");
                String address = rs.getString("homeAddress");
                String province = rs.getString("province");
                String major = rs.getString("major");
                int avgGrade = rs.getInt("avgGrade");

                //Creating new Student Object
                Student newStudent = new Student(studentNum, firstName, lastName,  telephone, address, province, avgGrade, major);

                //adding in the object list
                students.add(newStudent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static void main(String[] args) throws SQLException {
        getStudentsFromDB();
    }
}
