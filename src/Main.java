

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    private static volatile List<String> strList2;
    private static volatile boolean flagOK = false;
    private static volatile List<APdata> aPdata;
    private static volatile List<TableData> tableDataList;

    public static void main(String[] args) throws IOException {

        TCP_server(22441);
        new Thread(() -> {
            while(true){
                if(flagOK){
                    System.out.println("FLAG_OK");
                    //切割处理部分
                    if(strList2 != null){
                        for (int i = 0; i < strList2.size(); i++) {
                            if(i == 1){
                                System.out.println(strList2.get(i).substring(5));
                            }
                            if(i == 2){
                                System.out.println(strList2.get(i).substring(5));
                            }
                            if(i == 3){
                                System.out.println(strList2.get(i).substring(5));

                            }
                            //从3号元素开始是wifi信息
                            if (i > 3 && i < strList2.size() - 1) {
                                String str = strList2.get(i), temp1,temp2;
                                List<String> strL = Arrays.stream(str.split(",")).toList();
                                temp1 = strL.getFirst();
                                temp2 = strL.get(1);
                                temp1 = temp1.substring(1);
                                temp2 = temp2.substring(0,temp2.length()-1);
//                                    System.out.println("#" + (i-3) + ":" + temp1 + "~" + temp2);
                                aPdata.add(new APdata(temp1,Integer.parseInt(temp2)));
                            }
                        }
                    }
                    flagOK = false;
                }
            }

        }).start();

    }

    private static void TCP_server(int port) throws IOException {
        //创建一个server socket其端口与发送端的端口是一样的

        ServerSocket ss  = new ServerSocket(port);
        aPdata = new ArrayList<>();
        new Thread(() -> {
            while(true){
                try {
                    //侦听并接受到此套接字的连接，返回一个socket对象
                    Socket s = ss.accept();
                    //获取到输入流
                    InputStream is = s.getInputStream();
//                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    //接收收到的数据
                    byte[] buf = new byte[1024];
                    int line;
                    StringBuilder s1 = new StringBuilder();
                    while((line=is.read(buf))!=-1){
                        String temp = new String(buf,0,line);
                        //将接收到的数据在控制台输出
//                        System.out.print(temp);
                        //先把temp添加到s1
                        s1.append(temp);
                        //判断到达末尾，切割为List，清空s1
                        if(temp.endsWith("$")){
                            strList2 = Arrays.stream(s1.toString().split("\0")).toList();
//                            System.out.println(strList2);
                            s1.delete(0,s1.length());
                            flagOK = true;
                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}