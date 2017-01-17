package br.com.gabricio.mosquito.elementos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.util.Random;
import java.util.logging.Logger;

import br.com.gabricio.mosquito.R;
import br.com.gabricio.mosquito.engine.Cores;
import br.com.gabricio.mosquito.engine.IDMOrientacao;
import br.com.gabricio.mosquito.engine.Tela;
import br.com.gabricio.mosquito.engine.Tempo;
import br.com.gabricio.mosquito.engine.Utils;


/**
 * Created by Gabricio on 02/02/2016.
 */
public class MosquitoDengue {

    private Bitmap bitmapMosquito;
    private Tela tela;
    private boolean statusMosquito = true;
    public static final int RAIO = 100;
    private int velocidade = 0;
    private int direcao;
    private int x;
    private int y;
    private int tempoMosquitoMorto = 0;
    private Tempo tempo;
    private boolean limite;

    public MosquitoDengue(Context context, Tela tela, Bitmap bitmapMosquito, Tempo tempo) {
        this.tela = tela;
        this.direcao = direcaoAleatoria();
        this.bitmapMosquito = bitmapMosquito;
        this.x = Utils.gerarNumeroAlatorio(0, this.tela.getLargura() - RAIO);
        this.y = Utils.gerarNumeroAlatorio(0, this.tela.getAltura() - RAIO);
        this.velocidade = 0;
        this.tempo = tempo;
    }

    public boolean isStatusMosquito() {
        return statusMosquito;
    }

    public void setStatusMosquito(boolean statusMosquito) {
        this.statusMosquito = statusMosquito;
    }

    public int getDirecao() {
        return direcao;
    }

    public void setDirecao(int direcao) {
        this.direcao = direcao;
    }

    public void desenharNo(Canvas canvas) {

        canvas.drawBitmap(bitmapMosquito, x, y, Cores.getCorVermelho());

    }

    public boolean estaForaDaTela() {
        if (x < 0) {
            this.x = x + Utils.gerarNumeroAlatorio(0, 20);
            return true;
        }
        if (y < 0) {
            this.y = y + Utils.gerarNumeroAlatorio(0, 20);
            return true;
        }
        if ((x + RAIO) > tela.getLargura()) {
            this.x = x - Utils.gerarNumeroAlatorio(0, 20);
            return true;
        }
        if ((y + RAIO) > tela.getAltura()) {
            this.y = y - Utils.gerarNumeroAlatorio(0, 20);
            return true;
        }
        return false;
    }

    public boolean acertou(MotionEvent motionEvent) {
        return ((Math.sqrt(Math.pow(motionEvent.getX() - this.x, 2) + Math.pow(motionEvent.getY() - this.y, 2))) < RAIO);
    }

    private int direcaoAleatoria() {
        return Utils.gerarNumeroAlatorio(0, IDMOrientacao.QTDE_ORIENTACOES);
    }

    public void alterarOrientacao() {
        this.direcao = direcaoAleatoria();
    }

    public void mover() {
        switch (this.direcao) {
            case IDMOrientacao.NORTE:
                y -= calculaVelocidade();
                break;
            case IDMOrientacao.LESTE:
                x += calculaVelocidade();
                break;
            case IDMOrientacao.OESTE:
                x -= calculaVelocidade();
                break;
            case IDMOrientacao.SUL:
                y += calculaVelocidade();
                break;
            case IDMOrientacao.SUDESTE:
                x += calculaVelocidade();
                y += calculaVelocidade();
                break;
            case IDMOrientacao.NORDESTE:
                y -= calculaVelocidade();
                x += calculaVelocidade();
                break;
            case IDMOrientacao.SUDOESTE:
                y += calculaVelocidade();
                x -= calculaVelocidade();
                break;
            case IDMOrientacao.NOROESTE:
                x -= calculaVelocidade();
                y -= calculaVelocidade();
                break;
        }
    }

    public void aumentarTempoMosquito() {
        this.tempoMosquitoMorto++;
    }

    public int getTempoMosquitoMorto() {
        return tempoMosquitoMorto;
    }

    public void setTempoMosquitoMorto(int tempoMosquitoMorto) {
        this.tempoMosquitoMorto = tempoMosquitoMorto;
    }

    private int calculaVelocidade(){
        double tempo = this.tempo.atual();
        double velocidade = ((10 * tempo * tempo) / 2);
        return (int) velocidade;
    }

    public void setBitmapMosquito(Bitmap bitmapMosquito) {
        this.bitmapMosquito = bitmapMosquito;
    }
}