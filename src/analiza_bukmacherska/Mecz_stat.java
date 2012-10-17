public class Mecz_stat {
    String div;
    String date;
    String hometeam;
    String awayteam;
    Integer fthg;
    Integer ftag;
    String ftr;
    Integer hthg;
    Integer htag;
    String htr;
    Integer hs;
    Integer as;
    Integer hst;
    Integer ast;
    Integer hhw;
    Integer ahw;
    Integer hc;
    Integer ac;
    Integer hf;
    Integer af;
    Integer ho;
    Integer ao;
    Integer hy;
    Integer ay;
    Integer hr;
    Integer ar;
    Double k1;
    Double kx;
    Double k2;
    public Mecz_stat(){}
    public void zmien(String[] st){
        if(st[0]!=null) {
            div=st[0];
        }
        else {
            div = null;
        }
        if(st[1]!=null) {
            date=st[1];
        }
        else {
            date=null;
        }
        if(st[2]!=null) {
            hometeam=st[2];
        }
        else {
            hometeam=null;
        }
        if(st[3]!=null) {
            awayteam=st[3];
        }
        else {
            awayteam=null;
        }
        if(st[4]!=null) {
            fthg=Integer.parseInt(st[4]);
        }
        else {
            fthg=null;
        }
        if(st[5]!=null) {
            ftag=Integer.parseInt(st[5]);
        }
        else {
            ftag=null;
        }
        if(st[6]!=null) {
            ftr=st[6];
        }
        else {
            ftr=null;
        }
        if(st[7]!=null) {
            hthg=Integer.parseInt(st[7]);
        }
        else {
            hthg=null;
        }
        if(st[8]!=null) {
            htag=Integer.parseInt(st[8]);
        }
        else {
            htag=null;
        }
        if(st[9]!=null) {
            htr=st[9];
        }
        else {
            htr=null;
        }
        if(st[10]!=null) {
            try {
                hs=Integer.parseInt(st[10]);
            } catch (NumberFormatException e) {
                hs=null;
            }
        }
        else {
            hs=null;
        }
        if(st[11]!=null) {
            as=Integer.parseInt(st[11]);
        }
        else {
            as=null;
        }
        if(st[12]!=null) {
            hst=Integer.parseInt(st[12]);
        }
        else {
            hst=null;
        }
        if(st[13]!=null) {
            ast=Integer.parseInt(st[13]);
        }
        else {
            ast=null;
        }
        if(st[14]!=null) {
            hhw=Integer.parseInt(st[14]);
        }
        else {
            hhw=null;
        }
        if(st[15]!=null) {
            ahw=Integer.parseInt(st[15]);
        }
        else {
            ahw=null;
        }
        if(st[16]!=null) {
            hc=Integer.parseInt(st[16]);
        }
        else {
            hc=null;
        }
        if(st[17]!=null) {
            ac=Integer.parseInt(st[17]);
        }
        else {
            ac=null;
        }
        if(st[18]!=null) {
            hf=Integer.parseInt(st[18]);
        }
        else {
            hf=null;
        }
        if(st[19]!=null) {
            af=Integer.parseInt(st[19]);
        }
        else {
            af=null;
        }
        if(st[20]!=null) {
            ho=Integer.parseInt(st[20]);
        }
        else {
            ho=null;
        }
        if(st[21]!=null) {
            try {
                ao=Integer.parseInt(st[21]);
            } catch (NumberFormatException e) {
                ao=null;
            }
        }
        else {
            ao=null;
        }
        if(st[22]!=null) {
            try {
                hy=Integer.parseInt(st[22]);
            } catch (NumberFormatException e) {
                hy=null;
            }
        }
        else {
            hy=null;
        }
        if(st[23]!=null) {
            try {
                ay=Integer.parseInt(st[23]);
            } catch (NumberFormatException e) {
                ay=null;
            }
        }
        else {
            ay=null;
        }
        if(st[24]!=null) {
            try {
                hr=Integer.parseInt(st[24]);
            } catch (NumberFormatException e) {
                hr=null;
            }
        }
        else {
            hr=null;
        }
        if(st[25]!=null) {
            try {
                ar=Integer.parseInt(st[25]);
            } catch (NumberFormatException e) {
                ar=null;
            }
        }
        else {
            ar=null;
        }
        if(st[26]!=null) {
            k1=Double.parseDouble(st[26]);
        }
        else {
            k1=null;
        }
        if(st[27]!=null) {
            kx=Double.parseDouble(st[27]);
        }
        else {
            kx=null;
        }
        if(st[28]!=null) {
            k2=Double.parseDouble(st[28]);
        }
        else {
            k2=null;
        }
    }
}
