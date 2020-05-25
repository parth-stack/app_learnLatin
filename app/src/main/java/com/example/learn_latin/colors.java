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

public class colors extends AppCompatActivity {
    ArrayList<word> words=new ArrayList<word>();
    wordadapter itemsAdapter;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        words.add(new word("black","kénne",R.drawable.color_black,R.raw.color_black));
        words.add(new word("brown","ʔóṣṣa",R.drawable.color_brown,R.raw.color_brown));
        words.add(new word("dusty_yellow","tel(l)éeka",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new word("gray","húja",R.drawable.color_gray,R.raw.color_gray));
        words.add(new word("green","kenékkuh",R.drawable.color_green,R.raw.color_green));
        words.add(new word("mustard_yellow","pácciṭak",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        words.add(new word("red","ṣeelóowih",R.drawable.color_red,R.raw.color_red));
        words.add(new word("white","ʔóṣṣuwah",R.drawable.color_white,R.raw.color_white));
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
                        clear_resource();//      after completion  resource clear karne ke liye
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