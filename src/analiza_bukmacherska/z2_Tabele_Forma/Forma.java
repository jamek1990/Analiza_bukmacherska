package analiza_bukmacherska.z2_Tabele_Forma;
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



public class Forma extends JLabel implements Observer{
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
                Logger.getLogger(Forma.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Forma.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
    public Forma(Observable observable){
        this.observable = observable;
        observable.addObserver(this);
    }
    
    public void update_data() throws SQLException, ClassNotFoundException{
        SQL database =new SQL();
        Statement stat;
        stat = database.con.createStatement();
        String query = "SELECT   HOMETEAM, AWAYTEAM ,FTR,FTHG,FTAG, DATA FROM MECZE_STATYSTYKI  WHERE DIV ='" + liga + "' AND (HOMETEAM = '" + team + "' OR AWAYTEAM = '" + team +"') ORDER BY DATA DESC LIMIT 6";
        ResultSet rs = stat.executeQuery(query);
        int i=6;
        removeAll();
        while (rs.next()&& i>0) {
            i--;
            String team2;
            String wygrana=rs.getString(3);
            if(team.contentEquals(rs.getString(1))){
                team2 =rs.getString(2).substring(0, 3).toUpperCase(Locale.FRENCH);
            }else{
                team2 =rs.getString(1).substring(0, 3).toUpperCase(Locale.FRENCH);
                if(rs.getString(3).contentEquals("A")){
                    wygrana="H";
                }else if(rs.getString(3).contentEquals("H")){
                    wygrana="A";
                }
            }
            String wyn = rs.getString(4) + "-" + rs.getString(5);
            Spotkanie spot2 = new Spotkanie(wyn,team2,66+5*i,wygrana);
            add(spot2);
            spot2.setBounds(12+62*i, 60-5*i, 59, 66+5*i);
            add(spot2);
        }
    }
}
