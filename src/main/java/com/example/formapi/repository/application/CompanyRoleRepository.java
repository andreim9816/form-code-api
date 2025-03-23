package com.example.formapi.repository.application;

import com.example.formapi.domain.application.CompanyRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRoleRepository extends JpaRepository<CompanyRole, Long> {

    List<CompanyRole> getCompanyRoleByCompany_Id(Long id);
}
