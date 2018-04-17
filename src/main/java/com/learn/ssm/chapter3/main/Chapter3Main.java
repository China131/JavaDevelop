package com.learn.ssm.chapter3.main;


import com.learn.ssm.chapter3.mapper.RoleMapper;
import com.learn.ssm.chapter3.mapper.WeiBoMapper;
import com.learn.ssm.chapter3.pojo.Role;
import com.learn.ssm.chapter3.pojo.WeiBoModel;
import com.learn.ssm.chapter3.pojo.WeiBoResult;
import com.learn.ssm.chapter3.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jianhao on 2018/4/6.
 */
public class Chapter3Main {
    @Test
    public  void mains(){
//        this.initResult();
        //发布微博数量
//          getPublicCOunt();
//        getAllCommendCount();
        //获取平均微博转发数量
//        getAllRedirectCount();
        //获取平均微博点赞数量
//        getAllFavoriteCount();
        getCacheData();
    }

    public void getCacheData(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtils.openSqlSession();
            WeiBoMapper roleMapper = sqlSession.getMapper(WeiBoMapper.class);
            List<WeiBoModel>list = roleMapper.selectTest();
            long size = list.size();
            System.out.printf("size = "+size);

            long aimLength = size / 10;
            System.out.printf("aimLength = "+aimLength);

            List<String> list1 = new ArrayList<>();
            List<WeiBoModel> retModel = new ArrayList<>();
            while (list1.size() < aimLength){
                Random random = new Random();
                int index = random.nextInt(Integer.valueOf(size+"").intValue());
                if (list1.contains(index+"")){
                    continue;
                }
                WeiBoModel model = list.get(index);
                retModel.add(model);
                list1.add(index+"");
            }
            for (WeiBoModel model:retModel) {
                try {
                    roleMapper.insertIntoCache1(model);
                    sqlSession.commit();
                }catch (Exception e){
                    System.out.printf("");
                }
            }


            List<String> list2 = new ArrayList<>();
            List<WeiBoModel> retModel2 = new ArrayList<>();
            while (list2.size() < 100){
                Random random = new Random();
                int index = random.nextInt(Integer.valueOf(aimLength+"").intValue());
                if (list2.contains(index+"")){
                    continue;
                }
                WeiBoModel model = retModel.get(index);
                retModel2.add(model);
                list2.add(index+"");
            }

            for (WeiBoModel model:retModel2) {
                try {
                    roleMapper.insertIntoCache2(model);
                    sqlSession.commit();
                }catch (Exception e){
                    System.out.printf("");
                }
            }


            System.out.printf("");

        }catch (Exception exps){
            System.out.println("");
        }finally {
            if (sqlSession != null){
                sqlSession.close();
            }
        }
    }

    public void getAllFavoriteCount(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtils.openSqlSession();
            WeiBoMapper roleMapper = sqlSession.getMapper(WeiBoMapper.class);
            List<WeiBoResult>list = roleMapper.selectResult();
            for (WeiBoResult model:list){
                try {
                    long size = roleMapper.selectFavoriteCount(model.getUid());
                    model.setFavoriteAllCount(size);
                    model.setFavorite(Double.valueOf(size) / Long.valueOf(model.getPublicCount()).doubleValue());
                    roleMapper.updateResult(model);
                    System.out.printf("size = "+size);
                }catch (Exception exp){
                    System.out.println("插入失败");
                }
                sqlSession.commit();
            }
            System.out.println("");

        }finally {
            if (sqlSession != null){
                sqlSession.close();
            }
        }
    }

    public void getAllRedirectCount(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtils.openSqlSession();
            WeiBoMapper roleMapper = sqlSession.getMapper(WeiBoMapper.class);
            List<WeiBoResult>list = roleMapper.selectResult();
            for (WeiBoResult model:list){
                try {
                    long size = roleMapper.selectRedirectCount(model.getUid());
                    model.setRedirectAllCount(size);
                    model.setRedirectCount(Double.valueOf(size) / Long.valueOf(model.getPublicCount()).doubleValue());
                    roleMapper.updateResult(model);
                    System.out.printf("size = "+size);
                }catch (Exception exp){
                    System.out.println("插入失败");
                }
                sqlSession.commit();
            }
            System.out.println("");

        }finally {
            if (sqlSession != null){
                sqlSession.close();
            }
        }
    }

    public void getAllCommendCount(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtils.openSqlSession();
            WeiBoMapper roleMapper = sqlSession.getMapper(WeiBoMapper.class);
            List<WeiBoResult>list = roleMapper.selectResult();
            for (WeiBoResult model:list){
                try {
                    long size = roleMapper.selectCommendAllCount(model.getUid());
                    model.setCommendAllCount(size);
                    model.setCommendCount(Double.valueOf(size) / Long.valueOf(model.getPublicCount()).doubleValue());
                    roleMapper.updateResult(model);
                    System.out.printf("size = "+size);
                }catch (Exception exp){
                    System.out.println("插入失败");
                }
                sqlSession.commit();
            }
            System.out.println("");

        }finally {
            if (sqlSession != null){
                sqlSession.close();
            }
        }
    }

    public void getPublicCOunt(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtils.openSqlSession();
            WeiBoMapper roleMapper = sqlSession.getMapper(WeiBoMapper.class);
            List<WeiBoResult>list = roleMapper.selectResult();
            for (WeiBoResult model:list){
                try {
                    long size = roleMapper.selectPublishCount(model.getUid());
                    model.setPublicCount(size+"");
                    roleMapper.updateResult(model);
                }catch (Exception exp){
                    System.out.println("插入失败");
                }
                sqlSession.commit();
                System.out.println("success");
            }
            System.out.println("");

        }finally {
            if (sqlSession != null){
                sqlSession.close();
            }
        }
    }


    public void initResult(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtils.openSqlSession();
            WeiBoMapper roleMapper = sqlSession.getMapper(WeiBoMapper.class);
            List<WeiBoModel>list = roleMapper.selectTest();
            for (WeiBoModel model:list){
                WeiBoResult result = new WeiBoResult();
                result.setUid(model.getIR_UID());
                result.setNickName(model.getIR_SCREEN_NAME());
                try {
                    roleMapper.initResult(result);
                }catch (Exception exp){
                    System.out.println("插入失败");
                }
                sqlSession.commit();
                System.out.println("success");
            }
            System.out.println("");

        }finally {
            if (sqlSession != null){
                sqlSession.close();
            }
        }
    }
}
