

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Main {
    //储存切割后的接收到的消息
    private static volatile List<String> strList2;
    //当接收到末尾时，flagOK设为true
    private static volatile boolean flagOK = false;
    private static volatile int myPort;
    private static volatile String dbPath;
    private static volatile String dbFilePath;

    public static void main(String[] args) throws IOException {
        System.out.println("Start MAIN:\r\n");
        // db 文件存放路径地址
        dbPath = System.getProperty("user.dir")+ File.separator;
        System.out.println("SQLite_DB:path: " + dbPath + "\r\n");
        // 1、创建sqlite连接
        dbFilePath = dbPath + "sqlite-test1.db";
        // 需要判断文件是否存在，不存在则优先创建 .db 文件
        File dbFile = new File(dbFilePath);
        // 如果父路径不存在，则先创建父路径
        if(!dbFile.getParentFile().exists()){
            boolean a = dbFile.getParentFile().mkdirs();
            System.out.println("创建路径:" + a + "\r\n");
        }
        // 如果文件不存在，则创建文件
        if(!dbFile.exists()){
            boolean a = dbFile.createNewFile();
            System.out.println("创建db文件:" + a + "\r\n");
        }
        if(args.length > 0 && args[0].equals("-start")) {

            if(args.length > 1) {
                myPort = Integer.parseInt(args[1]);
            } else myPort = 22441;

            //启动接收服务线程
            MyServerThread myServerThread = new MyServerThread();
            Listener1 listener1 = new Listener1();
            myServerThread.addObserver(listener1);
            new Thread(myServerThread).start();
            System.out.println("Start SERVER:@" + myPort + "\r\n");
            //启动字符串处理线程
            MyStrDeal1 myStrDeal1 = new MyStrDeal1();
            Listener1 listener2 = new Listener1();
            myServerThread.addObserver(listener2);
            new Thread(myStrDeal1).start();

        } else {
            System.out.println("no Args!");
        }

    }

    //getter & setter
    public static int getMyPort() {
        return myPort;
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
    public static String getDbFilePath() {
        return dbFilePath;
    }

}