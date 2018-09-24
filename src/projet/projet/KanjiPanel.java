
package projet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
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
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import static projet.Accueil.backgroundApp;
import static projet.Accueil.boutonStyling;
import static projet.Accueil.boutonbg;
import static projet.Accueil.createFont;
import static projet.Accueil.getUserid;
import static projet.Accueil.hoverColor;


public class KanjiPanel extends JFrame implements ActionListener {
    
  private static Connection con1 = null;
  private static Statement stmt = null;
  private static Statement stmt2 = null;
  private static PreparedStatement prepst = null;
  private static ResultSet rs = null;
  private static ResultSet rs2 = null;
  private static ResultSet rs3 = null;
  static String kanji;  
  private JPanel container = new JPanel();
  private JPanel grid = new JPanel();
  private static JButton[] listeBoutons = null;
  Hashtable ht = new Hashtable();
  int i;
  private String choix1;  
  String niveau;
  Font police = new Font("MS Gothic", Font.BOLD, 20);
  public static Color badColor = new Color(226, 61, 66);
  public static Color lessBadColor = new Color(249, 171, 34);
  public static Color okColor = new Color(229, 210, 6);
  public static Color goodColor = new Color(115, 232, 48);
  public InputStream kanjistyle = getClass().getResourceAsStream("AiharaHudemojiKaisho3.00.ttf");
  
    
   public KanjiPanel(String choix, String level, String user){
       
       
       niveau = level;
       i = getMinKey(niveau);
       
    this.setTitle(level+"("+user+")");
    //implantation de la taille selon le niveau
    this.setSize(800, 800);
    this.setResizable(true);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLocationRelativeTo(null); 
    
    choix1=choix;
    
    container.setBackground(backgroundApp);
    container.setLayout(new BorderLayout());
    
    container.setFont(police);
    grid.setBackground(Color.lightGray);
    grid.setLayout(new GridLayout(8, 4));
       
    
    if (!"Hors connexion".equals(user)){
        kanjiGenLogged();
    }
    else
        kanjiGenUnlogged();
        
        
    container.add(grid, BorderLayout.CENTER);
    this.setContentPane(container);
    this.setVisible(true);
       
    
       
       System.out.println(choix1);    
       
       
       
   }
   
     public void kanjiGenUnlogged(){
         try{
            con1 = DBConnect.getConnection();
            stmt = con1.createStatement();
            String select = "SELECT caractere FROM kanji where JLPT = "+"'"+niveau+"'";
            rs = stmt.executeQuery(select);
            listeBoutons = new JButton[1000];
                    while(rs.next()){
                       
                    kanji = rs.getString("caractere");
                    ht.put(kanji, i);
                    listeBoutons[i] = new JButton();
                    listeBoutons[i].addActionListener(this);
                    listeBoutons[i].setText(kanji);
                    listeBoutons[i].setFocusPainted(false);
                    boutonStyling(listeBoutons[i]);
                    InputStream kanjistyle = getClass().getResourceAsStream("AiharaHudemojiKaisho3.00.ttf");
                    listeBoutons[i].setFont(createFont(kanjistyle, 28f));

                    
                   // this.add(listeBoutons[i]);
                    grid.add(listeBoutons[i]);
                    i++; 
                        
                    }
        } catch (SQLException err){
                System.out.println(err.getMessage());
            }
     }
   
     public void kanjiGenLogged(){
        
        try{
            con1 = DBConnect.getConnection();
            stmt = con1.createStatement();
            String select = "SELECT caractere FROM kanji where JLPT = "+"'"+niveau+"'";
            int score = 0;
            rs = stmt.executeQuery(select);
            listeBoutons = new JButton[1000];
                    while(rs.next()){      
                    kanji = rs.getString("caractere");
                    ht.put(kanji, i);
                    score = getScore(i);
                    if (score < 3){
                      colorSet("red");  
                    }
                    if (score >= 3 && score < 5){
                      colorSet("orange");  
                    }
                    if (score >= 5 && score < 9){
                      colorSet("yellow");  
                    }
                    if (score >= 10){
                      colorSet("green");  
                    } 
                    i++;
                    }
        } catch (SQLException err){
                System.out.println(err.getMessage());
            }
        
    }

     public String getNiveau() {
        return niveau;
    }
   
     public void actionPerformed(ActionEvent arg0) {
        Object recup = arg0.getSource();
        String caractere = arg0.getActionCommand();
        int key = (int) ht.get(caractere);
        if(arg0.getSource() == recup && "Révisons".equals(choix1)){
        CaractereRev carrev = new CaractereRev(caractere ,key, niveau, Accueil.getUsername());  
        }
        else
        if(arg0.getSource() == recup && "Exercices".equals(choix1)){
        CaractereEx carex = new CaractereEx(caractere ,key, niveau, Accueil.getUsername()); 
        }


       
}
     
     public int getScore(int i){
         int score = 0;
         
          try{
            stmt2 = con1.createStatement();
            String select = "SELECT `"+i+"` FROM user_data WHERE iduser ="+getUserid();
            rs2 = stmt2.executeQuery(select);
                    while(rs2.next()){
                     score = rs2.getInt(1);
                    }
        } catch (SQLException err){
                System.out.println(err.getMessage());
            }
         return score;
     }
     
     public void colorSet(String color){
         switch (color) {
             case "red": coloredButtonGen(badColor);
                    break;
             case "orange": coloredButtonGen(lessBadColor);
                    break;  
             case "yellow": coloredButtonGen(okColor);
                    break;
             case "green": coloredButtonGen(goodColor); 
                    break;       
         }
     }
     
     public int getMinKey(String level){
      int x =5;
    try{   
      con1 = DBConnect.getConnection();
      prepst = con1.prepareStatement("SELECT MIN(idkanji) as min from kanji where JLPT = ?");
      prepst.setString(1, level);
      rs3 = prepst.executeQuery();
            while(rs3.next()){
            x = rs3.getInt("min");
                System.out.println("x="+x);
            }
    } catch (SQLException err){
                System.out.println(err.getMessage());
            } 
      return x;
  }
     
     public static void boutonStyling2(JButton bouton, Color customcol){
    bouton.setForeground(customcol);
    bouton.setBackground(boutonbg);
    Border line = new LineBorder(Color.BLACK);
    
    Border margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    bouton.setBorder(compound);
    bouton.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        bouton.setBackground(customcol);
        bouton.setForeground(Color.WHITE);
    }
        public void mouseExited(java.awt.event.MouseEvent evt) {
        bouton.setBackground(boutonbg);
        bouton.setForeground(customcol);
    }
});
    }
     
     public void coloredButtonGen(Color customcol){
                    listeBoutons[i] = new JButton();
                    listeBoutons[i].addActionListener(this);
                    listeBoutons[i].setText(kanji);
                    listeBoutons[i].setFocusPainted(false);
                    boutonStyling2(listeBoutons[i], customcol);
                    InputStream kanjistyle = getClass().getResourceAsStream("AiharaHudemojiKaisho3.00.ttf");
                    listeBoutons[i].setFont(createFont(kanjistyle, 28f));
                    this.add(listeBoutons[i]);
                    grid.add(listeBoutons[i]);
    }
    
}
