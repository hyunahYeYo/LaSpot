package hyunah.study.receiptproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    Button button;
    public static final String IPAddress = "INPUT IP ADDRESS";

    ImageView barcode;
    TextView codeNumber;
    String barcode_data = "12345678";

    String obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        MakeBarcode();


        ConnectThread thread = new ConnectThread(IPAddress.trim());
        thread.start();
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                intent.putExtra("receiptData", obj);
                startActivity(intent);
            }
        });
    }
    class ConnectThread extends Thread {
        String hostName;

        public ConnectThread(String addr) {
            hostName = addr;
        }
        public void run() {
            try {
                int port = 5001;
                Socket sock = new Socket(hostName, port);


                ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
                outstream.writeObject("Client Check...");
                outstream.flush();

                ObjectInputStream instream= new ObjectInputStream(sock.getInputStream());
                obj = (String)instream.readObject();

                sock.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void MakeBarcode() {
        MultiFormatWriter gen = new MultiFormatWriter();
        barcode = (ImageView)findViewById(R.id.barcode);
        codeNumber = (TextView)findViewById(R.id.codeNumber);
        codeNumber.setText(barcode_data);

        try {
            final int WIDTH = 600;
            final int HEIGHT = 180;
            BitMatrix bytemap = gen.encode(barcode_data, BarcodeFormat.CODE_128, WIDTH, HEIGHT);
            Bitmap bitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
            for (int i = 0 ; i < WIDTH ; ++i)
                for (int j = 0 ; j < HEIGHT ; ++j) {
                    bitmap.setPixel(i, j, bytemap.get(i,j) ? Color.BLACK : Color.WHITE);
                }

            barcode.setImageBitmap(bitmap);
            barcode.invalidate();
            System.out.println("done!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
