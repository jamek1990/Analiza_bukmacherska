package analiza_bukmacherska;
import java.util.Random;
public class mecz {
    String team1, team2;
    int data;
    double stawka;
    int R1, R2;
    
    public mecz(String teamA, String teamB, double stawkas, int datas, int RA, int RB)
    {
        team1 = teamA;
        team2 = teamB;
        data = datas;
        stawka = stawkas;
        R1 = RA;
        R2 = RB;
    }    
    public mecz(){
        Random rand = new Random();
        team1 = "" + rand.nextInt(999999);
        team2 = "" + rand.nextInt(999999);
        if(rand.nextBoolean()) data = 20100101;
        else data = 20100102;
        R1 = rand.nextInt(6);
        R2 = rand.nextInt(6);
        stawka = rand.nextDouble();
    }
    public String getTeam1(){
        return team1;
    }
    public String getTeam2(){
        return team2;
    }
    public int getData(){
        return data;
    }
    public double getStawka(){
        return stawka;
    }
    public int getTeam1R(){
        return R1;
    }
    public int getTeam2R(){
        return R2;
    }
    
}
