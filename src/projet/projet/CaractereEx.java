package projet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static projet.Accueil.backgroundApp;
import static projet.Accueil.boutonStyling;
import static projet.Accueil.boutonStylingcheck;
import static projet.Accueil.boutonStylingcross;
import static projet.Accueil.createFont;
import static projet.Accueil.getUserid;
import static projet.Accueil.hoverColor;
import static projet.KanjiPanel.badColor;
import static projet.KanjiPanel.goodColor;


public class CaractereEx extends JFrame implements ActionListener{
    
  private static Connection con1 = null;
  private static Connection con2 = null;
  private static Statement stmt = null;
  private static Statement stmt2 = null;
  private static Statement stmt3 = null;
  private static PreparedStatement prepst = null;
  private static ResultSet rs2 = null;
  private static ResultSet rs3 = null;
  private static ResultSet rs4 = null;
  private static ResultSet rs5 = null;
  static String kanji2;  
  static String lecture;
  static String lecture2;
  static String sens;
  private Panneau pan = new Panneau();
  private JButton bouton = new JButton("Afficher les réponses");
  private JButton bouton2 = new JButton("✓");
  private JButton bouton3 = new JButton("X");
  private JPanel container = new JPanel();
  private JPanel south = new JPanel();
  private JPanel west = new JPanel();
  private JPanel east = new JPanel();
  private JLabel label = new JLabel();
  private JLabel labelkun = new JLabel();
  private JLabel labelon = new JLabel();
  private JLabel labelsens = new JLabel();
  private JLabel[] listeLabel = null;
  private String level;
  Font police = new Font("MS Gothic", Font.BOLD, 80);
  Font police2 = new Font("Helvetica", Font.BOLD, 18);
  Font police3 = new Font("MS Gothic", Font.BOLD, 25);
  public InputStream kanjistyle = getClass().getResourceAsStream("AiharaHudemojiKaisho3.00.ttf");
  
  
  
  private int i = 1;
  private int j = 0;
  private int compteur = 0;
  private String namePlate;

  public CaractereEx(String caractere, int key, String niveau, String user){
    
    level = niveau;
    compteur = key;
    namePlate = caractere;
    
    
    this.setTitle(namePlate+"("+user+")");
    this.setSize(500, 400);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLocationRelativeTo(null); 
    
    

    
    container.setBackground(backgroundApp);
    container.setLayout(new BorderLayout());
    container.add(pan, BorderLayout.CENTER);
    west.setLayout(new GridLayout(7, 1));
    west.setBackground(backgroundApp);
    east.setLayout(new GridLayout(7, 1));
    east.setBackground(backgroundApp);
    container.add(west, BorderLayout.WEST);
    container.add(east, BorderLayout.EAST);
    labelkun.setText("Kun'Yomi");
    labelon.setText("On'Yomi");
    west.add(labelkun);
    east.add(labelon);
    
    
    label.setText(namePlate);

    
    label.setFont(createFont(kanjistyle, 150f));
    labelkun.setFont(police2);
    labelon.setFont(police2);
    labelsens.setFont(police2);
    west.setFont(police3);
    east.setFont(police3);
    label.setForeground(hoverColor);
    labelkun.setForeground(hoverColor);
    labelon.setForeground(hoverColor);
    
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setVerticalAlignment(JLabel.CENTER);
    labelsens.setForeground(Color.black);
    labelsens.setHorizontalAlignment(JLabel.CENTER);
    labelsens.setVerticalAlignment(JLabel.CENTER);
    container.add(label, BorderLayout.CENTER);
    container.add(labelsens, BorderLayout.NORTH);

    
    

    
    bouton.addActionListener(this);
    bouton2.addActionListener(this);
    bouton3.addActionListener(this);
    boutonStyling(bouton);
    boutonStylingcheck(bouton2);
    boutonStylingcross(bouton3);
    bouton.setFocusPainted(false);
    bouton2.setFocusPainted(false);
    bouton3.setFocusPainted(false);
    bouton2.setVisible(false);
    bouton3.setVisible(false);
    south.add(bouton);
    south.add(bouton2);
    south.add(bouton3);
    south.setBackground(hoverColor);
    container.add(south, BorderLayout.SOUTH);

    pageGen(compteur);
    
    this.setContentPane(container);
    this.setVisible(true);


        
  }
  


  public void actionPerformed(ActionEvent arg0) {
 
        if(arg0.getSource() == bouton){
        west.setVisible(true);
        east.setVisible(true);
        labelsens.setVisible(true);
        bouton.setVisible(false);
        bouton2.setVisible(true);
        bouton3.setVisible(true);
        
        }
        if(arg0.getSource() == bouton2){
          int recup = 0;  
            try{
            con1 = DBConnect.getConnection();
            stmt = con1.createStatement();
            stmt2 = con1.createStatement();
            stmt3 = con1.createStatement();
            String scorecheck = "SELECT `"+compteur+"` FROM `testk`.`user_data` WHERE iduser = "+getUserid();
            String scoreupdate = "UPDATE `testk`.`user_data` SET `"+compteur+"` = `"+compteur+"`+1 WHERE iduser = "+getUserid();
            String scorezero = "UPDATE `testk`.`user_data` SET `"+compteur+"` = 0 WHERE iduser = "+getUserid();
            rs2 = stmt.executeQuery(scorecheck);
            if(rs2.next()){
                rs2.getObject(1);
                if (rs2.wasNull()){
                    stmt2.executeUpdate(scorezero);
                }
            }

            stmt3.executeUpdate(scoreupdate);
         } catch (SQLException err){
            System.out.println(err.getMessage());
        }
        compteur = generateRandomIntIntRange(getMinKey(level), getMaxKey(level));
        pageRefresh(compteur);
        }
        if(arg0.getSource() == bouton3){
        compteur = generateRandomIntIntRange(getMinKey(level), getMaxKey(level));
        pageRefresh(compteur);    
        }    

      
      
}
  
