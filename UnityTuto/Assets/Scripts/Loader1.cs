using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Loader1 : MonoBehaviour
{
    //mira si un game ha estado instance it y si no lo hace
    public GameObject gameManager;

    // Start is called before the first frame update
    void Awake()
    {
        if(GameManager1.instance == null)
        {
            Instantiate(gameManager);
        }
    }
}
