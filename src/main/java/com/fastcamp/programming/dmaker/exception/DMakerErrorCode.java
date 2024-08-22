package com.fastcamp.programming.dmaker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DMakerErrorCode {

    NO_DEVELOPER_FOUND("Developer not found"),
    DUPLICATED_MEMBER_ID("Member ID already exists"),
    LEVER_EXPERIENCE_YEARS_NOT_MATCHED("Developer level and experience years do not match"),
    INTERNAL_SERVER_ERROR("Internal Server Error"),
    INVALID_REQUEST("Invalid Request");

    private final String message;
}
