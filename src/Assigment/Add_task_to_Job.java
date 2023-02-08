package Assigment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Add_task_to_Job extends JFrame {

    Add_task_to_Job(){

        JLabel jLabel1 = new JLabel();
        jLabel1.setText("Job id :- ");
        jLabel1.setFont(new Font("Arial", Font.PLAIN,20));
        jLabel1.setBounds(80,30,100,40);

        JTextField jTextField1 = new JTextField();
        jTextField1.setBounds(180,37,80,28);
        jTextField1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        jTextField1.setColumns(10);



        JLabel jLabel2 = new JLabel();
        jLabel2.setText("Task :- ");
        jLabel2.setFont(new Font("Arial", Font.PLAIN, 20));
        jLabel2.setBounds(85,70,100,40);

        JTextField jTextField2 = new JTextField();
        jTextField2.setBounds(180,77,120,28);
        jTextField2.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        jTextField2.setColumns(10);



        JLabel jLabel3 = new JLabel();
        jLabel3.setText("Depends on :- ");
        jLabel3.setBounds(40,110,150,40);
        jLabel3.setFont(new Font("Arial", Font.PLAIN, 20));

        JTextField jTextField3 = new JTextField();
        jTextField3.setBounds(180,117,130,28);
        jTextField3.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
        jTextField3.setColumns(10);



        JButton jButton = new JButton();
        jButton.setText("Ok");
        jButton.setBounds(140,165,80,30);
        jButton.setFocusable(false);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost3306/dsa-assignment","root","roshan09876");
                    System.out.println("Connection Successful");

                }catch (SQLException sqlException){
                    System.out.println("Error Failed Connection " + sqlException.getMessage());

                }
            }
        });



        this.setTitle("Add Task To Job");
        this.setLayout(null);
        this.setSize(450,320);
        this.add(jLabel1);
        this.add(jLabel2);
        this.add(jLabel3);
        this.add(jTextField1);
        this.add(jTextField2);
        this.add(jTextField3);
        this.add(jButton);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Add_task_to_Job();
    }
}
