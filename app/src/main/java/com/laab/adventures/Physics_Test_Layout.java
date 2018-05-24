package com.laab.adventures;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Physics_Test_Layout extends SurfaceView implements Runnable{

    Thread thread = null;
    boolean CanDraw = false;

    Paint red_fill;

    int rect_x = 0, rect_y = 0;
    int squareSize = 20;

    Object object;

    Canvas canvas;
    SurfaceHolder surfaceholder;

    public Physics_Test_Layout(Context context){
        super(context);
        surfaceholder = getHolder();
        prepBrushes();
        object = new Object(rect_x,rect_y,squareSize);
    }

    @Override
    public void run(){
        while(CanDraw){
            if(!surfaceholder.getSurface().isValid()){
                continue;
            }
            canvas = surfaceholder.lockCanvas();
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            int[] pos = object.MoveTo(rect_x,rect_y);
            canvas.drawRect(pos[0],pos[1],pos[2],pos[3], red_fill);

            surfaceholder.unlockCanvasAndPost(canvas);
            rect_x++;
            rect_y++;
        }
    }

    public void resume(){
        CanDraw = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause(){
        CanDraw = false;

        while(true){
            try {
                thread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        thread = null;
    }

    private void prepBrushes(){
        red_fill = new Paint();
        red_fill.setColor(Color.RED);
        red_fill.setStyle(Paint.Style.FILL);
    }
}
