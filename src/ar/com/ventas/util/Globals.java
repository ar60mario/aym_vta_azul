package ar.com.ventas.util;

/**
 *
 * @author argia
 */
public class Globals {
    public static ThreadLocal<String> USR_ORDEN;
    public static ThreadLocal<String> USR_NOMBRE;

    static {
        USR_ORDEN = new ThreadLocal<>();
        USR_NOMBRE = new ThreadLocal<>();
    }
}
