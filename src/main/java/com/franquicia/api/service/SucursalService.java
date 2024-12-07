package com.franquicia.api.service;

import com.franquicia.api.model.SucursalModel;
import com.franquicia.api.repository.SucursalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SucursalService {
    private final SucursalRepository sucursalRepository;
    private final FranquiciaService franquiciaService;

    public SucursalService(SucursalRepository sucursalRepository, FranquiciaService franquiciaService) {
        this.sucursalRepository = sucursalRepository;
        this.franquiciaService = franquiciaService;
    }

    public Flux<SucursalModel> obtenerTodos(){
        return sucursalRepository.findAll();
    }

    public Mono<SucursalModel> obtenerPorId(String id){
        return sucursalRepository.findById(id).switchIfEmpty(
                Mono.error(
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Sucursal con id = " + id + " no encontrada."
                        )
                )
        );
    }

    public Mono<SucursalModel> crearNueva(SucursalModel sucursal){
        return franquiciaService.obtenerPorId(sucursal.getIdFranquicia())
                .flatMap( franquiciaModel -> sucursalRepository.save(sucursal));
    }

    public Mono<SucursalModel> agregarSucursalAFranquicia(String idFranquicia, SucursalModel sucursal){
        return franquiciaService.obtenerPorId(idFranquicia)
                .flatMap(
                        franquiciaModel -> {
                            sucursal.setIdFranquicia(idFranquicia);
                            return sucursalRepository.save(sucursal);
                        }
                );
    }

    public Mono<SucursalModel> editarNombre(String id, String nuevoNombre){
        return  sucursalRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, "Sucursal con id = " + id + " no encontrada."
                                )
                        )
                ).flatMap(
                        sucursalModel -> {
                            sucursalModel.setNombre(nuevoNombre);
                            return sucursalRepository.save(sucursalModel);
                        }
                );
    }

    public Mono<Void> elminarPorId (String id){
        return sucursalRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, "Sucursal con id = " + id + " no encontrada."
                                )
                        )
                ).flatMap(sucursalRepository::delete);
    }


}
