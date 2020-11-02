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
public class Paciente implements Runnable {

    private SalaEspera sala;

    public Paciente(SalaEspera s) {
        this.sala = s;
    }

    public void run() {
        if (sala.entrar()) {
            sala.sentarse();
            sala.desayunar();
        } else {
            // Por como esta implementado, puede que nunca entre por este lado
            System.out.println(Thread.currentThread().getName() + " no encontro lugar, intentara otro dia.");
        }

    }

}
