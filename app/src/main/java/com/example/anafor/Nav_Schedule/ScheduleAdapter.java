package com.example.anafor.Nav_Schedule;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.R;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<ScheduleDTO> list;
    Context context;
    private Object ScheduleFragment3;

    public ScheduleAdapter(LayoutInflater inflater, ArrayList<ScheduleDTO> list, Context context) {
        this.inflater = inflater;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_schedule, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_schedule_title.setText(list.get(position).getSc_title());
        holder.tv_schedule_content.setText(list.get(position).getSc_memo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_schedule_title, tv_schedule_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_schedule_title = itemView.findViewById(R.id.tv_schedule_title);
            tv_schedule_content = itemView.findViewById(R.id.tv_schedule_content);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("원하시는 항목을 선택해주세요.");
                        builder.setSingleChoiceItems(new String[]{"수정", "삭제"}, 2, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // setSingleChoiceItems 2개인 각각의 아이템 중
                                // 0 번째 == 수정, 1 번째 == 삭제
                                if (which == 0){
                                    // 0 번째 == 수정
                                    // 액티비티를 제외한 프래그먼트, 어댑터는 본인의 출처 즉, 부모가 누구인지
                                    // 알아야 서로 넘겨주면서 사용이 가능하므로 Context 로 받아와서 사용하여야 함
                                    ((ScheduleActivity)context).changeFragment(new ScheduleFragment3(list.get(position)));
                                    dialog.dismiss();
                                }else if (which == 1){
                                    // 1 번지 == 삭제
                                    ((ScheduleActivity)context).changeFragment(new ScheduleFragment2());
                                    Toast.makeText(context.getApplicationContext(), "해당 일정이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            }
                        }).show();
                    }
                    return false;
                }
            });
        }
    }
}
