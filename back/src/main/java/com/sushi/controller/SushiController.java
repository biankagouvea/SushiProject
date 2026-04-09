package com.sushi.controller;
import com.sushi.dao.SushiDAO;
import com.sushi.model.Sushi;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sushis")
@CrossOrigin(origins = "http://localhost:4200")
/**
 * Contrôleur REST de gestion du catalogue de sushis.
 */
public class SushiController {
	private final SushiDAO dao = new SushiDAO();

    /**
     * Retourne tous les sushis disponibles.
     *
     * @return liste des sushis
     */
    @GetMapping
    public List<Sushi> getAllSushis() {
        return dao.getAllSushis();
    }

    /**
     * Retourne un sushi par identifiant.
     *
     * @param id identifiant sushi
     * @return sushi trouvé ou null
     */
    @GetMapping("/{id}")
    public Sushi getSushiById(@PathVariable int id) {
        return dao.getSushiById(id);
    }

    /**
     * Crée un sushi dans le catalogue.
     *
     * @param sushi données du sushi
     * @return résultat de création
     */
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

    /**
     * Met à jour un sushi existant.
     *
     * @param id identifiant sushi
     * @param sushi données de mise à jour
     * @return résultat de mise à jour
     */
    @PutMapping("/{id}")
    public Map<String, String> updateSushi(@PathVariable int id, @RequestBody Sushi sushi) {
        boolean ok = dao.updateSushi(id, sushi);

        if (!ok) {
            return Map.of("message", "Sushi introuvable");
        }

        return Map.of("message", "OK");
    }

    /**
     * Supprime un sushi du catalogue.
     *
     * @param id identifiant sushi
     * @return résultat de suppression
     */
    @DeleteMapping("/{id}")
    public Map<String, String> deleteSushi(@PathVariable int id) {
        boolean ok = dao.deleteSushi(id);

        if (!ok) {
            return Map.of("message", "Sushi introuvable");
        }

        return Map.of("message", "OK");
    }
}
