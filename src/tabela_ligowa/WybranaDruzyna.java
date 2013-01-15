package tabela_ligowa;
import java.util.Observable;
import java.util.Observer;
public class WybranaDruzyna extends Observable{
    String wybranaliga;
    String wybranadruzyna;
    public void Wyslij(){
        setChanged();
        notifyObservers();
    }
    public WybranaDruzyna(){}
    
    public void setWybranaliga(String wybranaliga){
        this.wybranaliga = wybranaliga;
    }
    public void setWybranadruzyna(String wybranadruzyna){
        this.wybranadruzyna = wybranadruzyna;
    }
    public String getWybranaliga(){
        return wybranaliga;
    }
    public String getWybranadruzyna(){
        return wybranadruzyna;
    }
}