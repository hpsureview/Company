package com.example.firma.Service;

import com.example.firma.Been.AddressRepos;
import com.example.firma.Dto.IshchiCompany;
import com.example.firma.Model.Address;
import com.example.firma.Model.Ishchi;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AddressService {
    @Autowired
    AddressRepos addressRepos;

    public String insert1(IshchiCompany dto){
        Optional<Address> optional=addressRepos.findById(dto.getFirmaId());
        if(!optional.isPresent())
            return "Bunday address mavjud emas";
        boolean nomer = addressRepos.existsByTelNomerAndFirmaId(dto.getTelNomer(), dto.getFirmaId());
        if(nomer)
            return "Bunady address mavjud";
        Address address=new Address();
        address.setStreet(address.getStreet());
        address.setHomeNumber(address.getHomeNumber());
        addressRepos.save(address);
        return "Muavfaqiyatli joylandi";
    }

    public List<Ishchi> select(Integer id){
        Optional<Address> optional=addressRepos.findById(id);
        if(optional.isPresent()){
            return addressRepos.findAllByFirmaId(id);
        }
        return null;
    }

    public String update(IshchiCompany dto, Integer id){
        Optional<Address> optional=addressRepos.findById(id);
        if(!optional.isPresent())
            return "Bunday adress yo'q";
        Optional<Address> optional1=addressRepos.findById(dto.getFirmaId());
        if(!optional1.isPresent())
            return "Bunday firma mavjud emas";
        boolean nomer = addressRepos.existsByIdNotAndTelNomerAndFirmaId(id, dto.getTelNomer(), dto.getFirmaId());
        if(nomer)
            return "Bunady address mavjud";
        Address address=optional.get();
        address.setStreet(address.getStreet());
        address.setHomeNumber(address.getHomeNumber());
        addressRepos.save(address);
        return "Muavfaqiyatli yangilandi";
    }

    public boolean delete(Integer id){
        Optional<Address> optional=addressRepos.findById(id);
        if(!optional.isPresent())
            return false;
        addressRepos.deleteById(id);
        return true;
    }

}
