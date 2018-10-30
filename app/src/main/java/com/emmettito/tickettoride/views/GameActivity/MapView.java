package com.emmettito.tickettoride.views.GameActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.FrameLayout;

import com.emmettito.tickettoride.R;

public class MapView extends View {

    private int width;
    private int height;

    public MapView(Context context) {
        super(context);
    }

    public MapView(Context context, int width, int height) {
        super(context);
        this.height = height;
        this.width = width;
        setLayoutParams(new FrameLayout.LayoutParams(this.width,this.height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundResource(R.drawable.ticket_to_ride_map_v2);

        // TODO: FOR PROOF THAT THE MAP COORDINATES WORK, UNCOMMENT THE LINES BELOW:
        // NOTE: THE COORDINATES HAVE ONLY BEEN TESTED ON A NEXUS 5

//        HardCodedData data = new HardCodedData();
//        List<Route> routes = data.getRoutes();
//        List<City> cities = data.getCities();
//
//        for (int i = 0; i < cities.size(); i++) {
//            Tuple t = cities.get(i).getLocation();
//            drawCircle(canvas, (float)t.getX(), (float)t.getY());
//        }
//
//        for (int i = 0; i < routes.size(); i++) {
//            List<Space> spaces = routes.get(i).getSpaces();
//            for (int j = 0; j < spaces.size(); j++) {
//                Space s = spaces.get(j);
//                drawRect(canvas, s.getX(), s.getY(), s.getAngle());
//            }
//        }
    }

    private void drawCircle(Canvas canvas, float x, float y) {
        int radius = 10; // need to change size depending on screen size
        Paint paint=new Paint();
        paint.setColor(Color.parseColor("#000000"));
        //canvas.drawCircle(width * x, height * y, radius, paint);
        canvas.drawCircle(x, y, radius, paint);
    }

    private void drawRect(Canvas canvas, float x, float y, float angle) {
        float x_pad = 25.5f;
        float y_pad = 8.5f;
        canvas.save();
        canvas.rotate(angle, x, y);
        Paint paint=new Paint();
        paint.setColor(Color.parseColor("#000000"));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x - x_pad, y + y_pad, x + x_pad,y - y_pad, paint);
        canvas.restore();
    }
}
