package br.com.gabricio.mosquito.elementos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.gabricio.mosquito.R;
import br.com.gabricio.mosquito.engine.Tela;
import br.com.gabricio.mosquito.engine.Tempo;
import br.com.gabricio.mosquito.engine.Utils;

/**
 * Created by Gabricio on 29/03/2016.
 */
public class EnxameMosquitos {

    public static final int QTDE_MOSQUITOS = 100;

    private Tela tela;
    private Context context;
    private List<MosquitoDengue> mosquitos;
    private Map<MosquitoDengue, MosquitoDengue> mosquitosMortos;
    private Pontuacao pontuacao;
    private Bitmap bitmapMosquitoMorto;
    private Bitmap bitmapMosquitoVivo;
    private Tempo tempo;

    public EnxameMosquitos(Context context, Tela tela, Pontuacao pontuacao, Tempo tempo) {
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.mosquitovivo);
        this.bitmapMosquitoVivo = Bitmap.createScaledBitmap(bp, MosquitoDengue.RAIO, MosquitoDengue.RAIO, false);
        Bitmap bp1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.mosquitomorto);
        this.bitmapMosquitoMorto = Bitmap.createScaledBitmap(bp1, MosquitoDengue.RAIO, MosquitoDengue.RAIO, false);
        this.pontuacao = pontuacao;
        this.tela = tela;
        this.context = context;
        this.mosquitos = new ArrayList<>();
        this.mosquitosMortos = new HashMap<>();
        this.tempo = tempo;
        inicializaMosquitos(context, tela, tempo);
    }

    public synchronized void desenharNo(Canvas canvas) {
        for (MosquitoDengue mosquito : this.mosquitos) {
            mosquito.desenharNo(canvas);
        }
    }

    public synchronized void voar() {
        for (int i = 0; i < mosquitos.size(); i++) {
            MosquitoDengue mosquito = mosquitos.get(i);
            if (mosquito.isStatusMosquito()) {
                if (mosquito.estaForaDaTela()) {
                    mosquito.alterarOrientacao();
                }
                if (Utils.gerarNumeroAlatorio(0, 1000) % 7 == 0){
                    mosquito.alterarOrientacao();
                }
                mosquito.mover();
            }
            removerMosquito(i,mosquito);
        }
    }

    private void removerMosquito(int indice, MosquitoDengue mosquito){
        if (mosquitosMortos.containsKey(mosquito)) {
            int tempo = mosquitos.get(indice).getTempoMosquitoMorto();
            mosquitos.get(indice).aumentarTempoMosquito();
            if (tempo == 10){
                mosquitos.remove(indice);
                mosquitosMortos.remove(mosquito);
                this.pontuacao.aumentarAcertos();
            }
        }
    }

    public synchronized void verificarColisao(MotionEvent motionEvent) {
        boolean ocorreuAcertos = false;
        for (MosquitoDengue mosquito : this.mosquitos) {
            if (mosquito.acertou(motionEvent)) {
                mosquito.setStatusMosquito(false);
                ocorreuAcertos = true;
                mosquito.setBitmapMosquito(bitmapMosquitoMorto);
                this.mosquitosMortos.put(mosquito, mosquito);
            }
        }
        if(!ocorreuAcertos){
            this.pontuacao.aumentarErros();
        }
    }

    private synchronized void inicializaMosquitos(Context context, Tela tela, Tempo tempo) {
        for (int i = 0; i < QTDE_MOSQUITOS; i++) {
            this.mosquitos.add(new MosquitoDengue(context, tela, bitmapMosquitoVivo, tempo));
        }
    }
}
