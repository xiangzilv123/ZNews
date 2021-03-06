package com.zzz.news.presenter;

import com.zzz.news.base.RxPresenter;
import com.zzz.news.model.bean.SectionListBean;
import com.zzz.news.model.http.RetrofitHelper;
import com.zzz.news.presenter.contract.SectionContract;
import com.zzz.news.util.ZRx;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 15:11
 */
public class SectionPresenter extends RxPresenter<SectionContract.View> implements SectionContract.Presenter{

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public SectionPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getSectionData() {
        Subscription rxSubscription = mRetrofitHelper.fetchSectionList()
                .compose(ZRx.<SectionListBean>rxSchedulerHelper())
                .subscribe(new Action1<SectionListBean>() {
                    @Override
                    public void call(SectionListBean sectionListBean) {
                            mView.showConent(sectionListBean);
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
