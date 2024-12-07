package com.franquicia.api.service;

import com.franquicia.api.model.FranquiciaModel;
import com.franquicia.api.repository.FranquiciaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FranquiciaService {
    private final FranquiciaRepository franquiciaRepository;

    public FranquiciaService(FranquiciaRepository franquiciaRepository) {
        this.franquiciaRepository = franquiciaRepository;
    }

    public Flux<FranquiciaModel> obtenerTodos(){
        return franquiciaRepository.findAll();
    }

    public Mono<FranquiciaModel> obtenerPorId(String id){
        return franquiciaRepository.findById(id).switchIfEmpty(
            Mono.error(
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Franquicia con id = " + id + " no encontrada."
                )
            )
        );
    }

    public Mono<FranquiciaModel> crearNueva(FranquiciaModel franquicia){
        return franquiciaRepository.save(franquicia);
    }

    public Mono<FranquiciaModel> editarNombre(String id, String nuevoNombre){
        return  franquiciaRepository.findById(id)
                .switchIfEmpty(
                    Mono.error(
                            new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "Franquicia con id = " + id + " no encontrada."
                            )
                    )
                ).flatMap(
                        franquiciaModel -> {
                            franquiciaModel.setNombre(nuevoNombre);
                            return franquiciaRepository.save(franquiciaModel);
                        }
                );
    }

    public Mono<Void> elminarPorId (String id){
        return franquiciaRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, "Franquicia con id = " + id + " no encontrada."
                                )
                        )
                ).flatMap(franquiciaRepository::delete);
    }


}
