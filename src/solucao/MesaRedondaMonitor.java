/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solucao;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Gabriel
 */
public class MesaRedondaMonitor implements MesaRedonda{
    
    private final int PENSANDO = 0;
    private final int COM_FOME = 1;
    private final int COMENDO = 2;

    private final int sizeMesa;

    private final int[] mesa;
    private final Integer[] filosofos;

    MesaRedondaMonitor(int tableSize) {
        this.sizeMesa = tableSize;

        this.filosofos = new Integer[this.sizeMesa];
        this.mesa = new int[this.sizeMesa];

        Arrays.fill(this.filosofos, 0);
        Arrays.fill(this.filosofos, this.PENSANDO);
    }

    private int getEsquerda(int i) {
        return (i + this.sizeMesa - 1) % this.sizeMesa;
    }

    private int getDireita(int i) {
        return (i + 1) % this.sizeMesa;
    }

    public void pegaTalheres(int i) {
        synchronized (filosofos[i]) {
            mesa[i] = COM_FOME;

            printAction(i, "tentando pegar os talheres", "COM_FOME", "COMENDO");
            testa(i);

            while (mesa[i] != COMENDO) {
                try {
                    filosofos[i].wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void soltaTalheres(int i) {
        synchronized (filosofos[i]) {
            printAction(i, "soltando os talheres", "COMENDO", "PENSANDO");
            mesa[i] = PENSANDO;
            testa(getEsquerda(i));
            testa(getDireita(i));
        }
    }

    private void testa(int i) {
        if (mesa[i] == COM_FOME && mesa[getEsquerda(i)] != COMENDO && mesa[getDireita(i)] != COMENDO) {
            printAction(i, "pegando os talheres", "COM_FOME", "COMENDO");
            mesa[i] = COMENDO;

            synchronized (filosofos[i]) {
                filosofos[i].notify();
            }

        } else if (mesa[i] == COM_FOME) {
            printAction(i, String.format("falhando em pegar os talheres, pois o Filósofo %s já está COMENDO",
                    mesa[getEsquerda(i)] == COMENDO ? getEsquerda(i) + 1 : getDireita(i) + 1), "COM_FOME", "COM_FOME");
        }
    }

    private void printAction(int id, String acao, String estado_atual, String proximo_estado) {
        System.out.printf("Filósofo %d > está %s, está %s e estará %s.%n", id + 1, estado_atual, acao, proximo_estado);
    }
    
}
