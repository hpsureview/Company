package com.example.firma.Been;

import com.example.firma.Model.Ishchi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IshchiBeen extends JpaRepository<Ishchi,Integer> {
    boolean existsByTelNomerAndFirmaId(String s,Integer id);
    boolean existsByIdNotAndTelNomerAndFirmaId(Integer id, String s,Integer f);
    List<Ishchi> findAllByFirmaId(Integer id);
}
