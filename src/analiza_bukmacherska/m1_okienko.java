import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
public class m1_okienko extends JLayeredPane{
    Integer wysokosc;
    Integer szerokosc;
    Integer polozenie_x;
    Integer polozenie_y;
    String tytyl;
    JLabel jL_tytul;
    JLabel jL_tloGorne;
    JLabel jL_Odswiez;
    JLabel kolo1;
    JLabel kolo2;
    JLabel kolo3;
    public m1_okienko(Integer szerokosc,Integer wysokosc,Integer polozenie_x,Integer polozenie_y,String tytul){
        this.szerokosc=szerokosc;
        this.wysokosc=wysokosc;
        this.polozenie_x=polozenie_x;
        this.polozenie_y=polozenie_y;
        this.tytyl=tytul;
        
        //ustaw tytul
        jL_tytul = new JLabel();
        jL_tytul.setFont(new java.awt.Font("Arial Black", 1, 10));
        jL_tytul.setForeground(new java.awt.Color(255, 255, 255));
        jL_tytul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_tytul.setText(tytul);
        
        //ustaw tlo gorne
        jL_tloGorne = new JLabel();
        jL_tloGorne.setBackground(new java.awt.Color(102, 102, 102));
        jL_tloGorne.setFont(new java.awt.Font("Tahoma", 1, 12));
        jL_tloGorne.setForeground(new java.awt.Color(255, 255, 255));
        jL_tloGorne.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_tloGorne.setIcon(new javax.swing.ImageIcon("images/TABELA_NAZWA2.jpg"));
        
        //ustaw okienko
        setBackground(new java.awt.Color(0, 153, 153));
        setLayout(null);
        add(jL_tytul,2);
        jL_tytul.setBounds(0, 0, szerokosc, 26);
        add(jL_tloGorne,2);
        jL_tloGorne.setBounds(0, 0, szerokosc, 26);
        setBounds(polozenie_x, polozenie_y, szerokosc, wysokosc);
    }
    public void dodajOdswiez(){
        jL_Odswiez = new JLabel();
        jL_Odswiez.setIcon(new javax.swing.ImageIcon("images/odswiezBaze2.png"));
        add(jL_Odswiez,1);
        jL_Odswiez.setBounds(szerokosc-30, 2, 26, 26);
    }
    public void dodajPodzial(){
         kolo3 = new JLabel();
         kolo3.setIcon(new javax.swing.ImageIcon("images/btok2.jpg"));
         add(kolo3,1);
         kolo3.setBounds(szerokosc-30, 5, 16, 16);
         
         kolo2 = new JLabel();
         kolo2.setIcon(new javax.swing.ImageIcon("images/btok2.jpg"));
         add(kolo2,1);
         kolo2.setBounds(szerokosc-30-20*1, 5, 16, 16);
         
         kolo1 = new JLabel();
         kolo1.setIcon(new javax.swing.ImageIcon("images/btok.jpg"));
         add(kolo1,1);
         kolo1.setBounds(szerokosc-30-20*2, 5, 16, 16);
    }
}
