package com.vetimeline.api.infrastructure.auth.entryPoint;

import com.vetimeline.api.domain.auth.JwtManager;
import com.vetimeline.api.domain.auth.JwtPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DecodeJwtController {
    @Autowired
    private JwtManager jwtManager;

    @RequestMapping(value = "auth/token", method = RequestMethod.GET)
    public JwtPayload decode(@RequestParam() String token) {
        return jwtManager.decode(token);
    }
}
