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
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int capSalaEspera = 5;
        int cantPacientes = 12;
        SalaEspera sala = new SalaEspera(capSalaEspera);

        Recepcionista r = new Recepcionista(sala);
        Thread recepcionista = new Thread(r);
        recepcionista.start();

        EspecialistaControl c = new EspecialistaControl(sala);
        Thread e1 = new Thread(c);
        e1.start();

        EspecialistaExtraccion e = new EspecialistaExtraccion(sala);
        Thread e2 = new Thread(e);
        e2.start();

        for (int i = 1; i <= cantPacientes; i++) {
            Paciente p = new Paciente(sala);
            Thread t = new Thread(p, "Paciente " + i);
            t.start();
        }
    }

}
