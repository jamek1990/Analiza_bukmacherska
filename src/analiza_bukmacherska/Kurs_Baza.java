package analiza_bukmacherska;
public class Kurs_Baza {
    String div;
    Integer date;
    String hometeam;
    String awayteam; 
    Double k1;
    Double kx;
    Double k2;
    public void zmien(String[] st){
        if(st[0]!=null) {
            div=st[0];
        }
        else {
            div = null;
        }
        if(st[1]!=null) {
            if(st[1].length()==8)
                date=Integer.parseInt(st[1].substring(0, 2))+Integer.parseInt(st[1].substring(3, 5))*100+(Integer.parseInt(st[1].substring(6, 8))+2000)*10000;
            else
                date=Integer.parseInt(st[1].substring(0, 2))+Integer.parseInt(st[1].substring(3, 5))*100+(Integer.parseInt(st[1].substring(8, 10))+2000)*10000; 
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
            k1=Double.parseDouble(st[4]);
        }
        else {
            k1=null;
        }
        if(st[5]!=null) {
            kx=Double.parseDouble(st[5]);
        }
        else {
            kx=null;
        }
        if(st[6]!=null) {
            k2=Double.parseDouble(st[6]);
        }
        else {
            k2=null;
        }
    }
}
