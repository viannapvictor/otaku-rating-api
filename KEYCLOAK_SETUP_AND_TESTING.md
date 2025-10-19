# Keycloak Setup and Testing Guide

This guide will walk you through setting up Keycloak with Docker, configuring realms and roles, and testing the API with Postman.

---

## Table of Contents
1. [Starting Keycloak with Docker](#1-starting-keycloak-with-docker)
2. [Accessing Keycloak Admin Console](#2-accessing-keycloak-admin-console)
3. [Creating a Realm](#3-creating-a-realm)
4. [Creating Realm Roles](#4-creating-realm-roles)
5. [Creating a Client](#5-creating-a-client)
6. [Creating Test Users](#6-creating-test-users)
7. [Assigning Roles to Users](#7-assigning-roles-to-users)
8. [Testing with Postman](#8-testing-with-postman)

---

## 1. Starting Keycloak with Docker

### Start Docker Compose

Navigate to your project directory and run:

```bash
cd C:\Users\vitor\OneDrive\Documentos\Victor - Estudos\Eng. de Software\08º-Módulo\Projeto de Bloco\otaku-rating-api
docker-compose up -d
```

### Verify Containers are Running

```bash
docker ps
```

You should see two containers:
- **Keycloak** (port 8443:8080)
- **MySQL** (port 3306:3306)

### Check Logs (if needed)

```bash
docker-compose logs -f keycloak
```

Wait until you see: `Keycloak 26.2.3 started`

---

## 2. Accessing Keycloak Admin Console

1. Open your browser and go to: **http://localhost:8443**

2. Click on **Administration Console**

3. Login with default admin credentials:
   - **Username**: `admin`
   - **Password**: `admin`

---

## 3. Creating a Realm

1. In the Keycloak Admin Console, hover over **master** (top-left dropdown)

2. Click **Create Realm**

3. Fill in the details:
   - **Realm name**: `otaku-rating-api`
   - **Enabled**: `ON`

4. Click **Create**

---

## 4. Creating Realm Roles

### Create Admin Role

1. In the left sidebar, click **Realm roles**

2. Click **Create role**

3. Fill in:
   - **Role name**: `ROLE_ADMIN`
   - **Description**: `Administrator role with full access`

4. Click **Save**

### Create Moderator Role

1. Click **Create role** again

2. Fill in:
   - **Role name**: `ROLE_MODERATOR`
   - **Description**: `Moderator role with limited admin access`

3. Click **Save**

### Create Common Role

1. Click **Create role** again

2. Fill in:
   - **Role name**: `ROLE_COMMON`
   - **Description**: `Common user role`

3. Click **Save**

**Result:** You should now have 3 realm roles: `ROLE_ADMIN`, `ROLE_MODERATOR`, and `ROLE_COMMON`

---

## 5. Creating a Client

### Create Client

1. In the left sidebar, click **Clients**

2. Click **Create client**

3. Fill in the **General Settings**:
   - **Client type**: `OpenID Connect`
   - **Client ID**: `otaku-rating-api-client`

4. Click **Next**

5. In **Capability config**:
   - **Client authentication**: `On` 
   - **Direct access grants**: `Checked`
   - **Service accounts roles**: `Checked`
   - **Authorization**: `OFF`
   - **Authentication flow**:
     - ✅ **Standard flow** (Authorization Code Flow)
     - ✅ **Direct access grants** (Resource Owner Password Credentials - for testing)
     - ✅ **Implicit flow** (optional)
     - ✅ **Service accounts roles** (optional)

6. Click **Next**

7. In **Login settings**:
   - **Valid redirect URIs**: `*` (for testing only - use specific URLs in production)
   - **Valid post logout redirect URIs**: `*`
   - **Web origins**: `*` (for CORS)

8. Click **Save**

### Configure Client Settings (Optional)

1. Go to the **Settings** tab of your client

2. Scroll down to **Advanced** section:
   - **Access Token Lifespan**: `15 Minutes` (default: 5 minutes)
   - **Refresh Token Lifespan**: `30 Minutes`

3. Click **Save**

---

## 6. Creating Test Users

### Create Admin User

1. In the left sidebar, click **Users**

2. Click **Create new user**

3. Fill in:
   - **Username**: `admin_user`
   - **Email**: `admin@otakurating.com`
   - **Email verified**: `ON`
   - **First name**: `Admin`
   - **Last name**: `User`
   - **Enabled**: `ON`

4. Click **Create**

5. Go to **Credentials** tab:
   - Click **Set password**
   - **Password**: `admin123`
   - **Temporary**: `OFF`
   - Click **Save**

### Create Moderator User

1. Click **Users** → **Create new user**

2. Fill in:
   - **Username**: `moderator_user`
   - **Email**: `moderator@otakurating.com`
   - **Email verified**: `ON`
   - **First name**: `Moderator`
   - **Last name**: `User`
   - **Enabled**: `ON`

3. Click **Create**

4. Go to **Credentials** tab:
   - **Password**: `moderator123`
   - **Temporary**: `OFF`
   - Click **Save**

### Create Common User

1. Click **Users** → **Create new user**

2. Fill in:
   - **Username**: `common_user`
   - **Email**: `user@otakurating.com`
   - **Email verified**: `ON`
   - **First name**: `Common`
   - **Last name**: `User`
   - **Enabled**: `ON`

3. Click **Create**

4. Go to **Credentials** tab:
   - **Password**: `user123`
   - **Temporary**: `OFF`
   - Click **Save**

---

## 7. Assigning Roles to Users

### Assign ROLE_ADMIN to admin_user

1. Go to **Users** → Search for `admin_user` → Click on it

2. Click **Role mapping** tab

3. Click **Assign role**

4. Select **Filter by realm roles**

5. Check `ROLE_ADMIN`

6. Click **Assign**

### Assign ROLE_MODERATOR to moderator_user

1. Go to **Users** → Search for `moderator_user` → Click on it

2. Click **Role mapping** tab

3. Click **Assign role**

4. Select **Filter by realm roles**

5. Check `ROLE_MODERATOR`

6. Click **Assign**

### Assign ROLE_COMMON to common_user

1. Go to **Users** → Search for `common_user` → Click on it

2. Click **Role mapping** tab

3. Click **Assign role**

4. Select **Filter by realm roles**

5. Check `ROLE_COMMON`

6. Click **Assign**

---

## 8. Testing with Postman

### Setup Postman Environment

1. Open Postman

2. Click **Environments** (left sidebar)

3. Click **Create Environment**

4. Name it: `Keycloak Local`

5. Add these variables:
   - `keycloak_url`: `http://localhost:8443`
   - `api_url`: `http://localhost:8080`
   - `realm`: `otaku-rating-api`
   - `client_id`: `otaku-rating-api-client`
   - `access_token`: (leave empty)

6. Click **Save**

7. Select the environment from the dropdown (top-right)

---

### Request 1: Get Access Token (Admin User)

**Method:** `POST`

**URL:** `{{keycloak_url}}/realms/{{realm}}/protocol/openid-connect/token`

**Headers:**
```
Content-Type: application/x-www-form-urlencoded
```

**Body (x-www-form-urlencoded):**
```
grant_type: password
client_id: {{client_id}}
username: admin_user
password: admin123
```

**Test Script (to auto-save token):**
```javascript
var jsonData = pm.response.json();
pm.environment.set("access_token", jsonData.access_token);
console.log("Access Token: " + jsonData.access_token);
```

**Expected Response:**
```json
{
  "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9...",
  "expires_in": 900,
  "refresh_expires_in": 1800,
  "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "token_type": "Bearer",
  "session_state": "...",
  "scope": "profile email"
}
```

---

### Request 2: Get Current User (GET /user/me)

**Method:** `GET`

**URL:** `{{api_url}}/user/me`

**Headers:**
```
Authorization: Bearer {{access_token}}
```

**Expected Response:**
```json
{
  "success": true,
  "result": {
    "id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
    "username": "admin_user",
    "email": "admin@otakurating.com",
    "firstName": "Admin",
    "lastName": "User",
    "enabled": true,
    "emailVerified": true,
    "createdTimestamp": 1234567890123
  },
  "error": null
}
```

---

### Request 3: Get All Users (GET /user)

**Method:** `GET`

**URL:** `{{api_url}}/user?page=0&size=10`

**Headers:**
```
Authorization: Bearer {{access_token}}
```

**Expected Response:**
```json
{
  "success": true,
  "result": [
    {
      "id": "...",
      "username": "admin_user",
      "email": "admin@otakurating.com",
      "firstName": "Admin",
      "lastName": "User",
      "enabled": true,
      "emailVerified": true,
      "createdTimestamp": 1234567890123
    },
    {
      "id": "...",
      "username": "moderator_user",
      "email": "moderator@otakurating.com",
      "firstName": "Moderator",
      "lastName": "User",
      "enabled": true,
      "emailVerified": true,
      "createdTimestamp": 1234567890456
    },
    {
      "id": "...",
      "username": "common_user",
      "email": "user@otakurating.com",
      "firstName": "Common",
      "lastName": "User",
      "enabled": true,
      "emailVerified": true,
      "createdTimestamp": 1234567890789
    }
  ],
  "error": null
}
```

---

### Request 4: Create New User (POST /user)

**Method:** `POST`

**URL:** `{{api_url}}/user`

**Headers:**
```
Authorization: Bearer {{access_token}}
Content-Type: application/json
```

**Body (JSON):**
```json
{
  "username": "newuser",
  "email": "newuser@example.com",
  "firstName": "New",
  "lastName": "User",
  "password": "newpassword123"
}
```

**Expected Response:**
```json
{
  "success": true,
  "result": {
    "id": "new-user-id-here",
    "username": "newuser",
    "email": "newuser@example.com",
    "firstName": "New",
    "lastName": "User",
    "enabled": true,
    "emailVerified": false,
    "createdTimestamp": 1234567891234
  },
  "error": null
}
```

---

### Request 5: Update Current User (PUT /user/me)

**Method:** `PUT`

**URL:** `{{api_url}}/user/me`

**Headers:**
```
Authorization: Bearer {{access_token}}
Content-Type: application/json
```

**Body (JSON):**
```json
{
  "firstName": "Updated",
  "lastName": "Name",
  "username": "admin_user_updated"
}
```

**Expected Response:**
```json
{
  "success": true,
  "result": {
    "id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
    "username": "admin_user_updated",
    "email": "admin@otakurating.com",
    "firstName": "Updated",
    "lastName": "Name",
    "enabled": true,
    "emailVerified": true,
    "createdTimestamp": 1234567890123
  },
  "error": null
}
```

---

### Request 6: Get User by Username

**Method:** `GET`

**URL:** `{{api_url}}/user/username/common_user`

**Headers:**
```
Authorization: Bearer {{access_token}}
```

**Expected Response:**
```json
{
  "success": true,
  "result": {
    "id": "...",
    "username": "common_user",
    "email": "user@otakurating.com",
    "firstName": "Common",
    "lastName": "User",
    "enabled": true,
    "emailVerified": true,
    "createdTimestamp": 1234567890789
  },
  "error": null
}
```

---

### Request 7: Get User by Email

**Method:** `GET`

**URL:** `{{api_url}}/user/email/moderator@otakurating.com`

**Headers:**
```
Authorization: Bearer {{access_token}}
```

**Expected Response:**
```json
{
  "success": true,
  "result": {
    "id": "...",
    "username": "moderator_user",
    "email": "moderator@otakurating.com",
    "firstName": "Moderator",
    "lastName": "User",
    "enabled": true,
    "emailVerified": true,
    "createdTimestamp": 1234567890456
  },
  "error": null
}
```

---

### Request 8: Get User by ID

**Method:** `GET`

**URL:** `{{api_url}}/user/{user-id-here}`

**Headers:**
```
Authorization: Bearer {{access_token}}
```

**Note:** Replace `{user-id-here}` with an actual user ID from a previous response.

---

### Request 9: Update User by ID (Admin only)

**Method:** `PUT`

**URL:** `{{api_url}}/user/{user-id-here}`

**Headers:**
```
Authorization: Bearer {{access_token}}
Content-Type: application/json
```

**Body (JSON):**
```json
{
  "firstName": "Modified",
  "lastName": "ByAdmin",
  "username": "modified_username"
}
```

---

### Request 10: Delete Current User (DELETE /user/me)

**Method:** `DELETE`

**URL:** `{{api_url}}/user/me`

**Headers:**
```
Authorization: Bearer {{access_token}}
```

**Expected Response:**
```json
{
  "success": true,
  "result": null,
  "error": null
}
```

**Warning:** This will delete the authenticated user from Keycloak!

---

### Request 11: Delete User by ID (Admin only)

**Method:** `DELETE`

**URL:** `{{api_url}}/user/{user-id-here}`

**Headers:**
```
Authorization: Bearer {{access_token}}
```

**Expected Response:**
```json
{
  "success": true,
  "result": null,
  "error": null
}
```

---

## Testing Different Roles

### Test with ROLE_COMMON user

1. Get a new token using `common_user` credentials:
   - Username: `common_user`
   - Password: `user123`

2. Try accessing `/user/me` - Should work ✅

3. Try accessing `/user` (list all users) - Should work if configured ✅

4. Try deleting a user by ID - Should fail if not configured ❌

### Test with ROLE_MODERATOR user

1. Get a new token using `moderator_user` credentials:
   - Username: `moderator_user`
   - Password: `moderator123`

2. Test moderator-specific endpoints (configure in your SecurityFilterChain)

### Test with ROLE_ADMIN user

1. Get a new token using `admin_user` credentials:
   - Username: `admin_user`
   - Password: `admin123`

2. All endpoints should work ✅

---

## Troubleshooting

### Issue: "401 Unauthorized"

**Solution:**
- Check if your token is valid (not expired)
- Get a new token using Request 1
- Verify the `Authorization` header is set correctly

### Issue: "403 Forbidden"

**Solution:**
- Check if the user has the required role
- Verify role assignments in Keycloak Admin Console
- Check your `WebSecurityConfig.java` role requirements

### Issue: "Unknown database 'keycloak'"

**Solution:**
- Make sure MySQL port is exposed in docker-compose.yml
- Connect to MySQL and create the database:
  ```bash
  docker exec -it <container-id> bash
  mysql -u root -pAdmin@123
  CREATE DATABASE keycloak;
  ```

### Issue: "Connection refused to Keycloak"

**Solution:**
- Check if Keycloak container is running: `docker ps`
- Check logs: `docker-compose logs keycloak`
- Wait for Keycloak to fully start

---

## Additional Resources

- [Keycloak Documentation](https://www.keycloak.org/documentation)
- [Spring Security OAuth2 Resource Server](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/index.html)
- [Postman Documentation](https://learning.postman.com/docs/getting-started/introduction/)

---

## Summary

You've successfully:
✅ Started Keycloak and MySQL with Docker Compose
✅ Created a realm: `otaku-rating-api`
✅ Created 3 roles: `ROLE_ADMIN`, `ROLE_MODERATOR`, `ROLE_COMMON`
✅ Created a client: `otaku-rating-api-client`
✅ Created 3 test users with different roles
✅ Tested all endpoints with Postman

Your Keycloak integration is now ready for development! 🎉