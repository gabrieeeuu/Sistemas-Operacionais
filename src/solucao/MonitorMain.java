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
public class MonitorMain {
    
    private static final int MESA_SIZE = 5;
    private static final int TEMPO_PARA_PENSAR = 1000;
    private static final int TEMPO_PARA_COMER = 500;

    public static void main(String[] args) {
        MesaRedonda table = new MesaRedondaMonitor(MonitorMain.MESA_SIZE);

        for (int i = 0; i < MonitorMain.MESA_SIZE; i++) {
            new Filosofo(i, MonitorMain.TEMPO_PARA_COMER, MonitorMain.TEMPO_PARA_PENSAR, table);
        }
    }
    
}
