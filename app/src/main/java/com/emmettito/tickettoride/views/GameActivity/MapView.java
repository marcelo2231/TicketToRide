package com.emmettito.tickettoride.views.GameActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.FrameLayout;

import com.emmettito.models.Game;
import com.emmettito.models.Player;
import com.emmettito.models.PlayerColor;
import com.emmettito.models.Route;
import com.emmettito.models.Space;
import com.emmettito.models.Tuple;
import com.emmettito.tickettoride.Client;
import com.emmettito.tickettoride.R;

import java.util.List;

public class MapView extends View {

    GameActivity gameActivity;

    private int width;
    private int height;

    private float circle_radius;
    private float rect_width;
    private float rect_height;
    private float rect_width_padding;
    private float rect_height_padding;

    Client data;

    private List<Route> allRoutes;

    private Bitmap map_background;
    private Bitmap branding;

    private float mLastTouchX;
    private float mLastTouchY;
    private float mPosX;
    private float mPosY;

    private double cPosX;
    private double cPosY;

    private ScaleGestureDetector scaleDetector;
    private GestureDetector tapDetector;

    private float zoom = 1;
    private float scalePointX;
    private float scalePointY;

    public MapView(Context context) {
        super(context);
    }

    public MapView(Context context, int width, int height) {
        super(context);
        gameActivity = (GameActivity)context;

        this.height = height;
        this.width = width;

        this.circle_radius = 0.006968641f * width;
        this.rect_width = 0.017770035f * width;
        this.rect_height = 0.009953162f * height;

        this.rect_width_padding = 0.02f * width;
        this.rect_height_padding = 0.01f * height;

        scaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        tapDetector = new GestureDetector(context, new SingleDoubleTapListener());

        map_background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ticket_to_ride_map_v2), width, height, false);
        map_background.setHeight(height);
        map_background.setWidth(width);

        branding = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.branding), width, height, false);
        branding.setHeight(height);
        branding.setWidth(width);

        data = Client.getInstance();
        allRoutes = data.getAllRoutes();

        setLayoutParams(new FrameLayout.LayoutParams(this.width,this.height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(branding, 0, 0, null);

        canvas.save();
        canvas.translate(mPosX, mPosY);
        canvas.scale(zoom, zoom, scalePointX, scalePointY);

        canvas.drawBitmap(map_background, 0, 0, null);

        Game game = data.getGame();
        List<Player> players = game.getPlayers();

        for (int i = 0; i < players.size(); i++) { // For each user
            Player player = players.get(i);
            String color = getColorHex(player.getColor());
            List<Integer> routes = player.getClaimedRoutes(); // Get user's routes

            if (routes == null) {
                return;
            }

            for (int j = 0; j < routes.size(); j++) {
                Route route = allRoutes.get(routes.get(j));
                List<Space> spaces = route.getSpaces();
                for (int k = 0; k < spaces.size(); k++) {
                    Space s = spaces.get(k);
                    drawRect(canvas, s.getX() * width, s.getY() * height, s.getAngle(), color);
                }
            }
        }

        canvas.restore();
    }

    private void drawCircle(Canvas canvas, float x, float y, String color) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor(color));
        canvas.drawCircle(x, y, circle_radius, paint);
    }

    private void drawRect(Canvas canvas, float x, float y, float angle, String color) {
        canvas.save();
        canvas.rotate(angle, x, y);
        Paint paint=new Paint();
        paint.setColor(Color.parseColor(color));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x - rect_width, y + rect_height, x + rect_width,y - rect_height, paint);
        canvas.restore();
    }

    public int onRoute(float x, float  y) {
        Client data = Client.getInstance();
        List<Route> routes = data.getAllRoutes();

        for (int i = 0; i < routes.size(); i++) {
            Route route = routes.get(i);
            List<Space> spaces = route.getSpaces();
            for (int j = 0; j < spaces.size(); j++) {
                Space s = spaces.get(j);
                Tuple t = calcNewPosition((int)(x), (int)(y), (int)((s.getX() * width * zoom) + cPosX), (int)((s.getY() * height * zoom) + cPosY), s.getAngle());

                Rect rect = getRect(s.getX(), s.getY());

                if (rect.contains((int)t.getX(), (int)t.getY())) {
                    return route.getID();
                }
            }
        }

        return -1;
    }

    private Tuple calcNewPosition(int x, int y, int rect_x, int rect_y, float degrees) {
        Double rad = Math.toRadians(degrees);

        int diff_x = x - rect_x;
        int diff_y = y - rect_y;

        int new_x = (int)(diff_x * Math.cos(rad) - diff_y * Math.sin(rad));
        int new_y = (int)(diff_x * Math.sin(rad) + diff_y * Math.cos(rad));

        int final_x = new_x + rect_x;
        int final_y = new_y + rect_y;

        return new Tuple(final_x, final_y);
    }

    private Rect getRect(float x, float y) {
        int left = (int)((x * width * zoom) + cPosX - (rect_width_padding * zoom));
        int top = (int)((y * height * zoom) + cPosY - (rect_height_padding * zoom));
        int right = (int)((x * width * zoom) + cPosX + (rect_width_padding * zoom));
        int bottom = (int)((y * height * zoom) + cPosY + (rect_height_padding * zoom));

        return new Rect(left, top, right, bottom);
    }

    private String getColorHex(PlayerColor color) {
        int colorID;

        if (color != null) {
            switch(color) {
                case Red:
                    colorID = R.color.redPlayer;
                    break;
                case Orange:
                    colorID = R.color.orangePlayer;
                    break;
                case Yellow:
                    colorID = R.color.yellowPlayer;
                    break;
                case Green:
                    colorID = R.color.greenPlayer;
                    break;
                case Blue:
                    colorID = R.color.bluePlayer;
                    break;
                default:
                    colorID = R.color.defaultPlayer;
                    break;
            }
        }
        else {
            colorID = R.color.defaultPlayer;
        }

        return getResources().getString(colorID);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (tapDetector.onTouchEvent(event)) {
            return true;
        }

        // Let the ScaleGestureDetector inspect all events.
        scaleDetector.onTouchEvent(event);

        final int action = event.getAction();

        switch(action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                final float x = (event.getX() - scalePointX)/zoom;
                final float y = (event.getY() - scalePointY)/zoom;
                mLastTouchX = x;
                mLastTouchY = y;
                break;
            }
            case MotionEvent.ACTION_MOVE: {

                final float x = (event.getX() - scalePointX)/zoom;
                final float y = (event.getY() - scalePointY)/zoom;
                // Only move if the ScaleGestureDetector isn't processing a gesture.
                if (!scaleDetector.isInProgress()) {
                    final float dx = x - mLastTouchX; // change in X
                    final float dy = y - mLastTouchY; // change in Y
                    mPosX += dx;
                    mPosY += dy;
                    cPosX += dx;
                    cPosY += dy;
                    invalidate();
                }

                mLastTouchX = x;
                mLastTouchY = y;
                break;

            }
            case MotionEvent.ACTION_UP: {
                mLastTouchX = 0;
                mLastTouchY = 0;
                invalidate();
                break;
            }
            default: break;
        }

        return true;
    }

    private class SingleDoubleTapListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            int routeID = onRoute(event.getX(), event.getY());

            if (routeID != -1) {
                gameActivity.claimRoute(routeID);
            }

            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent event) {
            if (zoom != 1f || mPosX != 0 || mPosY != 0) {
                mPosX = 0;
                mPosY = 0;
                cPosX = 0;
                cPosY = 0;
                zoom = 1;
            }
            else {
                scalePointX = event.getX();
                scalePointY = event.getY();
                zoom = 1.75f;
                adjustOrigin();
            }
            invalidate();
            return true;
        }
    }

    private void adjustOrigin() {
        cPosX = (-scalePointX * zoom) + scalePointX + mPosX;
        cPosY = (-scalePointY * zoom) + scalePointY + mPosY;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            zoom *= detector.getScaleFactor();
            scalePointX = detector.getFocusX();
            scalePointY = detector.getFocusY();

            // Doesn't let the view get too large or too small
            zoom = Math.max(0.5f, Math.min(zoom, 3.0f));

            // Adjusts the origin for the zoom and pan
            adjustOrigin();

            invalidate();
            return true;
        }
    }
}

