# ☕ Cafe Staff Panel - Backend System

A full-featured backend system for café management, built using **Spring Boot**, **MySQL**, and **Spring Security (JWT)**. This system supports multi-role user management, inventory, orders, products, tables, and real-time café operations.

---

## 📌 Key Features

- 🔐 **JWT-based Authentication & Authorization**
- 👨‍🍳 Role-based access: Admin, Waiter, Staff
- 📋 Table Management: Reservation, Availability
- 🛍️ Product & Category Management
- 🧾 Order Processing and Item Tracking
- 📦 Inventory tracking per café
- 🧑‍💼 User Approval Workflow (Admin-controlled)
- 
- 📊 Real-time logging and exception handling


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

![image](https://github.com/user-attachments/assets/996082b1-f6ac-4da1-95ca-928ee3248bcf)


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


🔐 JWT Authentication
Login via /api/auth/login

Token will be returned in response

Use Authorization: Bearer <token> in headers for secure endpoints

📧 Email Configuration
Already setup with Gmail SMTP. Change in application.properties:

spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password


🛠️ Build & Run with Maven
# Build
mvn clean install

# Run
mvn spring-boot:run

License
This project is open-source and available under the MIT License.

.

🙋‍♂️ Author
Satyam Goyal
Backend Developer | Java & Spring Boot Enthusiast
GitHub Profile


Profile

🎯 Contribution
Want to contribute? Fork this repo, create a feature branch, and raise a pull request. Let's build better software together!


---

### ✅ Next Step for You

1. Copy this content.
2. Paste it in your project root as a file named `README.md`.
3. Stage and push:

```bash
git add README.md
git commit -m "Added auto-generated README"
git push




