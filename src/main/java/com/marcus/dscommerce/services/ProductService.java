package com.marcus.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marcus.dscommerce.dto.ProductDTO;
import com.marcus.dscommerce.entities.Product;
import com.marcus.dscommerce.repositories.ProductRepository;
import com.marcus.dscommerce.services.exepitions.DatabaseExeption;
import com.marcus.dscommerce.services.exepitions.ResourceNotFoundExeption;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Product product = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundExeption("Recurso não encontrado"));
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){

        Page<Product> resul =repository.findAll(pageable);
        return resul.map(x -> new ProductDTO(x));
    }
    
    @Transactional
    public ProductDTO inset(ProductDTO dto){

        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){

        try{
            Product entity = repository.getReferenceById(id);
        copyDtoToEntity (dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
        } 
        catch(EntityNotFoundException e){
            throw new ResourceNotFoundExeption("Recurso não encontrado");
        }
        
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        try{
            repository.deleteById(id);

        }catch(EmptyResultDataAccessException e){
            throw new  ResourceNotFoundExeption("Recurso não encontrado");
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseExeption("Falha de integridade referencial");
        }
        
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }


}
