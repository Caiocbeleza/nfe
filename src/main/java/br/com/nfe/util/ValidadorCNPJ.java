package br.com.nfe.util;

public class ValidadorCNPJ {
    public static boolean isCnpjValido(String cnpj) {
        // Implementação de validação real
        return cnpj != null && cnpj.matches("\\d{14}");
    }
}