  public void pageGen(int compteur){
     
   
       
      try{
            con1 = DBConnect.getConnection();
            stmt = con1.createStatement();
            String kun = "SELECT KUN1, KUN2, KUN3, KUN4, KUN5, KUN6 FROM kanji WHERE idkanji="+compteur;
            rs2 = stmt.executeQuery(kun);
            String valLect;
            listeLabel = new JLabel[5];
                    while(rs2.next()){
                        for (i = 1; i < 6; i++){
                        valLect = rs2.getNString(i);    
                        listeLabel[j] = new JLabel(valLect);
                        listeLabel[j].setForeground(hoverColor);
                        InputStream kanjistyle = getClass().getResourceAsStream("AiharaHudemojiKaisho3.00.ttf");
                        listeLabel[j].setFont(createFont(kanjistyle, 35f));
                        this.add(listeLabel[j]);
                        west.add(listeLabel[j]);
                        west.setVisible(false);
                        }
                        
                    j++;    
                    }  
            String on = "SELECT ON1, ON2, ON3, ON4, ON5, ON6 FROM kanji WHERE idkanji="+compteur;
            rs3 = stmt.executeQuery(on);
                    while(rs3.next()){
                          for (i = 1; i < 6; i++){
                        valLect = rs3.getNString(i);    
                        listeLabel[j] = new JLabel(valLect);
                        listeLabel[j].setForeground(hoverColor);
                        InputStream kanjistyle = getClass().getResourceAsStream("AiharaHudemojiKaisho3.00.ttf");
                        listeLabel[j].setFont(createFont(kanjistyle, 35f));
                        this.add(listeLabel[j]);
                        east.add(listeLabel[j]);  
                        east.setVisible(false);
                        }
                        
                    j++;
                    }  
            String imi = "SELECT sensefr FROM kanji WHERE idkanji="+compteur;
            rs4 = stmt.executeQuery(imi);
                    while(rs4.next()){
                        sens = rs4.getString("sensefr");
                        labelsens.setText(sens);
                        labelsens.setForeground(hoverColor);
                        labelsens.setVisible(false);
                    }
        } catch (SQLException err){
                System.out.println(err.getMessage());
            }
  }
  
  public void pageRefresh(int compteur){
      
        west.removeAll();
        east.removeAll();
        west.add(labelkun);
        east.add(labelon);
        west.setVisible(false);
        east.setVisible(false);
        labelsens.setVisible(false);
        bouton.setVisible(true);
        bouton2.setVisible(false);
        bouton3.setVisible(false);
      
      try{
            con2 = DBConnect.getConnection();
            stmt = con2.createStatement();
            String kun = "SELECT KUN1, KUN2, KUN3, KUN4, KUN5, KUN6 FROM kanji WHERE idkanji="+compteur;
            rs2 = stmt.executeQuery(kun);
            String valLect;
            listeLabel = new JLabel[1000];
                    while(rs2.next()){
                        for (i = 1; i < 6; i++){
                        valLect = rs2.getNString(i);    
                        listeLabel[j] = new JLabel(valLect);
                        listeLabel[j].setForeground(hoverColor);
                        InputStream kanjistyle = getClass().getResourceAsStream("AiharaHudemojiKaisho3.00.ttf");
                        listeLabel[j].setFont(createFont(kanjistyle, 35f));
                        this.add(listeLabel[j]);
                        west.add(listeLabel[j]);  
                        }
                        
                    j++;    
                    }  
            String on = "SELECT ON1, ON2, ON3, ON4, ON5, ON6 FROM kanji WHERE idkanji="+compteur;
            rs3 = stmt.executeQuery(on);
                    while(rs3.next()){
                          for (i = 1; i < 6; i++){
                        valLect = rs3.getNString(i);    
                        listeLabel[j] = new JLabel(valLect);
                        listeLabel[j].setForeground(hoverColor);
                        InputStream kanjistyle = getClass().getResourceAsStream("AiharaHudemojiKaisho3.00.ttf");
                        listeLabel[j].setFont(createFont(kanjistyle, 35f));
                        this.add(listeLabel[j]);
                        east.add(listeLabel[j]);  
                        }
                        
                    j++;
                    }  
            String imi = "SELECT sensefr FROM kanji WHERE idkanji="+compteur;
            rs4 = stmt.executeQuery(imi);
                    while(rs4.next()){
                        sens = rs4.getString("sensefr");
                        labelsens.setText(sens);
                        labelsens.setForeground(hoverColor);
                    } 
            String kanji = "SELECT caractere FROM kanji WHERE idkanji="+compteur;
            rs5 = stmt.executeQuery(kanji);
                    while(rs5.next()){
                        kanji = rs5.getString("caractere");
                        label.setText(kanji);
                        this.setTitle(kanji);
                    }  
        } catch (SQLException err){
                System.out.println(err.getMessage());
            }
      
  }
  
  public static int generateRandomIntIntRange(int min, int max) {
    Random r = new Random();
    return r.nextInt((max - min) + 1) + min;
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
  
  public int getMaxKey(String level){
      int x =5;
    try{   
      con1 = DBConnect.getConnection();
      prepst = con1.prepareStatement("SELECT MAX(idkanji) as max from kanji where JLPT = ?");
      prepst.setString(1, level);
      rs2 = prepst.executeQuery();
            while(rs2.next()){
            x = rs2.getInt("max");
                System.out.println("x="+x);
            }
    } catch (SQLException err){
                System.out.println(err.getMessage());
            } 
      return x;
  }


}