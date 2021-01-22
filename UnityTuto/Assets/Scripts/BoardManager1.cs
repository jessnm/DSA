using System.Collections;
using System;
using Random = UnityEngine.Random;
using System.Collections.Generic;
using UnityEngine;

public class BoardManager1 : MonoBehaviour
{
    [Serializable]
    public class Count
    {
        public int minimo;
        public int maximo;
        public Count(int min, int max)
        {
            minimo = min;
            maximo = max;
        }

    }

    public int columnas = 8;
    public int filas = 8;

    public Count wallCount = new Count(5, 9);//Minimo de 5 paredes y max 9 en cada nivel
    public Count foodItems = new Count(1, 5);
    public GameObject salida;
    public GameObject[] SueloTiles;
    public GameObject[] paredesIntTiles;
    public GameObject[] comidaTiles;
    public GameObject[] enemigosTiles;
    public GameObject[] paredesExtTiles;

    private Transform boardHolder;
    private List<Vector3> gridPositions = new List<Vector3>();

    void InitialiseList()
    {
        gridPositions.Clear();

        for (int x = 1; x < columnas - 1; x++)//Esto recorre (1,1) a (6,1)
        {
            for (int y = 1; y < filas - 1; y++) //Esto recorre de (1,2) a (1,6) para tosdas las columnas 
            {
                gridPositions.Add(new Vector3(x, y, 0f)); //Añade 
            }
        }
    }

    void BoardSetUp()//Esta funcion hace la pared exterior y el suelo
    {
        boardHolder = new GameObject("Board").transform;
        for (int x = -1; x < columnas + 1; x++)//La parte exterior de lo que realmente es el juego
        {
            for (int y = -1; y < filas + 1; y++)
            {
                GameObject toInstantiate = SueloTiles[Random.Range(0, SueloTiles.Length)];//En todo el juego vamos a poner las tiles del suelo. Escoge aleatoriamente una de las tiles del prefab que tenemos
                if (x==-1 || x== columnas || y==-1 || y==columnas) //Si la x o la y esta dentro de la parte exterior
                {
                    toInstantiate = paredesExtTiles[Random.Range(0, paredesExtTiles.Length)];//Ponemos la paredes ext.
                }
                GameObject instance = Instantiate(toInstantiate, new Vector3(x, y, 0f), Quaternion.identity) as GameObject;
                instance.transform.SetParent(boardHolder);
            }
        }
    }

    Vector3 RandomPosition()
    {
        int randomIndex = Random.Range(0, gridPositions.Count);
        Vector3 randomPosition = gridPositions[randomIndex];
        gridPositions.RemoveAt(randomIndex);
        return randomPosition;
    }

    //LayoutObjectAtRandom accepts an array of game objects to choose from along with a minimum and maximum range for the number of objects to create.
    void LayoutObjectAtRandom(GameObject[] tileArray, int minimum, int maximum)
    {
        //Choose a random number of objects to instantiate within the minimum and maximum limits
        int objectCount = Random.Range(minimum, maximum + 1);

        //Instantiate objects until the randomly chosen limit objectCount is reached
        for (int i = 0; i < objectCount; i++)
        {
            //Choose a position for randomPosition by getting a random position from our list of available Vector3s stored in gridPosition
            Vector3 randomPosition = RandomPosition();

            //Choose a random tile from tileArray and assign it to tileChoice
            GameObject tileChoice = tileArray[Random.Range(0, tileArray.Length)];

            //Instantiate tileChoice at the position returned by RandomPosition with no change in rotation
            Instantiate(tileChoice, randomPosition, Quaternion.identity);
        }
    }

    //SetupScene initializes our level and calls the previous functions to lay out the game board
    public void SetupScene(int level)//La funcion que sera llamada;
    {
        //Creates the outer walls and floor.
        BoardSetUp();

        //Reset our list of gridpositions.
        InitialiseList();

        //Instantiate a random number of wall tiles based on minimum and maximum, at randomized positions.
        LayoutObjectAtRandom(paredesIntTiles, 1, paredesIntTiles.Length);

        //Instantiate a random number of food tiles based on minimum and maximum, at randomized positions.
        LayoutObjectAtRandom(comidaTiles, 1, comidaTiles.Length);

        //Determine number of enemies based on current level number, based on a logarithmic progression
        int enemyCount = (int)Mathf.Log(level, 2f);//La dificultad de los enemigos ira aumentando.

        //Instantiate a random number of enemies based on minimum and maximum, at randomized positions.
        LayoutObjectAtRandom(enemigosTiles, enemyCount, enemyCount);

        //Instantiate the exit tile in the upper right hand corner of our game board
        Instantiate(salida, new Vector3(columnas - 1, filas - 1, 0f), Quaternion.identity);
    }
}
