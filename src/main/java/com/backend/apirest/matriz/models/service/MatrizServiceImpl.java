package com.backend.apirest.matriz.models.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Repository("matrizService")
public class MatrizServiceImpl implements IMatrizService{

    /*** Variables **/
    int [][] matriz1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    int [][] matriz2 = {{4, 5, 6}, {7, 8, 9}, {1, 2, 3}};
    int [][] matriz3 = {{7, 8, 9}, {1, 2, 3}, {4, 5, 6}};


    @Override
    public List<int [][]> mostrarMatrices() {
        List<int [][]> listaMatriz = new ArrayList<>();
        listaMatriz.add(matriz1);
        listaMatriz.add(matriz2);
        listaMatriz.add(matriz3);

        return listaMatriz;
    }

    @Override
    public int[][] findById(int id) {
        List<int [][]> listaMatriz = new ArrayList<>();
        listaMatriz.add(matriz1);
        listaMatriz.add(matriz2);
        listaMatriz.add(matriz3);
        return listaMatriz.get(id);
    }

    @Override
    public int[][] save(int[][] matriz) {
        return matriz;
    }

    /** Rotacion de Matriz 90º Antihorario
        Matriz 2x2:
        11, 22             22,66
        55, 66      =>     11,55

        Matriz 3x3:
        10, 20, 30              30, 60, 90
        40, 50, 60     ==>      20, 50, 80
        70, 80, 90              10, 40, 70

        Matriz 4x4:
        11, 22, 33, 55          55,88,30,70
        55, 66, 77, 88          33,77,20,60
        99, 10, 20, 30    =>    22,66,10,50
        40, 50, 60, 70          11,55,99,40


     */

    @Override
    public int[][] rotarMatriz90Antihorario(int[][] matriz) {
        int tamanio = matriz.length;
        int[][] nuevamatriz = new int[tamanio][tamanio];

        for (int i = 0; i < tamanio / 2; i ++) {                                    //Barremos mitad de matriz
            for (int j = i; j < (tamanio -1 -i); j++) {

                int valorTemp2 = matriz[i][j];                                      // Almacenamos el valor esquina superior izquierda
                matriz[i][j] = matriz[j][tamanio -1 -i];                            // Asignamos el valor esquina superior derecha a la esquina superior izquierda
                matriz[j][tamanio -1 -i] = matriz[tamanio -1 -i][tamanio -1 -j];    // Asignamos el valor esquina inferior derecha a la esquina superior derecha
                matriz[tamanio -1 -i][tamanio -1 -j] = matriz[tamanio - 1 - j][i];  // Asignamos el valor esquina inferior izquierda a la esquina inferior derecha
                matriz[tamanio - 1 - j][i] = valorTemp2;                            // Asignamos el valor tamporal a la esquina inferior izquierda

            }
        }

        nuevamatriz = matriz;
        return nuevamatriz;
    }

    /** Rotacion de Matriz 90º Horario
     Matriz 2x2:
     10, 20              40, 10
     40, 50     ==>      50, 20

     Matriz 3x3:
     10, 20, 30              70, 40, 10
     40, 50, 60     ==>      80, 50, 20
     70, 80, 90              90, 60, 30

     Matriz 4x4:
     11, 22, 33, 55          40, 99, 55, 11
     55, 66, 77, 88          50, 10, 66, 22
     99, 10, 20, 30    =>    60, 20, 77, 33
     40, 50, 60, 70          70, 30, 88, 55

     */
    @Override
    public int[][] rotarMatriz90Horario(int[][] matriz) {
        int tamanio = matriz.length;

        int[][] nuevamatriz = new int[tamanio][tamanio];

        for (int i = 0; i < tamanio / 2; i ++) {                                    //Barremos mitad de matriz
            for (int j = i; j < (tamanio -1 -i); j++) {

                int valorTemp = matriz[i][j];                                       //Almacenamos el valor esquina superior izquierda
                matriz[i][j] = matriz[tamanio - 1 - j][i];                          //Asignamos el valor de la esquina inferior izquierda a la esquina superior izquierda
                matriz[tamanio -1 -j][i]= matriz[tamanio -1 -i][tamanio -1 -j];     //Asignamos el valor de la esquina inferior derecha a la esquina inferior izquierda
                matriz[tamanio -1 -i][tamanio -1 -j] = matriz[j][tamanio -1 -i];    //Asignamos el valor de la esquina superior derecha a la esquina inferior derecha
                matriz[j][tamanio -1 -i] = valorTemp;                               //Asignamos el valor temporal a la esquina superior derecha
            }
        }

        nuevamatriz = matriz;
        return nuevamatriz;
    }


}
