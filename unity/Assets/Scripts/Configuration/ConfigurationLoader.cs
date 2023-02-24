using System;
using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.Networking;

public class ConfigurationLoader : MonoBehaviour
{
    [SerializeField] private GameObject characterPrefab;
    [SerializeField] private GameObject orcPrefab;
    [SerializeField] private GameObject beholderPrefab;
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
            GameObject character = SpawnCharacter(battle);
            List<GameObject> enemies = new List<GameObject>();
            foreach (Enemy enemy in battle.enemies)
            {
                enemies.Add(SpawnEnemy(enemy));
            }
            SetEnemyPositions(enemies, battle.position, character);
        }
    }

    private void SetEnemyPositions(List<GameObject> enemyStates, Position position, GameObject character)
    {
        if (enemyStates.Count == 1)
        {
            enemyStates[0].GetComponent<State>().UpdatePosition(new Vector3(position.x, position.y, position.z + 2.5f));
            enemyStates[0].GetComponent<State>().UpdateDirection(180f);
        }
        else
        {
            float angleStep = 360f / enemyStates.Count;

            for (int i = 0; i < enemyStates.Count; i++)
            {
                float angle = i * angleStep;
                Vector3 newPosition = character.transform.position + Quaternion.Euler(0f, angle, 0f) * Vector3.forward * 2.5f;
                enemyStates[i].transform.position = newPosition;
                enemyStates[i].transform.LookAt(character.transform.position);
                character.GetComponent<State>().SetAnimationController("SPIN");
            }
        }
    }

    private GameObject SpawnCharacter(Battle battle)
    {
        GameObject characterObject = Instantiate(characterPrefab, characterContainer);
        State state = characterObject.GetComponent<State>();
        state.SetAnimationController(battle.character.state);
        state.UpdatePosition(new Vector3(battle.position.x, battle.position.y, battle.position.z));
        Transform nameTransform = characterObject.transform.Find("Name");
        GameObject name = nameTransform.gameObject;
        TextMeshPro text = name.GetComponent<TextMeshPro>();
        text.SetText(battle.character.name);
        return characterObject;
    }

    private GameObject SpawnEnemy(Enemy enemy)
    {
        GameObject enemyPrefab;
        if(enemy.type == "ORC")
        {
            enemyPrefab = orcPrefab;
        } else if(enemy.type == "BEHOLDER")
        {
            enemyPrefab = beholderPrefab;
        } else
        {
            enemyPrefab = orcPrefab;
        }

        GameObject enemyObject = Instantiate(enemyPrefab, enemyContainer);
        State state = enemyObject.GetComponent<State>();
        state.SetAnimationController(enemy.type == "BEHOLDER" ? "TAUNTING" : enemy.state);
        Transform nameTransform = enemyObject.transform.Find("Name");
        GameObject name = nameTransform.gameObject;
        TextMeshPro text = name.GetComponent<TextMeshPro>();
        text.SetText(enemy.name);
        return enemyObject;
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
    public string type;
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
    public float x;
    public float y;
    public float z;
}