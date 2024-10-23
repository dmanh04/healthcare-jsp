/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common.utils;

/**
 *
 * @author Admin
 */
public class IntegerUtils {

    public static boolean checkInteger(Integer data) {
        return data != null;
    }

    public static boolean isPositive(Integer data) {
        return data != null && data > 0;
    }
}
