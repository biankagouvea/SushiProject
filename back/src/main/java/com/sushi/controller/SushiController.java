package com.sushi.controller;
import com.sushi.dao.SushiDAO;
import com.sushi.model.Commande;
import com.sushi.model.Sushi;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sushis")
@CrossOrigin(origins = "http://localhost:4200")
public class SushiController {
	private final SushiDAO dao = new SushiDAO();

    @GetMapping
    public List<Sushi> getAllSushis() {
        return dao.getAllSushis();
    }
    @PostMapping("/commande")
    public void createCommande(@RequestBody Commande commande) {
        System.out.println("Commande reçue : " + commande.getNomClient());
    }
}
