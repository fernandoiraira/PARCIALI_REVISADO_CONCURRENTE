/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parciali_tema2_concurrente;

/**
 *
 * @author Fernando Iraira <fmiraira@gmail.com>
 */
public class EspecialistaExtraccion implements Runnable {

    private SalaEspera sala;

    public EspecialistaExtraccion(SalaEspera s) {
        this.sala = s;
    }

    public void run() {

        while (true) {
            this.sala.extraer();
        }

    }

}
