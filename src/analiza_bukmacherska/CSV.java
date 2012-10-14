package analiza_bukmacherska;
import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
public class CSV {
    public CSV() {}
    public void read()throws Exception {
        CSVReader reader = new CSVReader(new FileReader("C:\\Data\\D1.csv"));
        String [] nextLine;
        
        while ((nextLine = reader.readNext()) != null) {
            System.out.println("WYMIAR TABLICY: " + nextLine.length);
            System.out.println(nextLine[0] +" "+ nextLine[1] +" "+ nextLine[2]+" "+nextLine[3]+" "+nextLine[4]+" "+nextLine[5]);
        }
    };
}

