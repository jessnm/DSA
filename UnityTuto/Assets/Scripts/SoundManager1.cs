using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SoundManager1 : MonoBehaviour
{
    public AudioSource efxSource;
    public AudioSource musicSource;
    public static SoundManager1 instance = null;

    public float lowPitchRange = 0.95f;
    public float highPitchRange = 1.05f;//5% mas o menos del original pitch
    // Start is called before the first frame update
    void Awake()
    {
        if(instance == null)
        {
            instance = this;
        }
        else if (instance != this)
        {
            Destroy(gameObject);
        }

        DontDestroyOnLoad(gameObject);
    }

    public void playSingle(AudioClip clip)
    {
        efxSource.clip = clip;
        efxSource.Play();
    }

    public void randomizeSfx(params AudioClip [] clips)
    {
        int randomIndex = Random.Range(0, clips.Length);//Escoge un random clip para el juego
        float randomPitch = Random.Range(lowPitchRange, highPitchRange);

        efxSource.pitch = randomPitch;
        efxSource.clip = clips[randomIndex];
        efxSource.Play();
    }
}
