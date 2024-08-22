package com.fastcamp.programming.dmaker.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {

        EMPLOYED("EMPLOYED"),
        RETIRED("RETIRED");

        private final String description;
}
