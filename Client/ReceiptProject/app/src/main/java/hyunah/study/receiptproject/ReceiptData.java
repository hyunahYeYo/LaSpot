package hyunah.study.receiptproject;


import java.io.Serializable;

public class ReceiptData implements Serializable{
    String Name;
    int Price;
    ReceiptData next;

    public ReceiptData(String name, int price) {
        Name = name;
        Price = price;
        this.next = null;
    }
}
