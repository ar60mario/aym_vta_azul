/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ventas.util;

/**
 *
 * @author argia
 */
public class Globals {
    public static ThreadLocal<String> USR;

    static {
        USR = new ThreadLocal<>();
    }
}
