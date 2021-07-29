package com.arridhaamrad.authentication.dto.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GeneralExceptionData {
   private String message;
   private Date date = new Date();
   private int status;
}
