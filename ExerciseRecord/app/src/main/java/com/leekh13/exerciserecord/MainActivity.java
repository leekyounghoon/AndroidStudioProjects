package com.leekh13.exerciserecord;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import static android.widget.Toast.*;


public class MainActivity extends ActionBarActivity {

    Spinner spinner;

    static DBManager dbManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //db생성
        dbManager = new DBManager(getApplicationContext(), "Exercise.db", null, 1);

        //기본 날짜 입력
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
        Date currentTime = new Date();
        String strTime = simpleDate.format(currentTime);
        EditText edittext = (EditText) findViewById(R.id.editText);
        edittext.setText(strTime, TextView.BufferType.EDITABLE);

        spinner = (Spinner) findViewById(R.id.spinner);

        Spinner_Init();
    }

    @Override
    protected void onResume(){
        super.onResume();

        Spinner_Init();
    }

    public void Spinner_Init()
    {
        ArrayList<String> entries = new ArrayList<String>();

        dbManager.select_EXER_LIST(entries);

        if( entries.isEmpty() )
            entries.add("Setting 에서 운동 값을 넣으세요");

        ArrayAdapter<String> arrAdapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item , entries);

        arrAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrAdapt);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //menu.add(0, 0, Menu.NONE, "기록입력");
        //menu.add(0, 1, Menu.NONE , "기록");

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);

            return true;
        }

        if( id == R.id.action_record ){
            Intent intent = new Intent(this, RecordActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button:   //기록 입력 버튼 눌렸을때
            {
                EditText editText = (EditText) findViewById(R.id.editText);
                String strDate = editText.getText().toString();

                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                String strName = spinner.getSelectedItem().toString();

                String strKg = ((EditText) findViewById(R.id.editText4)).getText().toString();

                String strCount = ((EditText) findViewById(R.id.editText2)).getText().toString();

                int intensity = ((RatingBar) findViewById(R.id.ratingBar)).getNumStars();

                dbManager.insert("insert into EXER_RECORD(  exer_date , exer_name, exer_kg, exer_count , exer_intensity ) values(" +
                        "'" + strDate + "', '" + strName + "', " + strKg + "," + strCount + ", " + intensity + ")");
                Toast.makeText(this, "메시지 날짜:"+ strDate + " 운동명:" + strName + " 무게:" + strKg + " 횟수:" + strCount + " 강도:" + intensity , LENGTH_SHORT).show();



            }


                break;
        }
    }




}
