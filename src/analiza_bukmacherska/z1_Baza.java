package analiza_bukmacherska;
import analiza_bukmacherska.z2_Tabele_Forma.Forma;
import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import kursy.Kursy;
import tabela_ligowa.TabelaLigowa;
import tabela_ligowa.WybranaDruzyna;
import wybierz_lige.WybierzLige;
import wybierz_lige.WybranaLiga;

public class z1_Baza extends JLayeredPane{
    JLayeredPane panel2;
    JLabel jL_OdswiezBaze;
    JLabel baza3;
    m1_okienko jP_OknoAktualizujBaze;
    WybierzLige mwybierzLige;
    Kursy kursy;
    public z1_Baza() throws Exception{
        WybranaLiga  wybranaLiga = new WybranaLiga("I1");
        mwybierzLige = new WybierzLige(wybranaLiga);
        kursy=new Kursy(wybranaLiga);
        setLocation(0,0);
        setBounds(0, 0, 1024, 520);
        add(mwybierzLige);
        add(kursy);
    }
}
