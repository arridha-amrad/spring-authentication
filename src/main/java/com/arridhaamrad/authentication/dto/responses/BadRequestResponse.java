package com.arridhaamrad.authentication.dto.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BadRequestResponse {
   private final int status = HttpStatus.BAD_REQUEST.value();
   private final Instant date = Instant.now();
   private List<String> messages = new ArrayList<>();
}
