package kursy;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import tabela_ligowa.WybranaDruzyna;

public class Kurs extends JLabel{
   
    private final Color COLORDRA= new java.awt.Color(169, 160, 161);
    private final Color COLORDRA2= new java.awt.Color(109, 100, 101);
    private final Color COLORWIN= new java.awt.Color(109, 210, 111);
    private final Color COLORLOS= new java.awt.Color(199, 20, 51);
    JLabel data;
    JLabel druzyna_1;
    JLabel druzyna_2;
    JLabel kurs_1;
    JLabel kurs_x;
    JLabel kurs_2;
    
    public Kurs(String dat, String dr1,String dr2,String ku1,String kux,String ku2){
        //USTAW DATE
        this.data=new JLabel(dat);
        add(this.data);
        this.data.setForeground(Color.WHITE);
        this.data.setFont(new Font("sansserif",Font.PLAIN, 13));
        this.data.setBounds(0, 5, 100, 20);
        this.data.setHorizontalAlignment( SwingConstants.CENTER );
        Vector data = new Vector();
        data.add(Double.parseDouble(ku1)+0.001);
        data.add(Double.parseDouble(kux)+0.002); 
        data.add(Double.parseDouble(ku2)+0.003);  
        Collections.sort(data);
        //USTAW dr1
        this.druzyna_1=new JLabel(dr1);
        add(this.druzyna_1);
        druzyna_1.setBackground(COLORDRA2);
        druzyna_1.setOpaque(true);
        this.druzyna_1.setForeground(Color.WHITE);
        this.druzyna_1.setFont(new Font("sansserif",Font.PLAIN, 20));
        this.druzyna_1.setBounds(100, 0, 180, 33);
        this.druzyna_1.setHorizontalAlignment( SwingConstants.CENTER );
        
        //USTAW dr2
        this.druzyna_2=new JLabel(dr2);
        add(this.druzyna_2);
        druzyna_2.setOpaque(true);
        druzyna_2.setBackground(COLORDRA2);
        this.druzyna_2.setForeground(Color.WHITE);
        this.druzyna_2.setFont(new Font("sansserif",Font.PLAIN, 20));
        this.druzyna_2.setBounds(280, 0, 180, 33);
        this.druzyna_2.setHorizontalAlignment( SwingConstants.CENTER );
        
        //USTAW ku1
        this.kurs_1=new JLabel(ku1);
        add(this.kurs_1);
        if(data.elementAt(0).equals( Double.parseDouble(ku1)+0.001)){
           kurs_1.setBackground(COLORWIN); 
        }else if(data.elementAt(1).equals( Double.parseDouble(ku1)+0.001)){
           kurs_1.setBackground(COLORDRA); 
        }else{
           kurs_1.setBackground(COLORLOS); 
        }
        kurs_1.setOpaque(true);
        this.kurs_1.setForeground(Color.WHITE);
        this.kurs_1.setFont(new Font("sansserif",Font.PLAIN, 15));
        this.kurs_1.setBounds(458, 0, 100, 33);
        this.kurs_1.setHorizontalAlignment( SwingConstants.CENTER );
        
        //USTAW kux
        this.kurs_x=new JLabel(kux);
        add(this.kurs_x);
        if(data.elementAt(0).equals( Double.parseDouble(kux)+0.002)){
           kurs_x.setBackground(COLORWIN); 
        }else if(data.elementAt(1).equals( Double.parseDouble(kux)+0.002)){
           kurs_x.setBackground(COLORDRA); 
        }else{
           kurs_x.setBackground(COLORLOS); 
        }
        this.kurs_x.setOpaque(true);
        this.kurs_x.setForeground(Color.WHITE);
        this.kurs_x.setFont(new Font("sansserif",Font.PLAIN, 15));
        this.kurs_x.setBounds(558, 0, 100, 33);
        this.kurs_x.setHorizontalAlignment( SwingConstants.CENTER );
        
        //USTAW ku2
        this.kurs_2=new JLabel(ku2);
        add(this.kurs_2);
        if(data.elementAt(0).equals( Double.parseDouble(ku2)+0.003)){
           kurs_2.setBackground(COLORWIN); 
        }else if(data.elementAt(1).equals( Double.parseDouble(ku2)+0.003)){
           kurs_2.setBackground(COLORDRA); 
        }else{
           kurs_2.setBackground(COLORLOS); 
        }
        kurs_2.setOpaque(true);
        this.kurs_2.setForeground(Color.WHITE);
        this.kurs_2.setFont(new Font("sansserif",Font.PLAIN, 15));
        this.kurs_2.setBounds(658, 0, 100, 33);
        this.kurs_2.setHorizontalAlignment( SwingConstants.CENTER );
        
        setBounds(0, 5, 758, 20);
        setBackground(COLORDRA);
        setOpaque(true);
    }
}
