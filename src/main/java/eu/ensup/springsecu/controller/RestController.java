package eu.ensup.springsecu.controller;

import eu.ensup.springsecu.domaine.Etudiant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/v1/etudiant")
@org.springframework.web.bind.annotation.RestController
public class RestController {

   private final List<Etudiant> etudiants = new ArrayList<>(List.of(
            new Etudiant(1l,"Annaix","Flavien"),
            new Etudiant(2l,"Dazin","Maxime"),
            new Etudiant(3l,"Martineau","Brice")
    ));

    @GetMapping("/{id}")
    Etudiant getEtudiant(@PathVariable(name = "id") long id){
        return etudiants.stream().filter(e -> e.getId() == id).findAny().orElse(null) ;
    }
}

