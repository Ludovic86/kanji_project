/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Fenetre2 extends JFrame implements ActionListener {
 
    private static Connection con1 = null;
    private static Statement stmt = null;
    private static Statement stmt2 = null;
    private static Statement stmt3 = null;
    private static ResultSet rs = null;
    private static ResultSet rs2 = null;
    private static ResultSet rs3 = null;
    private static ResultSet rs4 = null;
    private static ResultSet rs5 = null;
    private static ResultSet rs6 = null;
    static String kanji;  
    static String lecture;
    static String lecture2;
    static String sens;
    private Panneau pan = new Panneau();
    private JButton bouton = new JButton("Caractère précédent");
    private JButton bouton2 = new JButton("Caractrère suivant");
    private JButton bouton3 = new JButton("Retour au premier caractère");
    private JPanel container = new JPanel();
    private JLabel label = new JLabel("Kanji");
    private JLabel label2 = new JLabel("Kun'yomi");
    private JLabel label3 = new JLabel("On'yomi");
    private JLabel label4 = new JLabel("Sens");
  
  
  private int compteur = 0;

  public Fenetre2(){
    this.setTitle("Exercice");
    this.setSize(600, 300);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    

    container.setBackground(Color.lightGray);
    container.setLayout(new BorderLayout());
    container.add(pan, BorderLayout.CENTER);

    bouton.addActionListener(this);
    bouton2.addActionListener(this);
    bouton3.addActionListener(this);

    JPanel south = new JPanel();
    south.setBackground(Color.lightGray);
    south.add(bouton);
    south.add(bouton2);
    south.add(bouton3);
    container.add(south, BorderLayout.SOUTH);
    
    JPanel west = new JPanel();
    west.setBackground(Color.lightGray);
    west.setLayout(new GridLayout(3,1));
    west.add(label3);
    container.add(west, BorderLayout.WEST);
   
    Font police = new Font("MS Gothic", Font.BOLD, 64);
    Font police2 = new Font("MS Gothic", Font.BOLD, 16);
    label.setFont(police);
    label2.setFont(police2);
    label3.setFont(police2);
    label4.setFont(police2);
    label.setForeground(Color.blue);
    label2.setForeground(Color.blue);
    label3.setForeground(Color.blue);
    label4.setForeground(Color.blue);
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setVerticalAlignment(JLabel.CENTER);
    label2.setHorizontalAlignment(JLabel.CENTER);
    label2.setVerticalAlignment(JLabel.CENTER);
    label3.setHorizontalAlignment(JLabel.CENTER);
    label3.setVerticalAlignment(JLabel.CENTER);
    label4.setHorizontalAlignment(JLabel.CENTER);
    label4.setVerticalAlignment(JLabel.CENTER);
    container.add(label, BorderLayout.CENTER);
    container.add(label2, BorderLayout.EAST);
//    container.add(label3, BorderLayout.WEST);
    container.add(label4, BorderLayout.NORTH);
    this.setContentPane(container);
    this.setVisible(true);

  }
  


  public void actionPerformed(ActionEvent arg0) {
 
        if(arg0.getSource() == bouton){

        }
        if(arg0.getSource() == bouton2){

        }
        if(arg0.getSource() == bouton3){

        }
      
        try{
            con1 = DBConnect.getConnection();
            stmt = con1.createStatement();
            stmt2 = con1.createStatement();
            stmt3 = con1.createStatement();
            String select = "SELECT caractere from kanji order by rand() limit 1";
            String select2 = "SELECT lecture1 from kanji where lecture1 is not null order by rand() limit 1";
            String select3 = "SELECT lecture2 from kanji where lecture2 is not null order by rand() limit 1";
            
            rs = stmt.executeQuery(select);
            rs2 = stmt2.executeQuery(select2);
            rs3 = stmt3.executeQuery(select3);
                    while(rs.next()){
                    kanji = rs.getString("caractere");
                    }
                   while(rs2.next()){
                    lecture = rs2.getString("Lecture1");
                    }
                    while(rs3.next()){
                    lecture2 = rs3.getString("lecture2");
                    }
        } catch (SQLException err){
                System.out.println(err.getMessage());
            }

        label.setText(kanji);
        label2.setText(lecture);
        label3.setText(lecture2);
        label4.setText(sens);
       
}
  

}
