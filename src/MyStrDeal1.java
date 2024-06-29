import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

public class MyStrDeal1 extends Observable implements Runnable{

    public void doBusiness() {
        super.setChanged();
        notifyObservers();
    }

    @Override
    public void run() {

        SqliteHelper sqliteHelper;
        String name = "null",POS1 = "null",POS2 = "null",ARG1 = "0",ARG2 = "0";
        try {
            sqliteHelper = new SqliteHelper(Main.getDbFilePath());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        String createUser = "create table if not exists singleTable1(id integer primary key autoincrement,name text,POS1 text,POS2 text,SSID text,RSSI text,ARG1 text,ARG2 text)";
        try {
            sqliteHelper.executeUpdate(createUser);
        } catch (SQLException | ClassNotFoundException e) {
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

                            }
                            if(i == 2){
                                //POS1
//                                System.out.println(strList2.get(i).substring(5));
                                System.out.println(ls1.get(i));
                                POS1 = ls1.get(i).substring(5);

                            }
                            if(i == 3){
                                //POS2
//                                System.out.println(strList2.get(i).substring(5));
                                System.out.println(ls1.get(i));
                                POS2 = ls1.get(i).substring(5);

                            }
                            if(i == 4){
                                //特殊参数1
                                System.out.println(ls1.get(i));
                                ARG1 = ls1.get(i).substring(5);

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

                                } catch (Exception e){
                                    doBusiness();
//                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    Main.setFlagOK(false);
                }
            } catch (Exception e) {
                doBusiness();
//                e.printStackTrace();
                break;
            }
        }

    }

}
