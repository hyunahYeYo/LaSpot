package hyunah.study.receiptproject;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser {
    String name;
    int price;

    public void JsonParse(String data) {
        try{
            Log.d("ConTest", data);
            JSONArray jsonArray = new JSONArray(data);
            /*JSONObject jsonObject = new JSONObject(data);
            name = jsonObject.getString("name");
            price = jsonObject.getInt("price");
            Log.d("ConTest", "Check2 : " + name + " / " + price);*/

            for(int i=0;i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                name = jsonObject.getString("name");
                price = jsonObject.getInt("price");
                Log.d("ConTest", "Check2 : " + name + " / " + price);
            }
        } catch(Exception e) {

        }
    }
}
