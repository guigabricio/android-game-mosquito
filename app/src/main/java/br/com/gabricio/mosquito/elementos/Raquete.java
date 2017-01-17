package br.com.gabricio.mosquito.elementos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import br.com.gabricio.mosquito.R;
import br.com.gabricio.mosquito.engine.Cores;
import br.com.gabricio.mosquito.engine.IDMOrientacao;
import br.com.gabricio.mosquito.engine.Tela;
import br.com.gabricio.mosquito.engine.Utils;

/**
 * Created by Gabricio on 03/04/2016.
 */
public class Raquete {

    private Bitmap bitmapRaquete;
    private Tela tela;
    private static int TAMANHO = 200;
    private int x;
    private int y;

    public Raquete(Context context, Tela tela, int drawable) {
        this.tela = tela;
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), drawable);
        this.bitmapRaquete = Bitmap.createScaledBitmap(bp, TAMANHO, TAMANHO, false);
    }

    public void desenharNo(Canvas canvas) {
        canvas.drawBitmap(bitmapRaquete, this.x - MosquitoDengue.RAIO, this.y - MosquitoDengue.RAIO, Cores.getCorAzul());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
