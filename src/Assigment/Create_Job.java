package Assigment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Create_Job extends JFrame {

    Create_Job(){

        JLabel jLabel1 = new JLabel();
        jLabel1.setText("Job :- ");
        jLabel1.setFont(new Font("Arial", Font.PLAIN,20));
        jLabel1.setBounds(92,50,80,30);

        JTextField jTextField1 = new JTextField();
        jTextField1.setBounds(160,55,100,30);
        jTextField1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        jTextField1.setColumns(10);



        JLabel jLabel2 = new JLabel();
        jLabel2.setText("Job name :- ");
        jLabel2.setFont(new Font("Arial", Font.PLAIN,20));
        jLabel2.setBounds(40,90,140,30);

        JTextField jTextField2 = new JTextField();
        jTextField2.setBounds(160,94,120,30);
        jTextField2.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        jTextField2.setColumns(10);



        JButton jButton = new JButton();
        jButton.setText("OK");
        jButton.setBounds(140,150,80,30);
        jButton.setFocusable(false);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                Start Connection in Database

                Connection connection = null;

                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dsa-assignment","root","roshan09876");
                    System.out.println("Connection Successful");

                }catch (SQLException sqlException){
                    sqlException.printStackTrace();
                    System.out.println("Error Connection Failed");

                }
//                End Connection in database


//                fetching data from textField of Create_Job
                String job = jTextField1.getText();
                String jobNAME = jTextField2.getText();

                String insertSQL = "INSERT INTO create_job (job, jobNAME) VALUES (?, ?)";

                try{
                   assert  connection != null;

                   try(PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)){
                       preparedStatement.setString(1, job);
                       preparedStatement.setString(2,jobNAME);

                       int rowCount = preparedStatement.executeUpdate();
                       System.out.println(rowCount + "Row Inserted Successfully");
                   }

                }catch (SQLException sqlException){
                    System.out.println("Insertion Failed " + sqlException.getMessage());

                }
            }
        });



        this.setTitle("Create Job");
        this.setLayout(null);
        this.setSize(450,320);
        this.add(jLabel1);
        this.add(jLabel2);
        this.add(jTextField1);
        this.add(jTextField2);
        this.add(jButton);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new Create_Job();
    }
}
