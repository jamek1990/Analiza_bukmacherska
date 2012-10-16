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
    private static Connection con;
    private Statement stat;
    
    public SQL() throws SQLException, ClassNotFoundException{
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:mydb.db");
    }
    
    public void createDB() throws SQLException{        
        stat = con.createStatement();        
        stat.executeUpdate("CREATE TABLE MECZE_STATYSTYKI("
	+"Div		TEXT,"
	+"DATA		NUMERIC,"
	+"HomeTeam 	TEXT,"
	+"AwayTeam	TEXT,"
	+"FTHG		Integer,"
	+"FTAG		INTEGER,"
	+"FTR		INTEGER,"
	+"HTHG		INTEGER,"
	+"HTAG		INTEGER,"
	+"HTR		INTEGER,"
	+"HSH		INTEGER,"
	+"ASH		INTEGER,"
	+"HST		INTEGER,"
	+"AST		INTEGER,"
	+"HHW		INTEGER,"
	+"AHW		INTEGER,"
	+"HC		INTEGER,"
	+"AC		INTEGER,"
	+"HF		INTEGER,"
	+"AF		INTEGER,"
	+"HO		INTEGER,"
	+"AO		INTEGER,"
	+"HY		INTEGER,"
	+"AY		INTEGER,"
	+"HR		INTEGER,"
	+"AR		INTEGER,"
	+"K1		REAL,"
	+"KX		REAL,"
	+"K2		REAL");
    }
    
    public void insert_do_statystyk(Mecz_stat MS) throws SQLException{
        PreparedStatement prep = con
          .prepareStatement("INSERT INTO MECZE_STATYSTYKI VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
        prep.setString(1, MS.div);
        prep.setString(2, MS.date);
        prep.setString(3, MS.hometeam);
        prep.setString(4, MS.awayteam);
        prep.setInt(5, MS.fthg);
        prep.setInt(6, MS.ftag);
        prep.setInt(7, MS.ftr);
        prep.setInt(8, MS.hthg);
        prep.setInt(9, MS.htag);
        prep.setInt(10, MS.htr);
        prep.setInt(11, MS.hs);
        prep.setInt(12, MS.as);
        prep.setInt(13, MS.hst);
        prep.setInt(14, MS.ast);
        prep.setInt(15, MS.hhw);
        prep.setInt(16, MS.ahw);
        prep.setInt(17, MS.hc);
        prep.setInt(18, MS.ac);
        prep.setInt(19, MS.hf);
        prep.setInt(20, MS.af);
        prep.setInt(21, MS.ho);
        prep.setInt(22, MS.ao);
        prep.setInt(23, MS.hy);
        prep.setInt(24, MS.ay);
        prep.setInt(25, MS.hr);
        prep.setInt(26, MS.ar);
        prep.setDouble(27, MS.k1);
        prep.setDouble(28, MS.kx);
        prep.setDouble(29, MS.k2);
        boolean rows = prep.execute();
    }
    
    
    public void insert(String home, String away, String wynik) throws SQLException{
        System.out.println(home + " " + " vs " + away);
        //wrzucanie danych
        PreparedStatement prep = con
          .prepareStatement("insert into mecze values(?,?,?,?);");
        prep.setString(2, home);
        prep.setString(3, away);
        prep.setString(4, wynik);
        boolean rows = prep.execute();
        System.out.println("update tabeli!");
    }
    
    //query - zapytanie sql
    public ResultSet executeQuery(String query) throws SQLException
    {
        return stat.executeQuery(query);
    }
}
