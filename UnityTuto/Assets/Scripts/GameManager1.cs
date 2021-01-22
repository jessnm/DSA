using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GameManager1 : MonoBehaviour
{
    public float levelStartDelay = 2f;//Time to wait before starting levels in seconds
    public static GameManager1 instance = null;
    public BoardManager1 boardScript;
    private int level = 1;
    public int playerFoodPoints = 100;
    [HideInInspector] public bool playersTurn = true;
    public float turnDelay = 0.1f;//How long the game is going to wait between turns

    private Text levelText;//Text que display the level number;
    private GameObject levelImage;
    private List<Enemigos> enemies;
    private bool enemiesMoving;
    private bool doingSetup;

    // Start is called before the first frame update
    void Awake()
    {
        if (instance == null)
        {
            instance = this;
        }
        else if (instance != this) {
            Destroy(gameObject);
        }
        DontDestroyOnLoad(gameObject);
        enemies = new List<Enemigos>();
        boardScript = GetComponent<BoardManager1>();
        InitGame();
    }

    private void OnLevelWasLoaded(int index)///It is called every time a scene is loaded
    {
        level++;
        InitGame();
    }

    void InitGame()
    {
        doingSetup = true;
        levelImage = GameObject.Find("levelImage");
        levelText = GameObject.Find("levelText").GetComponent<Text>();
        levelText.text = "DAY " + level;
        levelImage.SetActive(true);
        Invoke("HideLevelImage", levelStartDelay);//Once we display the image tag we are going to wait the seconds of levelstartdelay before turning it off
        enemies.Clear();//Cuando se hace reload del juego, hay que eliminar los enemigos dem nivem anterior.
        boardScript.SetupScene(level);
    }

    private void HideLevelImage()
    {
        levelImage.SetActive(false);
        doingSetup = false;
    }

    public void GameOver()
    {
        levelText.text = "After " + level + "days, you starved";
        levelImage.SetActive(true);
        enabled = false;
    }

    IEnumerator MoveEnemies()
    {
        //While enemiesMoving is true player is unable to move.
        enemiesMoving = true;

        //Wait for turnDelay seconds, defaults to .1 (100 ms).
        yield return new WaitForSeconds(turnDelay);

        //If there are no enemies spawned (IE in first level):
        if (enemies.Count == 0)
        {
            //Wait for turnDelay seconds between moves, replaces delay caused by enemies moving when there are none.
            yield return new WaitForSeconds(turnDelay);
        }

        //Loop through List of Enemy objects.
        for (int i = 0; i < enemies.Count; i++)
        {
            //Call the MoveEnemy function of Enemy at index i in the enemies List.
            enemies[i].MoveEnemy();

            //Wait for Enemy's moveTime before moving next Enemy, 
            yield return new WaitForSeconds(enemies[i].moveTime);
        }
        //Once Enemies are done moving, set playersTurn to true so player can move.
        playersTurn = true;

        //Enemies are done moving, set enemiesMoving to false.
        enemiesMoving = false;
    }

    public void AddEnemyToList(Enemigos script)
    {
        //Add Enemy to List enemies.
        enemies.Add(script);
    }

    //Update is called every frame.
    void Update()
    {
        //Check that playersTurn or enemiesMoving or doingSetup are not currently true.
        if (playersTurn || enemiesMoving || doingSetup)

            //If any of these are true, return and do not start MoveEnemies.
            return;

        //Start moving enemies.
        StartCoroutine(MoveEnemies());
    }
}
