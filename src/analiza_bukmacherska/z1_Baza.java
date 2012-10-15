import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.BorderLayout;
public class z1_Baza extends JLayeredPane{
    JLayeredPane panel2;
    JLabel jL_OdswiezBaze;
    JLabel baza3;
    m1_okienko  jP_OknoAktualizujBaze;
    public z1_Baza(){
        jP_OknoAktualizujBaze = new m1_okienko(200,200,805,5,"AKTUALIZACJA");
        jL_OdswiezBaze = new JLabel();
        
        jL_OdswiezBaze.setForeground(Color.red);
        //jL_OdswiezBaze.setIcon(new javax.swing.ImageIcon("images/odswiezBaze.png"));
        jP_OknoAktualizujBaze.add(jL_OdswiezBaze,1);
        jL_OdswiezBaze.setBounds(70, 80, 80, 80);
        jP_OknoAktualizujBaze.dodajOdswiez();
        /*jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {jButton4MouseEntered(evt);}
            public void mouseExited(java.awt.event.MouseEvent evt)  {jButton4MouseExited(evt);}
            public void mousePressed(java.awt.event.MouseEvent evt) {//jButton4MousePressed(evt);}
            }});*/
        setLocation(0,0);
        setBounds(0, 0, 1024, 300);
        add(jP_OknoAktualizujBaze);
    }
}
