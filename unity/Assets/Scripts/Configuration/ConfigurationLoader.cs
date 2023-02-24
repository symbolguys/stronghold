using System.Collections;
using TMPro;
using UnityEngine;
using UnityEngine.Networking;

public class ConfigurationLoader : MonoBehaviour
{
    [SerializeField] private GameObject characterPrefab;
    [SerializeField] private GameObject enemyPrefab;
    [SerializeField] private Transform characterContainer;
    [SerializeField] private Transform enemyContainer;
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

        foreach (Battle battle in configurationData.battles)
        {
            SpawnCharacter(battle);
        }
    }

    private void SpawnCharacter(Battle battle)
    {
        GameObject characterObject = Instantiate(characterPrefab, characterContainer);
        State state = characterObject.GetComponent<State>();
        state.UpdatePosition(new Vector3(battle.position.x, battle.position.y, battle.position.z));
        state.SetAnimationController(battle.character.state);
        Transform nameTransform = characterObject.transform.Find("Name");
        GameObject name = nameTransform.gameObject;
        TextMeshPro text = name.GetComponent<TextMeshPro>();
        text.SetText(battle.character.name);
    }
}

[System.Serializable]
public class ConfigurationData
{
    public Battle[] battles;
    public Project[] projects;
}

[System.Serializable]
public class Battle
{
    public Enemy[] enemies;
    public Character character;
    public Position position;
}

[System.Serializable]
public class Project
{
    public Character[] characters;
    public string type;
    public Position position;
}

[System.Serializable]
public class Enemy
{
    public string id;
    public string name;
    public string state;
}

[System.Serializable]
public class Character
{
    public string name;
    public string id;
    public string state;
}

[System.Serializable]
public class Position
{
    public int x;
    public int y;
    public int z;
}