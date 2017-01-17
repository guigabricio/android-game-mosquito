package br.com.gabricio.mosquito.engine;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Gabricio on 02/02/2016.
 */
public class Tela {

    private DisplayMetrics displayMetrics;

    public Tela(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        this.displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
    }

    public int getAltura() {
        return this.displayMetrics.heightPixels;
    }

    public int getLargura() {
        return this.displayMetrics.widthPixels;
    }

}
