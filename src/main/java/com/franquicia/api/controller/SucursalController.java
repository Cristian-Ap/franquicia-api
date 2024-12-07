package com.franquicia.api.controller;

import com.franquicia.api.model.SucursalModel;
import com.franquicia.api.service.SucursalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/sucursal")
public class SucursalController {
    public final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @GetMapping
    public Flux<SucursalModel> obtenerTodos() { return sucursalService.obtenerTodos(); }

    @GetMapping("/{idSucursal}")
    public Mono<SucursalModel> obtenerPorId(@PathVariable String idSucursal) {
        return sucursalService.obtenerPorId(idSucursal);
    }

    @PostMapping
    public Mono<SucursalModel> crearSucursal(@RequestBody SucursalModel sucursal){
        return sucursalService.crearNueva(sucursal);
    }

    @PatchMapping("/{idSucursal}/nombre")
    public Mono<SucursalModel> actualizarNombre(@PathVariable String idSucursal, @RequestBody String nuevoNombre){
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nuevo nombre requerido, el valor no puede estar vacío.");
        }
        return sucursalService.editarNombre(idSucursal, nuevoNombre);
    }

    @DeleteMapping("/{idSucursal}")
    public Mono<Void> eliminarPorId(@PathVariable String idSucursal){
        return sucursalService.elminarPorId(idSucursal);
    }
}
