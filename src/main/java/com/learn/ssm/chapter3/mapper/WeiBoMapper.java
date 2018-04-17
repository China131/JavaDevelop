package com.learn.ssm.chapter3.mapper;

import com.learn.ssm.chapter3.pojo.WeiBoModel;
import com.learn.ssm.chapter3.pojo.WeiBoResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jianhao on 2018/4/16.
 */
public interface WeiBoMapper {
   public List<WeiBoModel> selectTest();
   public List<WeiBoResult> selectResult();
   //获取用户的发布数量
   public long selectPublishCount(@Param("id") long id);
   //获取用户原创数量
   public long selectOriginPublishCount(@Param("id") long id);
   //获取用户转发数量
   public long selectRedirectPublishCount(@Param("id") long id);
   //获取用户评论总数
   public long selectCommendAllCount(@Param("id") long id);
   //获取用户转发总数
    public long selectRedirectCount(@Param("id") long id);
   //获取用户点赞总数
   public long selectFavoriteCount(@Param("id") long id);
   public int initResult(WeiBoResult model);
   public int updateResult(WeiBoResult model);
   public int insertIntoCache1(WeiBoModel model);
    public int insertIntoCache2(WeiBoModel model);

}
