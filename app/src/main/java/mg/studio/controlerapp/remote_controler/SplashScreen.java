package mg.studio.controlerapp.remote_controler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class SplashScreen extends Activity{
    protected boolean _active = true;
    protected int _splashTime = 5000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_scrren);

        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(_active && (waited < _splashTime)) {
                        sleep(100);
                        if(_active) waited += 100;
                    }
                }catch(InterruptedException e) {
                    // do nothing
                }finally {
                    finish();
                    // 启动主应用
                    startActivity(new Intent("mg.studio.controlerapp.remote_controler.MainActivity"));
                    //stop();
                }
            }
        };
        splashTread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            _active = false;
        }
        return true;
    }
}
