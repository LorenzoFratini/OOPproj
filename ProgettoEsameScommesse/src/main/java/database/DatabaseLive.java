package database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import com.google.gson.Gson;
import model.currencyHistorical;
import model.currencyLive;

/**
 * 
 * 
 *  @author Murtezi Adrian & Abbruzzetti Matteo
 *
 */

public class DatabaseLive {
	
	public static final String ACCESS_KEY = "568cb386a5b16ba22a7a0a99b0f08f54";
    public static final String BASE_URL = "http://api.currencylayer.com/";
    public static final String ENDPOINT = "live";

    public static ArrayList<currencyHistorical> datas =new ArrayList<currencyHistorical>();
    
    /**
     * Metodo che legge i dati dall'url e restituisce il tasso di cambio
     * tra USD e tutte le altre valute
     * 
     * @return ritorna un ArrayList contenente tutti i possibili scambi
     * @throws Exception
     */

	public static ArrayList<String> ValuteLive() throws Exception{
		
		currencyHistorical p = new currencyHistorical();
			
		try {
			URL url = new URL(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY  );
			URLConnection URLConn = url.openConnection();
			InputStream in = URLConn.getInputStream();
			
			 String dati = "";
			 String line = "";
			 try {
			   
			   BufferedReader br = new BufferedReader( new InputStreamReader( in ) );
			  
			   while ( ( line = br.readLine() ) != null ) {
				   dati+= line;
			   }
			 } finally {
			   in.close();
			 }
			 Gson g = new Gson(); 
			 p = g.fromJson(dati, currencyHistorical.class);
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ArrayList<String> vet = new ArrayList<String>();
		vet.addAll(p.quotes.keySet());
		return vet;
		//return p;
	}
	
/**
 * 
 * Metodo che legge i dati dall'url e restituisce il tasso di cambio
 * tra USD e la valuta scelta
 * 
 * @param currency indica la valuta scelta
 * @return ritorna il tasso di cambio tra USD e la valuta scelta
 * @throws Exception
 */
	
public static Double ValutaSceltaLive(String currency) throws Exception{
		
		currencyLive p = new currencyLive();
			
		try {
			URL url = new URL(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY + "&currencies" + currency);
			URLConnection URLConn = url.openConnection();
			InputStream in = URLConn.getInputStream();
			
			 String dati = "";
			 String line = "";
			 try {
			   
			   BufferedReader br = new BufferedReader( new InputStreamReader( in ) );
			  
			   while ( ( line = br.readLine() ) != null ) {
				   dati+= line;
			   }
			 } finally {
			   in.close();
			 }
			 Gson g = new Gson(); 
			 p = g.fromJson(dati, currencyLive.class);
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
		Double q = p.quotes.get(currency);
		//ArrayList<String> vet = new ArrayList<String>();
		//vet.addAll(p.quotes.keySet());
		return q;
		//return p;
		
		//invece di arraylist creare un hashmap con keys e values
	}
	

}
