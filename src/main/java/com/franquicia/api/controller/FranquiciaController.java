package com.franquicia.api.controller;

import com.franquicia.api.model.FranquiciaModel;
import com.franquicia.api.model.SucursalModel;
import com.franquicia.api.service.FranquiciaService;
import com.franquicia.api.service.SucursalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/franquicia")
public class FranquiciaController {
    public final FranquiciaService franquiciaService;
    public final SucursalService sucursalService;

    public FranquiciaController(FranquiciaService franquiciaService, SucursalService sucursalService) {
        this.franquiciaService = franquiciaService;
        this.sucursalService = sucursalService;
    }

    @GetMapping
    public Flux<FranquiciaModel> obtenerTodos() { return franquiciaService.obtenerTodos(); }

    @GetMapping("/{idFranquicia}")
    public Mono<FranquiciaModel> obtenerPorId(@PathVariable String idFranquicia) {
        return franquiciaService.obtenerPorId(idFranquicia);
    }

    @PostMapping
    public Mono<FranquiciaModel> crearFranquicia(@RequestBody FranquiciaModel franquicia){
        return franquiciaService.crearNueva(franquicia);
    }

    @PostMapping("/{idFranquicia}/agregarsucursal")
    public Mono<SucursalModel> agregarSucursalAFranquicia(@PathVariable String idFranquicia, @RequestBody SucursalModel sucursal){
        return sucursalService.agregarSucursalAFranquicia(idFranquicia, sucursal);
    }

    @PatchMapping("/{idFranquicia}/nombre")
    public Mono<FranquiciaModel> actualizarNombre(@PathVariable String idFranquicia, @RequestBody String nuevoNombre){
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nuevo nombre requerido, el valor no puede estar vacío.");
        }
        return franquiciaService.editarNombre(idFranquicia, nuevoNombre);
    }

    @DeleteMapping("/{idFranquicia}")
    public Mono<Void> eliminarPorId(@PathVariable String idFranquicia){
        return franquiciaService.elminarPorId(idFranquicia);
    }
}
