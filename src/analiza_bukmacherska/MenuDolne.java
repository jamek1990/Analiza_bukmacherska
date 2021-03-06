package analiza_bukmacherska;
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
    JLabel BM_teamvsteam;
    JLabel BM_prognozy;
    JLabel BM_symulacja;
    JLabel Tlo_Menu;
    z1_Baza z1_baza;
    z2_Tabele z2_tabele;
    z3_TeamVsTeam z3_teamvsteam;
    z4_Prognozy z4_prognozy;
    z5_Symulacja z5_symulacja;
    Integer zaswiec =1;
    
    private void BM_bazaMouseEntered(java.awt.event.MouseEvent evt) {
        if(zaswiec != 1){
            BM_baza.setIcon(new javax.swing.ImageIcon("images/button_down_baza.jpg")); 
        }
    }
    
    private void BM_bazaMouseExited(java.awt.event.MouseEvent evt) {
        if(zaswiec != 1){
            BM_baza.setIcon(new javax.swing.ImageIcon("images/button_up_baza.jpg")); 
        }
    }
    
    private void BM_bazaMousePressed(java.awt.event.MouseEvent evt) throws Exception { 
        //CSV cs = new CSV();
        //cs.read();
        if(zaswiec != 1){
            BM_baza.setIcon(new javax.swing.ImageIcon("images/button_press_baza.jpg")); 
            BM_tabele.setIcon(new javax.swing.ImageIcon("images/button_up_tabele.jpg"));
            BM_teamvsteam.setIcon(new javax.swing.ImageIcon("images/button_up_team.jpg")); 
            BM_prognozy.setIcon(new javax.swing.ImageIcon("images/button_up_prognoza.jpg"));
            BM_symulacja.setIcon(new javax.swing.ImageIcon("images/button_up_symulacja.jpg"));
            z1_baza.setVisible(true);
            z2_tabele.setVisible(false);
            z3_teamvsteam.setVisible(false);
            z4_prognozy.setVisible(false);
            z5_symulacja.setVisible(false);
            zaswiec=1;
        }
    }
    
    private void BM_tabeleMouseEntered(java.awt.event.MouseEvent evt) {          
        if(zaswiec != 2){
            BM_tabele.setIcon(new javax.swing.ImageIcon("images/button_down_tabele.jpg")); 
        }
    }
    private void BM_tabeleMouseExited(java.awt.event.MouseEvent evt) {
        if(zaswiec != 2){
            BM_tabele.setIcon(new javax.swing.ImageIcon("images/button_up_tabele.jpg")); 
        }
    }
    private void BM_tabeleMousePressed(java.awt.event.MouseEvent evt) throws Exception { 
        if(zaswiec != 2){
            BM_tabele.setIcon(new javax.swing.ImageIcon("images/button_press_tabele.jpg")); 
            BM_baza.setIcon(new javax.swing.ImageIcon("images/button_up_baza.jpg")); 
            BM_teamvsteam.setIcon(new javax.swing.ImageIcon("images/button_up_team.jpg")); 
            BM_prognozy.setIcon(new javax.swing.ImageIcon("images/button_up_prognoza.jpg"));
            BM_symulacja.setIcon(new javax.swing.ImageIcon("images/button_up_symulacja.jpg"));
            z1_baza.setVisible(false);
            z2_tabele.setVisible(true);
            z3_teamvsteam.setVisible(false);
            z4_prognozy.setVisible(false);
            z5_symulacja.setVisible(false);
            zaswiec =2;
        }
    }
    private void BM_teamvsteamMouseEntered(java.awt.event.MouseEvent evt) {          
        if(zaswiec != 3){
            BM_teamvsteam.setIcon(new javax.swing.ImageIcon("images/button_down_team.jpg")); 
        }
    }
    private void BM_teamvsteamMouseExited(java.awt.event.MouseEvent evt) {
        if(zaswiec != 3){
            BM_teamvsteam.setIcon(new javax.swing.ImageIcon("images/button_up_team.jpg")); 
        }
    }
    private void BM_teamvsteamMousePressed(java.awt.event.MouseEvent evt) throws Exception { 
        if(zaswiec != 3){
            BM_teamvsteam.setIcon(new javax.swing.ImageIcon("images/button_press_team.jpg")); 
            BM_tabele.setIcon(new javax.swing.ImageIcon("images/button_up_tabele.jpg")); 
            BM_baza.setIcon(new javax.swing.ImageIcon("images/button_up_baza.jpg")); 
            BM_prognozy.setIcon(new javax.swing.ImageIcon("images/button_up_prognoza.jpg"));
            BM_symulacja.setIcon(new javax.swing.ImageIcon("images/button_up_symulacja.jpg"));
            z1_baza.setVisible(false);
            z2_tabele.setVisible(false);
            z3_teamvsteam.setVisible(true);
            z4_prognozy.setVisible(false);
            z5_symulacja.setVisible(false);
            zaswiec =3;
        }
    }
    private void BM_prognozyMouseEntered(java.awt.event.MouseEvent evt) {          
        if(zaswiec != 4){
            BM_prognozy.setIcon(new javax.swing.ImageIcon("images/button_down_prognoza.jpg")); 
        }
    }
    private void BM_prognozyMouseExited(java.awt.event.MouseEvent evt) {
        if(zaswiec != 4){
            BM_prognozy.setIcon(new javax.swing.ImageIcon("images/button_up_prognoza.jpg")); 
        }
    }
    private void BM_prognozyMousePressed(java.awt.event.MouseEvent evt) throws Exception { 
        if(zaswiec != 4){
            BM_prognozy.setIcon(new javax.swing.ImageIcon("images/button_press_prognoza.jpg")); 
            BM_teamvsteam.setIcon(new javax.swing.ImageIcon("images/button_up_team.jpg")); 
            BM_tabele.setIcon(new javax.swing.ImageIcon("images/button_up_tabele.jpg")); 
            BM_baza.setIcon(new javax.swing.ImageIcon("images/button_up_baza.jpg")); 
            BM_symulacja.setIcon(new javax.swing.ImageIcon("images/button_up_symulacja.jpg"));
            z1_baza.setVisible(false);
            z2_tabele.setVisible(false);
            z3_teamvsteam.setVisible(false);
            z4_prognozy.setVisible(true);
            z5_symulacja.setVisible(false);
            zaswiec =4;
        }
    }
    private void BM_symulacjaMouseEntered(java.awt.event.MouseEvent evt) {          
        if(zaswiec != 5){
            BM_symulacja.setIcon(new javax.swing.ImageIcon("images/button_down_symulacja.jpg")); 
        }
    }
    private void BM_symulacjaMouseExited(java.awt.event.MouseEvent evt) {
        if(zaswiec != 5){
            BM_symulacja.setIcon(new javax.swing.ImageIcon("images/button_up_symulacja.jpg")); 
        }
    }
    private void BM_symulacjaMousePressed(java.awt.event.MouseEvent evt) throws Exception { 
        if(zaswiec != 5){
            BM_prognozy.setIcon(new javax.swing.ImageIcon("images/button_up_prognoza.jpg")); 
            BM_teamvsteam.setIcon(new javax.swing.ImageIcon("images/button_up_team.jpg")); 
            BM_tabele.setIcon(new javax.swing.ImageIcon("images/button_up_tabele.jpg")); 
            BM_baza.setIcon(new javax.swing.ImageIcon("images/button_up_baza.jpg")); 
            BM_symulacja.setIcon(new javax.swing.ImageIcon("images/button_press_symulacja.jpg"));
            z1_baza.setVisible(false);
            z2_tabele.setVisible(false);
            z3_teamvsteam.setVisible(false);
            z4_prognozy.setVisible(false);
            z5_symulacja.setVisible(true);
            zaswiec =5;
        }
    }
    public MenuDolne(z1_Baza z1,z2_Tabele z2,z3_TeamVsTeam z3,z4_Prognozy z4,z5_Symulacja z5){
        this.z1_baza=z1;
        this.z2_tabele=z2;
        this.z3_teamvsteam=z3;
        this.z4_prognozy=z4;
        this.z5_symulacja=z5;
        setBackground(new java.awt.Color(153, 153, 153));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panel= new JLayeredPane();
        panel.setLayout(null);
        Tlo_Menu= new JLabel();
        Tlo_Menu.setIcon(new javax.swing.ImageIcon("images/menu_dolne.jpg"));
        BM_baza = new JLabel();
        BM_baza.setIcon(new javax.swing.ImageIcon("images/button_press_baza.jpg"));
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
        BM_teamvsteam = new JLabel();
        BM_teamvsteam.setIcon(new javax.swing.ImageIcon("images/button_up_team.jpg"));
        BM_teamvsteam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BM_teamvsteamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BM_teamvsteamMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                try {
                    BM_teamvsteamMousePressed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(MenuDolne.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        BM_prognozy = new JLabel();
        BM_prognozy.setIcon(new javax.swing.ImageIcon("images/button_up_prognoza.jpg"));
        BM_prognozy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BM_prognozyMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BM_prognozyMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                try {
                    BM_prognozyMousePressed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(MenuDolne.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        BM_symulacja = new JLabel();
        BM_symulacja.setIcon(new javax.swing.ImageIcon("images/button_up_symulacja.jpg"));
        BM_symulacja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BM_symulacjaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BM_symulacjaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                try {
                    BM_symulacjaMousePressed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(MenuDolne.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        Tlo_Menu.setBounds(0, 0, 1024, 49);
        BM_baza.setBounds(331, 0, 65, 49);
        BM_tabele.setBounds(331+65, 0, 65, 49);
        BM_teamvsteam.setBounds(331+2*65, 0, 65, 49);
        BM_prognozy.setBounds(331+3*65, 0, 65, 49);
        BM_symulacja.setBounds(331+4*65, 0, 65, 49);
        setSize(1024, 49);
        setLocation(0,523);
        add(BM_baza,1);
        add(BM_tabele,1);
        add(BM_teamvsteam,1);
        add(BM_prognozy,1);
        add(BM_symulacja,1);
        add(Tlo_Menu,8);
    }
}
