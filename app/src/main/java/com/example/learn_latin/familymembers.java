package com.example.learn_latin;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class familymembers extends AppCompatActivity {
    ArrayList<word> words=new ArrayList<word>();
    wordadapter itemsAdapter;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        words.add(new word("dughter","kénne",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new word("father","ʔóṣṣa",R.drawable.family_father,R.raw.family_father));
        words.add(new word("grandfather","tel(l)éeka",R.drawable.family_grandfather,R.raw.family_grandfather));
        words.add(new word("grandmother","húja",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new word("mother","kenékkuh",R.drawable.family_mother,R.raw.family_mother));
        words.add(new word("older_brother","pácciṭak",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new word("older_sister","ṣeelóowih",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new word("son","ʔóṣṣuwah",R.drawable.family_son,R.raw.family_son));
        words.add(new word("younger_brother","kénnekotoh",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new word("yonger_sister","kíccih",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        itemsAdapter = new wordadapter(getApplicationContext(), words);
        ListView listView =findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clear_resource();     //agar resource clear nhi kiya toh 2 click krne par 2 file ek saath bajengi
                mp=MediaPlayer.create(getApplicationContext(),itemsAdapter.getItem(position).getPlayresource());
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        clear_resource();    //      after completion  resource clear karne ke liye
                    }
                });
                Log.v("mylog ||"+getApplicationContext().toString(),"|| "+itemsAdapter.getItem(position).toString());
            }
        });
    }

    @Override
    protected void onStop() {// clear resource if avtivity imideatly stopped
        super.onStop();
        clear_resource();
    }

    private void clear_resource(){
        if(mp!=null){
            mp.release();
            mp=null;
        }
    }
}