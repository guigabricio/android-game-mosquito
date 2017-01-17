package br.com.gabricio.mosquito.engine;

import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * Created by Gabricio on 02/02/2016.
 */
public class Cores {

    public static Paint getCorDaPontuacaoAcertos() {
        Paint branco = new Paint();
        branco.setColor(0xFFFFFFFF);
        branco.setTextSize(80);
        branco.setTypeface(Typeface.DEFAULT_BOLD);
        branco.setShadowLayer(3, 5, 5, 0xFF000000);
        return branco;
    }

    public static Paint getCorDaPontuacaoErros() {
        Paint vermelho = new Paint();
        vermelho.setColor(0xFFFF0000);
        vermelho.setTextSize(80);
        vermelho.setTypeface(Typeface.DEFAULT_BOLD);
        vermelho.setShadowLayer(3, 5, 5, 0xFF000000);
        return vermelho;
    }

    public static Paint getCorDoGameOver() {
        Paint vermelho = new Paint();
        vermelho.setColor(0xFFFF0000);
        vermelho.setTextSize(50);
        vermelho.setTypeface(Typeface.DEFAULT_BOLD);
        vermelho.setShadowLayer(2, 3, 3, 0xFF000000);
        return vermelho;
    }

    public static Paint getCorDoVenceu() {
        Paint verde = new Paint();
        verde.setColor(0xFF00FF00);
        verde.setTextSize(50);
        verde.setTypeface(Typeface.DEFAULT_BOLD);
        verde.setShadowLayer(2, 3, 3, 0xFF000000);
        return verde;
    }

    public static Paint getCorCinza() {
        Paint cor = new Paint();
        cor.setColor(0xFF333333);
        return cor;
    }

    public static Paint getCorBranco() {
        Paint cor = new Paint();
        cor.setColor(0xFFFFFFFF);
        return cor;
    }

    public static Paint getCorAzul() {
        Paint cor = new Paint();
        cor.setColor(0xFF0066CC);
        return cor;
    }

    public static Paint getCorAmarelo() {
        Paint cor = new Paint();
        cor.setColor(0xFFCCFF00);
        return cor;
    }

    public static Paint getCorVermelho() {
        Paint cor = new Paint();
        cor.setColor(0xFFCC0000);
        return cor;
    }

    public static Paint getColorRandom(){
        int valor = (int) (Math.random() * 6);
        Paint cor = null;
        switch (valor){
            case 0:
                cor = getCorAmarelo();
                break;
            case 1:
                cor = getCorAzul();
                break;
            case 2:
                cor = getCorBranco();
                break;
            case 3:
                cor = getCorCinza();
                break;
            case 4:
                cor = getCorVermelho();
                break;
            default:
                cor = getCorAzul();
                break;
        }
        return cor;
    }
}
