package com.bookmenow.catalog.controller;
import com.bookmenow.catalog.service.CatalogService;
import com.bookmenow.dto.CatalogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping
    public ResponseEntity<List<CatalogDTO>> getAllCatalogs(){
        List<CatalogDTO> catalogDTO = catalogService.getAllCatalogs();
        return ResponseEntity.status(HttpStatus.OK).body(catalogDTO);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CatalogDTO> getCatalogById(@PathVariable("id") Long id){
        CatalogDTO catalogDTO = catalogService.getCatalogById(id);
        return ResponseEntity.ok(catalogDTO);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CatalogDTO> getCatalogByName(@PathVariable("name") String name){
        CatalogDTO catalogDTO = catalogService.getCatalogByName(name);
        return ResponseEntity.ok(catalogDTO);
    }
    @PostMapping
    public ResponseEntity<CatalogDTO> postCatalog(@RequestBody CatalogDTO catalogDTO){
        CatalogDTO catalogDTO1 = catalogService.postCatalog(catalogDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(catalogDTO1);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CatalogDTO> putCatalog (@PathVariable Long id, @RequestBody CatalogDTO catalogDTO){
        CatalogDTO catalogDTO1 = catalogService.putCatalog(id, catalogDTO);
        return ResponseEntity.ok(catalogDTO1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCatalog (@PathVariable Long id){
        catalogService.DeleteCatalog(id);
        return ResponseEntity.noContent().build();
    }
}
