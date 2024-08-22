package com.fastcamp.programming.dmaker.controller;

import com.fastcamp.programming.dmaker.dto.*;
import com.fastcamp.programming.dmaker.exception.DMakerErrorResponse;
import com.fastcamp.programming.dmaker.exception.DMakerException;
import com.fastcamp.programming.dmaker.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DMakerController {

    private final DMakerService dmakerService;

    @GetMapping("/developers")
    public List<DeveloperDto> getAllDevelopers() {
        log.info("Get all developers");

        return dmakerService.getAllEmployeDevelopers();
    }

    @GetMapping("/developers/{memberId}")
    public DeveloperDetailDto getFindMemberIdDevelopers(
            @PathVariable String memberId
    ) {
        log.info("Get developers by memberId");

        return dmakerService.getFindMemberIdDevelopers(memberId);
    }

    @PutMapping("/developers/{memberId}")
    public DeveloperDetailDto editDeveloper(
            @PathVariable String memberId ,@Valid @RequestBody EditDeveloper.Request request
    ){
        log.info("Get updated developer info by memberId");

        return dmakerService.editDeveloper(memberId , request);
    }

    @DeleteMapping("/developers/{memberId}")
    public void deleteDeveloper(
            @PathVariable String memberId
    ) {
        log.info("Delete a developer by memberId");

        dmakerService.deleteDeveloper(memberId);
    }

    @PostMapping("/developers/create")
    public CreateDeveloper.Response createDeveloper(
            @Valid @RequestBody CreateDeveloper.Request request) {

        log.info("Create a developer");
        log.info("Request: {}", request);

        return dmakerService.createDeveloper(request);
    }
}
