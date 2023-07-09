package com.kristjan.controller;

import com.kristjan.dto.OwnerDTO;
import com.kristjan.entity.Clinic;
import com.kristjan.entity.Owner;
import com.kristjan.entity.Pet;
import com.kristjan.respository.ClinicRepository;
import com.kristjan.respository.OwnerRepository;
import com.kristjan.respository.PetRepository;
import com.kristjan.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PetController {
    @Autowired
    PetRepository petRepository;
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    ClinicRepository clinicRepository;
    @PostMapping("pets") // localhost:8080/pet/?name=Koer&weight=50
    public List<Pet> addPet(
            @RequestParam String name,
            @RequestParam double weight
    ) {
        Pet pet = new Pet(name, weight);
        petRepository.save(pet);
        return petRepository.findAll();
    }

    @PostMapping("owners") // localhost:8080/owners?name=Kristjan
    public List<Owner> addOwner(
            @RequestBody Owner owner
    ) throws Exception {
        if (ownerRepository.findByPersonalCode(owner.getPersonalCode()) != null) {
            throw new Exception("The owner with this personal code already exists");
        }

        owner.setPets(new ArrayList<>());
        ownerRepository.save(owner);
        return ownerRepository.findAll();
    }

    @GetMapping("owner/add-pet") // http://localhost:8080/owner/add-pet?owner=Kristjan&pet=Koer
    public Owner addPetToOwner(
            @RequestParam String owner,
            @RequestParam String pet

    ) {
        Owner foundOwner = ownerRepository.findById(owner).get();
        Pet foundPet = petRepository.findById(pet).get();


        List<Pet> pets = foundOwner.getPets();
        pets.add(foundPet);
        foundOwner.setPets(pets);

        return ownerRepository.save(foundOwner);
    }

    @GetMapping("owner/petCount") // http://localhost:8080/owner/petCount?owner=Kristjan
    public int getOwnerPetCount(
            @RequestParam String owner
    ) {
        Owner foundOwner = ownerRepository.findById(owner).get();
        return foundOwner.getPets().size();
    }


    @GetMapping("owner/maxAndMinWeight") // http://localhost:8080/owner/maxAndMinWeight?owner=Kristjan
    public String getOwnerMaxAndMinWeightPets(
            @RequestParam String owner
    ) {
        Owner foundOwner = ownerRepository.findById(owner).get();
        Collections.sort(foundOwner.getPets(), Comparator.comparingDouble(Pet::getWeight));

        return "The heaviest pet is " + foundOwner.getPets().get(foundOwner.getPets().size() - 1).getName()
                + " and the lightest pet is " + foundOwner.getPets().get(0).getName();
    }

    @GetMapping("owner/inBetweenWeight") // http://localhost:8080/owner/inBetweenWeight?maxWeight=80&minWeight=19
    public List<Pet> getPetsByWeight(
            @RequestParam double maxWeight,
            @RequestParam double minWeight
    ) {
        List<Pet> pets = petRepository.findAll();
        List<Pet> foundPets = new ArrayList<>();
        for (Pet p : pets) {
            if (p.getWeight() >= minWeight && p.getWeight() <= maxWeight) {
                foundPets.add(p);
            }
        }
        return foundPets;
    }

    @GetMapping("clinic/pet") // http://localhost:8080/clinic/pet?name=Koer
    public Pet getPetFromClinic(
            @RequestParam String name
    ) {
        List<Clinic> clinics = clinicRepository.findAll();
        for (Clinic c : clinics) {
            for (Pet p : c.getPets()) {
                if (p.getName().equals(name)) {
                    return p;
                }
            }
        }
        return null;
    }

    @GetMapping("clinic/mostPets") // http://localhost:8080/clinic/mostPets
    public Clinic getClinicWithMostPets(
    ) {
        List<Clinic> clinics = clinicRepository.findAll();
        Clinic clinicWithMostPets = null;
        int maxPets = 0;

        for (Clinic clinic : clinics) {
            int numPets = clinic.getPets().size();
            if (numPets > maxPets) {
                maxPets = numPets;
                clinicWithMostPets = clinic;
            }
        }
        return clinicWithMostPets;
    }

    @Autowired
    OwnerService ownerService;
    @GetMapping("owners") // http://localhost:8080/owners
    public List<OwnerDTO> getAllOwners() {
        return ownerService.findOwners();
    }

    @GetMapping("ownerPersonalCode") // http://localhost:8080/ownerPersonalCode
    public Owner findOwnerByPersonalCode(@PathVariable String personalCode) {
        return ownerRepository.findByPersonalCode(personalCode);
    }

    @GetMapping("ownerPets") // http://localhost:8080/ownerPersonalCode
    public List<Owner> findAllByPetsGreaterThan() {
        return ownerRepository.findAllByPetsGreaterThan(0);
    }
}
