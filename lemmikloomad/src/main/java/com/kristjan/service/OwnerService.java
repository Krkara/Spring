package com.kristjan.service;

import com.kristjan.dto.OwnerDTO;
import com.kristjan.entity.Owner;
import com.kristjan.respository.OwnerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<OwnerDTO> findOwners() {
        List<Owner> owners = ownerRepository.findAll();
        List<OwnerDTO> ownerDTOs = new ArrayList<>();
        for (Owner o : owners) {
            OwnerDTO ownerDTO = modelMapper.map(o, OwnerDTO.class);
            ownerDTOs.add(ownerDTO);
        }
        return ownerDTOs;
    }


}
