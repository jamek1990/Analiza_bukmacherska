package analiza_bukmacherska.z2_Tabele_Forma;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
public class Spotkanie extends JLabel{
    
    JLabel wynik;
    JLabel druzyna;
    private final Color COLORDRA= new java.awt.Color(169, 160, 161);
    private final Color COLORWIN= new java.awt.Color(109, 210, 111);
    private final Color COLORLOS= new java.awt.Color(199, 20, 51);
    
    public Spotkanie(String wynik,String druzyna,Integer wysokosc,String wygrana){
        
        //USTAW DRUZYNE
        this.druzyna=new JLabel(druzyna);
        add(this.druzyna);
        
        this.druzyna.setForeground(Color.WHITE);
        this.druzyna.setFont(new Font("sansserif",Font.PLAIN, 20));
        this.druzyna.setBounds(0, 5, 60, 20);
        this.druzyna.setHorizontalAlignment( SwingConstants.CENTER );
        
        //USTAW WYNIK
        this.wynik = new JLabel(wynik);
        add(this.wynik);
        
        this.wynik.setForeground(Color.WHITE);
        this.wynik.setFont(new Font("sansserif", Font.BOLD, 32));
        this.wynik.setBounds(5, wysokosc-33, 50, 30);
        
        //USTAW SPOTKANIE
        setBounds(12, 77, 59, 66);
        if(wygrana.contentEquals("D")){
            setBackground(COLORDRA);
        }else if(wygrana.contentEquals("H")){
            setBackground(COLORWIN);
        }else{
            setBackground(COLORLOS);
        }
        setOpaque(true);
    }
}
