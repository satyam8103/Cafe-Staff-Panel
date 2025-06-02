# ☕ Cafe Staff Panel - Backend System

A full-featured backend system for café management, built using **Spring Boot**, **MySQL**, and **Spring Security (JWT)**. This system supports multi-role user management, inventory, orders, products, tables, and real-time café operations.

---

## 📌 Key Features

- 🔐 **JWT-based Authentication & Authorization**
- 👨‍🍳 Role-based access: Admin, Waiter, Staff
- 📋 Table Management: Reservation, Availability
- 🛍️ Product & Category Management
- 🧾 Order Processing and Item Tracking
- 📦 Inventory Control
- 🧑‍💼 User Approval Workflow (Admin-controlled)
- 📊 Real-time logging and exception handling
- 🌐 RESTful API endpoints with Swagger documentation

---

## ⚙️ Technologies Used

| Layer | Tools |
|------|-------|
| Backend | Java 21, Spring Boot 3.5 |
| Security | Spring Security + JWT |
| DB | MySQL |
| ORM | Hibernate, JPA |
| Docs | SpringDoc OpenAPI (Swagger) |
| Mail | Gmail SMTP for notifications |
| Build | Maven |
| Misc | Lombok, ModelMapper, Logging |

---

## 🧱 Project Structure

src/
├── main/
│ ├── java/com/cafe/
│ │ ├── controller/ # REST controllers
│ │ ├── entity/ # Entity classes
│ │ ├── repository/ # Spring Data JPA Repos
│ │ ├── service/ # Interfaces + Implementations
│ │ ├── security/ # JWT + Custom Auth
│ │ ├── configuration/ # App configs (ModelMapper, Security)
│ │ ├── Dto/ # DTOs for APIs
│ │ └── exception/ # Global + custom exception handlers
│ └── resources/
│ ├── application.properties
│ ├── application-dev.properties
│ ├── application-prod.properties
└── test/java/com/cafe/


---

## 🗃️ Database Schema

Database name: `cafeData`

- `users`, `roles`, `cafes`
- `tables` (linked to cafes)
- `categories`, `products` (linked to cafes)
- `orders`, `order_items` (linked to users & products)
- `inventory` (per café with product link)
- `ENUMS`: `PaymentType`, `OrderStatus`, `TableStatus`

👉 Tables are pre-created using the provided `database.sql`.

---

## 🚀 Getting Started

### 📦 Prerequisites

- Java 21+
- MySQL 8.x+
- Maven 3.x

### 🔧 Setup Steps

```bash
# 1. Clone the repository
git clone https://github.com/satyam8103/Cafe-Staff-Panel.git

# 2. Navigate into the folder
cd Cafe-Staff-Panel

# 3. Import database
# Run database.sql in MySQL Workbench or CLI

# 4. Configure application.properties (already done)

# 5. Build and run the application
./mvnw spring-boot:run



