/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analiza_bukmacherska;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Ceneo_tab {
    String info="";
    static void pobierz(String url) throws Exception {
        URL pogoda = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(pogoda.openStream()));
        String inputLine;
        int C=0;
        int c=0;
        int P=0;
        int p=0;
        int E=0;
        int e=0;
        int R=0;
        int r=0;
        int T=0;
        int t=0;
        int W=0;
        int w=0;
        int N=0;
        int n=0;
        int Pr=0;
        int pr=0;
        int S=0;
        int s=0;
        String wypisz[] = new String[11];
        while((inputLine = in.readLine())!= null){
            
            if(inputLine.contains("<table summary=\"Parametry produktu ")){
                N=1;
                n=0;
            }
            if(inputLine.contains("<th scope=\"row\">System operacyjny</th>")){
                S=1;
                s=0;
            }
            
            if(inputLine.contains("<div class=\"soffer\" >")){
                C=1;
                c=0;
            }
            if(inputLine.contains("<th scope=\"row\">Procesor</th>")){
                Pr=1;
                pr=0;
            }
            if(inputLine.contains("<th scope=\"row\">Dysk twardy</th>")){
                P=1;
                p=0;
            }
            if(inputLine.contains(" RAM</th>")){
                R=1;
                r=0;
            }
            if(inputLine.contains("<th scope=\"row\">Ekran</th>")){
                E=1;
                e=0;
            }
            if(inputLine.contains("<th scope=\"row\">Czas pracy</th>")){
                T=1;
                t=0;
            }
            if(inputLine.contains("<th scope=\"row\">Waga</th>")){
                W=1;
                w=0;
            }
            
            
            
            if(C==1 && c<4){
                 Pattern pat = Pattern.compile("-?[0-9]+\\.[0-9]+");
                Matcher mat = pat.matcher(inputLine);
                //int k=1;
                while (mat.find()) {
                    wypisz[c-1]=mat.group();
                    //System.out.println("Cena: " + mat.group());
                }
                c++;
            }
            if(P==1 && p<2){
                 Pattern pat = Pattern.compile("-?[0-9]+GB");
                Matcher mat = pat.matcher(inputLine);

                while (mat.find()) {
                    wypisz[3]=mat.group();
                    //System.out.println("Dysk Twardy: " + mat.group());
                }
                p++;
            }
            if(R==1 && r<2){
                 Pattern pat = Pattern.compile("-?[0-9]+GB");
                Matcher mat = pat.matcher(inputLine);

                while (mat.find()) {
                    wypisz[4]=mat.group();
                    //System.out.println("RAM: " + mat.group());
                }
                pat = Pattern.compile("-?[0-9]+MB");
                mat = pat.matcher(inputLine);

                while (mat.find()) {
                    wypisz[4]=mat.group();
                    //System.out.println("RAM: " + mat.group());
                }
                r++;
            }
            if(E==1 && e<2){
                 Pattern pat = Pattern.compile("-?[0-9]+x[0-9]+");
                Matcher mat = pat.matcher(inputLine);

                while (mat.find()) {
                    wypisz[5]=mat.group();
                    //System.out.println("Rozdzialczosc: " + mat.group());
                }
                pat = Pattern.compile("-?([0-9,])+\"");
                mat = pat.matcher(inputLine);

                while (mat.find()) {
                    wypisz[6]=mat.group().substring(0, mat.group().length()-1).replaceAll(",", ".");
                    //System.out.println("przekatna: " + mat.group());
                }
                e++;
            }
            if(T==1 && t<2){
                 Pattern pat = Pattern.compile("-?[0-9]+h");
                Matcher mat = pat.matcher(inputLine);

                while (mat.find()) {
                    wypisz[7]=mat.group();
                    //System.out.println("Czas: " + mat.group());
                }
                t++;
            }
            if(W==1 && w<2){
                 Pattern pat = Pattern.compile("-?[0-9]+g");
                Matcher mat = pat.matcher(inputLine);

                while (mat.find()) {
                    wypisz[8]=mat.group();
                    //System.out.println("Waga: " + mat.group());
                }
                w++;
            }
            if(N==1 && n<1){
                 Pattern pat = Pattern.compile("(?i)(Parametry produktu )(.+?)(\")");
                Matcher mat = pat.matcher(inputLine);
                System.out.print(wypisz[0]);
                for(int i=1;i<11;i++){System.out.print(";"+wypisz[i]);}
                System.out.println();
                while (mat.find()) {
                    for(int i=0;i<11;i++){wypisz[i]="";}
                    wypisz[0]=mat.group().substring(19, mat.group().length()-1);
                    //System.out.println("Nazwa: " + mat.group().substring(19, mat.group().length()-1));
                }
                n++;
            }
            if(Pr==1 && pr<2){
                 Pattern pat = Pattern.compile("(?i)(<td>)(.+?)(</td>)");
                Matcher mat = pat.matcher(inputLine);

                while (mat.find()) {
                    wypisz[9]=mat.group().substring(4, mat.group().length()-5);
                    //System.out.println("Procesor: " + mat.group().substring(4, mat.group().length()-5));
                }
                pr++;
            }
            if(S==1 && s<2){
                 Pattern pat = Pattern.compile("(?i)(<td>)(.+?)(</td>)");
                Matcher mat = pat.matcher(inputLine);

                while (mat.find()) {
                    wypisz[10]=mat.group().substring(4, mat.group().length()-5);
                    //System.out.println("System Operacyjny: " + mat.group().substring(4, mat.group().length()-5));
                }
                s++;
            }
            
        
        
        }in.close();
    }
    public static void main(String args[]) throws Exception {
       String s="http://www.skapiec.pl/site/cat/20/filtr/_0_0_0_0_0_0_0_0_0_0/min3/";
       for(Integer i=1;i<14;i++){
           pobierz(s+Integer.toString(i));
       }
    }
}
