import java.util.ArrayList;
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
        List<APdata> tmpData = new ArrayList<APdata>();
        // 此方法一经调用，立马可以通知观察者，在本例中是监听线程


        while(true) {
            try {
                if(Main.isFlagOK()){
                    System.out.println("FLAG_OK\r\n");
                    //切割处理部分
                    List<String> ls1 = Main.getStrList2();
                    if(ls1 != null){
                        for (int i = 0; i < ls1.size(); i++) {
                            if(i == 1){
//                                System.out.println(strList2.get(i).substring(5));
                                System.out.println(ls1.get(i));
                            }
                            if(i == 2){
//                                System.out.println(strList2.get(i).substring(5));
                                System.out.println(ls1.get(i));
                            }
                            if(i == 3){
//                                System.out.println(strList2.get(i).substring(5));
                                System.out.println(ls1.get(i));

                            }
                            if(i == 4){
                                System.out.println(ls1.get(i));
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
                                    tmpData.add(new APdata(temp1,Integer.parseInt(temp2)));
                                } catch (Exception e){
                                    doBusiness();
                                    e.printStackTrace();

                                }
                            }
                        }
                        Main.setaPdata(tmpData);
                        tmpData.clear();
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
