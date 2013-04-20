package analiza_bukmacherska;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.plaf.LayerUI;

public class Rejestracja {
    static void rysuj() throws SQLException, ClassNotFoundException, Exception {
     
        final JFrame f2=new JFrame();
        f2.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        f2.setTitle("Analiza Bukmacherska");
        f2.setLayout(null);
        final WaitLayerUI layerUI = new WaitLayerUI();
        ImagePanel p2 = new ImagePanel(new ImageIcon("images/intro.png").getImage());
           JLabel jB_Anuluj = new javax.swing.JLabel();;
        JLabel jB_rejest= new javax.swing.JLabel();;
        JTextField jTextField1 = new javax.swing.JTextField();
        JLabel jLabel1 = new javax.swing.JLabel();
        final JTextField jTextField2 = new javax.swing.JTextField();
        JLabel jLabel2 = new javax.swing.JLabel();
        final JTextField jTextField3 = new javax.swing.JTextField();
        JLabel jLabel3 = new javax.swing.JLabel();
        final JTextField jTextField4 = new javax.swing.JTextField();
        JLabel jLabel4 = new javax.swing.JLabel();
        JLabel jLabel5 = new javax.swing.JLabel();
        final JTextField jTextField5 = new javax.swing.JTextField();
        JLabel jLabel6 = new javax.swing.JLabel();
        final JTextField jTextField6 = new javax.swing.JTextField();
        JPasswordField jPasswordField1 = new javax.swing.JPasswordField();
        JLabel jLabel7 = new javax.swing.JLabel();
        JPasswordField jPasswordField2 = new javax.swing.JPasswordField();
        JLabel jLabel8 = new javax.swing.JLabel();

        f2.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nazwa Użytkownika : ");
        jLabel2.setText("Imię : ");
        jLabel3.setText("Nazwisko : ");
        jLabel4.setText("Adres e-mail : ");
        jLabel5.setText("Numer Telefonu : ");
        jLabel6.setText("Adres : ");
        jLabel7.setText("Hasło : ");
        jLabel1.setBounds(60, 75, 230, 30);
        jLabel2.setBounds(149, 100, 230, 30);
        jLabel3.setBounds(118, 125, 230, 30);
        jLabel4.setBounds(101, 150, 230, 30);
        jLabel5.setBounds(83, 175, 230, 30);
        jLabel6.setBounds(139, 200, 230, 30);
        jLabel7.setBounds(141, 225, 230, 30);
        jTextField1.setBounds(220, 80, 230, 20);
        jTextField2.setBounds(220, 105, 230, 20);
        jTextField3.setBounds(220, 130, 230, 20);
        jTextField4.setBounds(220, 155, 230, 20);
        jTextField5.setBounds(220, 180, 230, 20);
        jTextField6.setBounds(220, 205, 230, 20);
        jPasswordField1.setBounds(220, 230, 230, 20);
        p2.add(jLabel1);
        p2.add(jLabel2);
        p2.add(jLabel3);
        p2.add(jLabel4);
        p2.add(jLabel5);
        p2.add(jLabel6);
        p2.add(jLabel7);
        p2.add(jTextField1);
        p2.add(jTextField2);
        p2.add(jTextField3);
        p2.add(jTextField4);
        p2.add(jTextField5);
        p2.add(jTextField6);
        p2.add(jPasswordField1);
         jB_Anuluj.setText("         ");
        jB_rejest.setText("        ");

        jB_Anuluj.setBounds(280, 265, 150, 30);
        jB_rejest.setBounds(400, 265, 250, 30);
       // jB_rejest.setBounds(220, 205, 230, 20);
       p2.add(jB_Anuluj);
       p2.add(jB_rejest);
        
        JLayer<JPanel> jlayer2 = new JLayer<JPanel>(p2, layerUI);
        f2.add(jlayer2);
        jlayer2.setBounds(0,0,500,300);
        f2.setUndecorated(true);
        f2.setSize(new Dimension(500,300));
        f2.setResizable(false);
        f2.setLocationRelativeTo(null);
        f2.setVisible(true);
        
       
        jB_Anuluj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
               
                    try {
                        Logowanie.rysuj();
                    } catch (Exception ex) {
                        Logger.getLogger(Intro.class.getName()).log(Level.SEVERE, null, ex);
                    }
              
                f2.dispose();
            }
        });
        jB_rejest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
               
                    try {
                        System.out.println("sdfdsf");
                        SQL sql = new SQL();
                        sql.rejestracja(jTextField2.getText(), jTextField3.getText(), jTextField4.getText(), jTextField5.getText(), jTextField6.getText());
                        sql.con.close(); 

                        
                        Logowanie.rysuj();
                    } catch (Exception ex) {
                        Logger.getLogger(Intro.class.getName()).log(Level.SEVERE, null, ex);
                    }
              
                f2.dispose();
            }
        });
    }
}

