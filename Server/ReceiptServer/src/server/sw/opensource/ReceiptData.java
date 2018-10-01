package server.sw.opensource;

import java.io.Serializable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class ReceiptData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String Name;
	int Price;
	
	JSONObject data;
	
	public ReceiptData(String name, int price) {
		data = new JSONObject();
		
		data.put("name", name);
		data.put("price", price);
	}
	
}
