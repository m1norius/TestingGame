package com.example.minorius.myapplication;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.minorius.myapplication.Data.DataGeter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by minorius on 29.12.2016.
 */

public class Quiz extends AppCompatActivity {

    private ArrayList<String> list_of_sounds;
    private ArrayList<String> list_of_pict;
    private ArrayList<Integer> list_of_random_hash_img;

    private String name_sound;
    private int    hash_sound;

    private int answers = 0;
    private List<ImageView> list_of_image_view;

    private ImageView img_1;
    private ImageView img_2;
    private ImageView img_3;
    private ImageView img_4;
    private ImageView img_5;
    private ImageView img_6;

    private MediaPlayer media;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_la);

        img_1 = (ImageView) findViewById(R.id.img_1);
        img_2 = (ImageView) findViewById(R.id.img_2);
        img_3 = (ImageView) findViewById(R.id.img_3);
        img_4 = (ImageView) findViewById(R.id.img_4);
        img_5 = (ImageView) findViewById(R.id.img_5);
        img_6 = (ImageView) findViewById(R.id.img_6);

        list_of_image_view = new ArrayList<>();

        list_of_sounds = new ArrayList<>();
        list_of_sounds.addAll(DataGeter.getSoundsList());

        list_of_pict = new ArrayList<>();
        list_of_pict.addAll(DataGeter.getImagesList());

        list_of_random_hash_img = new ArrayList<>();


        selectLevel();

    }



    public void selectLevel(){

        if (answers <= 5) {
            list_of_image_view.clear();
            list_of_image_view.add(img_1);
            list_of_image_view.add(img_2);
            beginGame(list_of_image_view, 1);

        }else if(answers > 5 && answers <=10){
            list_of_image_view.clear();
            list_of_image_view.add(img_1);
            list_of_image_view.add(img_2);
            list_of_image_view.add(img_3);
            beginGame(list_of_image_view, 2);

        }else if(answers > 10 && answers <=14){
            list_of_image_view.clear();
            list_of_image_view.add(img_1);
            list_of_image_view.add(img_2);
            list_of_image_view.add(img_3);
            list_of_image_view.add(img_4);
            beginGame(list_of_image_view, 3);

        }else if(answers > 14 && answers <=18){
            list_of_image_view.clear();
            list_of_image_view.add(img_1);
            list_of_image_view.add(img_2);
            list_of_image_view.add(img_3);
            list_of_image_view.add(img_4);
            list_of_image_view.add(img_5);
            beginGame(list_of_image_view, 4);

        }else if(answers > 18 && answers <=23){
            list_of_image_view.clear();
            list_of_image_view.add(img_1);
            list_of_image_view.add(img_2);
            list_of_image_view.add(img_3);
            list_of_image_view.add(img_4);
            list_of_image_view.add(img_5);
            list_of_image_view.add(img_6);
            beginGame(list_of_image_view, 5);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void beginGame(List<ImageView> list_of_image_view, Integer num_of_view){

        playSound(getHashForSound());

        Collections.shuffle(list_of_image_view);

        //Задаємо зображення відповідне до програного звуку
        list_of_image_view.get(0).setImageResource(getHashCodeForImgBySoundName(name_sound));
        list_of_image_view.get(0).setBackground(getResources().getDrawable(R.drawable.on_click_true));

        //Заповнюєм ImageView рандомними зображеннями
        for(int i = 1; i <= num_of_view; i++){
            list_of_image_view.get(i).setImageResource(getHashCodeForRandomImg());
            list_of_image_view.get(i).setBackground(getResources().getDrawable(R.drawable.on_click_false));
        }

        list_of_random_hash_img.clear();

        //Вішаємо лісенер на кожну з кнопок
        for (int i = 0; i <=num_of_view; i++){
            onClickByImgListener(list_of_image_view.get(i));
        }
    }

    public void onClickByImgListener(final ImageView i){

        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i.getId() == list_of_image_view.get(0).getId()){
                    media.stop();
                    answers++;
                    delSoundFromArray(name_sound);
                    if(answers != 24){
                        selectLevel();
                    }else {

                        Toast.makeText(getApplicationContext(), "Перемога", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), Menu.class);
                        startActivity(i);
                    }
                }else {
                    list_of_image_view.get(0).setBackgroundColor(getResources().getColor(R.color.light_blue));
                    Toast.makeText(getApplicationContext(), "Не вірно", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void delSoundFromArray(String s){
        list_of_sounds.remove(s);
        String copy_string = null;
        String deletable_str = s.replaceAll("_\\d+", "");
        for(String string : list_of_sounds){
            if(deletable_str.equals(string.replaceAll("_\\d+", ""))){
                copy_string = string;
            }
        }
        list_of_sounds.remove(copy_string);
    }

    public int getHashForSound(){

        int random_sound = new Random().nextInt(list_of_sounds.size());
        name_sound = list_of_sounds.get(random_sound);
        hash_sound = DataGeter.getHashSound(name_sound);

        return hash_sound;
    }

    public int getHashCodeForImgBySoundName(String name_sound){

        int hash_img = 0;

        for(String s : list_of_pict){
            if(name_sound.replaceAll("_\\d+", "").equals(s.replace("img_", ""))){
                hash_img = DataGeter.getHashImg(s);
            }
        }
        return hash_img;
    }

    public int getHashCodeForRandomImg(){

        String name_of_img;
        int hash_img;

        do{
            name_of_img = list_of_pict.get(new Random().nextInt(list_of_pict.size()));
            hash_img = DataGeter.getHashImg(name_of_img);
        }while(list_of_random_hash_img.contains(hash_img) || name_of_img.replace("img_", "").equals(name_sound.replaceAll("_\\d+", "")));
        list_of_random_hash_img.add(hash_img);
        return hash_img;
    }

    private void playSound(final Integer i){

        new Runnable() {
            @Override
            public void run() {
                media = MediaPlayer.create(Quiz.this, i);
                media.start();
            }
        }.run();

    }

    

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        media.stop();

    }

    @Override
    protected void onStop() {
        super.onStop();
        media.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        media.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        media.stop();
    }
}
