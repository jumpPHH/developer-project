package com.fastcamp.programming.dmaker.exception;

import lombok.Getter;

@Getter
public class DMakerException extends RuntimeException  {

    private final DMakerErrorCode dMakerErrorCode;
    private final String errorMessage;

    public DMakerException(DMakerErrorCode dMakerErrorCode) {
        super(dMakerErrorCode.getMessage());
        this.dMakerErrorCode = dMakerErrorCode;
        this.errorMessage = dMakerErrorCode.getMessage();
    }

    public DMakerException(DMakerErrorCode dMakerErrorCode , String errorMessage) {
        super(errorMessage);
        this.dMakerErrorCode = dMakerErrorCode;
        this.errorMessage = errorMessage;

    }
}
