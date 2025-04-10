package com.example.formapi.repository.application;

import com.example.formapi.domain.application.CompanyRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRoleRepository extends JpaRepository<CompanyRole, Long> {

    Optional<CompanyRole> findByName(String name);

    List<CompanyRole> getCompanyRoleByCompany_Id(Long id);
}
