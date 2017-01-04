package com.example.minorius.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.minorius.myapplication.Data.DataGeter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by minorius on 03.01.2017.
 */
public class Fragment1 extends Fragment {
    public static MediaPlayer media_fragment;
    public LinearLayout animals_id;
    public View root_view;

    List<ImageView> list_of_obj;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        animals_id = (LinearLayout) root_view.findViewById(R.id.animals_id);

        list_of_obj = new ArrayList<>();
        List<String> list_of_animals = Arrays.asList(
                "img_cat",
                "img_chicken",
                "img_cow",
                "img_dog",
                "img_elephant",
                "img_frog",
                "img_horse",
                "img_lion",
                "img_monkey",
                "img_pig",
                "img_sheep",
                "img_tiger"
        );


        for(int i = 0; i<list_of_animals.size(); i++) {
            list_of_obj.add(new ImageView(getActivity()));
        }

        for(int i = 0; i<list_of_obj.size(); i++){
            ImageView test = list_of_obj.get(i);
            TextView tv = new TextView(getActivity());
            final String name_img = list_of_animals.get(i);
            int hash_img = DataGeter.getHashImg(name_img);
            String text_to_text_view = name_img.replace("img_", "").replace("_", " ");
            tv.setText(text_to_text_view);
            tv.setTextSize(40);
            tv.setPadding(20, 20, 20, 20);

            test.setImageResource(hash_img);
            test.setScaleType(ImageView.ScaleType.FIT_START);
            test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(media_fragment!= null){
                        media_fragment.stop();
                    }
                    playSound(getHashCodeForSoundByImgName(name_img));

                }
            });
            animals_id.addView(tv);
            animals_id.addView(test);

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root_view = inflater.inflate(R.layout.fragment_1, container, false);
        return root_view;
    }

    private void playSound(final Integer i){

        new Runnable() {
            @Override
            public void run() {
                media_fragment = MediaPlayer.create(getActivity(), i);
                media_fragment.start();
            }
        }.run();
    }

    public int getHashCodeForSoundByImgName(String name_img){

        int hash_sound = 0;

        for(String s : DataGeter.getSoundsList()){
            if(s.replaceAll("_\\d+", "").equals(name_img.replace("img_", ""))){
                hash_sound = DataGeter.getHashSound(s);
            }
        }
        return hash_sound;
    }

}
