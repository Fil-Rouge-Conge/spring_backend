# 🔐 Spring Security API

Ce projet utilise **Spring Security** pour sécuriser l'ensemble des endpoints, à l'exception de ceux utilisés pour l'authentification.

## 🧭 Accès aux endpoints

- **Tous les endpoints** sauf ceux commençant par `/api/auth/**` nécessitent une authentification via **JWT Bearer Token**.
- L'accès aux ressources est contrôlé par rôle grâce à l'annotation `@Secured`.

---

## 🔑 Authentification

### Endpoint de connexion

```
POST /api/auth/login
```

### Corps de la requête

Envoyer un objet JSON contenant **les identifiants en clair** (non cryptés) :

```json
{
  "username": "votre_username",
  "password": "votre_password"
}
```

### Réponse

Un token JWT :

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."
}
```

---

## 🔐 Accès sécurisé

Pour accéder aux endpoints protégés, ajouter l'en-tête suivant à chaque requête :

```
Authorization: Bearer <votre_token>
```

---

## 🧹 Contrôle d’accès par rôle

Utilise l’annotation `@Secured` dans les contrôleurs pour restreindre l’accès selon les rôles.
Rôles disponibles :
- `ROLE_ADMIN`
- `ROLE_MANAGER`
- `ROLE_EMPLOYEE`
