package com.emmettito.tickettoride.views.GameActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.FrameLayout;

import com.emmettito.models.City;
import com.emmettito.models.HardCoded.HardCodedData;
import com.emmettito.models.Route;
import com.emmettito.models.Space;
import com.emmettito.models.Tuple;
import com.emmettito.tickettoride.R;

import java.util.List;

public class MapView extends View {

    private int width;
    private int height;

    private float circle_radius;
    private float rect_width;
    private float rect_height;

    public MapView(Context context) {
        super(context);
    }

    public MapView(Context context, int width, int height) {
        super(context);
        this.height = height;
        this.width = width;

        this.circle_radius = 0.006968641f * width;
        this.rect_width = 0.017770035f * width;
        this.rect_height = 0.009953162f * height;
        setLayoutParams(new FrameLayout.LayoutParams(this.width,this.height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundResource(R.drawable.ticket_to_ride_map_v2);

        // TODO: FOR PROOF THAT THE MAP COORDINATES WORK, UNCOMMENT THE LINES BELOW:
        // NOTE: THE COORDINATES HAVE ONLY BEEN TESTED ON A NEXUS 5

        HardCodedData data = new HardCodedData();
        List<Route> routes = data.getRoutes();
        List<City> cities = data.getCities();

        for (int i = 0; i < cities.size(); i++) {
            Tuple t = cities.get(i).getLocation();
            drawCircle(canvas, (float)t.getX() * width, (float)t.getY() * height);
        }

        for (int i = 0; i < routes.size(); i++) {
            List<Space> spaces = routes.get(i).getSpaces();
            for (int j = 0; j < spaces.size(); j++) {
                Space s = spaces.get(j);
                drawRect(canvas, s.getX() * width, s.getY() * height, s.getAngle());
            }
        }
    }

    private void drawCircle(Canvas canvas, float x, float y) {
        Paint paint=new Paint();
        paint.setColor(Color.parseColor("#000000"));
        canvas.drawCircle(x, y, circle_radius, paint);
    }

    private void drawRect(Canvas canvas, float x, float y, float angle) {
        canvas.save();
        canvas.rotate(angle, x, y);
        Paint paint=new Paint();
        paint.setColor(Color.parseColor("#000000"));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x - rect_width, y + rect_height, x + rect_width,y - rect_height, paint);
        canvas.restore();
    }
}
