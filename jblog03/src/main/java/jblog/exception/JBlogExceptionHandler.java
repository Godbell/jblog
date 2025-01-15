package jblog.exception;

import java.util.Arrays;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

@ControllerAdvice
public class JBlogExceptionHandler {
    @ExceptionHandler(HttpStatusCodeException.class)
    public String handleNotFoundException(NotFoundException e) {
        return "errors/" + e.getStatusCode().value();
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Model model, Exception e) {
        e.printStackTrace(System.out);
        model.addAttribute("stackTrace", Arrays.toString(e.getStackTrace()));
        return "errors/exception";
    }
}
