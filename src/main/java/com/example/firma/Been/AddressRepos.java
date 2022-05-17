package com.example.firma.Been;

import com.example.firma.Model.Address;
import com.example.firma.Model.Ishchi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepos extends JpaRepository<Address,Integer> {
    boolean existsByTelNomerAndFirmaId(String telNomer, Integer firmaId);

    List<Ishchi> findAllByFirmaId(Integer id);

    boolean existsByIdNotAndTelNomerAndFirmaId(Integer id, String telNomer, Integer firmaId);
}
