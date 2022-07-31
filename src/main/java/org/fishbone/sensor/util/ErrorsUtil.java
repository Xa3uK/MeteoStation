package org.fishbone.sensor.util;

import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ErrorsUtil {
    public static void returnErrors(BindingResult bindingResult){
        StringBuilder errorMsg = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            errorMsg.append(error.getField())
                .append(" - ").append(error.getDefaultMessage())
                .append(";");
        }
        throw new MeasurementException(errorMsg.toString());
    }
}
