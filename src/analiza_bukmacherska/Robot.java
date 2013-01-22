package analiza_bukmacherska;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Robot {
    private void RefreshDataBase(java.awt.event.MouseEvent evt) throws Exception {
        String ligi[]={"P1","I1","G1","D1","D2","E0","E1","E2","E3","EC","F1","F2","B1","I2","N1","SC0","SC1","SC2","SC3","SP1","SP2","T1"};
        String kolumny[]={"Div","Date","HomeTeam","AwayTeam","FTHG","FTAG","FTR","HTHG","HTAG","HTR","HS","AS","HST","AST","HHW","AHW","HC","AC","HF","AF","HO","AO","HY","AY","HR","AR"};
        String kolumny2[]={"***","***","HT","AT"};
        String mecz[]= new String[29];
        int[] hash = new int[29];
        int[] gdzie = new int[29];
        int gdzie_size=0;
        SQL sql = new SQL();
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
                System.out.println(url);
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
        sql.con.close();   
    }
    public void RefreshNewKurs() throws Exception {
        SQL sql = new SQL();
        sql.clear_stare_kursy();
        URL url = new URL("http://www.football-data.co.uk/fixtures.csv" );
        String kur[]= new String[7];
        URLConnection con = url.openConnection();
        InputStreamReader inStream = new InputStreamReader(con.getInputStream());
        BufferedReader buff= new BufferedReader(inStream);
        String content2 = buff.readLine();
        content2 = buff.readLine();
        while (content2 !=null){
            content2=content2.replaceAll(", ", "").replaceAll("\\s+","\\s");
            String[] kol2 = content2.split(",",-1);
            int j=0;
            for(int i=0;i<4;i++){
                kur[j]=kol2[i];j++;
            }
            for(int i=10;i<13;i++){
                kur[j]=kol2[i];j++;
            }
            for(int i=0;i<7;i++) {
                System.out.println(kur[i]);
            }
            Kurs_Baza k_b=new Kurs_Baza();
            k_b.zmien(kur);
            
            sql.insert_do_kursy(k_b);
            content2 = buff.readLine();       
        }
        sql.con.close(); 
    }
}
    

