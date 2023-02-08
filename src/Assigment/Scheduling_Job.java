package Assigment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Scheduling_Job extends JFrame {

    Scheduling_Job(){

        JLabel jLabel1 = new JLabel();
        jLabel1.setText("Job name :- ");
        jLabel1.setBounds(40,50,130,30);
        jLabel1.setFont(new Font("Arial", Font.PLAIN, 23));

        JTextField jTextField1 = new JTextField();
        jTextField1.setBounds(185,53,120,28);
        jTextField1.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        jTextField1.setColumns(10);



        JLabel jLabel2 = new JLabel();
        jLabel2.setText(" Time :- ");
        jLabel2.setBounds(80,90,110,30);
        jLabel2.setFont(new Font("Arial", Font.PLAIN, 23));
        this.setTitle("Scheduling Job");

        JTextField jTextField2 = new JTextField();
        jTextField2.setBounds(185,95,120,28);
        jTextField2.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        jTextField2.setColumns(10);



        JButton jButton = new JButton();
        jButton.setFocusable(false);
        jButton.setText("OK");
        jButton.setBounds(130,140,80,25);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Connection connection = null;

                try{
                    connection = DriverManager.getConnection("", "root", "roshan09876");
                    System.out.println("Connection Successful");

                }catch (SQLException sqlException){
                    System.out.println("Error Connection Unsuccessful");

                }
            }
        });



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
        new Scheduling_Job();
    }
}


