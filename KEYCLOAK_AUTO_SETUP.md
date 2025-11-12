# Keycloak Automatic Setup Guide

Este projeto está configurado para **importar automaticamente** todas as configurações do Keycloak ao iniciar o Docker Compose.

---

## Como Funciona

O arquivo `keycloak-realm.json` contém todas as configurações do realm e é automaticamente importado quando o Keycloak inicia.

### O que é criado automaticamente:

1. **Realm:** `otaku-rating-api`
2. **Client:** `otaku-rating-api-client`
   - Client Secret: `tryeur6JeDE4dAJ2qxjEpkE19NbbNCc0` (fixo e versionado)
   - Direct Access Grants: Habilitado
   - Service Accounts: Habilitado
3. **Roles:**
   - `ROLE_ADMIN` - Administrador com acesso total
   - `ROLE_MODERATOR` - Moderador com acesso limitado
   - `ROLE_COMMON` - Usuário comum
4. **Usuários de Teste:**
   - `admin_user` / `admin123` (ROLE_ADMIN)
   - `moderator_user` / `moderator123` (ROLE_MODERATOR)
   - `common_user` / `user123` (ROLE_COMMON)

---

## Iniciando o Projeto

### 1. Suba todos os serviços:

```bash
docker-compose up -d
```

### 2. Aguarde os containers iniciarem:

```bash
docker-compose logs -f keycloak
```

Aguarde até ver:
```
Importing realm from file: /opt/keycloak/data/import/otaku-rating-api.json
Realm 'otaku-rating-api' imported successfully
Keycloak 26.4.2 started
```

### 3. Verifique se todos os serviços estão rodando:

```bash
docker-compose ps
```

Você deve ver:
- ✅ consul (porta 8500)
- ✅ keycloak (porta 8080)
- ✅ user-db (MySQL)
- ✅ anime-db (MongoDB)
- ✅ gateway-service (porta 8081)
- ✅ user-service
- ✅ anime-service

---

## Acessando o Keycloak Admin Console

1. Abra: **http://localhost:8080**
2. Clique em **Administration Console**
3. Login:
   - **Username:** `admin123`
   - **Password:** `admin123`
4. Selecione o realm: **otaku-rating-api** (dropdown superior esquerdo)

Todas as configurações já estarão prontas!

---

## Testando a API

### Obter Token de Acesso (via Gateway)

```bash
curl -X POST http://localhost:8081/user-service/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin_user",
    "password": "admin123"
  }'
```

O token será retornado como **cookie** (`access_token` e `refresh_token`).

### Testar Endpoint Protegido

```bash
curl http://localhost:8081/user-service/user/me \
  -H "Cookie: access_token=<seu-token-aqui>"
```

---

## Endpoints da API

### User Service (via Gateway: `http://localhost:8081/user-service`)

#### Autenticação
- `POST /auth/login` - Login
- `POST /auth/refresh` - Refresh token
- `POST /auth/logout` - Logout

#### Usuários
- `GET /user/me` - Perfil do usuário autenticado
- `PUT /user/me` - Atualizar perfil
- `DELETE /user/me` - Deletar conta
- `GET /user` - Listar usuários (paginado)
- `GET /user/{id}` - Buscar por ID
- `GET /user/username/{username}` - Buscar por username
- `GET /user/email/{email}` - Buscar por email
- `POST /user` - Criar usuário

### Anime Service (via Gateway: `http://localhost:8081/anime-service`)

#### Animes
- `GET /v1/anime` - Listar animes (público)
- `GET /v1/anime/{id}` - Buscar anime (público)
- `POST /v1/anime` - Criar anime (ADMIN/MODERATOR)
- `PUT /v1/anime/{id}` - Atualizar anime (ADMIN/MODERATOR)
- `DELETE /v1/anime/{id}` - Deletar anime (ADMIN/MODERATOR)

#### Pessoas
- `GET /v1/person` - Listar pessoas (público)
- `GET /v1/person/{id}` - Buscar pessoa (público)
- `POST /v1/person` - Criar pessoa (ADMIN/MODERATOR)
- `PUT /v1/person/{id}` - Atualizar pessoa (ADMIN/MODERATOR)
- `DELETE /v1/person/{id}` - Deletar pessoa (ADMIN/MODERATOR)

