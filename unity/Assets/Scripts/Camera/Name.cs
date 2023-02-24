using UnityEngine;

public class Name : MonoBehaviour
{
    private Camera mainCamera;

    private void Start()
    {
        if (Camera.main != null)
        {
            mainCamera = Camera.main;
        }
    }

    void Update()
    {
        transform.LookAt(transform.position + mainCamera.transform.rotation * Vector3.forward,
            mainCamera.transform.rotation * Vector3.up);
    }
}
