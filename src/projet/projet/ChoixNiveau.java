
package projet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static projet.Accueil.backgroundApp;
import static projet.Accueil.boutonStyling;
import static projet.Accueil.getUserid;
import static projet.Accueil.getUsername;
import static projet.Accueil.hoverColor;


public class ChoixNiveau extends JFrame implements ActionListener {

  private JButton bouton = new JButton("JLPT 5");
  private JButton bouton2 = new JButton("JLPT 4");
  private JButton bouton3 = new JButton("JLPT 3");
  private JButton bouton4 = new JButton("JLPT 2");
  private JButton bouton5 = new JButton("JLPT 1");
  private JButton bouton6 = new JButton("Retour accueil");
  private JPanel container = new JPanel();
  private JPanel panbou = new JPanel();
  private JPanel panbou2 = new JPanel();
  private JLabel label = new JLabel();
  private String choix1;

  
  public ChoixNiveau(String choix, String user){
    this.setTitle(choix+"("+user+")");
    this.setSize(500, 400);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLocationRelativeTo(null);
    
    choix1=choix;
    

    panbou.setBackground(backgroundApp);
    panbou2.setBackground(backgroundApp);
    container.setBackground(backgroundApp);
    container.setLayout(new BorderLayout());
    
    label.setForeground(hoverColor);
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setVerticalAlignment(JLabel.CENTER);
    
    bouton.addActionListener(this);
    bouton2.addActionListener(this);
    bouton3.addActionListener(this);
    bouton4.addActionListener(this);
    bouton5.addActionListener(this);
    bouton6.addActionListener(this);
    
    bouton.setFocusPainted(false);
    bouton2.setFocusPainted(false);
    bouton3.setFocusPainted(false);
    bouton4.setFocusPainted(false);
    bouton5.setFocusPainted(false);
    
    boutonStyling(bouton);
    boutonStyling(bouton2);
    boutonStyling(bouton3);
    boutonStyling(bouton4);
    boutonStyling(bouton5);
    boutonStyling(bouton6);
    
    Font police = new Font("Helvetica", Font.BOLD, 32);
    Font police2 = new Font("Helvetica", Font.BOLD, 16);
    
    bouton.setFont(police2);
    bouton2.setFont(police2);
    bouton3.setFont(police2);
    bouton4.setFont(police2);
    bouton5.setFont(police2);
    label.setFont(police);
    label.setText(choix);

    

    JPanel grid = new JPanel();
    grid.setBackground(backgroundApp);
    grid.setLayout(new GridLayout(6, 1));
    grid.add(Box.createRigidArea(new Dimension(5,0)));
    grid.add(bouton);
    grid.add(bouton2);
    grid.add(bouton3);
    grid.add(bouton4);
    grid.add(bouton5);
    
    this.setContentPane(grid);
    this.setVisible(true);
    panbou.add(grid);
    container.add(label, BorderLayout.NORTH);
    container.add(panbou, BorderLayout.CENTER);
    container.add(bouton6, BorderLayout.SOUTH);
    
    System.out.println(choix1);

    
    this.setContentPane(container);
    this.setVisible(true);
}

public void actionPerformed(ActionEvent arg0) {
        if(arg0.getSource() == bouton && "Révisions".equals(choix1)){
        KanjiPanel kp = new KanjiPanel("Révisons", "N5", Accueil.getUsername()); 
        }
        if(arg0.getSource() == bouton && "Exercices".equals(choix1)){
        KanjiPanel kp = new KanjiPanel("Exercices", "N5", Accueil.getUsername()); 
        }
        if(arg0.getSource() == bouton2 && "Révisions".equals(choix1)){
        KanjiPanel kp = new KanjiPanel("Révisons", "N4", Accueil.getUsername());    
        }
        if(arg0.getSource() == bouton2 && "Exercices".equals(choix1)){
        KanjiPanel kp = new KanjiPanel("Exercices", "N4", Accueil.getUsername());   
        }
        if(arg0.getSource() == bouton3){
         
        }
        if(arg0.getSource() == bouton4){
            
        }  
        if(arg0.getSource() == bouton5){
         
        }
        if(arg0.getSource() == bouton6){
         this.dispose();   
         Accueil acc2 = new Accueil(getUsername(), getUserid());
         
        }
    }
}
