package com.dlp.platzimarket.persistence;

import com.dlp.platzimarket.domain.Product;
import com.dlp.platzimarket.domain.repository.ProductRepository;
import com.dlp.platzimarket.persistence.crud.ProductoCrudRepository;
import com.dlp.platzimarket.persistence.entity.Producto;
import com.dlp.platzimarket.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//@Component
public class ProductoRepository implements ProductRepository {
    private ProductoCrudRepository productoCrudRepository;
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByidCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
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

    public Producto guardar(Producto producto){
        return productoCrudRepository.save(producto);
    }

    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }
}
