package com.leekh13.myexercise;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import static com.leekh13.myexercise.R.*;


public class MainActivity extends Activity {

    Spinner spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        Init();
    }

    protected void Init(){

        //spinner
        spinner = (Spinner) findViewById(id.spinner);
        //spinner.setPrompt("골라바");
        ArrayAdapter<CharSequence>  adapter =
                ArrayAdapter.createFromResource( this, array.interest_array, android.R.layout.simple_spinner_item );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        TabHost tabHost = (TabHost) findViewById(id.tabHost );
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tab1");
        tabSpec.setIndicator("기록입력");
        tabSpec.setContent(id.tab1);
        tabHost.addTab(tabSpec);

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("tab2");
        tabSpec2.setIndicator("운동기록");
        tabSpec2.setContent(id.tab2);
        tabHost.addTab(tabSpec2);

        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("tab3");
        tabSpec3.setIndicator("설정");
        tabSpec3.setContent(id.tab3);
        tabHost.addTab(tabSpec3);

        tabHost.setCurrentTab(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onClick(View view) {
        switch (view.getId())
        {
            case id.button:
                //버튼을 눌렀을때의 처리
            Toast.makeText(this, "토스트메시지", Toast.LENGTH_SHORT).show();
            break;
        }

    }
}
