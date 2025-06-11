package br.com.nfe.util;

public class ValidadorCNPJ {
    public static boolean isCnpjValido(String cnpj) {
        if (cnpj == null) return false;

        // Remove tudo que não é número
        cnpj = cnpj.replaceAll("[^\\d]", "");

        if (cnpj.length() != 14) return false;

        // Verifica sequências inválidas (ex: 00000000000000, 11111111111111 ...)
        if (cnpj.chars().distinct().count() == 1) return false;

        try {
            int[] peso1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] peso2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            // Calcular primeiro dígito verificador
            int dig1 = calcularDigito(cnpj.substring(0, 12), peso1);

            // Calcular segundo dígito verificador
            int dig2 = calcularDigito(cnpj.substring(0, 12) + dig1, peso2);

            return cnpj.equals(cnpj.substring(0, 12) + dig1 + dig2);
        } catch (Exception e) {
            return false;
        }
    }

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int i = 0; i < str.length(); i++) {
            soma += (str.charAt(i) - '0') * peso[i];
        }
        int mod = soma % 11;
        return (mod < 2) ? 0 : 11 - mod;
    }
}