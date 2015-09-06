package com.oleg.chat.web.handlers;

import com.cmsu.accrual.web.client.controllers.ClientController;
import com.cmsu.accrual.web.common.beans.OperationResult;
import com.cmsu.accrual.web.common.beans.StatusCode;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(assignableTypes = {ClientController.class})
public class PageExceptionHandlers {

    @RequestMapping()
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> messages = convertToMessagesMap(ex);
        OperationResult operationResult = new OperationResult(StatusCode.NOT_VALID, messages);
        return resultViewPage(operationResult);
    }

    @RequestMapping()
    @ExceptionHandler(Exception.class)
    public ModelAndView otherException(Exception ex) {
        OperationResult operationResult = new OperationResult(StatusCode.SERVER_ERROR, ex.getLocalizedMessage());
        return resultViewPage(operationResult);
    }

    private ModelAndView resultViewPage(OperationResult operationResult) {
        ModelAndView modelAndView = new ModelAndView("resultView");
        modelAndView.addObject("operationResult", operationResult);
        return modelAndView;
    }

    private Map<String, String> convertToMessagesMap(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> messages = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            messages.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        for (ObjectError globalError : bindingResult.getGlobalErrors()) {
            messages.put(globalError.getObjectName(), globalError.getDefaultMessage());
        }
        return messages;
    }


}
