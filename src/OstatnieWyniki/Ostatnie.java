/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OstatnieWyniki;

import analiza_bukmacherska.z2_Tabele_Forma.Spotkanie;
import analiza_bukmacherska.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import tabela_ligowa.TabelaLigowa;
import tabela_ligowa.WybranaDruzyna;
import wybierz_lige.WybranaLiga;



public class Ostatnie extends JLabel implements Observer{
    Observable observable;
    String liga;
    String team;
    public void update(Observable obs, Object arg) {
        if (obs instanceof WybranaDruzyna) {
            WybranaDruzyna wybranaDruzyna = (WybranaDruzyna)obs;
            liga= wybranaDruzyna.getWybranaliga();
            team= wybranaDruzyna.getWybranadruzyna();
            try {
                update_data();
            } catch (SQLException ex) {
                Logger.getLogger(analiza_bukmacherska.z2_Tabele_Forma.Forma.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(analiza_bukmacherska.z2_Tabele_Forma.Forma.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
    public Ostatnie(Observable observable){
        this.observable = observable;
        observable.addObserver(this);
    }
    
    public void update_data() throws SQLException, ClassNotFoundException{
        SQL database =new SQL();
        Statement stat;
        stat = database.con.createStatement();
        String query = "SELECT   HOMETEAM, AWAYTEAM ,FTR,FTHG,FTAG, DATA FROM MECZE_STATYSTYKI  WHERE DIV ='" + liga + "' AND (HOMETEAM = '" + team + "' OR AWAYTEAM = '" + team +"') ORDER BY DATA DESC LIMIT 3";
        ResultSet rs = stat.executeQuery(query);
        int i=3;
        removeAll();
        while (rs.next()&& i>0) {
            i--;
            String team2;
            String team1;
            String wygrana=rs.getString(3);
            if(team.contentEquals(rs.getString(1))){
                team1 =rs.getString(1).toUpperCase(Locale.FRENCH);
                team2 =rs.getString(2).toUpperCase(Locale.FRENCH);
            }else{
                team1 =rs.getString(1).toUpperCase(Locale.FRENCH);
                team2 =rs.getString(2).toUpperCase(Locale.FRENCH);
                if(rs.getString(3).contentEquals("A")){
                    wygrana="H";
                }else if(rs.getString(3).contentEquals("H")){
                    wygrana="A";
                }
            }
            team1 = team1.substring(0, Math.min(team1.length(),8));
            team2 = team2.substring(0, Math.min(team2.length(),8));
            String wyn = rs.getString(4) + "-" + rs.getString(5);
            String dat = rs.getString(6);
            String d2="";
            if(dat.substring(4, 6).length()==1){
                d2+="0";
            }
            d2+=dat.substring(4, 6)+".";
            if(dat.substring(6, 8).length()==1){
                d2+="0";
            }
            d2+=dat.substring(6, 8);
            OstatniWynik ostwyn = new OstatniWynik(wyn,team1,team2,d2,wygrana);
            add(ostwyn);
            ostwyn.setBounds(12, 30+32*(2-i), 340, 30);
            add(ostwyn);
        }
    }
}
