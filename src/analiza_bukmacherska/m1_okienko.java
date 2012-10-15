import javax.swing.JLabel;
import javax.swing.JPanel;
public class m1_okienko extends JPanel{
    Integer wysokosc;
    Integer szerokosc;
    Integer polozenie_x;
    Integer polozenie_y;
    String tytyl;
    JLabel  jL_tytul;
    JLabel  jL_tloGorne;
    public m1_okienko(Integer szerokosc,Integer wysokosc,Integer polozenie_x,Integer polozenie_y,String tytul){
        this.szerokosc=szerokosc;
        this.wysokosc=wysokosc;
        this.polozenie_x=polozenie_x;
        this.polozenie_y=polozenie_y;
        this.tytyl=tytul;
        
        //ustaw tytul
        jL_tytul = new JLabel();
        jL_tytul.setFont(new java.awt.Font("Arial Black", 1, 10));
        jL_tytul.setForeground(new java.awt.Color(255, 255, 255));
        jL_tytul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_tytul.setText(tytul);
        
        //ustaw tlo gorne
        jL_tloGorne = new JLabel();
        jL_tloGorne.setBackground(new java.awt.Color(102, 102, 102));
        jL_tloGorne.setFont(new java.awt.Font("Tahoma", 1, 12));
        jL_tloGorne.setForeground(new java.awt.Color(255, 255, 255));
        jL_tloGorne.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_tloGorne.setIcon(new javax.swing.ImageIcon("images/TABELA_NAZWA2.jpg"));
        //dodaj do okienka
        setBackground(new java.awt.Color(0, 153, 153));
        setLayout(null);
        add(jL_tytul);
        jL_tytul.setBounds(0, 0, szerokosc, 26);
        add(jL_tloGorne);
        jL_tloGorne.setBounds(0, 0, szerokosc, 26);
        
        
        
        //ustaw okienko
        
        setBounds(polozenie_x, polozenie_y, szerokosc, wysokosc);
        
        
        
        
    }
}
