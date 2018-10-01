package hyunah.study.receiptproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.*;

public class ViewActivity extends AppCompatActivity {

    String name;
    int price;

    //TextView receiptView;
    TextView storeView;
    TextView proView;
    TextView priceView;
    TextView priceTotal;

    String result;

    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Intent intent = new Intent(this.getIntent());
        String receiptData = intent.getStringExtra("receiptData");

        //receiptView = (TextView)findViewById(R.id.receiptView);
        storeView = (TextView)findViewById(R.id.storeView);
        proView = (TextView)findViewById(R.id.proView);
        priceView = (TextView)findViewById(R.id.priceView);
        priceTotal = (TextView)findViewById(R.id.total);

        JsonParse(receiptData);
    }

    public void JsonParse(String data) {
        try{
            Log.d("ConTest", data);
            JSONObject jsonObject = new JSONObject(data);


            JSONArray storeArray = (JSONArray)jsonObject.get("storeInfo");

            JSONObject storeObject = storeArray.getJSONObject(0);
            storeView.setText(storeView.getText().toString() + "\n"
                                + storeObject.getString("storeName")+ "\n"
                                + storeObject.getString("address")+ "\n"
                                + storeObject.getString("master")+ "\n"
                                + storeObject.getString("call"));

            JSONArray productArray = (JSONArray)jsonObject.get("productInfo");

            for(int i=0;i<productArray.length();i++) {
                JSONObject productObject = productArray.getJSONObject(i);
                proView.setText(proView.getText().toString() + "\n"
                                    + productObject.getString("name"));
                priceView.setText(priceView.getText().toString() + "\n"
                                    +  productObject.getInt("price"));
                total += productObject.getInt("price");
            }
            priceTotal.setText("합   계 : " + total);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
