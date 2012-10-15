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
    public SQL() throws SQLException, ClassNotFoundException
    {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:mydb.db");
    }
    
    public void createDB() throws SQLException
    {        
        stat = con.createStatement();        
        //tworzenie tabeli ID|HOME|AWAY|WYNIK
        stat.executeUpdate("create table IF NOT EXISTS mecze(id INT,"
          + "home varchar(30)," 
          + "away varchar(30)," 
          + "wynik varchar(6),"
          + "primary key (id));");
    }
    
    public void insert(String home, String away, String wynik) throws SQLException
    {
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
