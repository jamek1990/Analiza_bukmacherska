import analiza_bukmacherska.CSV;
import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.BorderLayout;
public class MenuDolne extends JLayeredPane{
    JLayeredPane panel;
    JLabel BM_baza;
    JLabel BM_tabele;
    JLabel Tlo_Menu;
    z1_Baza z1_baza;
    z2_Tabele z2_tabele;
    private void BM_bazaMouseEntered(java.awt.event.MouseEvent evt) {                                      
        BM_baza.setIcon(new javax.swing.ImageIcon("images/button_down_baza.jpg")); 
    }
    private void BM_bazaMouseExited(java.awt.event.MouseEvent evt) {                                     
        BM_baza.setIcon(new javax.swing.ImageIcon("images/button_up_baza.jpg")); 
    }
    private void BM_bazaMousePressed(java.awt.event.MouseEvent evt) throws Exception { 
        //CSV cs = new CSV();
        //cs.read();
        BM_baza.setIcon(new javax.swing.ImageIcon("images/button_press_baza.jpg")); 
        z1_baza.setVisible(true);
        z2_tabele.setVisible(false);
    }
    private void BM_tabeleMouseEntered(java.awt.event.MouseEvent evt) {                                      
        BM_tabele.setIcon(new javax.swing.ImageIcon("images/button_down_tabele.jpg")); 
    }
    private void BM_tabeleMouseExited(java.awt.event.MouseEvent evt) {                                     
        BM_tabele.setIcon(new javax.swing.ImageIcon("images/button_up_tabele.jpg")); 
    }
    private void BM_tabeleMousePressed(java.awt.event.MouseEvent evt) throws Exception { 
        BM_tabele.setIcon(new javax.swing.ImageIcon("images/button_press_tabele.jpg")); 
        z1_baza.setVisible(false);
        z2_tabele.setVisible(true);
    }
    public MenuDolne(z1_Baza z1,z2_Tabele z2){
        this.z1_baza=z1;
        this.z2_tabele=z2;
        setBackground(new java.awt.Color(153, 153, 153));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panel= new JLayeredPane();
        panel.setLayout(null);
        Tlo_Menu= new JLabel();
        Tlo_Menu.setIcon(new javax.swing.ImageIcon("images/menu_dolne.jpg"));
        BM_baza = new JLabel();
        BM_baza.setIcon(new javax.swing.ImageIcon("images/button_up_baza.jpg"));
        BM_baza.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BM_bazaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BM_bazaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                try {
                    BM_bazaMousePressed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(MenuDolne.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        BM_tabele = new JLabel();
        BM_tabele.setIcon(new javax.swing.ImageIcon("images/button_up_tabele.jpg"));
        BM_tabele.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BM_tabeleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BM_tabeleMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                try {
                    BM_tabeleMousePressed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(MenuDolne.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        Tlo_Menu.setBounds(0, 0, 1024, 49);
        BM_baza.setBounds(351, 0, 65, 49);
        BM_tabele.setBounds(351+65, 0, 65, 49);
        setSize(1024, 49);
        setLocation(0,523);
        add(BM_baza,1);
        add(BM_tabele,1);
        add(Tlo_Menu,2);
    }
}
