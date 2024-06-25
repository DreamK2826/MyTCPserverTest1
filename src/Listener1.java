import  java.util.Observable;
import  java.util.Observer;
public  class Listener1 implements Observer{
    @Override
    public void update(Observable o, Object arg) {
        System.out.println( "ServerThread死机" );
        MyServerThread run =  new MyServerThread();
        run.addObserver( this );
        new Thread(run).start();
        System.out.println( "ServerThread重启" );
    }
}