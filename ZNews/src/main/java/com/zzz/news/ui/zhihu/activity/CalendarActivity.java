package com.zzz.news.ui.zhihu.activity;

import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.zzz.news.R;
import com.zzz.news.base.SimpleActivity;
import com.zzz.news.component.RxBus;
import com.zzz.news.util.ZDate;
import com.zzz.news.util.ZLog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @创建者 zlf
 * @创建时间 2016/9/20 14:41
 */
public class CalendarActivity extends SimpleActivity {

    @BindView(R.id.tool_bar)
    Toolbar              mToolBar;
    @BindView(R.id.view_calender)
    MaterialCalendarView mViewCalender;
    @BindView(R.id.tv_calender_enter)
    TextView             mTvCalenderEnter;
    private CalendarDay mData;

    @Override
    protected void initEventAndData() {
        setToolBar(mToolBar,"选择日期");
        mViewCalender.state().edit()
                .setFirstDayOfWeek(Calendar.WEDNESDAY)
                .setMinimumDate(CalendarDay.from(2013, 5, 20))
                .setMaximumDate(CalendarDay.from(ZDate.getCurrentYear(), ZDate.getCurrentMonth(), ZDate.getCurrentDay()))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        mViewCalender.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                mData = date;
                ZLog.i(date.toString());
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_calendar;
    }


    @OnClick(R.id.tv_calender_enter)
    void chooseDate() {
        RxBus.getDefault().post(mData);
        onBackPressedSupport();
    }
}
