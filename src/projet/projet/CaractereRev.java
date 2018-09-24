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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static projet.Accueil.backgroundApp;
import static projet.Accueil.boutonStyling;
import static projet.Accueil.createFont;
import static projet.Accueil.hoverColor;


public class CaractereRev extends JFrame implements ActionListener{
    
  private static Connection con1 = null;
  private static Connection con2 = null;
  private static Statement stmt = null;
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
  private JButton bouton = new JButton("Caractère précédent");
  private JButton bouton2 = new JButton("Caractrère suivant");
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

  public CaractereRev(String caractere, int key, String niveau, String user){
    
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
    labelsens.setForeground(hoverColor);
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setVerticalAlignment(JLabel.CENTER);
    labelsens.setForeground(hoverColor);
    labelsens.setHorizontalAlignment(JLabel.CENTER);
    labelsens.setVerticalAlignment(JLabel.CENTER);
    container.add(label, BorderLayout.CENTER);
    container.add(labelsens, BorderLayout.NORTH);

    
    

    
    bouton.addActionListener(this);
    bouton2.addActionListener(this);
    bouton.setFocusPainted(false);
    bouton2.setFocusPainted(false);
    boutonStyling(bouton);
    boutonStyling(bouton2);
    south.add(bouton);
    south.add(bouton2);
    south.setBackground(hoverColor);
    container.add(south, BorderLayout.SOUTH);

    pageGen(compteur, niveau);
    
    


    this.setContentPane(container);
    this.setVisible(true);
        
  }
  


  public void actionPerformed(ActionEvent arg0) {
 
        if(arg0.getSource() == bouton && compteur > getMinKey(level)){    
        compteur --;

        pageRefresh(compteur);
        }
        if(arg0.getSource() == bouton2 && compteur < getMaxKey(level)){
        compteur ++;

        pageRefresh(compteur);
        }

      
      
}
  
  public void pageGen(int compteur, String niveau){
       
      try{
            con1 = DBConnect.getConnection();
            prepst = con1.prepareStatement("SELECT KUN1, KUN2, KUN3, KUN4, KUN5, KUN6 FROM kanji WHERE idkanji= ? and JLPT = ?");
            prepst.setInt(1, compteur);
            prepst.setString(2, niveau);
            System.out.println(niveau);
            System.out.println(compteur);
            rs2 = prepst.executeQuery();
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
                        }
                        
                    j++;    
                    }  
            prepst = con1.prepareStatement("SELECT ON1, ON2, ON3, ON4, ON5, ON6 FROM kanji WHERE idkanji = ? and JLPT = ?");
            prepst.setInt(1, compteur);
            prepst.setString(2, niveau);
            rs3 = prepst.executeQuery();
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
            prepst = con1.prepareStatement("SELECT sensefr FROM kanji WHERE idkanji= ? and JLPT = ?");
            prepst.setInt(1, compteur);
            prepst.setString(2, niveau);
            rs4 = prepst.executeQuery();
                    while(rs4.next()){
                        sens = rs4.getString("sensefr");
                        labelsens.setText(sens);
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