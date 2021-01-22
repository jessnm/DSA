using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Enemigos : MovingObject1
{
    public int playerDamage;//Num puntos substracted cuando el enemigo ataca am jugador

    private Animator animator;
    private Transform target;//Donde esta el jugador y hacia donde tiene que ir el enemigo
    private bool skypMove;

    public AudioClip enemyAttack1;
    public AudioClip enemyAttack2;

    // Start is called before the first frame update
    protected override void Start()
    {
        GameManager1.instance.AddEnemyToList(this);
        animator = GetComponent<Animator>();
        target = GameObject.FindGameObjectWithTag("Player").transform;
        base.Start();
    }

    protected override void AttemptMove<T>(int xDir, int yDir)
    {
        if (skypMove)
        {
            skypMove = false;
            return;//Enemy to move every other turn
        }

        base.AttemptMove<T>(xDir, yDir);
        skypMove = true;
    }

    public void MoveEnemy()
    {
        int xDir = 0;
        int yDir = 0;

        if (Mathf.Abs(target.position.x - transform.position.x) < float.Epsilon)//Miramos si estan en la misma columna
        {
            yDir = target.position.y > transform.position.y ? 1 : -1;// comparamos posicion enemigo y jugador si true usamos positivo y false negativo
        }
        else
        {
            xDir = target.position.x > transform.position.x ? 1 : -1;
        }
        AttemptMove<Jugador1>(xDir, yDir);
    }

    protected override void OnCantMove<T>(T component)
    {
        //Si el enemigo intenta ir donde esta el jugador
        Jugador1 hitPlayer = component as Jugador1;
        animator.SetTrigger("Enemigo1Ataque");
        hitPlayer.LoseFood(playerDamage);
        SoundManager1.instance.randomizeSfx(enemyAttack1, enemyAttack2);
    }
}