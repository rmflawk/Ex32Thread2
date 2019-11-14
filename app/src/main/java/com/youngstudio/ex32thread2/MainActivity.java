package com.youngstudio.ex32thread2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    MyThread thread;


    @Override
    protected void onCreate(Bundle savedInstanceState) { // 화면에 안보일때
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onStart() { //화면에 보이기 시작할때
        super.onStart();
    }


    @Override
    protected void onResume() { //눌러지는 상황이 될때
        super.onResume();
    }


    //이 액티비티가 화면에서 안보이기 시작할때 자동 실행
    @Override
    protected void onPause() {
        super.onPause();
    }


    //이 액티비티가 아예 화면에서 안보이면
    @Override
    protected void onStop() {
        if(thread!=null){
            thread.stopThread();
        }
        super.onStop();
    }


    //이 액티비티가 메모리에서 없어지면
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    //뒤로가기 버튼을 눌렀을 때 발동하는 메소드
    @Override
    public void onBackPressed() {
        //Thread를 멈추기!!
        //이 메소드는 run메소드의 while가 무한반복중이라서 while break해야 run이 종료
        //thread.isRun= false;
//        if(thread!=null){
//            thread.stopThread();
//        }
        super.onBackPressed();
    }

    public void clickBtn(View view) {
        //5초에 한번씩 현재시간을 Toast로 출력
        thread=new MyThread();
        thread.start();

    }

    //이너클래스
    class MyThread extends Thread{

        boolean isRun= true;

        @Override
        public void run() {
            while(isRun){

                //현재시간
                Date now= new Date();
                final String s= now.toString();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //이 안에서는 UI변경 가능
                        Toast.makeText(MainActivity.this, s ,Toast.LENGTH_SHORT ).show();
                    }
                });

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }//while

        }//run method

        void stopThread(){
            isRun=false;
        }


    }//MyThreadclass


}// MainActivity class



