package cn.javadevelop.test.观察者模式;


import java.util.Observable;
import java.util.Observer;

/**
 * Created by jianhao on 2018/4/1.
 */
public class JingDongObserver implements Observer{
    @Override
    public void update(Observable o, java.lang.Object arg) {
        String newProduct = (String)arg;
        System.out.println("发送新产品："+newProduct+"同步到京东商城");
    }

}
