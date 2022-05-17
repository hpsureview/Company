package com.example.firma.Service;

import com.example.firma.Been.CompanyRepos;
import com.example.firma.Model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepos firmaBeen;

    public boolean insert(Company company){
        if(firmaBeen.existsByNomi(company.getNomi()))
            return false;
        Company company1 =new Company();
        company1.setEgasi(company.getEgasi());
        company1.setNomi(company.getNomi());
        company1.setAddress(company.getAddress());
        firmaBeen.save(company1);
        return true;
    }

    public List<Company> select(){
        return firmaBeen.findAll();
    }

    public String update(Integer id, Company company){
        Optional<Company> optional=firmaBeen.findById(id);
        if(!optional.isPresent())
            return "Bunday firma yo'q";
        boolean b = firmaBeen.existsByIdNotAndNomi(id, company.getNomi());
        if(b) return "Bunday firma mavjud";

            Company company1 =optional.get();
            company1.setEgasi(company.getEgasi());
            company1.setNomi(company.getNomi());
            company1.setAddress(company.getAddress());
            firmaBeen.save(company1);
            return "Muavfaqiyatli yangilandi";
    }

    public boolean delete(Integer id){
        Optional<Company> optional=firmaBeen.findById(id);
        if(optional.isPresent())
        {
            firmaBeen.deleteById(id);
            return true;
        }
        return false;
    }


}
