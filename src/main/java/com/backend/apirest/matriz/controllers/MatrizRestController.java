package com.backend.apirest.matriz.controllers;



import com.backend.apirest.matriz.models.service.IMatrizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping("/api")
public class MatrizRestController {

    /** VARIABLES **/
    @Autowired
    @Qualifier("matrizService")
    private IMatrizService matrizService;


    /** METODOS **/
    //Listar Matrices
    @GetMapping("/matrices")
    public List<int [][]> index(){

        return matrizService.mostrarMatrices();
    }

    //Mostrar matriz
    @GetMapping("/matrices/{id}")
    public int [][] show(@PathVariable int id){

        return matrizService.findById(id);
    }

    //Crear Matriz
    @PostMapping("/matrices/")
    @ResponseStatus(HttpStatus.CREATED)
    public int [][] createMatriz(@RequestBody int [][] matriz){

        return matrizService.save(matriz);
    }

    //Rotar Matriz 90º AntiHorario
    @PostMapping("/matrices/rotar90AntiHorario")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> rotarMatriz90AntiHorario(@RequestBody int [][] matriz){

        int [][] nuevaMatriz = null;

        int numFilas = matriz.length;
        int numColumnas = matriz[0].length;

        System.out.println("Filas: " + numFilas);
        System.out.println("Columnas: " + numColumnas);

        // Mapa para almacenar objetos asociado a nombres y asignar mensajes de error
        Map<String, Object> response = new HashMap<>();

        // Valido que la mattriz sea cuadrada NxN
        if(numFilas != numColumnas ){
            response.put("mensaje", "Error al realizar la rotacion antihoraria, la matriz ingresada debe ser cuadrada (NxN).");

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.PRECONDITION_REQUIRED);
        }

        try{
            nuevaMatriz = matrizService.rotarMatriz90Antihorario(matriz);
        }catch (Exception e){

            response.put("mensaje", "Error al realizar la rotacion de la matriz.");
            response.put("error", e.getMessage().concat(": ").concat(e.getLocalizedMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "la Matriz ha sido rotada 90º en sentido antihorario con éxito!");
        response.put("matriz", nuevaMatriz);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

    //Rotar Matriz 90º AntiHorario
    @PostMapping("/matrices/rotar90Horario")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> rotarMatriz90Horario(@RequestBody int [][] matriz,
                                                  BindingResult result){

        int [][] nuevaMatriz = null;

        int numFilas = matriz.length;
        int numColumnas = matriz[0].length;

        System.out.println("Filas: " + numFilas);
        System.out.println("Columnas: " + numColumnas);

        // Mapa para almacenar objetos asociado a nombres y asignar mensajes de error
        Map<String, Object> response = new HashMap<>();

        // Valido que la mattriz sea cuadrada NxN
        if(numFilas != numColumnas ){
            response.put("mensaje", "Error al realizar la rotacion horaria, la matriz ingresada debe ser cuadrada (NxN).");

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.PRECONDITION_REQUIRED);
        }


        try{
            nuevaMatriz = matrizService.rotarMatriz90Horario(matriz);
        }catch (Exception e){

            response.put("mensaje", "Error al realizar la rotacion de la matriz.");
            response.put("error", e.getMessage().concat(": ").concat(e.getLocalizedMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "la Matriz ha sido rotada 90º en sentido horario con éxito!");
        response.put("matriz", nuevaMatriz);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }




}
