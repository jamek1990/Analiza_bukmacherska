package analiza_bukmacherska;
import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class z3_TeamVsTeam extends JLayeredPane{
    m1_okienko  jP_TeamVsTeam;
    public z3_TeamVsTeam(){
        jP_TeamVsTeam = new m1_okienko(400,200,0,3,"TEAM VS TEAM");
        jP_TeamVsTeam.dodajPodzial();
        setLocation(0,0);
        setBounds(0, 0, 1024, 300);
        add(jP_TeamVsTeam);
    }
}
