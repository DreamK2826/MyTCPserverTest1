import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

public class MyStrDeal1 extends Observable implements Runnable{

    //储存name、POS1、POS2、等信息
    private static volatile List<String> preData;
    private static volatile List<APdata> tmpData;
    public void doBusiness() {
        super.setChanged();
        notifyObservers();
    }

    @Override
    public void run() {
        tmpData = new ArrayList<APdata>();
        preData = new ArrayList<>();
        SqliteHelper sqliteHelper = null;
        String name = "null",POS1 = "null",POS2 = "null",ARG1 = "0",ARG2 = "0";
        try {
            sqliteHelper = new SqliteHelper(Main.getDbFilePath());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String createUser = "create table if not exists singleTable1(id integer primary key autoincrement,name text,POS1 text,POS2 text,SSID text,RSSI text,ARG1 text,ARG2 text)";
        try {
            sqliteHelper.executeUpdate(createUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


//        String insertUserData = "insert into singleTable1(name,POS1,POS2,SSID,RSSI,ARG1,ARG2) values ('name1','1','2','6520','-58','0','0')";
//        sqliteHelper.executeUpdate(insertUserData);

        // 此方法一经调用，立马可以通知观察者，在本例中是监听线程
        while(true) {
            try {
                if(Main.isFlagOK()){
                    System.out.println("FLAG_OK\r\n");
                    //切割处理部分
                    List<String> ls1 = Main.getStrList2();
                    if(ls1.size() > 2){
                        for (int i = 0; i < ls1.size(); i++) {
                            if(i == 1){
                                //name
//                                System.out.println(strList2.get(i).substring(5));
                                System.out.println(ls1.get(i));
                                name = ls1.get(i).substring(5);
                                preData.add(ls1.get(i));
                            }
                            if(i == 2){
                                //POS1
//                                System.out.println(strList2.get(i).substring(5));
                                System.out.println(ls1.get(i));
                                POS1 = ls1.get(i).substring(5);
                                preData.add(ls1.get(i));
                            }
                            if(i == 3){
                                //POS2
//                                System.out.println(strList2.get(i).substring(5));
                                System.out.println(ls1.get(i));
                                POS2 = ls1.get(i).substring(5);
                                preData.add(ls1.get(i));
                            }
                            if(i == 4){
                                //特殊参数1
                                System.out.println(ls1.get(i));
                                ARG1 = ls1.get(i).substring(5);
                                preData.add(ls1.get(i));
                            }
                            //从4号元素开始是wifi信息
                            if (i > 4 && i < ls1.size() - 1) {

                                String str = ls1.get(i), temp1,temp2;
                                List<String> strL = Arrays.stream(str.split(",")).toList();
                                try{
                                    temp1 = strL.getFirst();
                                    temp2 = strL.get(1);
                                    temp1 = temp1.substring(1);
                                    temp2 = temp2.substring(0,temp2.length()-1);
                                    System.out.println("#" + (i-4) + ":" + temp1 + "~" + temp2);
//                                    + 'name1','1','2','6520','-58','0','0')";
                                    String insertUserData = "insert into singleTable1(name,POS1,POS2,SSID,RSSI,ARG1,ARG2) " +
                                            "values ('" + name + "','" + POS1 + "','" + POS2  + "','" + temp1  + "','" + temp2  + "','" + ARG1  + "','" + ARG2 + "')";
                                    sqliteHelper.executeUpdate(insertUserData);
                                    tmpData.add(new APdata(temp1,Integer.parseInt(temp2)));

                                } catch (Exception e){
                                    doBusiness();
                                    e.printStackTrace();
                                }
                            }
                        }
                        Main.setaPdata(tmpData);
                        Main.setPreData1(preData);


                        Main.setFlag_pre_OK(true);

                        tmpData.clear();
                        preData.clear();
                    }
                    Main.setFlagOK(false);
                }
            } catch (Exception e) {
                doBusiness();
                e.printStackTrace();
                break;
            }
        }

    }

}
