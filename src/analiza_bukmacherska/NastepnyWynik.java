package analiza_bukmacherska;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class NastepnyWynik extends JLabel{
    
    JLabel wynik;
    JLabel druzyna;
    JLabel druzyna1;
    JLabel data;
    private final Color COLORDRA= new java.awt.Color(169, 160, 161);
    private final Color COLORWIN= new java.awt.Color(109, 210, 111);
    private final Color COLORLOS= new java.awt.Color(199, 20, 51);
    
    public NastepnyWynik(String wynik,String druzyna1,String druzyna,String dat, int col){
        
        //USTAW DRUZYNE
        this.data=new JLabel(dat);
        add(this.data);
        
        this.data.setForeground(Color.WHITE);
        this.data.setFont(new Font("sansserif",Font.PLAIN, 15));
        this.data.setBounds(0, 1, 50, 30);
        this.data.setHorizontalAlignment( SwingConstants.CENTER );
        
        
        this.druzyna1=new JLabel(druzyna1);
        add(this.druzyna1);
        
        this.druzyna1.setForeground(Color.WHITE);
        this.druzyna1.setFont(new Font("sansserif",Font.PLAIN, 17));
        this.druzyna1.setBounds(50, 1, 100, 30);
        this.druzyna1.setHorizontalAlignment( SwingConstants.CENTER );
        
        this.druzyna=new JLabel(druzyna);
        add(this.druzyna);
        
        this.druzyna.setForeground(Color.WHITE);
        this.druzyna.setFont(new Font("sansserif",Font.PLAIN, 17));
        this.druzyna.setBounds(150, 1, 100, 30);
        this.druzyna.setHorizontalAlignment( SwingConstants.CENTER );
        
        //USTAW WYNIK
        this.wynik = new JLabel(wynik);
        add(this.wynik);
        
        this.wynik.setForeground(Color.WHITE);
        this.wynik.setFont(new Font("sansserif", Font.BOLD, 32));
        this.wynik.setBounds(270, 1, 50, 30);
        
        //USTAW SPOTKANIE
        setBounds(12, 77, 400, 36);
        if(col == 0){
            setBackground(COLORDRA);
        }else if(col == 1){
                    setBackground(COLORWIN);
        }else{
            setBackground(COLORLOS);
        }
        setOpaque(true);
    }
}
