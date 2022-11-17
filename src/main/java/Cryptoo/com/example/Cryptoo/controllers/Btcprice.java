package Cryptoo.com.example.Cryptoo.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Btcprice {
    public static void main(String[] args) throws IOException{
        URL url = new URL("https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT");
        URLConnection yc = url.openConnection();
        System.out.println("connected");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(yc.getInputStream())
        );
        String inputline;
        while ((inputline = in.readLine()) != null )
            System.out.println(inputline);
            in.close();
    }
}
