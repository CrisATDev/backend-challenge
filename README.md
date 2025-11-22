# 🎧 DevSoulify – Backend Challenge (Kotlin + Spring Boot)

Welcome!  
This is the official **Backend Technical Challenge** for developers who want to join **DevSoul**.

Your mission is to build a production-ready backend service using **Kotlin + Spring Boot**, integrating with the **Spotify Web API**, storing curated data in your database and exposing clean REST endpoints for a frontend/mobile client.

You’ll connect **one API (Spotify)** to **your own API**, with your own data model and persistence.

---

## 🚀 Tech Stack (Required)

You must use:

- **Kotlin**
- **Spring Boot 3+**
- **Gradle (KTS)**
- **Spring Web**
- **Spring Data JPA**
- **PostgreSQL o MySQL** 
- **Coroutines** (WebClient or OkHttp + kotlinx-coroutines)
- Clean architecture (controllers → services → domain → repositories)

Optional but welcome:

- Swagger / Spring Doc  
- Liquibase / Flyway  
- Docker Compose  

---

## 🎯 Goal of the Challenge

Build a backend that:

1. Authenticates with **Spotify Web API** via **Client Credentials Flow**
2. Fetches:
   - New Releases (albums)
   - Featured Playlists
   - Categories
3. **Stores** the data in your PostgreSQL database
4. **Exposes your own REST API** (not a Spotify proxy)
5. Provides an endpoint to **sync/refresh** Spotify data
6. Uses **clean architecture principles**

---

## 🧩 Functional Requirements

### 1. Spotify Authentication (Client Credentials Flow)

Implement:

POST https://accounts.spotify.com/api/token grant_type=client_credentials

Use the token to call:

- `/v1/browse/new-releases`
- `/v1/browse/featured-playlists`
- `/v1/browse/categories`

You must **cache the token** until it expires (`expires_in`).

---

### 2. Data to Persist

Suggested schema (you may improve it):

#### `artists`
- `id` (UUID PK)
- `spotify_id`
- `name`

#### `albums`
- `id` (UUID PK)
- `spotify_id`
- `name`
- `release_date`
- `image_url`

#### `album_artists` (many-to-many)
- `album_id`
- `artist_id`

#### `playlists`
- `id` (UUID PK)
- `spotify_id`
- `name`
- `description`
- `image_url`

#### `categories`
- `id` (UUID PK)
- `spotify_id`
- `name`
- `icon_url`

---

## 🔗 REST API Endpoints

Data must come from **your database**, not directly from Spotify.

### Public Endpoints

| Method | Path | Description |
|--------|------|-------------|
| `GET` | `/api/albums/new-releases` | Paginated list of new releases |
| `GET` | `/api/playlists/featured` | Paginated list of featured playlists |
| `GET` | `/api/categories` | All categories (or paginated) |
| `GET` | `/api/albums/{id}` | Album detail including artists |

### Admin Endpoint

| Method | Path | Description |
|--------|------|-------------|
| `POST` | `/api/admin/sync` | Fetch Spotify data → store in DB |

---

## ✨ Evaluation Criteria

We will look for:

- Clean architecture (controllers → services → repositories)
- Good Kotlin usage (data classes, suspending functions, coroutines)
- Solid domain modeling
- Spotify token handling
- Pagination, filtering, proper status codes
- DTOs vs Entities separation
- Error handling (API errors, network errors)
- Readable, maintainable code
- Database schema quality

Bonus:

- Swagger / documentation
- Docker support

---

## 🔐 Configuration

Use environment variables:

SPOTIFY_CLIENT_ID=xxxx
SPOTIFY_CLIENT_SECRET=xxxx
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/devsoulify
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres


Include `.env.example` if applicable.

---

🧪 Example API Response
GET /api/albums/new-releases

{
  "items": [
    {
      "id": "8c1bdb4a-5d72-4f34-a328-e7e1e90a58f0",
      "name": "Album Name",
      "releaseDate": "2024-01-01",
      "imageUrl": "https://...",
      "artists": [
        { "id": "0f4a...", "name": "Artist Name" }
      ]
    }
  ],
  "limit": 20,
  "offset": 0,
  "total": 120
}


## 📦 Deliverables

You must deliver:

Full Kotlin + Spring Boot project
Updated README with:
- Your design decisions
- Setup steps
- SQL schema or migration scripts
- (Optional) Swagger / Postman collection

## 📝 Notes

No user authentication required
Treat this like production-quality code
Think about maintainability, readability and scalability
Your API must not act as a direct proxy to Spotify

Good luck — we’re excited to see what you build! 🚀
