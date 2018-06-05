package com.laab.adventures;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.ByteArrayOutputStream;

public abstract class GameLoop_Layout extends SurfaceView implements Runnable, SurfaceHolder.Callback {

    private Thread thread = null;
    private double frames_per_second, frame_time_seconds, frame_time_ms, frame_time_ns;
    private double tLF, tEOR, delta_t,physics_rate,dt,dt_pool;

    Paint red_paintbrush_fill, blue_paintbrush_fill, green_paintbrush_fill, gray_panitbrush_fill;
    Paint red_paintbrush_stroke,blue_paintbrush_stroke,green_paintbrush_stroke;

    Boolean CanDraw;
    SurfaceHolder surfaceHolder;
    Bitmap backGround;
    Canvas canvas;

    //screen info
    float cheight, cwidth;


    public GameLoop_Layout(Context context) {
        super(context);
        CanDraw= false;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        frames_per_second=30;
        frame_time_seconds=1/frames_per_second;
        frame_time_ms=frame_time_seconds*1000;
        frame_time_ns=frame_time_ms*1000000;
        //1s=1,000 ms
        //1s=1,000,000,000 ns
        //1ms=1,000,000 ns

        // 50 physics updates per second, = 1/50 sec = (1/50)*1,000,000,000 nanosecs
        // 1 physics update every 20,000,000 nanoseconds
        physics_rate=400;
        dt=(1/physics_rate)*1000000000;
        dt_pool=0;
    }

    @Override
    public void run() {
        prepPaintBrushes();

        tLF= System.nanoTime();
        delta_t=0;

        //GAME LOOP START
        while (CanDraw) {
            // DO SOME X,Y STATE UPDATES
            update_BIG_DELTA(delta_t);
            // DO SOME DRAWING
            if (!surfaceHolder.getSurface().isValid()) {
                continue;
            }
            draw();
            //END DRAWING

            //GET END OF RENDER TIMESTAMP, Returns nanoseconds
            tEOR=System.nanoTime();

            //CALCULATE DELTAT
            delta_t= frame_time_ns - (tEOR - tLF);

            //SLEEP THE FRAME FOR DELTA T
            try {
                //sleep takes milliseconds as long argument.  ms = ns / 1000000;
                //must cast double vars to long. Double are needed for decimal calculations.

                if(delta_t > 0 ) {
                    thread.sleep((long) (delta_t / 1000000));
                    // ms=ns/1000000;
                };

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //END SLEEP

            //GET END OF LAST FRAME TIME
            tLF=System.nanoTime();


        }
        //GAME LOOP END
    }


    private void update_BIG_DELTA(double delta_t){
        if (delta_t <0){
            delta_t = frame_time_ns - delta_t;
        }

        delta_t=delta_t+dt_pool;
        dt_pool=0;

        while ((delta_t>=dt)){
            update(dt);
            delta_t=delta_t-dt;
        }
        dt_pool=delta_t;
    }

    abstract void update(double delta_t);

        //delta_t is in nanoseconds, trajectory values are in seconds...
        //need to convert

//        velocity_xtn_dt = velocity_xtn + (acceleration_x*delta_t/1000000000);
//        velocity_ytn_dt = velocity_ytn + (acceleration_y*delta_t/1000000000);
//
//        d_cannon_ball_xtn_dt = d_cannon_ball_xtn + ( velocity_xtn * delta_t/1000000000 ) + (0.5 * acceleration_x * (delta_t/1000000000) * (delta_t/1000000000));
//        d_cannon_ball_ytn_dt = d_cannon_ball_ytn + ( velocity_ytn * delta_t/1000000000 ) + (0.5 * acceleration_y * (delta_t/1000000000) * (delta_t/1000000000));
//
//        velocity_xtn=velocity_xtn_dt;
//        velocity_ytn=velocity_ytn_dt;
//        d_cannon_ball_xtn=d_cannon_ball_xtn_dt;
//        d_cannon_ball_ytn=d_cannon_ball_ytn_dt;



    abstract void draw();
//        canvas = surfaceHolder.lockCanvas();
//        canvas.drawRect(0, 0, cwidth, cheight, green_paintbrush_fill);
//        canvas.drawCircle(cwidth, cheight, toPxs(10), red_paintbrush_fill);
//
//        //cannon ball
//        canvas.drawCircle((float) d_cannon_ball_xtn_dt, (float) d_cannon_ball_ytn_dt, toPxs(3), red_paintbrush_fill);
//
//        surfaceHolder.unlockCanvasAndPost(canvas);


    protected void prepPaintBrushes(){
        red_paintbrush_fill = new Paint();
        red_paintbrush_fill.setColor(Color.RED);
        red_paintbrush_fill.setStyle(Paint.Style.FILL);

        blue_paintbrush_fill = new Paint();
        blue_paintbrush_fill.setColor(Color.BLUE);
        blue_paintbrush_fill.setStyle(Paint.Style.FILL);

        green_paintbrush_fill = new Paint();
        green_paintbrush_fill.setColor(Color.GREEN);
        green_paintbrush_fill.setStyle(Paint.Style.FILL);

        red_paintbrush_stroke = new Paint();
        red_paintbrush_stroke.setColor(Color.RED);
        red_paintbrush_stroke.setStyle(Paint.Style.STROKE);
        red_paintbrush_stroke.setStrokeWidth(10);

        blue_paintbrush_stroke = new Paint();
        blue_paintbrush_stroke.setColor(Color.BLUE);
        blue_paintbrush_stroke.setStyle(Paint.Style.STROKE);
        blue_paintbrush_stroke.setStrokeWidth(10);

        green_paintbrush_stroke = new Paint();
        green_paintbrush_stroke.setColor(Color.GREEN);
        green_paintbrush_stroke.setStyle(Paint.Style.STROKE);
        green_paintbrush_stroke.setStrokeWidth(10);

        gray_panitbrush_fill = new Paint();
        gray_panitbrush_fill.setColor(Color.DKGRAY);
        gray_panitbrush_fill.setStyle(Paint.Style.FILL);
    }

    protected float toPxsWidth(float gameUnits){
        return ((gameUnits/900) * getResources().getDisplayMetrics().widthPixels);
    }

    protected float toPxsHeight(float gameUnits){
        return ((gameUnits/1600) * getResources().getDisplayMetrics().heightPixels);
    }

    public Bitmap getResizedBitmap(Bitmap b, int newWidth, int newHeight) {
        return Bitmap.createScaledBitmap(b, newWidth, newHeight, false);

    }

    public void pause(){
        CanDraw=false;

        while(true){
            try {
                thread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        thread=null;
    }

    public void resume(){
        CanDraw = true;

        if(thread==null){
            thread = new Thread(this);
            thread.start();
        };
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        cheight=height;
        cwidth=width;

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}