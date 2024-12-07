package com.franquicia.api.controller;

import com.franquicia.api.model.ProductoModel;
import com.franquicia.api.model.SucursalModel;
import com.franquicia.api.service.ProductoService;
import com.franquicia.api.service.SucursalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {
    public final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public Flux<ProductoModel> obtenerTodos() { return productoService.obtenerTodos(); }

    @GetMapping("/{idProducto}")
    public Mono<ProductoModel> obtenerPorId(@PathVariable String idProducto) {
        return productoService.obtenerPorId(idProducto);
    }

    @PostMapping
    public Mono<ProductoModel> crearProducto(@RequestBody ProductoModel producto){
        return productoService.crearNuevo(producto);
    }

    @PatchMapping("/{idProducto}/nombre")
    public Mono<ProductoModel> actualizarNombre(@PathVariable String idProducto, @RequestBody String nuevoNombre){
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nuevo nombre requerido, el valor no puede estar vacío.");
        }
        return productoService.editarNombre(idProducto, nuevoNombre);
    }

    @PatchMapping("/{idProducto}/stock")
    public Mono<ProductoModel> actualizarStock(@PathVariable String idProducto, @RequestBody int nuevoStock){
        if (nuevoStock <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El valor del nuevo stock debe ser superior/diferente de cero.");
        }
        return productoService.editarStock(idProducto, nuevoStock);
    }

    @DeleteMapping("/{idProducto}")
    public Mono<Void> eliminarPorId(@PathVariable String idProducto){
        return productoService.elminarPorId(idProducto);
    }
}
