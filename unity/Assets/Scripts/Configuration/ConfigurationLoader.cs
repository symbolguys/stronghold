using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

public class ConfigurationLoader : MonoBehaviour
{
    public string configEndpointUrl;
    public float updateIntervalSec = 5f;

    public GameObject characterPrefab;

    private void Start()
    {
        InvokeRepeating("GetConfig", 0f, updateIntervalSec);
    }

    private IEnumerator GetConfig()
    {
        UnityWebRequest request = UnityWebRequest.Get(configEndpointUrl);

        yield return request.SendWebRequest();

        if (request.result == UnityWebRequest.Result.Success)
        {
            string configData = request.downloadHandler.text;
            ProcessConfig(configData);
        }
        else
        {
            Debug.LogError("Error retrieving configuration: " + request.error);
        }
    }

    private void ProcessConfig(string configData)
    {
        ConfigurationData data = JsonUtility.FromJson<ConfigurationData>(configData);

        foreach (Team team in data.teams)
        {
            foreach (Character character in team.characters)
            {
                GameObject characterObj = Instantiate(characterPrefab, Vector3.zero, Quaternion.identity, transform);
                characterObj.name = character.name;
                characterObj.transform.localPosition = new Vector3(character.position.x, character.position.y, 0f);

                Animator animator = characterObj.GetComponent<Animator>();
                if (animator != null)
                {
                    animator.Play(character.state); // convert the enum value to a string and pass it to animator.Play
                }
            }
        }
    }
}


public class ConfigurationData
{
    public Team[] teams;
}

public class Team
{
    public string name;
    public Character[] characters;
}

[System.Serializable]
public class Character
{
    public string name;
    public Vector2 position;
    public string state;
}