package com.moudy.alshafie.Data.DataSets;

import java.util.Random;

public class NotificationDataset {

    public static String getNof (){


String[] word={"hello","new","test"};

               int idx = new Random().nextInt(word.length);
        String randomm = (word[idx]);
        return  randomm;

    }


}
