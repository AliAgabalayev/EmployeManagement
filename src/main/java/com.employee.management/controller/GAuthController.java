//package com.employee.management.controller;
//
//import com.employee.management.model.UsernameRequest;
//import com.employee.management.model.ValidateKeyRequest;
//import com.employee.management.model.ValidateKeyResponse;
//import com.employee.management.service.GAuthService;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("${root.url}/auth-g")
//public class GAuthController {
//    private final GAuthService gAuthService;
//
//    @PostMapping("/generate-qr")
//    public void generate(@RequestBody UsernameRequest usernameRequest, HttpServletResponse response) {
//        gAuthService.generate(usernameRequest, response);
//    }
//
//    @PostMapping("/validate-otp/key")
//    public ValidateKeyResponse validateKey(@RequestBody ValidateKeyRequest validateCodeDto) {
//        return gAuthService.validateKey(validateCodeDto);
//    }
//
////    @PostMapping("/recover-qr")
////    public void recoverQr(@RequestBody UsernameRequest usernameRequest) {
////        gAuthService.recoverQr(usernameRequest);
////    }
//
//}
