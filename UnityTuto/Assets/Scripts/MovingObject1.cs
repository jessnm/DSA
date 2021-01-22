using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public abstract class MovingObject1 : MonoBehaviour
{
    public float moveTime = 0.1f;//Tiempo en el que el objeto se mueve en segundos.
    public LayerMask blockingLayer;//Check colision

    private BoxCollider2D boxColiider;
    private Rigidbody2D rb2D;
    private float inverseMoveTime;//Esto hace movement calculations more efficient.

    // Start is called before the first frame update
    protected virtual void Start()
    {
        //Este tipo de clases se pueden sobreescribir 
        boxColiider = GetComponent<BoxCollider2D>();
        rb2D = GetComponent<Rigidbody2D>();
        inverseMoveTime = 1f / moveTime;
    }

    protected bool Move(int xDir, int yDir, out RaycastHit2D hit)
    {
        Vector2 start = transform.position;
        Vector2 end = start + new Vector2(xDir, yDir);
        boxColiider.enabled = false; //Nos aseguramos que no chocamos nuestro propio collider. 
        hit = Physics2D.Linecast(start, end, blockingLayer);//hace una linea recta y comprueba si nos chocariamos
        boxColiider.enabled = true;//Lo enable otra vez

        if(hit.transform == null)//Si no nos chocamos
        {
            StartCoroutine(SmoothMovement(end));
            return true; //We are able to move
        }
        return false;
    }

    protected IEnumerator SmoothMovement(Vector3 end)//Mueve unidades de un espacio a otro
    {
        float sqrRemainingDistance = (transform.position - end).sqrMagnitude;

        while(sqrRemainingDistance>float.Epsilon)//Esto mira si la distanciae es mas grande a un numero muy cercano a 0
        {
            Vector3 newPosition = Vector3.MoveTowards(rb2D.position, end, inverseMoveTime*Time.deltaTime);//Mueve en linea recta hacia una posicion
            rb2D.MovePosition(newPosition);
            sqrRemainingDistance = (transform.position - end).sqrMagnitude;
            yield return null;//Esperamos por un Frame antes de reevaluar el loop (es como n+1, pero cuando nos digan)
        }
    }

    protected virtual void AttemptMove <T>(int xDir, int yDir)
        where T: Component
    {
        RaycastHit2D hit;
        bool canMove = Move(xDir, yDir, out hit);//Esto quiere decir que pondremos true si es suceefsul an dfalse si falla.

        if(hit.transform ==null)
        {
            return;//Esto es que si nose golpea nada hacemos return y no se hace este codigo
        }
        //Si hemos golpeado algo hara lo siguiente: 
        T hitComponent = hit.transform.GetComponent<T>();

        if(!canMove && hitComponent != null)
        {
            OnCantMove(hitComponent);
        }
    }


    protected abstract void OnCantMove<T>(T component)
        where T : Component;
}
