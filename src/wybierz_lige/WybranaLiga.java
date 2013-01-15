package wybierz_lige;
import java.util.Observable;
import java.util.Observer;

public class WybranaLiga extends Observable{
    String wybranaliga;
     
    public WybranaLiga(){}
    public WybranaLiga(String wybranaliga){
        this.wybranaliga = wybranaliga;
    }
    public void Wyslij(){
        setChanged();
        notifyObservers();
    }
    
    public void setWybranaliga(String wybranaliga){
        this.wybranaliga = wybranaliga;
    }
    
    public String getWybranaliga(){
        return wybranaliga;
    }
}
