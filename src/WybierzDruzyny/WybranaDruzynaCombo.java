package WybierzDruzyny;

import analiza_bukmacherska.SQL;
import analiza_bukmacherska.m1_okienko;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import tabela_ligowa.WybranaDruzyna;
import wybierz_lige.WybranaLiga;

public class WybranaDruzynaCombo extends JLayeredPane implements Observer{
    Observable observable;
    WybranaDruzyna wybranaDruzyna;
    public JComboBox team1;
    public JComboBox team2;
    m1_okienko jP_Druzyna;
    String wybranaLigas;
    SQL database;
    Statement stat;
    public void update(Observable obs, Object arg) {
        if (obs instanceof WybranaLiga) {
            try {
                WybranaLiga wybranaLiga = (WybranaLiga)obs;
                wybranaLigas= wybranaLiga.getWybranaliga();
                team1.removeAllItems();
                team2.removeAllItems();
                database =new SQL();
                stat = database.con.createStatement();
                String query = "SELECT DISTINCT HOMETEAM FROM MECZE_STATYSTYKI  WHERE DIV ='" + wybranaLigas +  "' ORDER BY HOMETEAM ASC";
                ResultSet rs = stat.executeQuery(query);
                while (rs.next()) {
                    team1.addItem(rs.getString(1));
                    team2.addItem(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(WybranaDruzynaCombo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(WybranaDruzynaCombo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public  WybranaDruzynaCombo (Observable observable){
        this.observable = observable;
        observable.addObserver(this);
        team1 = new JComboBox();
        team2 = new JComboBox();
        team1.setBounds(5, 35, 210, 24);
        team2.setBounds(5, 65, 210, 24);
        jP_Druzyna= new  m1_okienko(220,100,0,0,"DRUŻYNA");
        jP_Druzyna.add(team1);
        jP_Druzyna.add(team2);
        add(jP_Druzyna);
        setOpaque(true);
        jP_Druzyna.setBackground(new java.awt.Color(209, 210, 211));
        jP_Druzyna.setOpaque(true);
        setBackground(new java.awt.Color(209, 210, 211));
        setBounds(795, 417, 220, 100);
    }
}
