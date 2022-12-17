package Cryptoo.com.example.Cryptoo;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;


@Service
public class BtcPrice {

    private WebSocketClient mWebSocketClient;
     private WebSocketClient mWebSocketClient1;
     private WebSocketClient mWebSocketClient2;
     private WebSocketClient mWebSocketClient3;
     private WebSocketClient mWebSocketClient4;
     private WebSocketClient mWebSocketClient5;
     private WebSocketClient mWebSocketClient6;
     private WebSocketClient mWebSocketClient7;
     private WebSocketClient mWebSocketClient8;
     private WebSocketClient mWebSocketClient9;
     static public String btc;
   // BtcPrice bcPrice=new BtcPrice();
     static public String eth;
     static public String bnb;
     static public String xrp;
     static public String doge;
     static public String ada;

     static public String shib;

    //Coins coins=new Coins();


     private void ConnectToWebSocket(String pair)  {
         URI uri;
         try {
             uri = new URI("wss://fstream.binance.com/ws/"+pair+"usdt@aggTrade");
         } catch (URISyntaxException e) {
             e.printStackTrace();
             return;
         }
         mWebSocketClient = new WebSocketClient(uri) {
             @Override
             public void onOpen(ServerHandshake serverHandshake) {
             }
             @Override
             public void onMessage(String s) {
                 //mWebSocketClient.close();
                 Object o1 = JSONValue.parse(s);
                 JSONObject jsonObj = (JSONObject) o1;
                 btc = (String) jsonObj.get("p");
                 try {
                     mWebSocketClient.closeBlocking();
                     //TimeUnit.MILLISECONDS.sleep(100);
                 } catch (InterruptedException e) {
                     throw new RuntimeException(e);
                 }
             }
             @Override
             public void onClose(int i, String s, boolean b) {
             }
             @Override
             public void onError(Exception e) {
                 System.out.println("Error");
             }
         };
         mWebSocketClient.connect();
     }
    private void ConnectToWebSocket1(String pair)  {
        URI uri;
        try {
            uri = new URI("wss://fstream.binance.com/ws/"+pair+"usdt@aggTrade");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        mWebSocketClient1 = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
            }
            @Override
            public void onMessage(String s) {
                //mWebSocketClient.close();
                Object o1 = JSONValue.parse(s);
                JSONObject jsonObj = (JSONObject) o1;
                eth = (String) jsonObj.get("p");
                //System.out.println(eth);
                try {
                    mWebSocketClient1.closeBlocking();
                    //TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public void onClose(int i, String s, boolean b) {
            }
            @Override
            public void onError(Exception e) {
                System.out.println("Error");
            }
        };
        mWebSocketClient1.connect();
    }

    private void ConnectToWebSocket2(String pair)  {
        URI uri;
        try {
            uri = new URI("wss://fstream.binance.com/ws/"+pair+"usdt@aggTrade");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        mWebSocketClient2 = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
            }
            @Override
            public void onMessage(String s) {
                //mWebSocketClient.close();
                Object o1 = JSONValue.parse(s);
                JSONObject jsonObj = (JSONObject) o1;
                bnb = (String) jsonObj.get("p");
                //System.out.println(bnb);
                try {
                    mWebSocketClient2.closeBlocking();
                    //TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public void onClose(int i, String s, boolean b) {
            }
            @Override
            public void onError(Exception e) {
                System.out.println("Error");
            }
        };
        mWebSocketClient2.connect();
    }

    private void ConnectToWebSocket3(String pair)  {
        URI uri;
        try {
            uri = new URI("wss://fstream.binance.com/ws/"+pair+"usdt@aggTrade");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        mWebSocketClient3 = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
            }
            @Override
            public void onMessage(String s) {
                //mWebSocketClient.close();
                Object o1 = JSONValue.parse(s);
                JSONObject jsonObj = (JSONObject) o1;
                xrp = (String) jsonObj.get("p");
                //System.out.println(bnb);
                try {
                    mWebSocketClient3.closeBlocking();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public void onClose(int i, String s, boolean b) {
            }
            @Override
            public void onError(Exception e) {
                System.out.println("Error");
            }
        };
        mWebSocketClient3.connect();
    }

    private void ConnectToWebSocket4(String pair)  {
        URI uri;
        try {
            uri = new URI("wss://fstream.binance.com/ws/"+pair+"usdt@aggTrade");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        mWebSocketClient4= new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
            }
            @Override
            public void onMessage(String s) {
                //mWebSocketClient.close();
                Object o1 = JSONValue.parse(s);
                JSONObject jsonObj = (JSONObject) o1;
                doge = (String) jsonObj.get("p");
                //System.out.println(bnb);
                try {
                    mWebSocketClient4.closeBlocking();
                    //TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public void onClose(int i, String s, boolean b) {
            }
            @Override
            public void onError(Exception e) {
                System.out.println("Error");
            }
        };
        mWebSocketClient4.connect();
    }


    private void ConnectToWebSocket5(String pair)  {
        URI uri;
        try {
            uri = new URI("wss://fstream.binance.com/ws/"+pair+"usdt@aggTrade");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        mWebSocketClient5= new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
            }
            @Override
            public void onMessage(String s) {
                //mWebSocketClient.close();
                Object o1 = JSONValue.parse(s);
                JSONObject jsonObj = (JSONObject) o1;
                ada = (String) jsonObj.get("p");
                //System.out.println(bnb);
                try {
                    mWebSocketClient5.closeBlocking();
                    //TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public void onClose(int i, String s, boolean b) {
            }
            @Override
            public void onError(Exception e) {
                System.out.println("Error");
            }
        };
        mWebSocketClient5.connect();
    }


    private void ConnectToWebSocket6(String pair)  {
        URI uri;
        try {
            uri = new URI("wss://fstream.binance.com/ws/"+pair+"usdt@aggTrade");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        mWebSocketClient6= new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
            }
            @Override
            public void onMessage(String s) {
                //mWebSocketClient.close();
                Object o1 = JSONValue.parse(s);
                JSONObject jsonObj = (JSONObject) o1;
                shib = (String) jsonObj.get("p");
                //System.out.println(bnb);
                try {
                    mWebSocketClient6.closeBlocking();
                    //TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public void onClose(int i, String s, boolean b) {
            }
            @Override
            public void onError(Exception e) {
                System.out.println("Error");
            }
        };
        mWebSocketClient6.connect();
    }


    @Scheduled(fixedDelay = 1800)
    public void manageOrder() throws InterruptedException {
        ConnectToWebSocket("btc");
        ConnectToWebSocket1("eth");
        ConnectToWebSocket2("bnb");
        ConnectToWebSocket3("xrp");
        ConnectToWebSocket4("doge");
        ConnectToWebSocket5("ada");
        ConnectToWebSocket6("1000shib");
    }

 }
