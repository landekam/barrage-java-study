package com.setronica.eventing.web;

import com.setronica.eventing.dto.ApplicationExceptionDto;
import com.setronica.eventing.exceptions.ApplicationLogicException;
import com.setronica.eventing.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.setronica.eventing.exceptions.BadRequestException;

@Slf4j
@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler({ ApplicationLogicException.class })
    public ResponseEntity<ApplicationExceptionDto> handleApplicationLogicException(ApplicationLogicException ex) {
        ApplicationExceptionDto exceptionDto = new ApplicationExceptionDto(ex.getMessage());
        log.error("Application logic error: ", ex);
        return new ResponseEntity<>(exceptionDto, HttpStatusCode.valueOf(500));
    }

    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<ApplicationExceptionDto> handleNotFoundException(NotFoundException ex) {
        ApplicationExceptionDto exceptionDto = new ApplicationExceptionDto(ex.getMessage());
        log.info("Not found error: ", ex);
        return new ResponseEntity<>(exceptionDto, HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler({ BadRequestException.class })
    public ResponseEntity<ApplicationExceptionDto> handleBadRequestException(BadRequestException ex) {
        ApplicationExceptionDto exceptionDto = new ApplicationExceptionDto(ex.getMessage());
        log.info("Bad request error: ", ex);
        return new ResponseEntity<>(exceptionDto, HttpStatusCode.valueOf(400));
    }
}
