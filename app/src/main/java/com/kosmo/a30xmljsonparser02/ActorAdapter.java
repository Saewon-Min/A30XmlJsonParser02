package com.kosmo.a30xmljsonparser02;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

// BaseAdapter 클래스를 상속받아 커스텀 어뎁터를 정의한다.
public class ActorAdapter extends BaseAdapter {

    // 메인액티비티에서 사용하기 위한 컨텍스트
    private Context context;
    // 어뎁터에서 사용할 데이터를 저장해놓은 컬렉션
    private List<ActorVO> items;
    // 커스텀 레이아웃의 리소스 아이디
    private int layoutResId;


    public ActorAdapter(Context context, List<ActorVO> items, int layoutResId) {
        this.context = context;
        this.items = items;
        this.layoutResId = layoutResId;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    // 하나의 항목을 표현하는 메소드
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // 생성된 커스텀 레이아웃이 없다면 inflate()한다.
        if(view==null){
            view = View.inflate(context, layoutResId, null);
        }

        // 커스텀뷰에서 위젯 가져오기
        TextView tvName = view.findViewById(R.id.tv_name);
        TextView tvAge = view.findViewById(R.id.tv_age);
        TextView tvHobbys = view.findViewById(R.id.tv_hobbys);
        TextView tvLogin = view.findViewById(R.id.tv_login);
        ImageView profileImg = view.findViewById(R.id.imageView);

        // 컬렉션에 저장된 값을 통해 각 위젯을 설정한다.
        tvName.setText(items.get(i).getName());
        tvAge.setText(items.get(i).getAge());
        tvHobbys.setText(items.get(i).getHobbys());
        tvLogin.setText(items.get(i).getLogin());
        profileImg.setImageResource(items.get(i).getProfileImg());

        // 리스트뷰에 스트라이프 효과를 주기 위한 연산
        if(i%2==0){
            view.setBackgroundColor(0x99dadada);
        }else{
            view.setBackgroundColor(0x99ffffff);
        }


        return view;
    }
}
