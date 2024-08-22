package com.fastcamp.programming.dmaker.service;

import com.fastcamp.programming.dmaker.code.StatusCode;
import com.fastcamp.programming.dmaker.constant.DMakerConstant;
import com.fastcamp.programming.dmaker.dto.CreateDeveloper;
import com.fastcamp.programming.dmaker.dto.DeveloperDetailDto;
import com.fastcamp.programming.dmaker.dto.DeveloperDto;
import com.fastcamp.programming.dmaker.dto.EditDeveloper;
import com.fastcamp.programming.dmaker.entity.Developer;
import com.fastcamp.programming.dmaker.entity.RetiredDeveloper;
import com.fastcamp.programming.dmaker.exception.DMakerException;
import com.fastcamp.programming.dmaker.repository.DeveloperRepository;
import com.fastcamp.programming.dmaker.repository.RetiredDeveloperRepository;
import com.fastcamp.programming.dmaker.type.DeveloperLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.fastcamp.programming.dmaker.constant.DMakerConstant.MAX_JUNIOR_EXPERIENCE_YEARS;
import static com.fastcamp.programming.dmaker.constant.DMakerConstant.MIN_SEIOR_EXPERIENCE_YEARS;
import static com.fastcamp.programming.dmaker.exception.DMakerErrorCode.*;
import static com.fastcamp.programming.dmaker.type.DeveloperLevel.*;

@Service
@RequiredArgsConstructor
public class DMakerService {

    private final DeveloperRepository developerRepository;
    private final RetiredDeveloperRepository retiredDeveloperRepository;

    private final EntityManager em;

    // ACID
    // Atomicity - 트랜잭션 내의 모든 작업이 성공적으로 완료되거나 , 하나라도 실패하면 전체가 롤백됩니다.
    // Consistent - 트랜잭션이 완료되면 데이터베이스는 일관된 상태를 유지합니다.
    // Isolation - 트랜잭션이 수행되는 동안 다른 트랜잭션이 간섭하지 않도록 격리 수준을 설정할 수 있습니다.
    // Durability - 트랜잭션이 성공적으로 완료되면 그 결과가 영구적으로 반영됩니다.
    @Transactional
    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request) {

        request.getDeveloperLevel().validateExperienceYears(request.getExperienceYears());
//      vailDateDeveloperLevel(request.getDeveloperLevel(), request.getExperienceYears());

        developerRepository.findByMemberId(request.getMemberId())
                .ifPresent((developer -> {
                    throw new DMakerException(DUPLICATED_MEMBER_ID);
                }));

        try {

            // 비지니스 로직 시작
            Developer developer = Developer.builder()
                    .developerLevel(request.getDeveloperLevel())
                    .developerSkillType(request.getDeveloperSkillType())
                    .experienceYears(request.getExperienceYears())
                    .statusCode(StatusCode.EMPLOYED)
                    .memberId(request.getMemberId())
                    .name(request.getName())
                    .age(request.getAge())
                    .build();

            developerRepository.save(developer);

            return CreateDeveloper.Response.fromEntity(developer);

        }catch (ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Transactional
    public List<DeveloperDto> getAllEmployeDevelopers() {

        return developerRepository.findDevelopersByStatusCodeEquals(StatusCode.EMPLOYED)
                .stream()
                .map(DeveloperDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public DeveloperDetailDto getFindMemberIdDevelopers(String memberId) {

        return developerRepository.findByMemberId(memberId)
                .map(DeveloperDetailDto::fromEntity)
                .orElseThrow(()-> new DMakerException(NO_DEVELOPER_FOUND));
    }

    @Transactional
    public DeveloperDetailDto editDeveloper(String memberId, EditDeveloper.Request request) {
          request.getDeveloperLevel().validateExperienceYears(request.getExperienceYears());
//        vailDateDeveloperLevel(request.getDeveloperLevel() , request.getExperienceYears());

        Developer developer = developerRepository.findByMemberId(memberId)
                .orElseThrow(()-> new DMakerException(NO_DEVELOPER_FOUND));

        developer.setDeveloperLevel(request.getDeveloperLevel());
        developer.setDeveloperSkillType(request.getDeveloperSkillType());
        developer.setExperienceYears(request.getExperienceYears());

        return DeveloperDetailDto.fromEntity(developerRepository.save(developer));
    }

/*    private static void vailDateDeveloperLevel(DeveloperLevel developerLevel, Integer experienceYears) {

//      3차 리펙토링
        developerLevel.validateExperienceYears(experienceYears);

//      2차 리펙토링

//        if(experienceYears < developerLevel.getMinExperienceYears() ||
//                experienceYears > developerLevel.getMaxExperienceYears()){
//            throw new DMakerException(LEVER_EXPERIENCE_YEARS_NOT_MATCHED);
//        }

//      1차 리펙토링

//        if(developerLevel == SENIOR && experienceYears < MIN_SEIOR_EXPERIENCE_YEARS){
//            throw new DMakerException(LEVER_EXPERIENCE_YEARS_NOT_MATCHED);
//        }
//
//        if(developerLevel == JUNGNIOR && (experienceYears < MAX_JUNIOR_EXPERIENCE_YEARS || experienceYears > MIN_SEIOR_EXPERIENCE_YEARS)){
//            throw new DMakerException(LEVER_EXPERIENCE_YEARS_NOT_MATCHED);
//        }
//
//        if(developerLevel == JUNIOR && experienceYears > MAX_JUNIOR_EXPERIENCE_YEARS){
//            throw new DMakerException(LEVER_EXPERIENCE_YEARS_NOT_MATCHED);
//        }

    }*/

    @Transactional
    public DeveloperDetailDto deleteDeveloper(String memberId) {
        // delete logic
        Developer developer = developerRepository.findByMemberId(memberId)
                .orElseThrow(()-> new DMakerException(NO_DEVELOPER_FOUND));

        developer.setStatusCode(StatusCode.RETIRED);

        RetiredDeveloper retiredDeveloper = RetiredDeveloper.builder()
                .memberId(memberId)
                .name(developer.getName())
                .build();

        retiredDeveloperRepository.save(retiredDeveloper);
        return DeveloperDetailDto.fromEntity(developerRepository.save(developer));
    }
}
