import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class A_B{
    z1_Baza baza ;
    z2_Tabele tabele;
    z3_TeamVsTeam team;
    MenuDolne menu ;
    
    static void rysuj() throws SQLException, ClassNotFoundException {
        JFrame f=new JFrame();
        f.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        f.setTitle("Analiza Bukmacherska");
        f.setBackground(new java.awt.Color(153, 153, 153));
        f.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        f.setExtendedState(1);
        f.setIconImages(null);
        f.setResizable(false);
        z1_Baza baza = new z1_Baza();
        z2_Tabele tabele = new z2_Tabele();
        z3_TeamVsTeam team = new z3_TeamVsTeam();
        MenuDolne menu = new MenuDolne(baza,tabele,team);
        JLayeredPane p2 = new JLayeredPane();
        p2.add(baza,1);
        p2.add(tabele,2);
        p2.add(team,3);
        p2.add(menu,1);
        f.add(p2);
        tabele.setVisible(false);
        team.setVisible(false);
        f.setSize(1024, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        rysuj();
    }
}