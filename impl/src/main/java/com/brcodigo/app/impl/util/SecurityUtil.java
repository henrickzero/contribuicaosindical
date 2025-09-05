package com.brcodigo.app.impl.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.*;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class SecurityUtil {
    private static final String ZONE_ID = "America/Sao_Paulo";
    private static final String BR_FORMAT = "dd/MM/yyyy HH:mm:ss";

    @SneakyThrows
    public static String generateHash(String input1, String input2) {
        try {
            // Concatena as duas strings
            String combinedInput = input1 + input2;

            // Obtem uma instância do gerador de hash SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Aplica o hash no input combinado
            byte[] hash = digest.digest(combinedInput.getBytes());

            // Converte o array de bytes em uma string hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
