package cn.javadevelop.test.观察者模式;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by jianhao on 2018/4/1.
 */
public class TaoBaoObserver implements Observer{
    @Override
    public void update(Observable o, Object arg) {
        String newProduct = (String)arg;
        System.out.println("发送产品："+arg+"同步到淘宝商城");
    }
}
