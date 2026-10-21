package com.ggmouse.mobile;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class FloatingService extends Service {
    private WindowManager windowManager;
    private View bubble;
    private WindowManager.LayoutParams params;
    private float downX, downY;
    private int startX, startY;

    @Override public void onCreate() {
        super.onCreate();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        TextView icon = new TextView(this);
        icon.setText("GG");
        icon.setTextColor(0xFFFFFFFF);
        icon.setTextSize(16);
        icon.setGravity(Gravity.CENTER);
        icon.setBackgroundColor(0xFF1565C0);
        int type = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
            ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            : WindowManager.LayoutParams.TYPE_PHONE;
        params = new WindowManager.LayoutParams(58, 58, type,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.TOP | Gravity.START;
        params.x = 30;
        params.y = 220;
        bubble = icon;
        icon.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downX = event.getRawX(); downY = event.getRawY();
                    startX = params.x; startY = params.y; return true;
                case MotionEvent.ACTION_MOVE:
                    params.x = startX + (int)(event.getRawX() - downX);
                    params.y = startY + (int)(event.getRawY() - downY);
                    windowManager.updateViewLayout(bubble, params); return true;
                case MotionEvent.ACTION_UP:
                    if (Math.abs(event.getRawX()-downX) < 8 && Math.abs(event.getRawY()-downY) < 8) {
                        Toast.makeText(this, "GG Mouse: painel de controles em desenvolvimento.", Toast.LENGTH_SHORT).show();
                    }
                    return true;
            }
            return true;
        });
        windowManager.addView(bubble, params);
    }

    @Override public int onStartCommand(Intent intent, int flags, int startId) { return START_STICKY; }
    @Override public void onDestroy() {
        if (windowManager != null && bubble != null) windowManager.removeView(bubble);
        super.onDestroy();
    }
    @Override public IBinder onBind(Intent intent) { return null; }
}
