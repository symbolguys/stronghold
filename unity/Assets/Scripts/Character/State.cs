using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class State : MonoBehaviour
{
    public GameObject nirmo;
    // Start is called before the first frame update
    void Start()
    {
        GetComponent<Animator>().Play("Idle");
    }

    // Update is called once per frame
    void Update()
    { 
        GameObject developer = new GameObject();
    }

    void UpdatePosition(Vector3 position)
    {
        transform.position = position;
    }

    void SetAnimationController(string state)
    {
        GetComponent<Animator>().Play(state);
    }
}
