package server.sw.opensource;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Scanner;

import org.json.simple.*;

public class PosServer{

	public static final int ServerPort = 5001;
	public static final String ServerIP = "ip address";
	
	public static JSONArray purchaseList;
	
	public static JSONObject jsonObject;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		jsonObject = new JSONObject();
		
		try {
			System.out.println("Connecting...");
			ServerSocket server = new ServerSocket(ServerPort);
			System.out.println("Linstening at " + ServerPort);
			
			System.out.print("Barcode : ");
			String barcode = scanner.nextLine();
			
			while(true) {
				Socket sock = server.accept();
				InetAddress clientHost = sock.getLocalAddress();
				int clientPort = sock.getPort();
				//System.out.println("A Client Connected host : " + clientHost + " / port : " + clientPort);
				System.out.println("A Client Connected");
				
				ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
				Object obj = instream.readObject();
				System.out.println("Input : " + obj);
				
				StoreInfo();
				ProductInfo();
				ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
				outstream.writeObject(jsonObject.toJSONString());
				outstream.flush();
				
				sock.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void StoreInfo() {
		JSONObject store = new JSONObject();
		JSONArray storeArray = new JSONArray();
		
		String storeName = "Lotte Mart";
		String address = "서울특별시 서울구 서울로 48";
		String master = "김서울";
		String call = "02-2929-2929";
		
		store.put("storeName", storeName);
		store.put("address", address);
		store.put("master", master);
		store.put("call", call);
		
		storeArray.add(store);
		
		jsonObject.put("storeInfo", storeArray);
	}
	public static void ProductInfo() {
		JSONObject product = new JSONObject();
		JSONArray productArray = new JSONArray();
		
		product.put("name", "빼빼로");
		product.put("price", 1200);
		productArray.add(product);
		
		product = new JSONObject();
		product.put("name", "포카리스웨트");
		product.put("price", 2500);
		productArray.add(product);
		
		product = new JSONObject();
		product.put("name", "계란(30)");
		product.put("price", 5500);
		productArray.add(product);
		
		product = new JSONObject();
		product.put("name", "코카콜라 제로");
		product.put("price", 3200);
		productArray.add(product);
		
		product = new JSONObject();
		product.put("name", "추파춥스");
		product.put("price", 500);
		productArray.add(product);
		
		jsonObject.put("productInfo", productArray);
	}
}
