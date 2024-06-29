

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    //储存切割后的接收到的消息
    private static volatile List<String> strList2;
    //当接收到末尾时，flagOK设为true
    private static volatile boolean flagOK = false;
    //储存排序后的APdata
    private static volatile List<APdata> aPdata;
    private static List<String> preData1;
    //消息处理完
    private static volatile boolean flag_pre_OK = false;

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
            dbFile.getParentFile().mkdirs();
            System.out.println("创建路径\r\n");
        }
        // 如果文件不存在，则创建文件
        if(!dbFile.exists()){
            dbFile.createNewFile();
            System.out.println("创建db文件\r\n");
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
    public static boolean isFlag_pre_OK() {
        return flag_pre_OK;
    }
    public static void setFlag_pre_OK(boolean flag_pre_OK) {
        Main.flag_pre_OK = flag_pre_OK;
    }
    public static List<String> getPreData1() {
        return preData1;
    }
    public static void setPreData1(List<String> preData1) {
        Main.preData1 = preData1;
    }
    public static String getDbPath() {
        return dbPath;
    }
    public static void setDbPath(String dbPath) {
        Main.dbPath = dbPath;
    }
    public static String getDbFilePath() {
        return dbFilePath;
    }
    public static void setDbFilePath(String dbFilePath) {
        Main.dbFilePath = dbFilePath;
    }
}