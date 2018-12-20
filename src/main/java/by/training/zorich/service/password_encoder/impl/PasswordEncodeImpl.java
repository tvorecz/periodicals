package by.training.zorich.service.password_encoder.impl;

import by.training.zorich.service.password_encoder.PasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncodeImpl implements PasswordEncoder {
    private final String salt = "$2a$10$llw0G6IyibUob8h5XRt9xuRczaGdCm/AiV6SSjf5v78XS824EGbh.";

    @Override
    public String encodePassword(String realPassword) {
        String hashedPassword = BCrypt.hashpw(realPassword, salt);

        return hashedPassword;
    }
}
