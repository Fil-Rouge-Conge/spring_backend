# 🚀 Guide d'installation Jenkins + SonarQube

---

## 🛠️ Configuration de Jenkins

### 1. Ajouter les outils nécessaires

Aller dans `Tableau de Bord → Administrer Jenkins → Tools`

- **JDK** :
    - Rechercher **Installations JDK**
    - Ajouter un JDK si aucun n'est présent
    - ✅ Cocher `Install automatically`

- **Maven** :
    - Rechercher **Installations Maven**
    - Ajouter Maven si absent
    - Donner un nom (ex : `Maven3`)
    - ✅ Cocher `Install Automatically`
    - Version recommandée : `3.9.9`

--- 

### 2. Création du Pipeline

- Aller dans `Tableau de Bord → Nouveau Item`
- Créer un **Pipeline** avec un nom


aller dans les configurations du Pipeline et entrer le script suivant

#### ⚠️ Attention :
- Adaptez le script à **votre machine**
- Modifiez la **branche Git** si besoin


```groovy
pipeline {
    agent any
    
    tools {
        maven 'Maven3'
        jdk 'Java22'
    }
    
    environment {
        SONARQUBE_ENV = 'SonarQube_1'
        SONAR_PROJECT_KEY = 'FilRouge'
        SRV_DEPLOY = '192.168.1.100'
    }

    stages {
        stage('Clonage du dépôt') {
            steps {
                git branch: 'main_mvn_test', url: 'https://github.com/Fil-Rouge-Conge/spring_backend'
            }
        }
        
        stage('Tests unitaires') {
            steps {
                bat 'mvn test'
            }
            /*
            post {
                always {
                    junit 'target/truc/*.xml'
                }
            }
            */
        }

        
        stage('Analyse SonarQube') {
            steps {
                withSonarQubeEnv("${env.SONARQUBE_ENV}") {
                    bat "mvn sonar:sonar -Dsonar.projectKey=${env.SONAR_PROJECT_KEY}"
                }
            }
        }

        stage('Validation manuelle pour déploiement') {
            steps {
                input message: 'Souhaitez-vous déployer en staging ?'
            }
        }

    /*
    stage('deploiement') {
        steps {
            bat """
            echo Déploiement réussi vers %SRV_DEPLOY%
            pscp target\\*.war user@%SRV_DEPLOY%:/opt/tomcat/webapps/
            """
        }
    }
    */
}

        post {
            success {
                echo "La pipeline terminée avec succès"
            }
            failure {
                echo "La pipeline a échoué"
            }
        }
    }
```

✔️ Sauvegarder et appliquer.

## 🐳 Installation de SonarQube via Docker

### 1. Prérequis

- Avoir **Docker** installé

### 2. Créer les fichiers nécessaires

- Créer un dossier avec les droits d’accès
- Créer un fichier `docker-compose.yaml` contenant :

dans ce fichier rajouter les lignes suivantes :

```yaml
services:
    sonarqube:
        container_name: sonarqube
        image: sonarqube
        depends_on:
            - sonarqube-database
        environment:
            - SONARQUBE_JDBC_USERNAME=sonarqube
            - SONARQUBE_JDBC_PASSWORD=sonarpass
            - SONARQUBE_JDBC_URL=jdbc:postgresql://sonarqube-database:5432/sonarqube
        volumes:
            - sonarqube_conf:/opt/sonarqube/conf
            - sonarqube_data:/opt/sonarqube/data
            - sonarqube_extensions:/opt/sonarqube/extensions
            - sonarqube_bundled-plugins:/opt/sonarqube/lib/bundled-plugins
        ports:
            - 9000:9000

    sonarqube-database:
        container_name: sonarqube-database
        image: postgres:12
        environment:
            - POSTGRES_DB=sonarqube
            - POSTGRES_USER=sonarqube
            - POSTGRES_PASSWORD=sonarpass
        volumes:
            - sonarqube_database:/var/lib/postgresql
            - sonarqube_database_data:/var/lib/postgresql/data
        ports:
            -   5432:5432


volumes:
    sonarqube_database_data:
    sonarqube_bundled-plugins:
    sonarqube_conf:
    sonarqube_data:
    sonarqube_database:
    sonarqube_extensions:
```

sauvegarder le fichier.  

### 3. Lancer les services

Avec un terminal, aller dans le dossier ou ce trouve le fichier crée précédament et faire la commande suivante

```bash
docker compose up -d
```

👉 Accéder à SonarQube via : [http://localhost:9000](http://localhost:9000)
les log pour la première connection sont

### 🔐 Identifiants par défaut

```
Utilisateur  : admin
Mot de passe : admin
```

Un changement de mot de passe vous sera demandé à la première connexion.

---

## 📊 Configuration de SonarQube

### 1. Créer un projet

- Nom : ce que vous voulez
- **Project key : `FilRouge`** (⚠️ important pour Jenkins)
- Choisir :
    - **Global settings**
    - `Create project`
- Méthode d'analyse :
    - `Jenkins` → `Github` → `Maven`

---

## 🔗 Lier Jenkins à SonarQube

### 1. Générer un token SonarQube

- Aller dans `My Account → Security`
- Créer un token :
    - Nom : ce que vous voulez
    - Type : `Global`
    - Expiration : à votre convenance
- ⚠️ **Copier et conserver ce token** (vous ne pourrez plus le voir après)


Dans Jenkins

Aller sur le `Tableau de Bord` => `Administrer jenkins` => `System`  
Aller a la parti intitulé `SonarQube servers`

Dans cette parti faite `Ajouter une installations SonarQube`  
Nommé la comme vous le souhaiter ( ex : `SonarQube_1`) et gardé le nom pour le pipeline  
dedans rajouter l'URL d'accès à SonarQube ( ex : `http://localhost:9000` )  
ensuite on va rajouter le Token pour ça cliquer sur le bouton `Ajouter` => `Jenkins`

Le `Type` du token est `Secret text`  
Dans `Secret` mettre le token généré par SonarQube  
Dans `ID` le nom du token que vous voulez puis appuyer sur le bouton `Ajouter`  

Une fois la pop-up fermé dans la parti `Server authentication token` sélectionner le Token crée

## ✅ Vérifications finales dans le pipeline

dans le script du pipeline il faut vérifier que les bon noms sont mit dans les configuration faite

```
tools {
  maven 'Maven3' // Le nom de la configuration Maven crée dans Jenkins
  jdk 'Java22' // le nom du JDK configurer au tout début dans Jenkins
}

environment {
  SONARQUBE_ENV = 'SonarQube_1' // Nom du SonarQube Server crée dans Jenkins
  SONAR_PROJECT_KEY = 'FilRouge' // Nom du key project dans SonarQube
  SRV_DEPLOY = '192.168.1.100'
}

stages {
  stage('Clonage du dépôt') {
    steps {
        git branch: 'main_mvn_test', url: 'https://github.com/Fil-Rouge-Conge/spring_backend' // mettre la branche a récupéré
    }
  }
```

## ▶️ Lancer le pipeline

Il ne vous reste plus qu'à lancer votre pipeline depuis Jenkins 🎉