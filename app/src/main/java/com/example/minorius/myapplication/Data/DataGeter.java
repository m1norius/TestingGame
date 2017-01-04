package com.example.minorius.myapplication.Data;

import com.example.minorius.myapplication.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by minorius on 29.12.2016.
 */

public class DataGeter {

    static Class<R.drawable> class_img;
    static List<String> list_images;

    static Class<R.raw> class_sounds;
    static List<String> list_sounds;


    //Заливаю всі файли з папки дравабл які починаються "img_", На виході колекція

    public static List<String> getImagesList(){

        list_images = new ArrayList<>();
        class_img = R.drawable.class;

        for(Field f : class_img.getDeclaredFields()){
            String t = f.getName();
            if(t.startsWith("img_")){
                list_images.add(t);
            };
        }

        return list_images;
    }

    //Отримую хешкод картинки

    public static int getHashImg(String s){

        class_img = R.drawable.class;

        for(Field f : class_img.getDeclaredFields()){

            if(f.getName().equals(s)){
                try {
                    Object obj = f.get(s);
                    return Integer.valueOf(obj.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return 0;
    }

    public static List<String> getSoundsList(){

        list_sounds = new ArrayList<>();
        class_sounds = R.raw.class;

        for(Field f : class_sounds.getDeclaredFields()){
            String t = f.getName();
            if(!t.equals("serialVersionUID")){
                try {
                    Object o = f.get(t);
                    if(o != null ){
                        list_sounds.add(t.toString());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return list_sounds;
    }

    public static int getHashSound(String s){

        class_sounds = R.raw.class;

        for(Field f : class_sounds.getDeclaredFields()){

            if(f.getName().equals(s)){
                try {
                    Object obj = f.get(s);
                    return Integer.valueOf(obj.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return 0;

    }
}
