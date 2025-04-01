package com.example.formapi.repository.application;

import com.example.formapi.domain.application.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumber(String phoneNumber);

    @Query("""
            select usr from User usr join usr.companies company
            where company.id = :companyId
            and com.example.formapi.domain.enumeration.UserType.COMPLIANCE member of usr.userTypes
            """)
    List<User> findAllComplianceUsersForCompany(@Param("companyId") Long companyId);
}
