package jblog.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

@ControllerAdvice
public class JBlogExceptionHandler {
    @ExceptionHandler(HttpStatusCodeException.class)
    public String handleNotFoundException(NotFoundException e) {
        return "errors/" + e.getStatusCode().value();
    }
}
