package com.fastcamp.programming.dmaker.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DMakerErrorResponse {
    private DMakerErrorCode errorCode;
    private String errorMessage;

}
