package com.dlp.platzimarket.persistence.crud;

import com.dlp.platzimarket.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//CrudRepository
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    List<Producto> findByidCategoriaOrderByNombreAsc(int idCategoria);

    @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    List<Producto> getByCategoriaSql(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);



}
