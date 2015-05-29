package com.leekh13.exerciserecord;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by leekh on 2015-05-08.
 */
public class SettingActivity extends Activity {

    private ListView m_ListView;

    private ArrayAdapter<String> m_Adapter;

    private  ArrayList<String> m_entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        Refresh();

    }

    public void Refresh(){
        m_entry = new ArrayList<String>();

        MainActivity.dbManager.select_EXER_LIST(m_entry);
        m_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, m_entry );

        m_ListView = (ListView) findViewById(R.id.listView);

        m_ListView.setAdapter(m_Adapter);
        m_ListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }

    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button2:
                String Name = ((EditText) findViewById(R.id.editText3)).getText().toString();
                MainActivity.dbManager.insert("insert into EXER_LIST( exer_name ) values('" + Name + "')");
                Toast.makeText(   this , Name + "입력 완료" , Toast.LENGTH_SHORT).show();
                Refresh();
                break;
            case R.id.button3:
                finish();
                break;
            case R.id.listView:

                break;
            case R.id.button4:
                long Position = ((ListView) findViewById(R.id.listView)).getCheckedItemPosition();
                String exerName = m_entry.get((int) Position);

                MainActivity.dbManager.delete("delete from EXER_LIST where exer_name = '" + exerName + "'");

                Toast.makeText(   this , exerName + " 삭제 합니다.", Toast.LENGTH_SHORT).show();

                m_entry.remove((int) Position);
                m_Adapter.notifyDataSetChanged();

                break;

        }

    }



}
