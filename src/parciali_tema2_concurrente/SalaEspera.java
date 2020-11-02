/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parciali_tema2_concurrente;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Fernando Iraira <fmiraira@gmail.com>
 */
public class SalaEspera {

    private int capSalaEspera;
    private Semaphore semRecepcionista;
    private Semaphore semAtendido;
    private Semaphore semControl;
    private Semaphore semExtraccion;
    private Semaphore mutex;
    private Semaphore semDesayunar;

    public SalaEspera(int cap) {
        this.capSalaEspera = cap;
        this.semRecepcionista = new Semaphore(0, true);
        this.semAtendido = new Semaphore(0, true);
        this.semControl = new Semaphore(0, true);
        this.semExtraccion = new Semaphore(0, true);
        this.mutex = new Semaphore(1, true);
        this.semDesayunar = new Semaphore(0, true);
    }

    public boolean entrar() {
        boolean puedeEntrar = false;

        try {
            this.mutex.acquire();
            this.semRecepcionista.release();
            this.semAtendido.acquire();

            if (this.capSalaEspera != 0) {
                System.out.println("La recepcionista ingreso al paciente.");
                this.capSalaEspera--;
                puedeEntrar = true;
            }

        } catch (Exception e) {
        }

        this.mutex.release();

        return puedeEntrar;
    }

    public void atender() {
        System.out.println("La recepcionista esta realizando otras tareas...");

        try {
            this.semRecepcionista.acquire();
            System.out.println("La recepcionista esta ingresando al paciente...");
            this.realizarTarea();
            this.semAtendido.release();
        } catch (Exception e) {
        }
    }

    public void sentarse() {
        System.out.println(Thread.currentThread().getName() + " esta en la sala de espera.");
        this.semControl.release();
        //PODRIA LIBERAR EL ASIENTO ACA
    }

    public void desayunar() {
        try {
            this.semDesayunar.acquire();
            System.out.println(Thread.currentThread().getName() + " esta desayunando...");
            this.realizarTarea();
            System.out.println(Thread.currentThread().getName() + " termino de desayunar y se fue.");
        } catch (Exception e) {
        }
    }

    public void controlar() {
        try {
            this.semControl.acquire();

            this.capSalaEspera++;

            System.out.println("Se esta realizando el control de un paciente...");
            this.realizarTarea();
            System.out.println("Se termino de realizar el control del paciente.");
            this.semExtraccion.release();
        } catch (Exception e) {
        }
    }

    public void extraer() {
        try {
            this.semExtraccion.acquire();
            System.out.println("Se esta realizando la extraccion al paciente...");
            this.realizarTarea();
            System.out.println("Se termino de realizar la extraccion.");
            System.out.println("El especialista entrega el certificado al paciente y le indica que vaya a la cafeteria.");
            this.semDesayunar.release();
        } catch (Exception e) {
        }
    }

    // METODOS IMPORTANTES PARA OTROS PARCIALES
    private void realizarTarea() {
        try {
            Thread.sleep(this.randomHasta(5));
        } catch (Exception e) {
        }
    }

    private int randomHasta(int maximo) {
        Random rand = new Random();

        return ((rand.nextInt(maximo)) + 1) * 1000;
    }
}
