using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class State : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {

    }

    public void UpdatePosition(Vector3 position)
    {
        transform.position = position;
    }

    public void SetAnimationController(string state)
    {
        GetComponent<Animator>().Play(state);
    }

    public void UpdateDirection(float degrees)
    {
        transform.Rotate(Vector3.up * degrees);
    }
}
