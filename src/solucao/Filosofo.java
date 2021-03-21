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
public class Filosofo implements Runnable{
    
    private final int i;
    private final int tempoParaComer;
    private final int tempoParaPensar;

    private final int PENSANDO = 0;
    private final int COM_FOME = 1;
    private final int COMENDO = 2;

    private final MesaRedonda mesa;
    private int estado;
    
    Filosofo(int i, int timeToEat, int thinkTime, MesaRedonda mesa) {
        this.i = i;
        this.tempoParaComer = timeToEat;
        this.tempoParaPensar = thinkTime;
        this.mesa = mesa;
        this.estado = 0;
        
        new Thread(this, "Filósofo " + i).start();
    }
    
    private void pensar() {
        try {
            this.estado = PENSANDO;
            Thread.sleep(this.tempoParaComer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private void pegarTalheres() {
        this.estado = COM_FOME;
        this.mesa.pegaTalheres(i);
    }
    
    private void soltarTalheres() {
        this.mesa.soltaTalheres(i);
        this.estado = PENSANDO;
    }
    
    private void comer() {
        try {
            this.estado = COMENDO;
            Thread.sleep(this.tempoParaComer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                pensar();
                pegarTalheres();
                comer();
                soltarTalheres();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
