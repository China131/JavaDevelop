package cn.javadevelop.test.观察者模式;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by jianhao on 2018/4/1.
 */
public class ProductList extends Observable{
    private List<String>productList = null;
    private static ProductList instance;//类唯一实例 单例
//    private ProductList(){}//构建方法私有化

    public ProductList(){}

    /**
     * 取得唯一实例
     * @return
     */
    public static ProductList getInstance(){
        if (instance == null){
            instance = new ProductList();
            instance.productList = new ArrayList<String>();
        }
        return instance;
    }

    /**
     * 增加观察者
     * @param observer
     */
    public void addProductListObserver(Observer observer){
        this.addObserver(observer);
    }

    public void addProduct(String newProduct){
        productList.add(newProduct);
        System.out.println("产品列表新增了产品："+newProduct);
        this.setChanged();//设置被观察对象发生了变化
        this.notifyObservers(newProduct);
    }


    @Test
    public void observer(){
        ProductList observer = ProductList.getInstance();
        TaoBaoObserver taoBaoObserver = new TaoBaoObserver();
        JingDongObserver jingDongObserver = new JingDongObserver();
        observer.addObserver(taoBaoObserver);
        observer.addObserver(jingDongObserver);
        observer.addProduct("新增产品1");
    }





























}
