package com.aa.CouponsProject.repos;

import com.aa.CouponsProject.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {

    boolean existsByEmail(String email);
    boolean existsByName(String name);

}
