package com.team2.service;

import com.team2.model.entity.PasswordResetToken;

public interface PasswordResetTokenService {
    void createAndSendPasswordResetToken(String email) throws Exception;
    PasswordResetToken findByToken(String token);
    void removePasswordResetToken(PasswordResetToken passwordResetToken);
    boolean isValidToken(String token);
    void resetPassword(String token, String newPassword);
}
