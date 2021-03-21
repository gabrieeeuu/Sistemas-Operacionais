/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solucao;

/**
 *
 * @author Gabriel
 */
public class SemaforoMain {

    private static final int MESA_SIZE = 5;
    private static final int TEMPO_PARA_PENSAR = 1000;
    private static final int TEMPO_PARA_COMER = 500;

    public static void main(String[] args) {
        MesaRedondaSemaforo table = new MesaRedondaSemaforo(SemaforoMain.MESA_SIZE);

        for (int i = 0; i < SemaforoMain.MESA_SIZE; i++) {
            new Filosofo(i, SemaforoMain.TEMPO_PARA_COMER, SemaforoMain.TEMPO_PARA_PENSAR, table);
        }
    }
    
}
