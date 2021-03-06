package com.zzz.news.model.http;

import com.zzz.news.model.bean.JokeBean;
import com.zzz.news.model.bean.LishiBean;
import com.zzz.news.model.bean.RobotBean;
import com.zzz.news.model.bean.TopNewsBean;
import com.zzz.news.model.bean.WeixinBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @创建者 zlf
 * @创建时间 2016/9/26 17:22
 */

public interface JuHeApis {

    String HOST = "http://v.juhe.cn/";

    String HOST_JOKE = "http://japi.juhe.cn/";

    String HOST_ROBOT = "http://op.juhe.cn/";

    String HOST_LISHI = "http://api.juheapi.com/";
    /**
     * 头条
     * http://v.juhe.cn/toutiao/index?type=top&key=bd8e0a7114136f806abe023bfd16d4d6
     * @return
     */
    @GET("toutiao/index?type=top&key=bd8e0a7114136f806abe023bfd16d4d6")
    Observable<JuHeHttpResponse<TopNewsBean.ResultBean>> getTopNewsList();


    /**
     * 笑话
     * http://v.juhe.cn/toutiao/index?type=top&key=bd8e0a7114136f806abe023bfd16d4d6
     * @return
     */
    @GET("joke/content/list.from")
    Observable<JuHeHttpResponse<JokeBean.ResultBean>> getJokeList(@Query("key")String key,@Query("page")int page,@Query("pagesize")int pagesize,@Query("sort")String sort,@Query("time")String time);

    /**
     * 微信精选
     * http://japi.juhe.cn/joke/content/list.from?key=189df93e385f308c4ca8c71c4bc7fbae&page=2&pagesize=10&sort=asc&time=1418745237
     * @return
     */
    @GET("weixin/query")
    Observable<JuHeHttpResponse<WeixinBean.ResultBean>> getWeixinList(@Query("key") String key,@Query("ps")int ps,@Query("pno")int pno);

    /**
     * 机器人
     * http://op.juhe.cn/robot/index?info=%E8%A0%A2%E8%B4%A7&key=647657f24cb2146d82a0663404bc0d16
     * @return
     */
    @GET("robot/index")
    Observable<JuHeHttpResponse<RobotBean.ResultBean>> getRobotInfo(@Query("info") String info, @Query("key") String key);

    /**
     * 历史上的今天
     * http://api.juheapi.com/japi/toh?key=您申请的KEY&v=1.0&month=11&day=1
     * @return
     */
    @GET("japi/toh")
    Observable<JuHeHttpResponse<List<LishiBean.ResultBean>>>getLishiInfo(@Query("key")String key, @Query("v")String v, @Query("month")int month, @Query("day")int day);

}
