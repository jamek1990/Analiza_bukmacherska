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
public class z1_Baza extends JLayeredPane{
    JLayeredPane panel2;
    JLabel jL_OdswiezBaze;
    JLabel baza3;
    m1_okienko  jP_OknoAktualizujBaze;
    
    private void RefreshDataBase(java.awt.event.MouseEvent evt) throws Exception { 
        try {
            String ligi[]={"B1","D1","D2","E0","E1","E2","E3","EC","F1","F2","G1","I1","I2","N1","P1","SC0","SC1","SC2","SC3","SP1","SP2","T1"};
            for(int i=0;i<ligi.length;i++){
                for(int j = 94;j<113;j++){
                    String data="";
                    String data_od="";
                    String data_do="";
                    if(j%100<10)data_od="0";
                    if((j+1)%100<10)data_do="0";
                    data_od=data_od+Integer.toString(j%100);
                    data_do=data_do+Integer.toString((j+1)%100);
                    data=data_od+data_do;
                    URL url12  = new URL("http://www.football-data.co.uk/mmz4281/"+data+"/" + ligi[i]+".csv" );
                    System.out.println(url12.getPath());
                    URLConnection urlConn = url12.openConnection();
                    InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
                    BufferedReader buff= new BufferedReader(inStream);
                    //String content1 = buff.readLine();
                    String content2 = buff.readLine();
                    while (content2 !=null){
                        System.out.println(content2);
                        content2 = buff.readLine(); 
                        break;
                    }
                }
            }
        }
        catch (Exception e){
            System.out.print("KHONG TAI DUOC DU LIEU");
        }
    }
    public z1_Baza(){
        jP_OknoAktualizujBaze = new m1_okienko(200,200,805,5,"AKTUALIZACJA");
        jL_OdswiezBaze = new JLabel();
        
        jL_OdswiezBaze.setForeground(Color.red);
        //jL_OdswiezBaze.setIcon(new javax.swing.ImageIcon("images/odswiezBaze.png"));
        jP_OknoAktualizujBaze.add(jL_OdswiezBaze,1);
        jL_OdswiezBaze.setBounds(70, 80, 80, 80);
        jP_OknoAktualizujBaze.dodajOdswiez();
        jP_OknoAktualizujBaze.jL_Odswiez.addMouseListener(new java.awt.event.MouseAdapter() {
            //public void mouseEntered(java.awt.event.MouseEvent evt) {jButton4MouseEntered(evt);}
            //public void mouseExited(java.awt.event.MouseEvent evt)  {jButton4MouseExited(evt);}
            public void mousePressed(java.awt.event.MouseEvent evt) {try {
                    RefreshDataBase(evt);
                } catch (Exception ex) {
                    Logger.getLogger(z1_Baza.class.getName()).log(Level.SEVERE, null, ex);
                }
}
            });
        setLocation(0,0);
        setBounds(0, 0, 1024, 300);
        add(jP_OknoAktualizujBaze);
    }
}
