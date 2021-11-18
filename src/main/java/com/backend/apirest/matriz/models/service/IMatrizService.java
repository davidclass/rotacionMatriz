package com.backend.apirest.matriz.models.service;

import java.util.List;

public interface IMatrizService {

    public List<int [][]> mostrarMatrices();

    public int [][] findById(int id);

    public int [][] save(int[][] matriz);

    public int[][] rotarMatriz90Antihorario(int[][] matriz);

    public int[][] rotarMatriz90Horario(int[][] matriz);


}