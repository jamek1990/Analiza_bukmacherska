package analiza_bukmacherska.z2_Tabele_Forma;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import javax.swing.JLabel;

public class Forma extends JLabel{
    
    public Forma(){
        /*for(int i=0;i<6;i++){
            Spotkanie spot2 = new Spotkanie("1-2","BAY",66+5*i);
            add(spot2);
            spot2.setBounds(12+62*i, 77-5*i, 59, 66+5*i);
            add(spot2);
        }*/
    }
    
    public void update_data(ResultSet rs,String druzyna) throws SQLException{
        int i=6;
        removeAll();
        while (rs.next()&& i>0) {
            i--;
            String team;
            String wygrana=rs.getString(3);
            if(druzyna.contentEquals(rs.getString(1))){
                team =rs.getString(2).substring(0, 3).toUpperCase(Locale.FRENCH);
            }else{
                team =rs.getString(1).substring(0, 3).toUpperCase(Locale.FRENCH);
                if(rs.getString(3).contentEquals("A")){
                    wygrana="H";
                }else if(rs.getString(3).contentEquals("H")){
                    wygrana="A";
                }
            }
            String wyn = rs.getString(4) + "-" + rs.getString(5);
            Spotkanie spot2 = new Spotkanie(wyn,team,66+5*i,wygrana);
            add(spot2);
            spot2.setBounds(12+62*i, 77-5*i, 59, 66+5*i);
            add(spot2);
            
        }
        
    }
    
}
