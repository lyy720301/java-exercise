package cn.lz.spring.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * handle controller methods parameter validation exceptions
     *
     * @param e exception
     * @return wrapped result
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map> handle(ConstraintViolationException e) {
        log.error("controller exception error => ", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder builder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            builder.append(violation.getMessage()).append("; ");
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("message", builder.toString());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 处理所有不可知的异常，作为全局的兜底
    @ExceptionHandler
    public ResponseEntity handleException(Exception e) {
        log.error("controller exception error => ", e);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("msg", e.getMessage());
        map.put("code", 1);
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("controller exception error => ", ex);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("msg", ex.getLocalizedMessage());
        map.put("code", 1);
        body = body == null ? map : body;
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
