package com.kosmo.a30xmljsonparser02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    // 위젯 처리를 위한 변수
    TextView tv;
    Button btnJson;
    ListView listView;

    int[] profileImg = {
            R.drawable.actor01,R.drawable.actor02,R.drawable.actor03,
            R.drawable.actor04,R.drawable.actor05,R.drawable.actor06,
            R.drawable.actor07,R.drawable.actor08,R.drawable.actor09,
            R.drawable.actor10,R.drawable.actor11,R.drawable.actor12
    };

    // 리스트뷰에 출력할 항목을 위한 변수
    private List<ActorVO> items = new Vector<ActorVO>();
    private ActorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJson = findViewById(R.id.btn_json);
        btnJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getJsonParser();
            }
        });

    }//// onCreate()

    // 리소스에 저장된 txt파일을 IO스트림을 통해 연결한 후 내용을 읽어온다.
    private String readJsonTxt(){
        // 읽어온 내용을 저장할 변수
        String jsonData = null;
        // 리소스 폴더 하위의 raw 폴더에서 파일을 가져온다.
        InputStream inputStream=
                getResources().openRawResource(R.raw.json);
        // 파일의 내용을 읽기 위해 스트림을 생성한다.
        ByteArrayOutputStream byteArrayOutputStream =
                new ByteArrayOutputStream();
        int i;
        try {
            // 파일의 내용을 읽어옴
            i = inputStream.read();
            // 파일의 끝까지 읽으면 -1을 반환한다.
            while (i != -1){
                // 읽어온 내용을 저장한다.
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            // 저장된 내용을 문자열로 변환한다.
            jsonData = byteArrayOutputStream.toString();
            inputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return jsonData;

    }

    private void getJsonParser(){

        String jsonStr = readJsonTxt();

        Log.i("ResourcesRAW","json.txt내용 : "+jsonStr);

        try {
            // 읽어온 JSON 데이터는 객체이므로 JSONObject로 파싱한다.
            JSONObject object = new JSONObject(jsonStr);
            // key값 member는 배열 데이터를 가지므로 getJSONArray()로 파싱한다.
            JSONArray array = object.getJSONArray("member");
            // 읽어온 배열의 크기만큼 반복
            for(int i=0; i<array.length(); i++){
                // 배열의 각 요소는 객체
                JSONObject item = array.getJSONObject(i);
                // 각 key에 해당하는 value를 가져옴
                String name = item.getString("name");
                String age = item.getString("age");

                // hobby는 배열임
                JSONArray hobbyArr = item.getJSONArray("hobbys");
                String hobbys = "";
                for(int j=0; j<hobbyArr.length(); j++){
                    hobbys += hobbyArr.getString(j)+" ";
                }

                // login은 객체임
                String user = item.getJSONObject("login").getString("user");
                String pass = item.getJSONObject("login").getString("pass");
                String loginInfo = String.format("아이디 : %s 비밀번호 :%s",user,pass);
                String printStr = String.format("이름 : %s 나이 : %s 취미 : %s아이디 : %s 비밀번호 :%s"
                        ,name,age,hobbyArr,user,pass);
                Log.i("JsonParsing","정보 > "+printStr);

                /*
                파싱한 정보를 VO 객체에 지정한 후 리스트 컬렉션에 추가한다.
                해당 컬렉션에 저장된 값을 어뎁터 객체에서 데이터로 사용하게 된다.
                 */
                items.add(new ActorVO(name, age, hobbys, loginInfo, profileImg[i]));
            } //// for문

            /*
            커스텀 어뎁터 객체를 생성한다. 인자로 정보를 저장한 리스트 컬렉션과
            레이아웃을 전달한다. 어뎁터에서는 레이아웃을 전개하고, getView()에서
            리스트 컬렉션에 저장된 항목을 하나씩 반환하게 된다.
             */
            adapter = new ActorAdapter(this, items, R.layout.actor_layout);
            listView = findViewById(R.id.listview);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    // 선택한 항목에서 배우의 이름만 토스트로 출력한다.
                    Toast.makeText(getApplicationContext(),
                            "선택한 배우 : "+items.get(i).getName(),
                            Toast.LENGTH_LONG).show();
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }


    }
}