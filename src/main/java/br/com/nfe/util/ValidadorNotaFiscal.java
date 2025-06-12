package br.com.nfe.util;

import java.math.BigDecimal;

public class ValidadorNotaFiscal {

    public static boolean isNotaComValorZero(BigDecimal valor) {
        return valor == null || valor.compareTo(BigDecimal.ZERO) == 0;
    }

    public static boolean isNCMValido(String ncm) {
        return ncm != null && ncm.matches("^\\d{8}$") && !ncm.matches("^0+$");
    }

    public static boolean isCFOPValido(String cfop) {
        try {
            int cod = Integer.parseInt(cfop);
            return (cod >= 1000 && cod <= 9999);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}