# 🔐 Spring Boot JWT Authentication & Product Management System

This project is a **Spring Boot REST API** that demonstrates **secure authentication using JWT (JSON Web Token)** and **product management operations**.
It follows **stateless authentication**, **clean architecture**, and **industry-level security flow**.

---

## 📌 Project Features

### 🔑 Authentication & Authorization

* User **Signup** (Register)
* User **Signin** (Login)
* JWT Token generation & validation
* Stateless authentication (no sessions)
* Password encryption using **BCrypt**

### 📦 Product Management

* Add product (secured)
* Get all products (public)
* Search product (public)
* Update product (secured)
* Buy product (secured)

### 🛡 Security

* Spring Security
* Custom JWT filter
* Role-based access
* Global exception handling
* Proper logging

---

## 🏗 Project Architecture

```
Controller
   ↓
Service
   ↓
Repository
   ↓
Database (PostgreSQL / MySQL)

Security Layer
   ↓
JwtAuthenticationFilter
   ↓
SecurityContext
```

---

## 📂 Project Structure

```
src/main/java/com/msf/securityjwttask
│
├── config
│   ├── SecurityConfiguration.java
│   ├── ApplicationConfiguration.java
│   └── JwtAuthenticationFilter.java
│
├── controller
│   ├── AuthController.java
│   └── ProductController.java
│
├── entity
│   ├── User.java
│   └── Product.java
│
├── repository
│   ├── UserRepository.java
│   └── ProductRepository.java
│
├── services
│   ├── AuthenticationService.java
│   ├── JwtService.java
│   └── ProductService.java
│
├── dto
│   ├── SignupRequest.java
│   ├── SigninRequest.java
│   └── ProductRequest.java
│
├── exception
│   └── GlobalExceptionHandler.java
│
└── SecurityJwtTaskApplication.java
```

---

## 🔐 Authentication APIs

### 1️⃣ Signup API

**Endpoint**

```
POST /auth/signup
```

**Request JSON**

```json
{
  "name": "Aniket",
  "email": "aniket@gmail.com",
  "password": "9898"
}
```

**Description**

* Registers a new user
* Password is encrypted using BCrypt
* No token is generated here

---

### 2️⃣ Signin API

**Endpoint**

```
POST /auth/signin
```

**Request JSON**

```json
{
  "email": "aniket@gmail.com",
  "password": "9898"
}
```

**Response**

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

**Description**

* Verifies user credentials
* Generates JWT token on successful login

---

## 📦 Product APIs

### ➕ Add Product (Secured)

```
POST /product/add
```

**Headers**

```
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json
```

**Request JSON**

```json
{
  "name": "Laptop",
  "price": 55000,
  "quantity": 10
}
```

---

### 📃 Get All Products (Public)

```
GET /product/all
```

---

### 🔍 Search Product (Public)

```
GET /product/search?name=Laptop
```

---

### ✏ Update Product (Secured)

```
PUT /product/update/{id}
```

**Request JSON**

```json
{
  "name": "Gaming Laptop",
  "price": 75000,
  "quantity": 5
}
```

---

### 🛒 Buy Product (Secured)

```
POST /product/buy/{id}
```

**Request JSON**

```json
{
  "quantity": 2
}
```

---

## 🔐 JWT Security Flow

1. User signs in with email & password
2. Spring Security validates credentials
3. JWT token is generated
4. Token is sent in `Authorization` header
5. `JwtAuthenticationFilter` validates token
6. User is authenticated via `SecurityContext`

---

## ⚠️ Error Handling

| Error Code | Meaning                  |
| ---------- | ------------------------ |
| 401        | Invalid or missing token |
| 403        | Access denied            |
| 400        | Bad request              |
| 404        | Resource not found       |

Handled using **GlobalExceptionHandler**

---

## 🧪 Testing

* All APIs tested using **Postman**
* JWT token used for secured endpoints
* Proper HTTP status codes returned

---

## 🛠 Technologies Used

* Java 17+
* Spring Boot
* Spring Security
* JWT
* Hibernate / JPA
* PostgreSQL / MySQL
* Maven
* Postman

---

## 🧠 Learning Outcomes

* Implemented JWT-based authentication
* Understood Spring Security internals
* Built stateless REST APIs
* Applied real-world security concepts
* Followed clean architecture

---

                ┌────────────────────┐
                │       CLIENT       │
                │ (Postman / UI)     │
                └─────────┬──────────┘
                          │
                          ▼
                ┌────────────────────┐
                │  SecurityFilterChain│
                │  (Spring Security) │
                │                    │
                │  requestMatchers() │
                │  authenticated()   │
                │  permitAll()       │
                └─────────┬──────────┘
                          │
        ┌─────────────────┴─────────────────┐
        │                                   │
        ▼                                   ▼
┌──────────────────┐             ┌──────────────────┐
│ JwtAuth Filter   │             │ Request Rejected │
│ (Token Validate) │             │ (401/403)        │
└─────────┬────────┘             └──────────────────┘
          │
          ▼
┌────────────────────────┐
│ Controller Method      │
│ (@MFS maybe present)   │
└─────────┬──────────────┘
          │
          ▼
┌────────────────────────┐
│  @MFS Aspect (AOP)     │
│  (Extra check only)    │
└────────────────────────┘





