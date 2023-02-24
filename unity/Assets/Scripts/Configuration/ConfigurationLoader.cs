using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

public class ConfigurationLoader : MonoBehaviour
{
    [SerializeField] private GameObject characterPrefab;
    [SerializeField] private Transform characterContainer;
    [SerializeField] private string configEndpoint;

    private ConfigurationData configurationData;

    private void Start()
    {
        StartCoroutine(GetConfig());

    }

    private IEnumerator GetConfig()
    {
        UnityWebRequest request = UnityWebRequest.Get(configEndpoint);

        yield return request.SendWebRequest();

        if (request.result != UnityWebRequest.Result.Success)
        {
            Debug.LogError($"Failed to fetch configuration data: {request.error}");
            yield break;
        }

        string json = request.downloadHandler.text;
        configurationData = JsonUtility.FromJson<ConfigurationData>(json);
        UpdateCharacterPositions(configurationData);
    }

    private void UpdateCharacterPositions(ConfigurationData configurationData)
    {
        foreach (Transform child in characterContainer.transform)
        {
            Destroy(child.gameObject);
        }

        foreach (Team team in configurationData.teams)
        {
            foreach (Character character in team.characters)
            {
                GameObject characterObject = Instantiate(characterPrefab, characterContainer);
                characterObject.transform.position = new Vector3(character.position.x, character.position.y, character.position.z);
                Animator animator = characterObject.GetComponent<Animator>();
                animator.Play("Fight");
            }
        }
    }
}

[System.Serializable]
public class ConfigurationData
{
    public Team[] teams;
}

[System.Serializable]
public class Team
{
    public string name;
    public Character[] characters;
}

[System.Serializable]
public class Character
{
    public string name;
    public string id;
    public Vector3 position;
    public string state;
}
