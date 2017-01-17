package br.com.gabricio.mosquito.engine;

/**
 * Created by Gabricio on 30/03/2016.
 */
public class Utils {
    public static int gerarNumeroAlatorio(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }
}
