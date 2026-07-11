package me.loyalty.loyaltylogin.security;

import java.security.MessageDigest;

public class PasswordManager {


    public static String hash(String password) {

        try {

            MessageDigest md =
                    MessageDigest.getInstance("SHA-256");


            byte[] hash =
                    md.digest(password.getBytes());


            StringBuilder hex =
                    new StringBuilder();


            for(byte b : hash) {

                hex.append(
                        String.format("%02x", b)
                );

            }


            return hex.toString();


        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }


    public static boolean verify(
            String password,
            String hashedPassword
    ) {

        return hash(password)
                .equals(hashedPassword);

    }

}
