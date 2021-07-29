package com.arridhaamrad.authentication.dto.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Getter
@Setter
public class FieldsError {
   private List<Map<String, Object>> errors = new ArrayList<>();
   private final Date date = new Date();
   private final int status = HttpStatus.BAD_REQUEST.value();
}
