import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class MyServerThread extends Observable implements Runnable {

    // 此方法一经调用，立马可以通知观察者，在本例中是监听线程
    public void doBusiness() {
        super.setChanged();
        notifyObservers();
    }
    public void run() {
        //创建一个server socket其端口与发送端的端口是一样的
        ServerSocket ss;
        try {
            ss = new ServerSocket(Main.getMyPort());
        } catch (IOException e) {
            doBusiness();
            throw new RuntimeException(e);
        }
        ServerSocket finalSs = ss;
            while(true){
                try {
                    //侦听并接受到此套接字的连接，返回一个socket对象
                    Socket s = finalSs.accept();
                    //获取到输入流
                    InputStream is = s.getInputStream();
//                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    //接收收到的数据
                    byte[] buf = new byte[512];
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
                            Main.setStrList2(Arrays.stream(s1.toString().split("\0")).toList());
//                            System.out.println(s1);
                            s1.delete(0,s1.length());
                            Main.setFlagOK(true);
                        }
                    }
                } catch (Exception exception) {
                    doBusiness();
                    exception.printStackTrace();
                    break;
                }
            }
    }
}
