package com.employee.management.service.impl;

import com.employee.management.model.UsernameRequest;
import com.employee.management.model.ValidateKeyRequest;
import com.employee.management.model.ValidateKeyResponse;
import com.employee.management.service.GAuthService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
@RequiredArgsConstructor
public class GAuthServiceImpl implements GAuthService {
    private final GoogleAuthenticator googleAuthenticator;
    @Override
    @SneakyThrows
    public void generate(UsernameRequest usernameRequest, HttpServletResponse response) {
        String username= usernameRequest.getName();
        String qrUrl=generateQrURL(username);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrUrl, BarcodeFormat.QR_CODE, 200, 200);
        ServletOutputStream outputStream = response.getOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        outputStream.close();
    }

    @Override
    public ValidateKeyResponse validateKey(ValidateKeyRequest validateCodeDto) {
        boolean authorizedUser = googleAuthenticator.authorizeUser(validateCodeDto.getUsername(), validateCodeDto.getCode());
        return new ValidateKeyResponse(authorizedUser);

    }

    private String generateQrURL(String username) {
        final GoogleAuthenticatorKey key = googleAuthenticator.createCredentials(username);
        return GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL("Employee-Management-App", username, key);
    }

}
