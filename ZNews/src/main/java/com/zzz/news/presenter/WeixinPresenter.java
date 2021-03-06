package com.zzz.news.presenter;

import com.zzz.news.base.RxPresenter;
import com.zzz.news.model.bean.WeixinBean;
import com.zzz.news.model.http.JuHeHttpResponse;
import com.zzz.news.model.http.RetrofitHelper;
import com.zzz.news.presenter.contract.WeixinContract;
import com.zzz.news.util.ZLog;
import com.zzz.news.util.ZRx;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * @创建者 zlf
 * @创建时间 2016/9/28 18:08
 */

public class WeixinPresenter extends RxPresenter<WeixinContract.View> implements WeixinContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    private int ps = 20;
    private int pno = 1;

    @Inject
    public WeixinPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getWeixinData() {
        pno = 1;
        Subscription rxSubscription = mRetrofitHelper.fetchWeixinList(ps,pno)
                .compose(ZRx.<JuHeHttpResponse<WeixinBean.ResultBean>>rxSchedulerHelper())
                .compose(ZRx.<WeixinBean.ResultBean>handleJhResult())
                .subscribe(new Action1<WeixinBean.ResultBean>() {
                    @Override
                    public void call(WeixinBean.ResultBean resultBean) {
                        mView.showContent(resultBean.getList());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError(throwable.toString());
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getMoreData() {
        ps = ps + 20;
        ZLog.i(pno+"");
        Subscription rxSubscription = mRetrofitHelper.fetchWeixinList(ps,pno)
                .compose(ZRx.<JuHeHttpResponse<WeixinBean.ResultBean>>rxSchedulerHelper())
                .compose(ZRx.<WeixinBean.ResultBean>handleJhResult())
                .subscribe(new Action1<WeixinBean.ResultBean>() {
                    @Override
                    public void call(WeixinBean.ResultBean resultBean) {
                        mView.showMoreContent(resultBean.getList());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError(throwable.toString());
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getNewContent() {
        Subscription rxSubscription = mRetrofitHelper.fetchWeixinList(ps,++pno)
                .compose(ZRx.<JuHeHttpResponse<WeixinBean.ResultBean>>rxSchedulerHelper())
                .compose(ZRx.<WeixinBean.ResultBean>handleJhResult())
                .subscribe(new Action1<WeixinBean.ResultBean>() {
                    @Override
                    public void call(WeixinBean.ResultBean resultBean) {
                        mView.showNewContent(resultBean.getList());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError(throwable.toString());
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
