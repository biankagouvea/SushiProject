# Deployment - Sushi Talence

Ce document couvre 3 scénarios:
1. Lancer le projet en local sans Tomcat (mode développement)
2. Lancer le projet avec Tomcat depuis Eclipse
3. Générer le fichier ROOT.war

## 1) Lancement local sans Tomcat

### Prérequis
- Java 17+
- Node.js 18+

### Backend (Spring Boot)
Depuis le dossier `back`:

```powershell
.\mvnw.cmd spring-boot:run
```

Le backend démarre sur `http://localhost:8084`.

### Frontend (Angular)
Depuis le dossier `front`:

```powershell
npm install
npm start
```

Le frontend démarre sur `http://localhost:4200`.

## 2) Lancement avec Tomcat dans Eclipse

### Prérequis
- Eclipse IDE (Enterprise Java recommandé)
- Tomcat 10.1+
- Java 17+

### Import du projet
1. `File` -> `Import...` -> `Maven` -> `Existing Maven Projects`
2. Sélectionner le dossier `back`

### Ajouter Tomcat
1. Ouvrir la vue `Servers`
2. `New` -> `Server` -> `Apache Tomcat v10.1`
3. Choisir ton installation Tomcat

### Déployer le module
1. Clic droit sur Tomcat -> `Add and Remove...`
2. Ajouter le module `demo(ROOT)`
3. Vérifier que le path est `/` (racine)
4. `Finish`

### Publier et démarrer
1. Clic droit Tomcat -> `Clean`
2. Clic droit Tomcat -> `Publish`
3. `Start`

### URL
- Application: `http://localhost:8080/`

## 3) Génération du ROOT.war

### Option A - Script complet (recommandé)
Depuis la racine du projet:

```powershell
.\build-war.bat
```

Ce script:
1. Build le frontend Angular en production
2. Copie les assets dans `back/src/main/resources/static`
3. Build Maven

### Option B - Manuel

#### 1. Build front
```Shell
cd front
npm install
npm run build -- --configuration production
```

#### 2. Copier les assets dans le backend
Copier le contenu de `front/dist/sushi/browser` vers `back/src/main/resources/static`.

#### 3. Build war
```PowerShell
cd ..\back
.\mvnw.cmd clean package -DskipTests
```
ou
```Shell
cd ..\back
.\mvn.cmd clean package -DskipTests
```

### Résultat
- Fichier généré: `back/target/ROOT.war`

## Données JSON (important)

Les données runtime ne sont pas stockées dans le repo pendant l'exécution Tomcat.

Elles sont écrites dans:
- `<catalina.base>/sushi-data/users.json`
- `<catalina.base>/sushi-data/commandes.json`
- `<catalina.base>/sushi-data/sushis.json`

Comportement au premier démarrage:
- `sushis.json`: initialisé avec les sushis de base
- `users.json`: vide
- `commandes.json`: vide

## Reset des données

Pour remettre à zéro:
1. Stop Tomcat
2. Supprimer le dossier `<catalina.base>/sushi-data`
3. Redémarrer Tomcat


