package com.arridhaamrad.authentication.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<T> {
   private int status;
   private List<String> messages = new ArrayList<>();
   private T payload;
}
