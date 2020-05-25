package com.example.learn_latin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class wordadapter extends ArrayAdapter<word> {

    public wordadapter(@NonNull Context context, ArrayList<word> words) {
        super(context,0,words);
        ////resource id=0 because we don't need android to implement any resource file we will do it manually by override getview method
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemview=convertView;
        if(listitemview==null){
            listitemview= LayoutInflater.from(getContext()).inflate(R.layout.listitem,parent,false);
        }
        word curentword=getItem(position);
        TextView defaulttranslation=listitemview.findViewById(R.id.defaulttranslation);

        defaulttranslation.setText(curentword.getDefaultranslation());

        TextView miwoktranslation=listitemview.findViewById(R.id.miwoktranslation);

        miwoktranslation.setText(curentword.getMiwoktranslation());

        ImageView image=listitemview.findViewById(R.id.image);
        if(curentword.getResourceid()!=0){ image.setImageResource(curentword.getResourceid());}
        else{image.setVisibility(View.GONE);}
        return listitemview;
    }
}