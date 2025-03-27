package org.one.afternoon.domain;
import lombok.Data;
@Data
public class ChangePasswordRequest {
    private String username;
    private String password;
    private String newPassword;
}