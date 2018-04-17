package com.learn.ssm.chapter3.pojo;

/**
 * Created by jianhao on 2018/4/16.
 */
public class WeiBoResult {
    private Long uid;
    private String nickName;
    //发布数量
    private String publicCount;
    //平均评论数量
    private Double commendCount;
    private Long commendAllCount;
    private Long originPublicCount;
    private Double redirectCount;
    private Double favorite;
    private Long favoriteAllCount;
    private Long redirectAllCount;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPublicCount() {
        return publicCount;
    }

    public void setPublicCount(String publicCount) {
        this.publicCount = publicCount;
    }

    public Double getCommendCount() {
        return commendCount;
    }

    public void setCommendCount(Double commendCount) {
        this.commendCount = commendCount;
    }

    public Long getCommendAllCount() {
        return commendAllCount;
    }

    public void setCommendAllCount(Long commendAllCount) {
        this.commendAllCount = commendAllCount;
    }

    public Long getOriginPublicCount() {
        return originPublicCount;
    }

    public void setOriginPublicCount(Long originPublicCount) {
        this.originPublicCount = originPublicCount;
    }

    public Double getRedirectCount() {
        return redirectCount;
    }

    public void setRedirectCount(Double redirectCount) {
        this.redirectCount = redirectCount;
    }

    public Double getFavorite() {
        return favorite;
    }

    public void setFavorite(Double favorite) {
        this.favorite = favorite;
    }

    public Long getFavoriteAllCount() {
        return favoriteAllCount;
    }

    public void setFavoriteAllCount(Long favoriteAllCount) {
        this.favoriteAllCount = favoriteAllCount;
    }

    public Long getRedirectAllCount() {
        return redirectAllCount;
    }

    public void setRedirectAllCount(Long redirectAllCount) {
        this.redirectAllCount = redirectAllCount;
    }
}
