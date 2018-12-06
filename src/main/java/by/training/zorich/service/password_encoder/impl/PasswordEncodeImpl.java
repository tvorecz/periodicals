package by.training.zorich.service.password_encoder.impl;

import by.training.zorich.service.password_encoder.PasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncodeImpl implements PasswordEncoder {
    private final String salt = "fjkfhadhwydgawdakjdhasdjgwtydgqd";

    @Override
    public String encodePassword(String realPassword) {
        String hashedPassword = BCrypt.hashpw(realPassword, salt);

        return hashedPassword;
    }
}
