package com.laab.adventures;

import android.content.Context;
import android.util.Log;

public class GameActivity_Layout extends GameLoop_Layout {

    private float x, y = 0;
    public GameActivity_Layout(Context context) {
        super(context);

    }

    @Override
    void update(double delta_t) {

        if(x < cwidth){
            x += toPxs(1);
        }
        y += toPxs(1);

    }

    @Override
    void draw() {
        canvas = surfaceHolder.lockCanvas();
        canvas.drawRect(0, 0, cwidth, cheight, green_paintbrush_fill);
        canvas.drawCircle(cwidth, cheight, toPxs(10), red_paintbrush_fill);

        //cannon ball
        canvas.drawCircle(x , y, toPxs(3), red_paintbrush_fill);

        surfaceHolder.unlockCanvasAndPost(canvas);
    }
}
