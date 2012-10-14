import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.BorderLayout;
public class z1_Baza extends JLayeredPane{
    JLayeredPane panel2;
    JLabel baza2;
    JLabel baza3;
    public z1_Baza(){
        panel2= new JLayeredPane();
        setLayout(null);
        baza2 = new JLabel("DODAĆ ŁADOWANIE BAZY PRELOADER TABELKE Z AKTUALNYMI KURSAMI STATYSTYKA BAZY2()):, ale również stronę tytułową");
        baza2.setBounds(0, 0, 1024, 20);
        panel2.add(baza2);
        panel2.setBounds(0, 0, 1024, 300);
        setLocation(0,0);
        setBounds(0, 0, 1024, 300);
        add(panel2);
    }
}
