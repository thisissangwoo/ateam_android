package com.example.anafor.Nav_Schedule;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;

import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.anafor.Hp_List.Hp_ListFragment;
import com.example.anafor.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

public class ScheduleActivity extends AppCompatActivity {

    MaterialCalendarView calendarView;
    ImageView imgv_my_allim_back;
    TextView tv_schedule_insert, tv_schedule_diary_date;
    ArrayList<ScheduleDTO> list = new ArrayList<>();

    //int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        tv_schedule_insert = findViewById(R.id.tv_schedule_insert);
        tv_schedule_diary_date = findViewById(R.id.tv_schedule_diary_date);
        imgv_my_allim_back = findViewById(R.id.imgv_my_allim_back);

        // 뒤로가기 버튼 누르면 finish 로 끝내고 드로어블 유지
        imgv_my_allim_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_schedule_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_schedule, new ScheduleFragment1(tv_schedule_diary_date.getText() + "")).commit();
//                cnt ++;
//                if (cnt % 2 == 1){
//                    getSupportFragmentManager().beginTransaction().replace(R.id.container_schedule, new ScheduleFragment1()).commit();
//                }else{
//                    cnt = 0;
//                }
            }
        });

        calendarView = findViewById(R.id.calendarview);

        calendarView.state()
                .edit()
                .setFirstDayOfWeek(DayOfWeek.of(Calendar.SATURDAY))
                .setMinimumDate(LocalDate.now().minusMonths(3)) // 최소 표현 달
                .setMaximumDate(LocalDate.now().plusMonths(24))  // 최대 표현 달
                .commit();

        calendarView.setSelectionColor(Color.parseColor("#696aad")); // 선택된 날짜 색상주기

        // 월, 요일을 한글로 보이게 설정 => Strings 참고
        calendarView.setTitleFormatter(new MonthArrayTitleFormatter(getResources().getTextArray(R.array.custom_months)));
        calendarView.setWeekDayFormatter(new ArrayWeekDayFormatter(getResources().getTextArray(R.array.custom_weekdays)));

        // 특정일에 파란색 점을 찍기 위해여 필요한 데이터 나중에 DB 에서 가지고 오면 될 것 같음
        // 하루 , 이틀 전에 대하여 파란점을 찍게 만듬
        ArrayList<CalendarDay> dates = new ArrayList<>();
        dates.add(CalendarDay.from(LocalDate.now().minusDays(1)));
        dates.add(CalendarDay.from(LocalDate.now().minusDays(2)));

        // addDecorators 를 이용해서 일요일 , 토요일에 색을 주고 , 특정일 ↑ 선택이 되게끔 데이터 넘김
        calendarView.addDecorators(new SundayDecorator(),new SaturdayDecorator() , new EventDecorator(Color.RED, dates,this));
        calendarView.setSelectedDate(CalendarDay.today()); // 처음에 오늘 날짜를 선택하게 해둠

        // 선택한 날짜랑 DB 에서 가져온 날짜랑 비교했을 때 있으면 없으면 비교 -예정-
        // 수정하기 누르면 모달창 띄우기 -예정-
        // 원하는 날짜를 클릭시 setText 로 date 찍어주는 이벤트
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                tv_schedule_diary_date.setText(String.format(date.getYear() + "년 " + date.getMonth() + "월 " + date.getDay() + "일 " ));
            }
        });
    }

    // 일요일 모양주기
    public class SundayDecorator implements DayViewDecorator {

        private final Calendar calendar = Calendar.getInstance();

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            int sunday = day.getDate().with(DayOfWeek.SUNDAY).getDayOfMonth();
            return sunday == day.getDay();
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.RED));

        }
    }

    // 토요일 모양주기
    public class SaturdayDecorator implements DayViewDecorator {

        public SaturdayDecorator() {

        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            int sunday = day.getDate().with(DayOfWeek.SATURDAY).getDayOfMonth();
            return sunday == day.getDay();
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.BLUE));
        }
    }

    // 특정일 모양주기
    public class EventDecorator implements DayViewDecorator {

        private final Drawable drawable;
        private int color;
        private HashSet<CalendarDay> dates;

        public EventDecorator(int color, Collection<CalendarDay> dates, Activity context) {
            drawable = context.getResources().getDrawable(R.drawable.ic_baseline_arrow_drop_up_24);
            this.color = color;
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new DotSpan(10, Color.BLUE)); // 날자 밑에 점
            // 날짜에 텍스트? 리스트가 null 이 아니면 ~ 점 찍어주기?
            //if ()
        }
    }

    public void changeFragment(Fragment frag){
        getSupportFragmentManager().beginTransaction().replace(R.id.container_schedule, frag).commit();
    }
}