package com.duong.hau.controller;

import com.duong.hau.convertDTO.CompanyConvert;
import com.duong.hau.dto.CompanyDTO;
import com.duong.hau.entity.Company;
import com.duong.hau.exception.APIResponse;
import com.duong.hau.exception.FileDuplicateException;
import com.duong.hau.exception.ResourceNotFoundException;
import com.duong.hau.repository.CompanyRepository;
import com.duong.hau.service.CompanyService;
import com.duong.hau.ultil.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1")
public class CompanyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public ResponseEntity<APIResponse<List<CompanyDTO>>> getAllCompanies() {
        Optional<List<CompanyDTO>> companyDTOS = companyService.findAllCompany();
        if (companyDTOS.isPresent()) {
            return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), "get all company success!", companyDTOS.get()));
        }
        LOGGER.info(" No company found!");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanyDTO> getEmployeeById(@PathVariable(value = "id") Long companyId)
            throws ResourceNotFoundException {

        LOGGER.info("Find by id company :: ", companyId);
        CompanyDTO companyDTO = companyService.getCompanyById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + companyId));
        LOGGER.info("Find by id company success!");
        return ResponseEntity.ok().body(companyDTO);
    }

    @PostMapping("/companies")
    public ResponseEntity<CompanyDTO> createCompany(@Valid @RequestBody CompanyDTO companyDTO) {

        if (companyDTO != null && companyService.checkExist(companyDTO))
            throw new FileDuplicateException("Company is already exist!");
        LOGGER.info("starting save company...");
        Optional<CompanyDTO> createCompany = companyService.createCompany(companyDTO);
        return ResponseEntity.ok().body(createCompany.get());
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable(value = "id") Long companyId,
                                                    @Valid @RequestBody CompanyDTO companyDTO) throws ResourceNotFoundException {

        LOGGER.info("starting update company...");
        CompanyDTO updateCompany = companyService.updateCompany(companyId, companyDTO)
                .orElseThrow(() -> new ResourceNotFoundException(Constant.COMPANY_NOT_FOUNT + companyId));
        return ResponseEntity.ok(updateCompany);
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<CompanyDTO> deleteCompany(@PathVariable(value = "id") Long companyId)
            throws ResourceNotFoundException {
        LOGGER.info("start get company by id!");
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException(Constant.COMPANY_NOT_FOUNT + companyId));
        companyRepository.delete(company);
        LOGGER.info("delete company success!");
        return ResponseEntity.ok(CompanyConvert.convertCompanyToCompanyDTO(company));
    }
}
