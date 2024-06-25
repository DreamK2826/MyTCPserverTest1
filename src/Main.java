

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    //储存切割后的接收到的消息
    private static volatile List<String> strList2;
    //当接收到末尾时，flagOK设为true
    private static volatile boolean flagOK = false;
    //储存排序后的APdata
    private static volatile List<APdata> aPdata;
    //将用于生成excel的数据
    private static volatile List<TableData> tableDataList;
    private static volatile int myPort;

    public static void main(String[] args) throws IOException {
        List<APdata>  aPdata = new ArrayList<>();
        System.out.println("Start MAIN:\r\n");
        myPort = 22441;
        MyServerThread myServerThread = new MyServerThread();
        Listener1 listener1 = new Listener1();
        myServerThread.addObserver(listener1);
        new Thread(myServerThread).start();
        System.out.println("Start SERVER:\r\n");
        MyStrDeal1 myStrDeal1 = new MyStrDeal1();
        Listener1 listener2 = new Listener1();
        myServerThread.addObserver(listener2);
        new Thread(myStrDeal1).start();




//        TCP_server(22441);
//        new Thread(() -> {
//            while(true){
//                if(flagOK){
//                    System.out.println("FLAG_OK\r\n");
//                    //切割处理部分
//                    if(strList2 != null){
//                        for (int i = 0; i < strList2.size(); i++) {
//                            if(i == 1){
////                                System.out.println(strList2.get(i).substring(5));
//                                System.out.println(strList2.get(i));
//                            }
//                            if(i == 2){
////                                System.out.println(strList2.get(i).substring(5));
//                                System.out.println(strList2.get(i));
//                            }
//                            if(i == 3){
////                                System.out.println(strList2.get(i).substring(5));
//                                System.out.println(strList2.get(i));
//
//                            }
//                            if(i == 4){
//                                System.out.println(strList2.get(i));
//                            }
//                            //从4号元素开始是wifi信息
//                            if (i > 4 && i < strList2.size() - 1) {
//                                String str = strList2.get(i), temp1,temp2;
//                                List<String> strL = Arrays.stream(str.split(",")).toList();
//                                temp1 = strL.getFirst();
//                                temp2 = strL.get(1);
//                                temp1 = temp1.substring(1);
//                                temp2 = temp2.substring(0,temp2.length()-1);
//                                System.out.println("#" + (i-4) + ":" + temp1 + "~" + temp2);
//                                aPdata.add(new APdata(temp1,Integer.parseInt(temp2)));
//                            }
//                        }
//                    }
//                    flagOK = false;
//                }
//            }
//
//        }).start();

    }

    public static int getMyPort() {
        return myPort;
    }

    public static void setMyPort(int myPort) {
        Main.myPort = myPort;
    }

    public static List<String> getStrList2() {
        return strList2;
    }

    public static void setStrList2(List<String> strList2) {
        Main.strList2 = strList2;
    }

    public static boolean isFlagOK() {
        return flagOK;
    }

    public static void setFlagOK(boolean flagOK) {
        Main.flagOK = flagOK;
    }

    public static List<APdata> getaPdata() {
        return aPdata;
    }

    public static void setaPdata(List<APdata> aPdata) {
        Main.aPdata = aPdata;
    }

    public static List<TableData> getTableDataList() {
        return tableDataList;
    }

    public static void setTableDataList(List<TableData> tableDataList) {
        Main.tableDataList = tableDataList;
    }

    //    private static void TCP_server(int port) throws IOException {
//        //创建一个server socket其端口与发送端的端口是一样的
//        ServerSocket ss  = new ServerSocket(port);
//        aPdata = new ArrayList<>();
//        new Thread(() -> {
//            while(true){
//                try {
//                    //侦听并接受到此套接字的连接，返回一个socket对象
//                    Socket s = ss.accept();
//                    //获取到输入流
//                    InputStream is = s.getInputStream();
////                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
//                    //接收收到的数据
//                    byte[] buf = new byte[1024];
//                    int line;
//                    StringBuilder s1 = new StringBuilder();
//                    while((line=is.read(buf))!=-1){
//                        String temp = new String(buf,0,line);
//                        //将接收到的数据在控制台输出
////                        System.out.print(temp);
//                        //先把temp添加到s1
//                        s1.append(temp);
//                        //判断到达末尾，切割为List，清空s1
//                        if(temp.endsWith("$")){
//                            strList2 = Arrays.stream(s1.toString().split("\0")).toList();
////                            System.out.println(strList2);
//                            s1.delete(0,s1.length());
//                            flagOK = true;
//                        }
//                    }
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
}