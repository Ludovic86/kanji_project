/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import static projet.Accueil.backgroundApp;
import static projet.Accueil.boutonStyling;
import static projet.Accueil.hoverColor;




public class CompteMana extends JFrame implements ActionListener {
    
  private  Connection con1 = null;
  private  Statement stmt = null;
  private  ResultSet rs = null;
  private  ResultSet rs2 = null;
  private  PreparedStatement prepst = null;
  private  PreparedStatement prepst2 = null;
  private JButton bouton = new JButton("Soumettre");
  private JPanel container = new JPanel();
  private JPanel panbou = new JPanel();
  private JPanel panbou2 = new JPanel();
  private JLabel label = new JLabel();
  private JLabel labuser = new JLabel("Nom d'utilisateur");
  private JLabel labpass = new JLabel("Mot de passe");
  private JTextField username = new JTextField(15);
  private JPasswordField password = new JPasswordField(15);
  public String choix;

  
  public CompteMana(String choixCompte){
      
    choix = choixCompte;
      
    this.setTitle(choixCompte);
    this.setSize(350, 200);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLocationRelativeTo(null);
    
    Font police = new Font("Helvetica", Font.BOLD, 14);
    Font police2 = new Font("Helvetica", Font.BOLD, 16);
    
    Border border = labuser.getBorder();
    Border margin = new EmptyBorder(10,10,10,10);
    Border margin2 = new EmptyBorder(10,10,10,32);
    labuser.setBorder(new CompoundBorder(border, margin));
    labpass.setBorder(new CompoundBorder(border, margin2));
    
    
    
    panbou.setBackground(backgroundApp);
    panbou2.setBackground(backgroundApp);
    container.setBackground(backgroundApp);
    container.setLayout(new BorderLayout());
    
    username.setPreferredSize(new Dimension(200, 24));
    password.setPreferredSize(new Dimension(200, 24));
    label.setForeground(hoverColor);
    labuser.setForeground(hoverColor);
    labpass.setForeground(hoverColor);
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setVerticalAlignment(JLabel.CENTER);
    label.setText(choixCompte);
    
    bouton.addActionListener(this);
    username.addActionListener(this);
    password.addActionListener(this);

    bouton.setFocusPainted(false);

    
    label.setFont(police2);
    labuser.setFont(police);
    labpass.setFont(police);
    bouton.setFont(police2);
    boutonStyling(bouton);
    panbou.add(bouton);
 
    

    JPanel grid = new JPanel();
    grid.setBackground(backgroundApp);
    grid.setLayout(new FlowLayout());
    grid.add(labuser);
    grid.add(username);
    grid.add(labpass);
    grid.add(password);
    this.setContentPane(grid);
    this.setVisible(true);
   
    container.add(grid, BorderLayout.CENTER);
    container.add(panbou, BorderLayout.SOUTH);
    container.add(label, BorderLayout.NORTH);
    

    
    this.setContentPane(container);
    this.setVisible(true);
}

public void actionPerformed(ActionEvent arg0) {
        if(arg0.getSource() == bouton && "Création de compte".equals(choix)){
        String errcompte = null;
        String user = username.getText();
        String pass = password.getText();
          try{
            con1 = DBConnect.getConnection();
            prepst = con1.prepareStatement("INSERT INTO `testk`.`user_data` (user_name, user_pass)VALUES(?, ?)");
            prepst.setString(1, user);
            prepst.setString(2, pass);
            prepst.executeUpdate();
         } catch (SQLException err){
             System.out.println(err.getMessage());
             errcompte = err.getMessage();  
            }  
          if (errcompte != null){
          JOptionPane.showMessageDialog(this,"Nom d'utilisateur déjà utilisé","Information",JOptionPane.INFORMATION_MESSAGE); 
          this.dispose();
          CompteMana cm = new CompteMana("Création de compte"); 
          }
          else
          JOptionPane.showMessageDialog(this,"Compte crée avec succés","Succès",JOptionPane.INFORMATION_MESSAGE);
          this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); 
               

        
        }
        if(arg0.getSource() == bouton && "Connexion".equals(choix)){
        
        String user = username.getText();
        String pass = password.getText();
        String checkUser ="";
        String checkPass ="";
        int getid =0;
          try{
            con1 = DBConnect.getConnection();
            prepst = con1.prepareStatement("SELECT iduser, user_name, user_pass FROM user_data WHERE user_name = ?");
            prepst.setString(1, user);
            prepst2 = con1.prepareStatement("SELECT user_name, user_pass FROM user_data WHERE user_pass = ?");
            prepst2.setString(1, pass);
            rs = prepst.executeQuery();
            rs2 = prepst2.executeQuery();
            while(rs.next()){
                checkUser = rs.getString("user_name");
                getid = rs.getInt("iduser");
            }
            while(rs2.next()){
                checkPass = rs2.getString("user_pass");
            }    
         } catch (SQLException err){
            System.out.println(err.getMessage());
        }
            System.out.println(user);
            System.out.println(checkUser);
            System.out.println(pass);
            System.out.println(checkPass);
            System.out.println(getid);
          if (user.equals(checkUser) && pass.equals(checkPass)){
             JOptionPane.showMessageDialog(this,"Compte connecté avec succès","Succès",JOptionPane.INFORMATION_MESSAGE);
             this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));             
             Accueil.closeAccueil();
             Accueil acc2 = new Accueil(user, getid);  
          }
          if (!user.equals(checkUser) && pass.equals(checkPass)){    
             JOptionPane.showMessageDialog(this,"Nom d'utilisateur non reconnu, veuillez réessayer","Mon incorrect",JOptionPane.ERROR_MESSAGE); 
          }
          if (!pass.equals(checkPass) && user.equals(checkUser)){    
             JOptionPane.showMessageDialog(this,"Mot de passe incorrect, veuillez réessayer","Pass incorrect",JOptionPane.ERROR_MESSAGE);
          }
          if (!pass.equals(checkPass) && !user.equals(checkUser)){    
             JOptionPane.showMessageDialog(this,"Les données saisies ne sont pas reconnues, merci de créer un compte","Données absentes",JOptionPane.ERROR_MESSAGE);
             this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));  
          }    
              
             
              
        }
}




}
