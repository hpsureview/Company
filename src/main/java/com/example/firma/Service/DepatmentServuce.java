package com.example.firma.Service;

import com.example.firma.Been.DepartmentRepos;
import com.example.firma.Dto.IshchiCompany;
import com.example.firma.Model.Department;
import com.example.firma.Model.Ishchi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DepatmentServuce {
    @Autowired
    DepartmentRepos departmentRepos;

    public String insert1(IshchiCompany dto){
        Optional<Department> optional=departmentRepos.findById(dto.getFirmaId());
        if(!optional.isPresent())
            return "Bunday firma mavjud emas";
        boolean nomer = departmentRepos.existsByTelNomerAndFirmaId(dto.getTelNomer(), dto.getFirmaId());
        if(nomer)
            return "Bunady ishchi mavjud";
        Department department=new Department();
        department.setNomi(dto.getIsmi());
        department.setCompany(department.getCompany());
        departmentRepos.save(department);
        return "Muavfaqiyatli joylandi";
    }

    public List<Ishchi> select(Integer id){
        Optional<Department> optional=departmentRepos.findById(id);
        if(optional.isPresent()){
            return departmentRepos.findAllByFirmaId(id);
        }
        return null;
    }

    public String update(IshchiCompany dto, Integer id){
        Optional<Department> optional=departmentRepos.findById(id);
        if(!optional.isPresent())
            return "Bunday ishchi yo'q";
        Optional<Department> optional1=departmentRepos.findById(dto.getFirmaId());
        if(!optional1.isPresent())
            return "Bunday firma mavjud emas";
        boolean nomer = departmentRepos.existsByIdNotAndTelNomerAndFirmaId(id, dto.getTelNomer(), dto.getFirmaId());
        if(nomer)
            return "Bunady ishchi mavjud";
        Department department=optional.get();
        department.setNomi(dto.getIsmi());
        department.setCompany(department.getCompany());
        departmentRepos.save(department);
        return "Muavfaqiyatli yangilandi";
    }

    public boolean delete(Integer id){
        Optional<Department> optional=departmentRepos.findById(id);
        if(!optional.isPresent())
            return false;
        departmentRepos.deleteById(id);
        return true;
    }
}
