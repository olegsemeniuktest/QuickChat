package com.oleg.chat.common.beans;

public class OperationResult<RESULT> {

    private boolean success;
    private String message = "";
    private RESULT resultObject;

    public OperationResult() {
    }

    public OperationResult(boolean success) {
        this.success = success;
    }

    public OperationResult(RESULT object) {
        this.success = true;
        this.resultObject = object;
    }

    public OperationResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public OperationResult(boolean success, RESULT object) {
        this.success = success;
        this.resultObject = object;
    }

    public OperationResult(boolean success, String message, RESULT object) {
        this.success = success;
        this.message = message;
        this.resultObject = object;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RESULT getResultObject() {
        return resultObject;
    }

    public void setResultObject(RESULT resultObject) {
        this.resultObject = resultObject;
    }

}
