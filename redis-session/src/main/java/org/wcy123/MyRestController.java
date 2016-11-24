package org.wcy123;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wcy123.api.Protos;

import com.googlecode.protobuf.format.JsonFormat;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MyRestController {
    @RequestMapping(path = "/login", method = POST)
    public ResponseEntity<Protos.LoginResponse> login(HttpServletRequest request,
            HttpServletResponse response, @RequestBody Protos.LoginRequest command) {
        log.info("input is {}", new JsonFormat().printToString(command));
        return ResponseEntity.ok().body(Protos.LoginResponse.newBuilder()
                .setResult(Protos.LoginResponse.LoginResult.OK)
                .build());
    }
}
