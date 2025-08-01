package com.bookmenow.catalog.service;

import com.bookmenow.catalog.dto.CatalogDTO;

import java.util.List;
import java.util.Optional;

public interface CatalogService  {

    public List<CatalogDTO> getAllCatalogs ();

    public CatalogDTO getCatalogById(Long id);

    public CatalogDTO getCatalogByName(String name);

    public List<CatalogDTO> findAllCatalogsByName(String name);

    public CatalogDTO postCatalog (CatalogDTO catalogDTO);

    public CatalogDTO putCatalog (Long id, CatalogDTO catalogDTO);

    public void DeleteCatalog (Long id);

}
