using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Jugador1 : MovingObject1{

    public int wallDamage = 1; //Damage apply to the all cuando el jugador la golpea
    public int pointsPerFood = 10;
    public int pointPerSoda = 20; //puntos que obtendran cuando recogan la comida
    public float restartLevelDelay = 1f;

    public AudioClip moveSound1;
    public AudioClip moveSound2;
    public AudioClip eatSound1;
    public AudioClip eatSound2;
    public AudioClip drinkSound1;
    public AudioClip drinkSound2;
    public AudioClip gameOverSound;

    public Text foodText;
    private Animator animator;
    private int food; //Esto es para guardar la puntuacion del jugador cuando se cambia de nivel



    // Start is called before the first frame update
    protected override void Start()//implementacion diferente para jugador y objetos
    {
        animator = GetComponent<Animator>();
        food = GameManager1.instance.playerFoodPoints;
        //foodText.text = "Food: " + food;
        base.Start();
    }

    private void OnDisabled()
    {
        GameManager1.instance.playerFoodPoints = food;//Con esto pasamos los puntos al game manager
    }

    protected override void AttemptMove<T> (int xDir, int yDir)
    {
        food--; //Cada vez que el jugador se mueve se le quita un punto de comida
        foodText.text = "Food: " + food;
        base.AttemptMove<T>(xDir, yDir);
        RaycastHit2D hit;

        if(Move(xDir,yDir,out hit))
        {
            SoundManager1.instance.randomizeSfx(moveSound1, moveSound2);
        }
        CheckIfGameOver();//Como el jugador ha perdido comida, habra que mirar si es game over o no.
        GameManager1.instance.playersTurn = false;
    }

    protected override void OnCantMove<T> (T component)
    {
        Walls hitWall = component as Walls;
        hitWall.DamageWall(wallDamage);
        animator.SetTrigger("Jugador1Ataque");
    }

    private void CheckIfGameOver()
    {
        if(food<=0)
        {
            SoundManager1.instance.playSingle(gameOverSound);
            SoundManager1.instance.musicSource.Stop();
            GameManager1.instance.GameOver();
        }
    }

    private void Restart()
    {
        //Reload the level
        Application.LoadLevel(Application.loadedLevel);//Cuando se pasa de nivel se reload la misma escena
    }

    public void LoseFood(int loss)
    {
        //Cuando un enemigo ataca al jugador, se especifica cuantos puntos se va a perder. 
        animator.SetTrigger("Jugador1punos");
        food -= loss;
        foodText.text = "-" + loss + "Food: " + food;
        CheckIfGameOver();
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        if(other.tag =="Exit")
        {
            Invoke("Restart", restartLevelDelay);//1 sec y se resetea el nivel. Esto lo hemos hecho con la parte grafica
            enabled = false; 
        }
        else if (other.tag == "Food")
        {
            food += pointsPerFood;//Añadimos puntos de comida
            foodText.text = "+" + pointsPerFood + "Food: " + food;
            SoundManager1.instance.randomizeSfx(eatSound1,eatSound2);
            other.gameObject.SetActive(false);
        }
        else if (other.tag == "Soda")
        {
            food += pointPerSoda;//Añadimos puntos de refresco
            foodText.text = "+" + pointPerSoda + "Food: " + food;
            SoundManager1.instance.randomizeSfx(drinkSound1,drinkSound2);
            other.gameObject.SetActive(false);
        }
    }
    // Update is called once per frame
    void Update()
    {
        if (!GameManager1.instance.playersTurn) return;//Miramos si es el turno o no. Si no, el codigo no se ejecuta

        int horizontal = 0;
        int vertical = 0;

        horizontal = (int)Input.GetAxisRaw("Horizontal");//Cambia de float a int y lo guarda en la variable
        vertical = (int)Input.GetAxisRaw("Vertical");

        if(horizontal !=0)
        {
            vertical = 0; //Esto evita que el jugador se mueva en diagonal
        }

        if(vertical !=0 || horizontal !=0)
        {
            AttemptMove<Walls>(horizontal, vertical);
        }
    }
}
