package com.example.rokymielsen.tgwon;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Roky Mielsen on 23.01.2018.
 */

public class Sky {

    Paint paint=new Paint();

    final int numStars = 500;

    int xStar[] = new int[numStars];
    int yStar[] = new int[numStars];
    int alphaStar[] = new int[numStars];

    void makeSky(int scaleWidth,int scaleHeight)
    {

        int maxX = scaleWidth;
        int maxY = 2*scaleHeight;
        for (int i = 0; i < numStars; i++)
        {
            xStar[i] = (int)(Math.random() * maxX);
            yStar[i] = (int)(Math.random() * maxY);
            alphaStar[i] = (int)(Math.random() * 256);
        }
    }
    void drawSky(Canvas canvas)
    {
        canvas.drawColor(Color.BLACK);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        for (int i = 0; i < numStars; i++)
        {
            paint.setAlpha(alphaStar[i]);
            alphaStar[i] += (int)(Math.random() * 11) - 5;
            if (alphaStar[i] > 255) alphaStar[i] = 255;
            if (alphaStar[i] < 0) alphaStar[i] = 0;
            canvas.drawCircle(xStar[i], yStar[i], 3, paint);
        }
    }
}
