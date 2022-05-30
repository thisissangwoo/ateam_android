package com.example.anafor.Nav_Schedule;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;

import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;

import com.example.anafor.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    MaterialCalendarView calendarView;
    ImageView imgv_my_allim_back;
    TextView tv_schedule_insert, tv_schedule_diary_date;
    LinearLayout container_schedule;
    ArrayList<CalendarDay> dates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        tv_schedule_insert = findViewById(R.id.tv_schedule_insert);
        tv_schedule_diary_date = findViewById(R.id.tv_schedule_diary_date);
        imgv_my_allim_back = findViewById(R.id.imgv_my_allim_back);
        container_schedule = findViewById(R.id.container_schedule);

        imgv_my_allim_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 뒤로가기 버튼 누르면 finish 로 끝내고 드로어블 유지
            }// onClick
        });// setOnClickListener

        // 일정 등록하기의 TextView 를 누르면
        // 작성란을 만든 ScheduleFragment1 을 보여줌
        tv_schedule_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_schedule,
                        new ScheduleFragment1(tv_schedule_diary_date.getText() + "")).commit();
            } // onClick
        }); // setOnClickListener

        calendarView = findViewById(R.id.calendarview);
        
        // 최소, 최대의 월 를
        // minusMonths, plusMonths 로 표현해줌
        calendarView.state()
                .edit()
                .setFirstDayOfWeek(DayOfWeek.of(Calendar.SATURDAY))
                .setMinimumDate(LocalDate.now().minusMonths(3))
                .setMaximumDate(LocalDate.now().plusMonths(24))
                .commit();

        // 선택된 날짜의 배경 색상
        calendarView.setSelectionColor(Color.parseColor("#696aad"));

        // 월, 일을 한글로 보이게 설정 => Strings 참고
        calendarView.setTitleFormatter(new MonthArrayTitleFormatter(getResources().getTextArray(R.array.custom_months)));
        calendarView.setWeekDayFormatter(new ArrayWeekDayFormatter(getResources().getTextArray(R.array.custom_weekdays)));
        
        select();   // 조회를 반복적으로 사용하기 위해 메소드를 만들어서 사용했음

        // addDecorators 를 이용해서 일요일, 토요일에 색상을 주고,
        // 특정일에 선택이 되게끔 데이터 넘김
        calendarView.addDecorators(new SundayDecorator(), new SaturdayDecorator(), new EventDecorator(Color.RED, dates,this));

        // 처음에는 오늘 날짜를 선택되게 해주었음
        calendarView.setSelectedDate(CalendarDay.today());

        // 원하는 날짜를 클릭시 setText 로 date 찍어주는 이벤트
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                tv_schedule_diary_date.setText(String.format(date.getYear() + "년 " + date.getMonth() + "월 " + date.getDay() + "일 " ));
                // 선택한 일정 마다 onDateSelected 를 할 때 그 해당되는 날짜의 데이터가 있는지 여부를
                // 판단하기 위해 fragment2 에서 다시 조회를 하게 한 다음,
                // 다시 조회한 데이터를 보여주게 하기 위해 getSupport 로 다시 전환 해주었음
                getSupportFragmentManager().beginTransaction().replace(R.id.container_schedule, new ScheduleFragment2(tv_schedule_diary_date.getText() + "")).commit();
            }// onDateSelected
        });// setOnDateChangedListener

    }// onCreate


    // 일요일 모양주기
    public class SundayDecorator implements DayViewDecorator {

        private final Calendar calendar = Calendar.getInstance();

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            int sunday = day.getDate().with(DayOfWeek.SUNDAY).getDayOfMonth();
            return sunday == day.getDay();
        }// shouldDecorate

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.RED));

        }// decorate
    }// SundayDecorator



    // 토요일 모양주기
    public class SaturdayDecorator implements DayViewDecorator {

        public SaturdayDecorator() {

        }// SaturdayDecorator

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            int sunday = day.getDate().with(DayOfWeek.SATURDAY).getDayOfMonth();
            return sunday == day.getDay();
        }// shouldDecorate

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.BLUE));
        }// decorate
    }// SaturdayDecorator

    // 특정일에 표시를 해주는 부분
    public class EventDecorator implements DayViewDecorator {

        private final Drawable drawable;
        private int color;
        private HashSet<CalendarDay> dates;

        public EventDecorator(int color, Collection<CalendarDay> dates, Activity context) {
            drawable = context.getResources().getDrawable(R.drawable.ic_baseline_arrow_drop_up_24);
            this.color = color;
            this.dates = new HashSet<>(dates);
        }// EventDecorator

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }// shouldDecorate

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new DotSpan(10, Color.BLUE)); // 날자 밑에 점
        }// decorate
    }// EventDecorator

    public void changeFragment(Fragment frag){
        getSupportFragmentManager().beginTransaction().replace(R.id.container_schedule, frag).commit();
    }// changeFragment

    // 특정일에 파란색 점을 찍기 위하여
    // 필요한 데이터를 DB 에서 조회를 해옴
    public void select() {
        Gson gson = new Gson();
        AskTask task = new AskTask("/schedule_select");
        task.addParam("select", "admin");
        ArrayList<ScheduleDTO> selectdate = gson.fromJson(CommonMethod.executeAskGet(task),
                new TypeToken<List<ScheduleDTO>>() {
                }.getType());

        // selectdate 의 size 가 0 이 아닐 때 점을 찍어줌
        if (selectdate.size() != 0) {
            // ex) 2022년 5월 20일을 substring 으로 공백, 한글 년, 월, 일을 trim 처리 하였음
            // 이 과정을 Eclipse 에서 테스트를 거친 코드를 가지고와서 적용 시켰음
            // dates.add 를 통해 년 월 일에 맞게 점을 찍어주도록 하였음
            for (ScheduleDTO dto : selectdate) {
                String dateData = dto.getSc_date();
                int year, month, day;
                year = Integer.parseInt(dateData.substring(0, 4));
                month = Integer.parseInt(dateData.substring(dateData.indexOf("년") + 1, dateData.indexOf("월")).trim());
                day = Integer.parseInt(dateData.substring(dateData.indexOf("월") + 1, dateData.indexOf("일")).trim());
                dates.add(CalendarDay.from(year, month, day));
            } // for
        } // if
    }
    @Override
    protected void onResume() {
        super.onResume();
        select();
    }

}// class ScheduleActivity