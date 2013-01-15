package kursy;
import analiza_bukmacherska.SQL;
import analiza_bukmacherska.m1_okienko;
import analiza_bukmacherska.z2_Tabele_Forma.Spotkanie;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import tabela_ligowa.TabelaLigowa;
import tabela_ligowa.WybranaDruzyna;
import wybierz_lige.WybranaLiga;

public class Kursy extends JLayeredPane implements Observer{
   
    private final Color COLORDRA= new java.awt.Color(169, 160, 161);
    private final Color COLORWIN= new java.awt.Color(109, 210, 111);
    private final Color COLORLOS= new java.awt.Color(199, 20, 51);
    
    JLabel data;
    JLabel druzyna_1;
    JLabel druzyna_2;
    JLabel kurs_1;
    JLabel kurs_x;
    JLabel kurs_2;
    String wybranaLigas;
    Observable observable;
    m1_okienko jP_Kursy;
    SQL database;
    
    public void dodaj_kursy() throws SQLException, ClassNotFoundException{
        database =new SQL();
        Statement stat;
        String query="";
        Integer columnCount=10;
        Vector data = new Vector(columnCount);
        Vector row = new Vector(columnCount);
        Vector columnNames = new Vector(columnCount);
        stat = database.con.createStatement(); 
        query= "SELECT  Data,HomeTeam,AwayTeam,k1,kx,k2 FROM Kursy WHERE Div ='" + wybranaLigas + "'";
        ResultSet rs = stat.executeQuery(query);
        int i=0;
        JLabel tlo = new JLabel();
        add(tlo);
        tlo.setOpaque(true);
        tlo.setBackground(new java.awt.Color(209, 210, 211));
        while (rs.next()) {
            rs.getString(1);
            i++;
            Kurs kurs = new Kurs("2012-12-27",rs.getString(2),rs.getString(3),Double.toString(rs.getDouble(4)),Double.toString(rs.getDouble(5)),Double.toString(rs.getDouble(6)));
            kurs.setBounds(8,i*33+3, 758, 30);
            add(kurs);
        }
        i++;
        tlo.setBounds(8, i*33+3, 758, 600);
        
    }
    
    public void update(Observable obs, Object arg) {
        if (obs instanceof WybranaLiga) {
            WybranaLiga wybranaLiga = (WybranaLiga)obs;
            wybranaLigas= wybranaLiga.getWybranaliga();
            System.out.println(wybranaLigas);
            try {
                dodaj_kursy();
            } catch (SQLException ex) {
                Logger.getLogger(Kursy.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Kursy.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    public Kursy(Observable observable){
        this.observable = observable;
        observable.addObserver(this);
        //USTAW DATE
        jP_Kursy = new m1_okienko(768,800,5,5,"KURSY");
        jP_Kursy.setBackground(new java.awt.Color(209, 210, 211));
        jP_Kursy.setOpaque(true);
        add(jP_Kursy);
        setBounds(0, 0, 770, 700);
        

    }
}
