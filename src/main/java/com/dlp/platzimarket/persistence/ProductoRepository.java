package com.dlp.platzimarket.persistence;

import com.dlp.platzimarket.persistence.crud.ProductoCrudRepository;
import com.dlp.platzimarket.persistence.entity.Producto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//@Component
public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria){
        return productoCrudRepository.findByidCategoriaOrderByNombreAsc(idCategoria);
    }

    public List<Producto> getByCategoriaSql(int idCategoria){
        return productoCrudRepository.getByCategoriaSql(idCategoria);
    }

    public Optional<List<Producto>> getEscasos(int catidad){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(catidad, true);
    }

    public Optional<Producto> getProducto(int IdProducto){
        return productoCrudRepository.findById(IdProducto);
    }

    public Producto seve(Producto producto){
        return productoCrudRepository.save(producto);
    }

    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}
