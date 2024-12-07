package com.franquicia.api.service;

import com.franquicia.api.model.ProductoModel;
import com.franquicia.api.repository.ProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final SucursalService sucursalService;

    public ProductoService(ProductoRepository productoRepository, SucursalService sucursalService) {
        this.productoRepository = productoRepository;
        this.sucursalService = sucursalService;
    }

    public Flux<ProductoModel> obtenerTodos(){
        return productoRepository.findAll();
    }

    public Mono<ProductoModel> obtenerPorId(String id){
        return productoRepository.findById(id).switchIfEmpty(
                Mono.error(
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Producto con id = " + id + " no encontrado."
                        )
                )
        );
    }

    public Mono<ProductoModel> crearNuevo(ProductoModel producto){
        return sucursalService.obtenerPorId(producto.getIdSucursal())
                .flatMap( sucursalModel -> productoRepository.save(producto));
    }

    public Mono<ProductoModel> agregarSucursalAFranquicia(String idSucursal, ProductoModel producto){
        return sucursalService.obtenerPorId(idSucursal)
                .flatMap(
                        sucursalModel -> {
                            producto.setIdSucursal(idSucursal);
                            return productoRepository.save(producto);
                        }
                );
    }

    public Mono<ProductoModel> editarNombre(String id, String nuevoNombre){
        return  productoRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, "Producto con id = " + id + " no encontrado."
                                )
                        )
                ).flatMap(
                        productoModel -> {
                            productoModel.setNombre(nuevoNombre);
                            return productoRepository.save(productoModel);
                        }
                );
    }

    public Mono<Void> elminarPorId (String id){
        return productoRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, "Producto con id = " + id + " no encontrado."
                                )
                        )
                ).flatMap(productoRepository::delete);
    }


}
