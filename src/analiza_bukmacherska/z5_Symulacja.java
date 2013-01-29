package analiza_bukmacherska;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author User
 */
public class z5_Symulacja extends JLayeredPane{
    m1_okienko jPPreferencje;
    m1_okienko jPWykres;
    m1_okienko jPTabela;
    
    //textfieldy, labele, buttony
    JTextField jTFData1;
    JTextField jTFData2;
    JTextField jTFSaldo;
    
    JButton jBSymulacja;
    
    JLabel jLData1;
    JLabel jLData2;
    JLabel jLSaldo;
    JLabel jLStan;
    //z5_SymulacjaPanel z5;    
    public z5_Symulacja(){
        //z5 = new z5_SymulacjaPanel();
        //add(z5);
        //z5.setBounds(0, 0, 1024, 530);
        setLocation(0,0);
        setBounds(0, 0, 1024, 530);
        
        //okienko z preferencjami
        jPPreferencje = new m1_okienko(180, 513, 5, 5, "PREFERENCJE");
        jPPreferencje.setBackground(new java.awt.Color(209, 210, 211));
        jPPreferencje.setOpaque(true);
        
        jPWykres = new m1_okienko(825, 228, 190, 290, "STAN KONTA");
        jPWykres.setBackground(new java.awt.Color(209, 210, 211));
        jPWykres.setOpaque(true);
        
        jPTabela = new m1_okienko(825, 285, 190, 5, "TABELA");
        jPTabela.setBackground(new java.awt.Color(209, 210, 211));
        jPTabela.setOpaque(true);
        
        //elementy preferencji
        jLData1 = new JLabel("DATA ROZPOCZĘCIA");
        jLData1.setHorizontalAlignment(SwingConstants.CENTER);
        jLData1.setBounds(10, 30, 160, 20);
        
        jTFData1 = new JTextField();
        jTFData1.setBounds(10, 55, 160, 20);
                
        jLData2 = new JLabel("DATA ZAKOŃCZENIA");
        jLData1.setHorizontalAlignment(SwingConstants.CENTER);
        jLData2.setBounds(10, 80, 160, 20);
        
        jTFData2 = new JTextField();
        jTFData2.setBounds(10, 105, 160, 20);
        
        jLSaldo = new JLabel("SALDO POCZĄTKOWE");
        jLSaldo.setHorizontalAlignment(SwingConstants.CENTER);
        jLSaldo.setBounds(10, 130, 160, 20);
        
        jTFSaldo = new JTextField();
        jTFSaldo.setBounds(10, 155, 160, 20);
        
        jLStan = new JLabel("0.00");
        jLStan.setBounds(10, 180, 160, 160);
        jLStan.setFont(new Font("Arial Black", 1, 28));
        jLStan.setHorizontalAlignment(SwingConstants.CENTER);
        jLStan.setVerticalAlignment(SwingConstants.CENTER);
        
        jBSymulacja = new JButton("SYMULUJ");
        jBSymulacja.setBackground(Color.GRAY);
        jBSymulacja.setBounds(10, 345, 160, 40);
        
        jPPreferencje.add(jLData1);
        jPPreferencje.add(jTFData1);
        jPPreferencje.add(jLData2);
        jPPreferencje.add(jTFData2);
        jPPreferencje.add(jLSaldo);
        jPPreferencje.add(jTFSaldo);
        jPPreferencje.add(jLStan);
        jPPreferencje.add(jBSymulacja);
        
        add(jPPreferencje);
        add(jPWykres);
        add(jPTabela);
    }
}