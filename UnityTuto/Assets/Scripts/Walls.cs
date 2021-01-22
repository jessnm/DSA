using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Walls : MonoBehaviour
{

    public Sprite dmgSprite;//El sprite que se va a aenseñar cuando se destruye una pared.
    public int hp = 4;

    private SpriteRenderer spriteRenderer;

    public AudioClip chopSound1;
    public AudioClip chopSound2;

    // Start is called before the first frame update
    void Awake()
    {
        spriteRenderer = GetComponent<SpriteRenderer>();
    }

    public void DamageWall (int loss)
    {
        SoundManager1.instance.randomizeSfx(chopSound1, chopSound2);
        spriteRenderer.sprite = dmgSprite;
        hp -= loss;
        if (hp <= 0)
        {
            gameObject.SetActive(false);//Si la pared esta eliminada pues disable el sprote para que no se vea. 
        }
    }
}
