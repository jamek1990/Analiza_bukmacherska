package analiza_bukmacherska;
import OstatnieWyniki.Ostatnie;
import analiza_bukmacherska.z2_Tabele_Forma.Forma;
import tabela_ligowa.*;
import wybierz_lige.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class z2_Tabele extends JLayeredPane{
    JLayeredPane panel2;
    JLabel baza2;
    JLabel baza3;
    JLabel forma;
    JLabel forma2;
    
    WybierzLige mwybierzLige;
    TabelaLigowa mtabelaLigowa;
    m1_okienko  jP_WskaznikiFormy;
    m1_okienko  jP_OstatnieWyniki;
    JLabel jLabel3;
    String wybrana_liga;
    Forma form;
    Ostatnie ostatnie;
    SQL database;
   
    public z2_Tabele() throws SQLException, ClassNotFoundException{
        jLabel3 =new  JLabel();
        //jP_StatystykiMeczow = new  m1_okienko(250,201,410,375,"WSKAŹNIKI FORMY");
        jP_OstatnieWyniki= new  m1_okienko(364,201,410,388,"OSTATNIE WYNIKI");
        forma2 = new JLabel();
        //forma2.setIcon(new javax.swing.ImageIcon("images/forma2.png"));
       // jP_StatystykiMeczow.add(forma2);
       // forma2.setBounds(0, 20, 261, 125);
        
        setBounds(0, 0, 1024, 530);
        
        //mwybierzLige.setBounds(780,3,230,371);
        
        WybranaLiga  wybranaLiga = new WybranaLiga("E0");
        WybranaDruzyna wybranaDruzyna = new WybranaDruzyna();
        
        form = new Forma(wybranaDruzyna);
        ostatnie = new Ostatnie(wybranaDruzyna);
        mtabelaLigowa = new TabelaLigowa(wybranaLiga,wybranaDruzyna);
        mwybierzLige = new WybierzLige(wybranaLiga);
       
        
       // mtabelaLigowa = new TabelaLigowa(wybranaLiga);//,wybranaDruzyna);
        add(mwybierzLige);
        add(mtabelaLigowa);
       // add(jP_StatystykiMeczow);
        //jP_OstatnieWyniki.dodajPodzial();
        jP_OstatnieWyniki.add(ostatnie);
        ostatnie.setBounds(0, 0, 541, 130);
        jP_OstatnieWyniki.setBackground(new java.awt.Color(209, 210, 211));
        jP_OstatnieWyniki.setOpaque(true);
        add(jP_OstatnieWyniki);
        

        
        jP_WskaznikiFormy = new  m1_okienko(400,181,3,388,"WSKAŹNIKI FORMY");
        jP_WskaznikiFormy.add(form);
        //jP_WskaznikiFormy.dodajPodzial();
        form.setBounds(0, 0, 541, 130);
        jP_WskaznikiFormy.setBackground(new java.awt.Color(209, 210, 211));
        jP_WskaznikiFormy.setOpaque(true);
        add(jP_WskaznikiFormy);
        setBackground(new java.awt.Color(209, 210, 211));
        setOpaque(true);
    }
}
