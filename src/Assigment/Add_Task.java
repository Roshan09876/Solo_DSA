package Assigment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Add_Task extends JFrame {
    JFrame jFrame;
    JLabel jLabel1, jLabel2;
    Add_Task(){

        jLabel1 = new JLabel();
        jLabel1.setText("Task id :- ");
        jLabel1.setBounds(40,50,100,40);
        jLabel1.setFont(new Font("Arial", Font.PLAIN, 20));

        JTextField jTextField1 = new JTextField();
        jTextField1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        jTextField1.setBounds(140,56,100,25);
        jTextField1.setColumns(10);


        jLabel2 = new JLabel();
        jLabel2.setText("Task :- ");
        jLabel2.setBounds(40,100,100,40);
        jLabel2.setFont(new Font("Arial", Font.PLAIN, 20));

        JTextField jTextField2 = new JTextField();
        jTextField2.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        jTextField2.setBounds(140, 106,150,25);
        jTextField2.setColumns(10);


        JButton jButton = new JButton();
        jButton.setFocusable(false);
        jButton.setText("Ok");
        jButton.setBounds(120,145,80,25);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                Start Connection in Sql Database
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dsa-assignment", "root", "roshan09876");

                    System.out.println("Connection Successful");


                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                    System.out.println("Failed to connect");

                }
//                End Connection in SQl Database


 //                fetching text written in the text field in string variable
                String TaskID = jTextField1.getText();
                String Task = jTextField2.getText();

                String insertSQL = "INSERT INTO addtask (TaskID, Task) VALUES (?, ?)";

                try {
                    assert connection != null;
                    try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                        preparedStatement.setString(1, TaskID);
                        preparedStatement.setString(2, Task);

                        int rowCount = preparedStatement.executeUpdate();
                        System.out.println(rowCount + " row(s) inserted");
                    }
                } catch (SQLException sqlException) {
                    System.out.println("Insertion failed: " + sqlException.getMessage());
                }

            }
        });




        jFrame = new JFrame();
        jFrame.setLayout(null);
        jFrame.setTitle("Add task JFrame");
        jFrame.setSize(450, 320);
        jFrame.add(jLabel1);
        jFrame.add(jLabel2);
        jFrame.add(jTextField1);
        jFrame.add(jTextField2);
        jFrame.add(jButton);
        jFrame.setVisible(true);

    }

    public static void main(String[] args) {
        new Add_Task();
    }
}
