# Simple E-commerce Backend

This is a robust and scalable RESTful API built for a simple e-commerce system. It handles product management and order processing with built-in validation and centralized error handling.

## 🚀 Technologies Used

- **Java 17**: The latest LTS version for high performance and modern language features.
- **Spring Boot 3**: The foundation of the application, providing auto-configuration and rapid development.
- **Spring Data JPA**: For seamless database interactions.
- **H2 Database**: An in-memory database for fast development and testing without configuration.
- **Jakarta Validation**: Used to ensure data integrity at the API level (e.g., non-empty names, positive prices).
- **Global Exception Handling**: A centralized `@RestControllerAdvice` to manage all application errors and return consistent JSON responses.
- **Lombok**: To reduce boilerplate code like getters, setters, and constructors.

## 🛠️ Getting Started

### Prerequisites
- JDK 17 or higher
- Maven (or use the included wrapper `./mvnw`)

### Running the Application
1. Clone the repository.
2. Navigate to the project root.
3. Run the application:
   ```powershell
   ./mvnw spring-boot:run
   ```
The server will start on `http://localhost:8080`.

### 📖 API Documentation (Swagger UI)
Once the application is running, you can access the interactive API documentation at:
`http://localhost:8080/swagger-ui/index.html`

## 📂 API Endpoints

### Products
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/products` | Retrieve all products |
| `GET` | `/api/products/{id}` | Retrieve a specific product by ID |
| `POST` | `/api/products` | Create a new product |
| `PUT` | `/api/products/{id}` | Update an existing product |
| `DELETE` | `/api/products/{id}` | Delete a product |

### Orders
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/orders` | Place a new order (automatically calculates total and updates stock) |

---

## 🧪 Testing with PowerShell (`Invoke-RestMethod`)

Since this project is optimized for Windows developers, you can use `Invoke-RestMethod` (IRM) in PowerShell to test the API.

### 1. Create a Product
```powershell
$product = @{
    name = "Gaming Laptop"
    description = "High performance laptop"
    price = 1299.99
    stockQuantity = 50
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8080/api/products" -Method Post -Body $product -ContentType "application/json"
```

### 2. Get All Products
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/products" -Method Get
```

### 3. Place an Order
```powershell
$order = @{
    customerName = "John Doe"
    customerEmail = "john.doe@example.com"
    productId = 1
    quantity = 2
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8080/api/orders" -Method Post -Body $order -ContentType "application/json"
```

### 4. Test Validation (Error Handling)
Try creating an invalid product to see the **Global Exception Handler** in action:
```powershell
$invalidProduct = @{
    name = ""
    price = -10
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8080/api/products" -Method Post -Body $invalidProduct -ContentType "application/json"
```

## 🛡️ Error Handling
The application uses a **Global Exception Handler** to return meaningful error messages. For example, if you try to order more stock than available, you will receive:
```json
{
    "timestamp": "2025-12-26T23:20:00.000",
    "message": "Stock not sufficient for product: Gaming Laptop"
}
```
Validation errors return a list of specific field failures with a `400 Bad Request` status.
