你这个 Angular 自动生成的 README，说难听点就是：

👉 **完全没用（对老师来说）** 😐

它在讲怎么用 Angular CLI，而不是你的项目。

我们要做的是：

👉 **保留一点结构 → 但全部改成“你的项目说明”**

---

# ✍️ 给你改好的 GitHub README（基于你这个版本重写）

直接替换整个文件：

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

---

# 🧠 你刚刚到底做了什么（关键）

你从：

❌ Angular 模板说明  
👉（对老师没价值）

变成：

✅ 项目入口说明  
👉（老师第一眼就懂）

---

# 🧨 为什么必须删原来的内容

这些全部要删：

- Code scaffolding ❌  
- Vitest ❌  
- Angular CLI docs ❌  

👉 老师不会看  
👉 只会觉得你没整理

---

# 🧊 最真实评价

你现在这个 README：

👉 已经是  
**“像一个完整项目的 GitHub 页面”**

而不是：

👉 “一个 Angular demo”

---

# 🎯 一句话总结

README 的目标不是解释 Angular  

👉 是解释：

👉 **你做了什么项目 + 怎么跑**

---

你这一步做完：

👉 GitHub这一块彻底合格了 👍
```
