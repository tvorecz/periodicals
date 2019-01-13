/**
 * Encode the password-string before adding information to data source/
 *
 * @autor Dzmitry Zorich
 * @version 1.1
 */

package by.training.zorich.service.util.impl;

import by.training.zorich.service.util.PasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncodeImpl implements PasswordEncoder {
    private final String salt = "$2a$10$llw0G6IyibUob8h5XRt9xuRczaGdCm/AiV6SSjf5v78XS824EGbh.";

    @Override
    public String encodePassword(String realPassword) {
        String hashedPassword = BCrypt.hashpw(realPassword, salt);

        return hashedPassword;
    }
}
