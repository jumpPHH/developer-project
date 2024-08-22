package com.fastcamp.programming.dmaker.type;

import com.fastcamp.programming.dmaker.constant.DMakerConstant;
import com.fastcamp.programming.dmaker.exception.DMakerException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Function;

import static com.fastcamp.programming.dmaker.constant.DMakerConstant.MAX_JUNIOR_EXPERIENCE_YEARS;
import static com.fastcamp.programming.dmaker.constant.DMakerConstant.MIN_SEIOR_EXPERIENCE_YEARS;
import static com.fastcamp.programming.dmaker.exception.DMakerErrorCode.LEVER_EXPERIENCE_YEARS_NOT_MATCHED;

@AllArgsConstructor
@Getter
public enum DeveloperLevel {

/*    NEW("new developer" , 0 , 0),
    JUNIOR("new junior developer" , 1 , MAX_JUNIOR_EXPERIENCE_YEARS),
    JUNGNIOR("new jungior developer" ,
                            MAX_JUNIOR_EXPERIENCE_YEARS +1
                        ,   MIN_SEIOR_EXPERIENCE_YEARS -1),
    SENIOR("new senior developer" , MIN_SEIOR_EXPERIENCE_YEARS , 50);*/


    NEW("new developer" , years -> years == 0),
    JUNIOR("new junior developer" , years -> years <= MAX_JUNIOR_EXPERIENCE_YEARS),
    JUNGNIOR("new jungior developer" , years -> years > MAX_JUNIOR_EXPERIENCE_YEARS
                                                && years < MIN_SEIOR_EXPERIENCE_YEARS),
    SENIOR("new senior developer" , years -> years >= MIN_SEIOR_EXPERIENCE_YEARS);

    private final String description;
    private final Function<Integer, Boolean> validateFunction;

    public void validateExperienceYears(Integer years) {
        if(validateFunction.apply(years)){
            throw new DMakerException(LEVER_EXPERIENCE_YEARS_NOT_MATCHED);
        }

    }
}
