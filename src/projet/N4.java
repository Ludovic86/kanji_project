
package projet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class N4 extends JFrame implements ActionListener {
    
  private static Connection con1 = null;
  private static Statement stmt = null;
  private static PreparedStatement prepst = null;
  private static ResultSet rs = null;
  private static ResultSet rs2 = null;
  static String kanji;  
  private JPanel container = new JPanel();
  private JPanel grid = new JPanel();
  private JButton[] listeBoutons = null;
  Hashtable ht = new Hashtable();
  int i;
  private String choix1;  
    
   public N4(String choix, String user){
       
    this.setTitle("JLPT 4"+"("+user+")");
    this.setSize(800, 800);
    this.setResizable(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null); 
    
    choix1=choix;
    
    i = getMinKey("N4");
    
    container.setBackground(Color.lightGray);
    container.setLayout(new BorderLayout());
    Font police = new Font("MS Gothic", Font.BOLD, 20);
    container.setFont(police);
    grid.setBackground(Color.lightGray);
    grid.setLayout(new GridLayout(8, 4));
       
       
        try{
            con1 = DBConnect.getConnection();
            stmt = con1.createStatement();
            String select = "SELECT caractere FROM kanji where JLPT = 'N4'";
            rs = stmt.executeQuery(select);
            listeBoutons = new JButton[1000];
                    while(rs.next()){
                       
                    kanji = rs.getString("caractere");
                    ht.put(kanji, i);
                    listeBoutons[i] = new JButton();
                    listeBoutons[i].addActionListener(this);
                    listeBoutons[i].setText(kanji);
                 //   listeBoutons[i].setBorderPainted(false);
                    listeBoutons[i].setFocusPainted(false);
                    listeBoutons[i].setContentAreaFilled(false);
                    listeBoutons[i].setFont(police);
                    
                    this.add(listeBoutons[i]);
                    grid.add(listeBoutons[i]);
                    i++; 
                        
                    }
        } catch (SQLException err){
                System.out.println(err.getMessage());
            }
    container.add(grid, BorderLayout.CENTER);
    this.setContentPane(container);
    this.setVisible(true);
       
    
       
       System.out.println(choix1);    
       
       
       
   }
   
   
     public void actionPerformed(ActionEvent arg0) {
        Object recup = arg0.getSource();
        String caractere = arg0.getActionCommand();
        int key = (int) ht.get(caractere);
        if(arg0.getSource() == recup && "Révisons".equals(choix1)){
        CaractereRev carrev = new CaractereRev(caractere ,key, "N4", Accueil.getUsername());  
        }
        else
        if(arg0.getSource() == recup && "Exercices".equals(choix1)){
        CaractereEx carex = new CaractereEx(caractere ,key, "N4", Accueil.getUsername()); 
        }     
}
      public int getMinKey(String level){
      int x =5;
    try{   
      con1 = DBConnect.getConnection();
      prepst = con1.prepareStatement("SELECT MIN(idkanji) as min from kanji where JLPT = ?");
      prepst.setString(1, level);
      rs2 = prepst.executeQuery();
            while(rs2.next()){
            x = rs2.getInt("min");
                System.out.println("x="+x);
            }
    } catch (SQLException err){
                System.out.println(err.getMessage());
            } 
      return x;
  }
    
}
