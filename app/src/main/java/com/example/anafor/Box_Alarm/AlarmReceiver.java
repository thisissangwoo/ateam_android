package com.example.anafor.Box_Alarm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import com.example.anafor.MainActivity;
import com.example.anafor.R;

import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {

    private Context context;
    private String channelId="alarm_channel";
    private int intId= 11;


    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        //푸시 알람까지는 잘뜨는데 피에조에서 소리가 안남
        //설정한 시간보다 2-3 분 늦게 푸시 알림 도착 딜레이있음

        Intent busRouteIntent = new Intent(context, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(busRouteIntent);
        PendingIntent busRoutePendingIntent =
                stackBuilder.getPendingIntent(1, PendingIntent.FLAG_CANCEL_CURRENT|PendingIntent.FLAG_UPDATE_CURRENT);

        /*final NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(context,channelId)
                .setSmallIcon(R.mipmap.ic_launcher).setDefaults(Notification.DEFAULT_ALL)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true)
                .setContentTitle(intent.getStringExtra("test") + new Date(System.currentTimeMillis() + (10 * 1000L)) )
                .setContentText("울림")
                .setContentIntent(busRoutePendingIntent);*/
        //선생님

        final NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(context,channelId)

                .setSmallIcon(R.mipmap.ic_launcher).setDefaults(Notification.DEFAULT_ALL)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true)
                .setContentTitle("알람")
                .setContentText("울림")
                .setContentIntent(busRoutePendingIntent);


        final NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel(channelId,"Channel human readable title",NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        // int id=(int)System.currentTimeMillis(); <= 공지할때 여러건이 노출되게 하려면 사용하면 됨.

        //notificationManager.notify(intId,notificationBuilder.build());            선생님\

        int id=(int)System.currentTimeMillis();


        notificationManager.notify(id,notificationBuilder.build());

        ////////////////////////////////////////////////////////////////////////////////////////////////////

        context.sendBroadcast(new Intent("sendData"));

        /*Activity activity = (MainActivity) context;

        activity.sendData("g");         // 상태 확인문자 우노보드로 보내기
        try {
            Thread.sleep(1000); // 잠시대기
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        activity.beginListenForData();  // 상태 확인*/



        /*if (){
            ((MainActivity)context).sendData("g");         // 상태 확인문자 우노보드로 보내기
            try {
                Thread.sleep(1000); // 잠시대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ((MainActivity)context).beginListenForData();  // 상태 확인
        }*/



    }

}