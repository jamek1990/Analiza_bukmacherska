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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class Logowanie {

 
    static void rysuj() throws SQLException, ClassNotFoundException, Exception {
        JLabel jB_aktualizacja;
        JLabel jB_program;
        JButton mOrderButton;
        JLabel jB_exit;

        final JFrame f=new JFrame();

        f.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        f.setTitle("Analiza Bukmacherska");
        f.setLayout(null);
        final WaitLayerUI layerUI = new WaitLayerUI();
        final ImagePanel p = new ImagePanel(new ImageIcon("images/intro3.png").getImage());
        
        
        JLabel jLabel4 = new javax.swing.JLabel();
        JLabel jLabel5 = new javax.swing.JLabel();
        JPasswordField jPasswordField1 = new javax.swing.JPasswordField();
        JComboBox jComboBox1 = new javax.swing.JComboBox();
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Paweł", "Krzysiek", "Anita" }));
        jLabel4.setText("Użytkownik : ");
        jLabel5.setText("Hasło : ");
        jLabel4.setBounds(87, 130, 230, 30);
        jLabel5.setBounds(121, 155, 230, 30);
        jComboBox1.setBounds(201, 135, 230, 20);
        jPasswordField1.setBounds(201, 160, 230, 20);
        
        
        jComboBox1.removeAllItems();
        SQL database;
        Statement stat;
        database =new SQL();
        stat = database.con.createStatement();
        String query = "SELECT DISTINCT username FROM Login  ORDER BY username ASC";
        ResultSet rs = stat.executeQuery(query);
        while (rs.next()) {
            jComboBox1.addItem(rs.getString(1));
        }
        stat.close();
        database.con.close();

        p.add(jLabel4);
        p.add(jLabel5);
        p.add(jComboBox1);
        p.add(jPasswordField1);
        JLayer<JPanel> jlayer = new JLayer<JPanel>(p, layerUI);
        f.add(jlayer);
        jlayer.setBounds(0,0,500,300);
        f.setUndecorated(true);
        f.setSize(new Dimension(500,300));
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        jB_aktualizacja = new JLabel("           ");
        jB_program = new JLabel("         ");
        jB_exit = new JLabel("           ");
        jB_exit.setBounds(10, 270, 230, 30);
        jB_aktualizacja.setBounds(290, 270, 100, 30);
        jB_program.setBounds(400, 265, 250, 30);
        p.add(jB_aktualizacja);
        p.add(jB_program);
        p.add(jB_exit);
        
        jB_program.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
               
                    try {
                        A_B.rysuj();
                    } catch (Exception ex) {
                        Logger.getLogger(Intro.class.getName()).log(Level.SEVERE, null, ex);
                    }
              
                f.dispose();
            }
        });
         jB_aktualizacja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
               
                    try {
                       Rejestracja.rysuj();
                       // A_B.rysuj();
                    } catch (Exception ex) {
                        Logger.getLogger(Intro.class.getName()).log(Level.SEVERE, null, ex);
                    }
             // p.dispose();
                f.dispose();
            }
        });
        
        jB_exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                f.dispose();
            }
        });
        
        
        
    }
}

