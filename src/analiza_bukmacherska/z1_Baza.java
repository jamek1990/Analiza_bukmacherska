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

public class z1_Baza extends JLayeredPane{
    JLayeredPane panel2;
    JLabel jL_OdswiezBaze;
    JLabel baza3;
    m1_okienko jP_OknoAktualizujBaze;
    
    private void RefreshDataBase(java.awt.event.MouseEvent evt) throws Exception {
       // try {
            String ligi[]={"P1","I1","G1","D1","D2","E0","E1","E2","E3","EC","F1","F2","B1","I2","N1","SC0","SC1","SC2","SC3","SP1","SP2","T1"};
            String kolumny[]={"Div","Date","HomeTeam","AwayTeam","FTHG","FTAG","FTR","HTHG","HTAG","HTR","HS","AS","HST","AST","HHW","AHW","HC","AC","HF","AF","HO","AO","HY","AY","HR","AR"};
            String kolumny2[]={"***","***","HT","AT"};
            String mecz[]= new String[29];
            int[] hash = new int[29];
            int[] gdzie = new int[29];
            int gdzie_size=0;
            
            SQL sql = new SQL();
                    //sql.createDB();

            for(int i=0;i<ligi.length;i++){
                for(int j = 111;j<113;j++){
                    String data="";
                    String data_od="";
                    String data_do="";
                    if(j%100<10)data_od="0";
                    if((j+1)%100<10)data_do="0";
                    data_od=data_od+Integer.toString(j%100);
                    data_do=data_do+Integer.toString((j+1)%100);
                    data=data_od+data_do;
                    URL url = new URL("http://www.football-data.co.uk/mmz4281/"+data+"/" + ligi[i]+".csv" );
                    URLConnection con = url.openConnection();
                    InputStreamReader inStream = new InputStreamReader(con.getInputStream());
                    BufferedReader buff= new BufferedReader(inStream);
                    String content1 = buff.readLine();
                    String[] kol = content1.replaceAll("\\s+","\\s").split(",");
                    gdzie_size=0;
                    if(kol.length>2){
                        for(int l=0;l<kolumny.length;l++){
                            mecz[l]=null;
                            for(int k =0;k<kol.length;k++){
                                if(!kol[k].isEmpty()){
                                    if(kolumny[l].contentEquals(kol[k])){
                                        gdzie[gdzie_size]=l;
                                        gdzie_size++;
                                        hash[l]=k;
                                        break;
                                    }
                                }
                            }
                        }
                        for(int l=0;l<kolumny2.length;l++){
                            for(int k =0;k<kol.length;k++){
                                if(!kol[k].isEmpty()){
                                    if(kolumny2[l].contentEquals(kol[k])){
                                        gdzie[gdzie_size]=l;
                                        gdzie_size++;
                                        hash[l]=k;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if(kol.length>2){
                        String content2 = buff.readLine();
                        while (content2 !=null){
                            content2=content2.replaceAll(", ", "").replaceAll("\\s+","\\s");
                            String[] kol2 = content2.split(",",-1);
                            if(kol2.length>=kol.length){
                                for(int k=0;k<gdzie_size;k++){
                                    mecz[gdzie[k]]=null;
                                    if(!kol2[hash[gdzie[k]]].isEmpty())
                                            mecz[gdzie[k]]=kol2[hash[gdzie[k]]];
                                }
                                Mecz_stat m_s=new Mecz_stat();
                                m_s.zmien(mecz);
                                sql.insert_do_statystyk(m_s);
                            }
                            content2 = buff.readLine();
                            
                        }
                    }
                }
            }
       // }
            sql.con.close();
       
    }
    public z1_Baza(){
        jP_OknoAktualizujBaze = new m1_okienko(230,200,780,3,"AKTUALIZACJA");
        jL_OdswiezBaze = new JLabel();
        jL_OdswiezBaze.setForeground(Color.red);
        jP_OknoAktualizujBaze.add(jL_OdswiezBaze,1);
        jL_OdswiezBaze.setBounds(70, 80, 80, 80);
        jP_OknoAktualizujBaze.dodajOdswiez();
        jP_OknoAktualizujBaze.jL_Odswiez.addMouseListener(new java.awt.event.MouseAdapter() {
            //public void mouseEntered(java.awt.event.MouseEvent evt) {jButton4MouseEntered(evt);}
            //public void mouseExited(java.awt.event.MouseEvent evt) {jButton4MouseExited(evt);}
            public void mousePressed(java.awt.event.MouseEvent evt) {
                try {
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