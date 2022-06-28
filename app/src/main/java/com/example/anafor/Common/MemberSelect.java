package com.example.anafor.Common;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import com.example.anafor.Hp_Hash.HpDTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MemberSelect extends AsyncTask<Void, Void, InputStreamReader> {
    private static final String TAG = "main:MemberSelect";

    // 생성자를 만들어서 데이터를 받는다
    //Context context;  // 필요하면 받는다
    ArrayList<HpDTO> dtos;
    String query;
    //MemberAdapter adapter;

    //    public MemberSelect(ArrayList<HpDTO> dtos, MemberAdapter adapter) {
//        this.dtos = dtos;
//        //this.adapter = adapter;
//    }
    public MemberSelect(ArrayList<HpDTO> dtos, String query) {
        this.dtos = dtos;
        this.query = query;
        //this.adapter = adapter;
    }

    // 반드시 선언해야 할것들 : 무조건 해야함  복,붙
    HttpClient httpClient;       // 클라이언트 객체
    HttpPost httpPost;           // 클라이언트에 붙일 본문
    HttpResponse httpResponse;   // 서버에서의 응답받는 부분
    HttpEntity httpEntity;       // 응답내용

    // 실질적으로 일을하는 부분 : 접근 못함(textView, button)
    @Override
    protected InputStreamReader doInBackground(Void... voids) {

        try {
            // MultipartEntityBuilder 생성 : 무조건 해야함  복,붙
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            // 여기가 우리가 수정해야 하는 부분 : 서버로 보내는 데이터
            // builder에 문자열 및 데이터 추가하기
            builder.addTextBody("query", query, ContentType.create("Multipart/related", "UTF-8"));


            // 전송
            // 전송 url : 우리가 수정해야 하는 부분
            String postURL = CommonVal.HTTP_IP + "/anafor/hash";
            // 그대로 사용  복,붙
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);  // 보내고 응답 받는 부분
            httpEntity = httpResponse.getEntity();    // 응답내용을 저장
            inputStream = httpEntity.getContent();    // 응답내용을 inputStream 에 넣음

            // 데이터가 ArrayList<DTO> 형태일때
            return new InputStreamReader(inputStream);


        }catch (Exception e){
            e.getMessage();
        }finally {
            if(httpEntity != null){
                httpEntity = null;
            }
            if(httpResponse != null){
                httpResponse = null;
            }
            if(httpPost != null){
                httpPost = null;
            }
            if(httpClient != null){
                httpClient = null;
            }
        }

        return null;
    }

//    @Override
//    protected void onPostExecute(Void unused) {
//        super.onPostExecute(unused);
//
//        Log.d(TAG, "onPostExecute: List Select Complete !!!" );
//
//        // 데이터가 새로 삽입되어서 화면 갱신을 해준다
//        //adapter.notifyDataSetChanged();
//    }

//    public void readJsonStream(InputStream inputStream) throws IOException {
//        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
//        try {
//            reader.beginArray();
//            while (reader.hasNext()) {
//                dtos.add(readMessage(reader));
//            }
//            reader.endArray();
//        } finally {
//            Log.d(TAG, "readJsonStream:" + dtos.size());
//            reader.close();
//        }
//    }
//
//    public HpDTO readMessage(JsonReader reader) throws IOException {
//        String hp_code = "", hp_name = "", type_code = "", sido_code="",  sido_name="", sigungu_code="", sigungu_name="", hp_addr="", hp_tel="", hp_url="", hp_x="", hp_y="" ;
//
//        reader.beginObject();
//        while (reader.hasNext()) {
//            String readStr = reader.nextName();
//            if (readStr.equals("hp_code")) {
//                hp_code = reader.nextString();
//            } else if (readStr.equals("hp_name")) {
//                hp_name = reader.nextString();
//            } else if (readStr.equals("type_code")) {
//                type_code = reader.nextString();
//            } else if (readStr.equals("sido_code")) {
//                sido_code = reader.nextString();
//            }else if (readStr.equals("sigungu_code")) {
//                sigungu_code = reader.nextString();
//            }else if (readStr.equals("sigungu_name")) {
//                sigungu_name = reader.nextString();
//            }else if (readStr.equals("hp_addr")) {
//                hp_addr = reader.nextString();
//            }else if (readStr.equals("hp_tel")) {
//                hp_tel = reader.nextString();
//            }else if (readStr.equals("hp_url")) {
//                hp_url = reader.nextString();
//            }else if (readStr.equals("hp_x")) {
//                hp_x = reader.nextString();
//            }else if (readStr.equals("hp_y")) {
//                hp_y = reader.nextString();
//            }else {
//                reader.skipValue();
//            }
//        }
//        reader.endObject();
//        Log.d(TAG, hp_code + "," + hp_name + "," + type_code + "," + sido_code + "," + sido_name);
//        return new HpDTO(hp_code, hp_name, type_code, sido_code, sido_name, sigungu_code, sigungu_name, hp_addr ,hp_tel ,hp_url ,hp_x ,hp_y);
//    }

}
