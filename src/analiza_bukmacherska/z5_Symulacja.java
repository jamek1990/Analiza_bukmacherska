package analiza_bukmacherska;

import javax.swing.JLayeredPane;

/**
 *
 * @author User
 */
public class z5_Symulacja extends JLayeredPane{
    m1_okienko jP_Prognozy;
    z5_SymulacjaPanel z5;    
    public z5_Symulacja(){
        z5 = new z5_SymulacjaPanel();
        add(z5);
        z5.setBounds(0, 0, 1024, 530);
        setLocation(0,0);
        setBounds(0, 0, 1024, 600);
        
        
    }
}