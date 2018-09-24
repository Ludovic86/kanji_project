/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import static projet.KanjiPanel.badColor;
import static projet.KanjiPanel.goodColor;
import static projet.Main.acc1;


public class Accueil extends JFrame implements ActionListener  {

  

  public JButton bouton = new JButton("Révisions");
  public JButton bouton2 = new JButton("Exercices");
  public JButton bouton3 = new JButton("Login");
  public JButton bouton4 = new JButton("Création compte");
  public JPanel container = new JPanel();
  public JPanel panbou = new JPanel();
  public JPanel panbou2 = new JPanel();
  public JPanel panbou3 = new JPanel();
  JPanel grid = new JPanel();
  JPanel grid2 = new JPanel();
  public JLabel label = new JLabel("覚えるかんじ");
  public JLabel label2 = new JLabel("Ludovic Bonnefoy");
  public static String username;
  public static int userid;
  public static Color backgroundApp = new Color(180, 204, 227);
  public static Color hoverColor = new Color(69, 69, 69);
  public static Color boutonbg = new Color(219, 219, 219);
  public static Font police = new Font("Helvetica", Font.BOLD, 10);
  public static Font police2 = new Font("Helvetica", Font.BOLD, 16);
  public InputStream menustyle = getClass().getResourceAsStream("851MkPOP_001.ttf");

  public Accueil(String user, int id) {

      username = user; 
      userid = id;
      
    this.setTitle("Accueil"+"("+user+")");
    this.setSize(700, 400);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLocationRelativeTo(null);

  
    
    panbou.setBackground(backgroundApp);
    panbou2.setBackground(backgroundApp);
    panbou3.setBackground(backgroundApp);
    container.setBackground(backgroundApp);
    container.setLayout(new BorderLayout());
    
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setVerticalAlignment(JLabel.CENTER);
    label2.setHorizontalAlignment(JLabel.CENTER);
    
    bouton.addActionListener(this);
    bouton2.addActionListener(this);
    bouton3.addActionListener(this);
    bouton4.addActionListener(this);
    
    bouton.setFocusPainted(false);
    bouton2.setFocusPainted(false);
    bouton3.setFocusPainted(false);
    bouton4.setFocusPainted(false);
    
    boutonStyling(bouton);
    boutonStyling(bouton2);
    boutonStyling(bouton3);
    boutonStyling(bouton4);
    
 
    
    label.setFont(createFont(menustyle, 70f));

    label.setForeground(hoverColor);
    label2.setForeground(Color.WHITE);
    label2.setOpaque(true);
    label2.setBackground(hoverColor);
    label2.setFont(police);
    bouton.setFont(police2);
    bouton2.setFont(police2);
    bouton3.setFont(police2);
    bouton4.setFont(police2);
    panbou.add(bouton);
    panbou.add(bouton2);
    panbou2.add(bouton3);
    panbou2.add(bouton4);
    

    
    grid.setBackground(Color.WHITE);
    grid.setLayout(new GridLayout(2, 1)); 
    grid2.setBackground(backgroundApp);
    grid2.setLayout(new GridLayout(1, 2));
    grid.add(panbou);
    grid.add(label2);
    grid2.add(panbou2);
    this.setContentPane(grid);
    this.setVisible(true);
    container.add(panbou2, BorderLayout.EAST);
    container.add(label, BorderLayout.CENTER);
    container.add(grid, BorderLayout.SOUTH);

    
    this.setContentPane(container);
    this.setVisible(true);
}

    public static int getUserid() {
        return userid;
    }

    public static String getUsername() {
        return username;
    }

    public void actionPerformed(ActionEvent arg0) {


            if(arg0.getSource() == bouton){
            ChoixNiveau rev = new ChoixNiveau("Révisions", getUsername());
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
            if(arg0.getSource() == bouton2){
            ChoixNiveau rev = new ChoixNiveau("Exercices", getUsername());
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));    
            }
            if(arg0.getSource() == bouton3){
            CompteMana cm = new CompteMana("Connexion");
            }
            if(arg0.getSource() == bouton4){
            CompteMana cm = new CompteMana("Création de compte");    
            }  
    }

    public static void closeAccueil(){

        acc1.dispose();
    }
    
    public static void boutonStyling(JButton bouton){
    bouton.setForeground(hoverColor);
    bouton.setBackground(boutonbg);
    Border line = new LineBorder(Color.BLACK);
    
    Border margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    bouton.setBorder(compound);
    bouton.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        bouton.setBackground(hoverColor);
        bouton.setForeground(Color.WHITE);
    }
        public void mouseExited(java.awt.event.MouseEvent evt) {
        bouton.setBackground(boutonbg);
        bouton.setForeground(hoverColor);
    }
});
    } 
    
    public static void boutonStylingcheck(JButton bouton){
    bouton.setForeground(goodColor);
    bouton.setBackground(boutonbg);
    Border line = new LineBorder(Color.BLACK);
    
    Border margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    bouton.setBorder(compound);
    bouton.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        bouton.setBackground(goodColor);
        bouton.setForeground(Color.WHITE);
    }
        public void mouseExited(java.awt.event.MouseEvent evt) {
        bouton.setBackground(boutonbg);
        bouton.setForeground(goodColor);
    }
});
    }
        
    public static void boutonStylingcross(JButton bouton){
    bouton.setForeground(badColor);
    bouton.setBackground(boutonbg);
    Border line = new LineBorder(Color.BLACK);
    
    Border margin = new EmptyBorder(5, 15, 5, 15);
    Border compound = new CompoundBorder(line, margin);
    bouton.setBorder(compound);
    bouton.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        bouton.setBackground(badColor);
        bouton.setForeground(Color.WHITE);
    }
        public void mouseExited(java.awt.event.MouseEvent evt) {
        bouton.setBackground(boutonbg);
        bouton.setForeground(badColor);
    }
});
    } 
        
    public static Font createFont(InputStream is, float size){
        Font customFont = null;
        try {
            
     customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
     GraphicsEnvironment ge = 
         GraphicsEnvironment.getLocalGraphicsEnvironment();
     ge.registerFont(customFont);
        } catch (IOException|FontFormatException e) {
     
         }
      
        return customFont;
        
    }


}