#### Contribuições
- `GET /v1/anime-contribution/{animeId}/{personId}` - Buscar contribuição (público)
- `POST /v1/anime-contribution` - Criar contribuição (ADMIN/MODERATOR)
- `PUT /v1/anime-contribution/{animeId}/{personId}` - Atualizar contribuição (ADMIN/MODERATOR)
- `DELETE /v1/anime-contribution/{animeId}/{personId}` - Deletar contribuição (ADMIN/MODERATOR)

---

## Modificando as Configurações do Keycloak

Se precisar alterar configurações do Keycloak:

### 1. Edite o arquivo `keycloak-realm.json`

```json
{
  "realm": "otaku-rating-api",
  "enabled": true,
  "clients": [...],
  "roles": {...},
  "users": [...]
}
```

### 2. Recrie os containers:

```bash
docker-compose down
docker-compose up -d
```

**Importante:** O import só funciona quando o realm **não existe** no banco. Se você já tem o realm criado, ele não será sobrescrito.

### Para forçar reimport (CUIDADO - apaga dados):

```bash
docker-compose down -v  # Remove volumes (apaga banco de dados)
docker-compose up -d
```

---

## Client Secret

O Client Secret é **fixo** e está definido em dois lugares:

1. **keycloak-realm.json:**
```json
"secret": "tryeur6JeDE4dAJ2qxjEpkE19NbbNCc0"
```

2. **docker-compose.yml (user-service):**
```yaml
KEYCLOAK_CLIENT_SECRET: tryeur6JeDE4dAJ2qxjEpkE19NbbNCc0
```

**Vantagens:**
- Não precisa copiar/colar manualmente
- Funciona em qualquer ambiente (dev, CI/CD)
- Versionado no Git

**Segurança:**
- Em **produção**, use variáveis de ambiente ou secrets managers
- Nunca commite secrets reais em repositórios públicos

---

## Usuários de Teste

| Username | Password | Role | Email |
|----------|----------|------|-------|
| admin_user | admin123 | ROLE_ADMIN | admin@otakurating.com |
| moderator_user | moderator123 | ROLE_MODERATOR | moderator@otakurating.com |
| common_user | user123 | ROLE_COMMON | user@otakurating.com |

---

## Troubleshooting

### Problema: "Realm already exists"

**Causa:** O realm já foi importado anteriormente.

**Solução:** O Keycloak mantém o realm existente. Se quiser reimportar, delete o volume:

```bash
docker-compose down -v
docker-compose up -d
```

### Problema: "Client secret mismatch"

**Causa:** O secret no `keycloak-realm.json` difere do `docker-compose.yml`.

**Solução:** Certifique-se de que ambos os arquivos têm o mesmo secret:
- `keycloak-realm.json` → `"secret": "tryeur6JeDE4dAJ2qxjEpkE19NbbNCc0"`
- `docker-compose.yml` → `KEYCLOAK_CLIENT_SECRET: tryeur6JeDE4dAJ2qxjEpkE19NbbNCc0`

### Problema: user-service não conecta ao Keycloak

**Causa:** O user-service subiu antes do Keycloak terminar o import.

**Solução:** O docker-compose já tem health check. Reinicie o user-service:

```bash
docker-compose restart user-service
```

---

## Arquitetura de Autenticação

```
┌─────────────┐
│   Cliente   │
└──────┬──────┘
       │
       │ 1. Login (username/password)
       ▼
┌─────────────────────┐
│  Gateway Service    │ :8081
│  (Spring Gateway)   │
└──────┬──────────────┘
       │
       │ 2. Roteamento
       ▼
┌─────────────────────┐
│   User Service      │ :8082
│   (AuthController)  │
└──────┬──────────────┘
       │
       │ 3. Autentica via Keycloak Admin API
       ▼
┌─────────────────────┐
│     Keycloak        │ :8080
│  (otaku-rating-api) │
└─────────────────────┘
       │
       │ 4. Retorna tokens JWT
       │
       └──► Cliente recebe tokens como cookies
```

---

## Próximos Passos

1. ✅ Keycloak configurado automaticamente
2. ✅ Client secret fixo e versionado
3. ✅ Usuários de teste criados
4. ✅ Gateway rodando na porta 8081
5. ✅ Todos os microserviços integrados

**Agora você pode:**
- Testar a API via Postman ou curl
- Desenvolver novos endpoints
- Adicionar mais usuários via Keycloak Admin
- Configurar roles customizados

---

## Referências

- [Keycloak Import/Export](https://www.keycloak.org/docs/latest/server_admin/#_export_import)
- [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)
- [Spring Security OAuth2 Resource Server](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/index.html)