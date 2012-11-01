import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class SQL {
    public static Connection con;
    public Statement stat;
    
    public SQL() throws SQLException, ClassNotFoundException{
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:D:/Users/User/Documents/NetBeansProjects/A_B/Analiza_bukmacherska/mydb.db");
        /*con.setAutoCommit(false);
        con.commit();
        Statement stat = con.createStatement();
        stat.executeUpdate("drop table if exists MECZE_STATYSTYKI");
        createDB*/
    }
    
    public void createDB() throws SQLException{
        stat = con.createStatement();
        stat.executeUpdate("CREATE TABLE MECZE_STATYSTYKI("
+"Div TEXT,"
+"DATA NUMERIC,"
+"HomeTeam TEXT,"
+"AwayTeam TEXT,"
+"FTHG Integer,"
+"FTAG INTEGER,"
+"FTR INTEGER,"
+"HTHG INTEGER,"
+"HTAG INTEGER,"
+"HTR INTEGER,"
+"HSH INTEGER,"
+"ASH INTEGER,"
+"HST INTEGER,"
+"AST INTEGER,"
+"HHW INTEGER,"
+"AHW INTEGER,"
+"HC INTEGER,"
+"AC INTEGER,"
+"HF INTEGER,"
+"AF INTEGER,"
+"HO INTEGER,"
+"AO INTEGER,"
+"HY INTEGER,"
+"AY INTEGER,"
+"HR INTEGER,"
+"AR INTEGER,"
+"K1 REAL,"
+"KX REAL,"
+"K2 REAL);");
    }
    
    public void insert_do_statystyk(Mecz_stat MS) throws SQLException{
        PreparedStatement prep = con
          .prepareStatement("INSERT INTO MECZE_STATYSTYKI VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
        if(MS.div!=null){
            prep.setString(1, MS.div);
            System.out.println(MS.div);
        }else{
            prep.setNull(1,java.sql.Types.VARCHAR);
        }
        if(MS.date!=null){
            prep.setInt(2, MS.date);
        }else{
            prep.setNull(2,java.sql.Types.INTEGER);
        }
        if(MS.hometeam!=null){
            prep.setString(3, MS.hometeam);
        }else{
            prep.setNull(3,java.sql.Types.VARCHAR);
        }
        if(MS.awayteam!=null){
            prep.setString(4, MS.awayteam);
        }else{
            prep.setNull(4,java.sql.Types.VARCHAR);
        }
        if(MS.fthg!=null){
            prep.setInt(5, MS.fthg);
        }else{
            prep.setNull(5, java.sql.Types.INTEGER);
        }
        if(MS.fthg!=null){
            prep.setInt(5, MS.fthg);
        }else{
            prep.setNull(5, java.sql.Types.INTEGER);
        }
        if(MS.ftag!=null){
            prep.setInt(6, MS.ftag);
        }else{
            prep.setNull(6, java.sql.Types.INTEGER);
        }
        if(MS.ftr!=null){
            prep.setString(7, MS.ftr);
        }else{
            prep.setNull(7,java.sql.Types.VARCHAR);
        }
        if(MS.hthg!=null){
            prep.setInt(8, MS.hthg);
        }else{
            prep.setNull(8, java.sql.Types.INTEGER);
        }
        if(MS.htag!=null){
            prep.setInt(9, MS.htag);
        }else{
            prep.setNull(9, java.sql.Types.INTEGER);
        }
        if(MS.htr!=null){
            prep.setString(10, MS.htr);
        }else{
            prep.setNull(10,java.sql.Types.VARCHAR);
        }
        if(MS.hs!=null){
            prep.setInt(11, MS.hs);
        }else{
            prep.setNull(11, java.sql.Types.INTEGER);
        }
        if(MS.as!=null){
            prep.setInt(12, MS.as);
        }else{
            prep.setNull(12, java.sql.Types.INTEGER);
        }
        if(MS.hst!=null){
            prep.setInt(13, MS.hst);
        }else{
            prep.setNull(13, java.sql.Types.INTEGER);
        }
        if(MS.ast!=null){
            prep.setInt(14, MS.ast);
        }else{
            prep.setNull(14, java.sql.Types.INTEGER);
        }
        if(MS.hhw!=null){
            prep.setInt(15, MS.hhw);
        }else{
            prep.setNull(15, java.sql.Types.INTEGER);
        }
        if(MS.ahw!=null){
            prep.setInt(16, MS.ahw);
        }else{
            prep.setNull(16, java.sql.Types.INTEGER);
        }
        if(MS.hc!=null){
            prep.setInt(17, MS.hc);
        }else{
            prep.setNull(17, java.sql.Types.INTEGER);
        }
        if(MS.ac!=null){
            prep.setInt(18, MS.ac);
        }else{
            prep.setNull(18, java.sql.Types.INTEGER);
        }
        if(MS.hf!=null){
            prep.setInt(19, MS.hf);
        }else{
            prep.setNull(19, java.sql.Types.INTEGER);
        }
        if(MS.af!=null){
            prep.setInt(20, MS.af);
        }else{
            prep.setNull(20, java.sql.Types.INTEGER);
        }
        if(MS.ho!=null){
            prep.setInt(21, MS.ho);
        }else{
            prep.setNull(21, java.sql.Types.INTEGER);
        }
        if(MS.ao!=null){
            prep.setInt(22, MS.ao);
        }else{
            prep.setNull(22, java.sql.Types.INTEGER);
        }
        if(MS.hy!=null){
            prep.setInt(23, MS.hy);
        }else{
            prep.setNull(23, java.sql.Types.INTEGER);
        }
        if(MS.ay!=null){
            prep.setInt(24, MS.ay);
        }else{
            prep.setNull(24, java.sql.Types.INTEGER);
        }
        if(MS.hr!=null){
            prep.setInt(25, MS.hr);
        }else{
            prep.setNull(25, java.sql.Types.INTEGER);
        }
        if(MS.ar!=null){
            prep.setInt(26, MS.ar);
        }else{
            prep.setNull(26, java.sql.Types.INTEGER);
        }
        if(MS.k1!=null){
            prep.setDouble(27, MS.k1);
        }else{
            prep.setNull(27, java.sql.Types.DOUBLE);
        }
        if(MS.kx!=null){
            prep.setDouble(28, MS.kx);
        }else{
            prep.setNull(28, java.sql.Types.DOUBLE);
        }
        if(MS.k2!=null){
            prep.setDouble(29, MS.k2);
        }else{
            prep.setNull(29, java.sql.Types.DOUBLE);
        }
        //prep.addBatch();
        //prep.executeBatch();
        boolean rows = prep.execute();
       // prep.cancel();
    }
    
    
    public void insert(String home, String away, String wynik) throws SQLException{
        System.out.println(home + " " + " vs " + away);
        //wrzucanie danych
        PreparedStatement prep = con
          .prepareStatement("insert into mecze values(?,?,?,?);");
        prep.setString(2, home);
        prep.setString(3, away);
        prep.setString(4, wynik);
        prep.addBatch();
        prep.executeBatch();
        boolean rows = prep.execute();
        System.out.println("update tabeli!");
    }
    
    //query - zapytanie sql
    public ResultSet executeQuery(String query) throws SQLException
    {
        return stat.executeQuery(query);
    }
}