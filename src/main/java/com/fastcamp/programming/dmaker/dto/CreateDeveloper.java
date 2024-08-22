package com.fastcamp.programming.dmaker.dto;

import com.fastcamp.programming.dmaker.entity.Developer;
import com.fastcamp.programming.dmaker.type.DeveloperLevel;
import com.fastcamp.programming.dmaker.type.DeveloperSkillType;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateDeveloper {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        @NotNull
        private DeveloperLevel developerLevel;
        @NotNull
        private DeveloperSkillType developerSkillType;
        @NotNull
        @Min(0)
        @Max(20)
        private Integer experienceYears;

        @NotNull
        @Size(min = 3, max = 50 , message = "MemberId Size should be between 3 and 50 characters")
        private String memberId;
        @NotNull
        @Size(min = 3, max = 50 , message = "Name Size should be between 3 and 50 characters")
        private String name;
        private Integer age;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private DeveloperLevel developerLevel;
        private DeveloperSkillType developerSkillType;
        private Integer experienceYears;

        private String memberId;
        private String name;
        private Integer age;

        public static Response fromEntity(Developer developer){
            return Response.builder()
                   .developerLevel(developer.getDeveloperLevel())
                   .developerSkillType(developer.getDeveloperSkillType())
                   .experienceYears(developer.getExperienceYears())
                   .memberId(developer.getMemberId())
                   .name(developer.getName())
                   .age(developer.getAge())
                   .build();
        }

    }
}
