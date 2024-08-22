package com.fastcamp.programming.dmaker.repository;

import com.fastcamp.programming.dmaker.code.StatusCode;
import com.fastcamp.programming.dmaker.dto.EditDeveloper;
import com.fastcamp.programming.dmaker.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeveloperRepository
        extends JpaRepository<Developer, Long> {
    /*
     @Optional
     , 값이 존재할 수도 있고 존재하지 않을 수도 있는 컨테이너를 제공하여 null 참조를 처리하는 데 유용합니다.
     이를 통해 null 값을 피하고 명시적으로 값이 존재하는지 여부를 처리할 수 있습니다.
    */
    Optional<Developer> findByMemberId(String memberId);
    List<Developer> findDevelopersByStatusCodeEquals(StatusCode statusCode);
}
