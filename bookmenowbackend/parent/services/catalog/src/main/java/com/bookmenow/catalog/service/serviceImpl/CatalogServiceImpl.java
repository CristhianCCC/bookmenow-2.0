package com.bookmenow.catalog.service.serviceImpl;
import com.bookmenow.catalog.exceptions.exceptions.BusinessRuleException;
import com.bookmenow.catalog.exceptions.validators.CatalogValidators;
import com.bookmenow.catalog.feign.userclient.UserClient;
import com.bookmenow.catalog.model.Catalog;
import com.bookmenow.catalog.repository.CatalogRepository;
import com.bookmenow.catalog.service.CatalogService;
import com.bookmenow.dto.CatalogDTO;
import com.bookmenow.dto.UserDTO;
import com.bookmenow.enums.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private UserClient userClient;

    // converting dto to entity ----------------------------------------------------------------------------------------
    private Catalog toEntity (CatalogDTO dto){
        Catalog catalog = new Catalog();
        catalog.setId(dto.getId());
        catalog.setName(dto.getName());
        catalog.setDescription(dto.getDescription());
        catalog.setPrice(dto.getPrice());
        catalog.setDurationMinutes(dto.getDurationMinutes());
        catalog.setCategory(dto.getCategory());
        catalog.setAvailableDays(dto.getAvailableDays());
        catalog.setCreatedAt(LocalDateTime.now());
        catalog.setUpdatedAt(LocalDateTime.now());
        // Finding provider by email using Feign client
        UserDTO userDTO = userClient.getUserByEmail(dto.getProviderEmail());
        if (userDTO == null) {
            throw new RuntimeException("Email " + dto.getProviderEmail() + " was not found");
        }
        // Validating that user is a provider, otherwise it won't work
        if (userDTO.getUserRoles() != UserRoles.PROVIDER) {
            throw new RuntimeException("User is not a PROVIDER");
        }

        // Setting only the provider email (no relation with User entity)
        catalog.setProviderId(userDTO.getId());
        catalog.setProviderEmail(userDTO.getEmail());

        return catalog;
    }

    // converting entity to dto ----------------------------------------------------------------------------------------
    private CatalogDTO toDTO (Catalog catalog){
        CatalogDTO dto = new CatalogDTO();
        dto.setId(catalog.getId());
        dto.setName(catalog.getName());
        dto.setDescription(catalog.getDescription());
        dto.setPrice(catalog.getPrice());
        dto.setDurationMinutes(catalog.getDurationMinutes());
        dto.setCategory(catalog.getCategory());
        dto.setAvailableDays(catalog.getAvailableDays());
        dto.setCreatedAt(catalog.getCreatedAt());
        dto.setUpdatedAt(catalog.getUpdatedAt());
        // If providerEmail exists, fetch provider info using Feign client
        if (catalog.getProviderEmail() != null && !catalog.getProviderEmail().isEmpty()) {
            try {
                //encoding email to use %64 instead of @ which is a reserved symbol
                String encodedEmail = URLEncoder.encode(catalog.getProviderEmail(), StandardCharsets.UTF_8);
                //using encoded email as user email
                UserDTO userDTO = userClient.getUserByEmail(encodedEmail);
                if (userDTO.getUserRoles() == UserRoles.PROVIDER) {
                    dto.setProviderId(userDTO.getId());
                    dto.setProviderName(userDTO.getName());
                    dto.setProviderEmail(userDTO.getEmail());
                    dto.setProviderAdress(userDTO.getAddress());
                    dto.setRole(com.bookmenow.enums.UserRoles.valueOf(userDTO.getUserRoles().name()));
                }
            } catch (Exception e) {
                // Handle case where user does not exist or service is unavailable
                System.out.println("Warning: Could not fetch provider info for email: " + catalog.getProviderEmail());
            }
        }
        return dto;
    }
// ---------------------------------------------------------------------------------------------------------------------

    @Override
    public List<CatalogDTO> getAllCatalogs() {
        List<Catalog> catalogs = catalogRepository.findAll();
        return catalogs.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CatalogDTO getCatalogById(Long id) {
        Catalog catalog = catalogRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("3001", "Catalog with ID" + id + " does not exist", HttpStatus.BAD_REQUEST));
        return toDTO(catalog);
    }

    @Override
    public CatalogDTO getCatalogByName(String name) {
        Catalog catalog = catalogRepository.findByName(name)
                .orElseThrow(() -> new BusinessRuleException("3002", "Catalog with name " + name + " does not exist", HttpStatus.BAD_REQUEST));
        return toDTO(catalog);
    }

    @Override
    public List<CatalogDTO> findAllCatalogsByName(String name) {
        List<Catalog> catalogs = catalogRepository.findAllByName(name);
        return catalogs.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CatalogDTO postCatalog(CatalogDTO catalogDTO) {
        //validations --------------------------------------------------------------------------------------------------
        CatalogValidators.validate(catalogDTO, true, catalogRepository);
        Catalog catalog = toEntity(catalogDTO);
        Catalog catalogSaved = catalogRepository.save(catalog);
        return toDTO(catalogSaved);
    }

    @Override
    public CatalogDTO putCatalog(Long id, CatalogDTO catalogDTO) {
        //validations --------------------------------------------------------------------------------------------------
        CatalogValidators.validate(catalogDTO, false, catalogRepository);
        catalogDTO.setId(id);
        return catalogRepository.findById(id).map(catalogFound -> {
            catalogFound.setName(catalogDTO.getName());
            catalogFound.setDescription(catalogDTO.getDescription());
            catalogFound.setPrice(catalogDTO.getPrice());
            catalogFound.setDurationMinutes(catalogDTO.getDurationMinutes());
            catalogFound.setCategory(catalogDTO.getCategory());
            catalogFound.setAvailableDays(catalogDTO.getAvailableDays());
            catalogFound.setUpdatedAt(LocalDateTime.now());
            Catalog catalogSaved = catalogRepository.save(catalogFound);
            return toDTO(catalogSaved);
        }).orElseThrow(() -> new RuntimeException("Catalog not found, please verify if ID " + id + "exist"));
    }

    @Override
    public void DeleteCatalog(Long id) {
        Catalog catalog = catalogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catalog with id " + id + " was not found"));
        //deleting catalog
        catalogRepository.deleteById(id);
    }
}
