package com.example.firma.Been;

import com.example.firma.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepos extends JpaRepository<Company,Integer> {
   boolean existsByNomi(String s);
   boolean existsByIdNotAndNomi(Integer id, String s);
}
