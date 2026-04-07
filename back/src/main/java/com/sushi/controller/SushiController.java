package com.sushi.controller;
import com.sushi.dao.SushiDAO;
import com.sushi.model.Sushi;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/{id}")
    public Sushi getSushiById(@PathVariable int id) {
        return dao.getSushiById(id);
    }

    @PostMapping
    public Map<String, Object> createSushi(@RequestBody Sushi sushi) {
        Sushi created = dao.createSushi(sushi);

        if (created == null) {
            return Map.of("message", "Erreur création sushi");
        }

        return Map.of(
                "message", "OK",
                "id", created.getId()
        );
    }

    @PutMapping("/{id}")
    public Map<String, String> updateSushi(@PathVariable int id, @RequestBody Sushi sushi) {
        boolean ok = dao.updateSushi(id, sushi);

        if (!ok) {
            return Map.of("message", "Sushi introuvable");
        }

        return Map.of("message", "OK");
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteSushi(@PathVariable int id) {
        boolean ok = dao.deleteSushi(id);

        if (!ok) {
            return Map.of("message", "Sushi introuvable");
        }

        return Map.of("message", "OK");
    }
}
