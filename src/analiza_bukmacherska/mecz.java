package analiza_bukmacherska;

public class mecz {
    String team1, team2;
    String data;
    double stawka;
    
    public mecz(String teamA, String teamB, double stawkas, String datas)
    {
        team1 = teamA;
        team2 = teamB;
        data = datas;
        stawka = stawkas;
    }    
}
