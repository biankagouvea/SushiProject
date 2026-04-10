
````markdown
# 🍣 Projet Sushi – Application de commande

## 📌 Description

Ce projet est une application web permettant de :
- consulter un menu de sushis
- passer une commande
- gérer un compte utilisateur

Un espace administrateur permet également de gérer les produits et consulter les commandes.

---

## 🛠️ Technologies

- Front-end : Angular
- Back-end : Spring Boot
- Persistance : fichiers JSON
- Serveur : Apache Tomcat (compatible WAR)

---

## ▶️ Exécution

### Back-end (Spring Boot)

```bash
cd back
mvn spring-boot:run
````

Serveur disponible sur :
[http://localhost:8084](http://localhost:8084)

---

### Front-end (Angular)

```bash
cd front
npm install
ng serve
```

Application disponible sur :
[http://localhost:4200](http://localhost:4200)

---

## 🔗 API

Le front-end communique avec le back-end via une API REST disponible sur :
[http://localhost:8084](http://localhost:8084)

---

## ⚠️ Remarque

Le projet est compatible avec un déploiement sur Apache Tomcat via un fichier WAR.
Cependant, pour simplifier l’exécution, il est lancé ici avec Spring Boot.

---

## 🌐 GitHub

Branche utilisée : **Sushi(new)**

---

## 👥 Auteurs

* Huiying Zhou
* Bianka Gouvêa
* Noufel Bouguerra

```

