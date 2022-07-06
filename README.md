
# testatelier1

Ce projet a été créé en réponse a la demande de test technique de la societé 'L'atelier'.

## Compilation et test

C'est un projet fait avec Maven, il suffit donc de compiler avec lui.

```bash
mvn compile
```

*Les tests se lanceron de la même maniére.

```bash
mvn test
```
## Lancement

Par défaut, l'application lance un serveur http qui écoute sur le port 9988 en localhost.

```bash
mvn exec:java
```

Mais il est possible de changer le port et son adresse d'écoute.

```bash
mvn exec:java -Dexec.args="--http-port 9999 --http-host 0.0.0.0"
```

## Les tâches demandées

### Tâche 1

Crée un endpoint qui retourne les joueurs. La liste doit être triée du meilleur au moins bon.

Le service '/players' est trié par défaut de cette façon.

```bash
curl -X GET http://localhost:9988/players/
```

### Tâche 2

Créer un endpoint qui permet de retourner les informations d’un joueur grâce à son ID.

Ce même endpoint '/players' peux être utiliser pour récupérer cette information

```bash
curl -X GET http://localhost:9988/players/52
```

### Tâche 3

Créer un endpoint qui retourne les statistiques suivantes :

- Pays qui a le plus grand ratio de parties gagnées
- IMC moyen de tous les joueurs
- La médiane de la taille des joueurs

Un service '/stat' a été rajouté pour cet usage.

```bash
curl -X GET http://localhost:9988/stat
```

### Tâche 4

Une execution de l'application est disponible a l'URL:
 
 https://a


## Ajout supplémentaire

Les tâches demandées n'ont nécessité que d’utiliser la méthode GET. L'idée étant de rajouter les méthodes manquantes pour avoir un cycle de vie d'objet complet.

### POST

Pour l'ajout d'un nouveau joueur

```bash
curl -X POST -H "Content-Type: application/json" http://localhost:9988/players/ -d {\"id\":2,\"lastname\":.......}
```

### PUT

Pour la modification d'un joueur existant

```bash
curl -X PUT -H "Content-Type: application/json" http://localhost:9988/players -d {\"id\":2,\"lastname\":.......}
```

### DELETE

Pour la suppression d'un joueur existant

```bash
curl -X DELETE -H "Content-Type: application/json" http://localhost:9988/players/52
```