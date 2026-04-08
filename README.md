# 💳 E-Wallet System

> A sophisticated Java-based command-line E-Wallet System for seamless digital transactions

---

## 📌 Overview

Experience a modern digital wallet management system built entirely in **Java**. This feature-rich application simulates real-world banking operations with secure account management, instant fund transfers, and comprehensive transaction tracking.

### ✨ Key Highlights
- 🔐 **Secure Authentication** with advanced error handling
- 💰 **Smart Fund Management** preventing overdrafts and invalid operations
- 📊 **Complete Transaction History** for all account activities
- 👑 **Admin Dashboard** for system oversight
- ✅ **Robust Input Validation** ensuring data integrity

---

## 🎯 Core Features

| Feature | Description |
|---------|-------------|
| 📝 **Sign-Up** | Create accounts with validated username, password, age, and phone number |
| 🔑 **Secure Login** | Limited invalid attempt protection with session management |
| 💵 **Deposit & Withdraw** | Add or remove funds with comprehensive validation |
| 🔄 **Money Transfer** | Send funds to other users safely and instantly |
| 👤 **Account Details** | View all account information and current balance |
| 🔐 **Change Password** | Securely update account credentials |
| 🚪 **Logout** | Safe session termination |
| 🛡️ **Admin Panel** | Manage accounts, view statistics, and system control |
| 📋 **Transaction History** | Detailed logs of all deposits, withdrawals, and transfers |

---

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- IntelliJ IDEA or any Java IDE (or command line)

### Installation & Execution

```bash
# 1. Clone the project
git clone <repository-url>
cd E-wallet_system

# 2. Open in your IDE
# Open in IntelliJ IDEA or your preferred Java IDE

# 3. Run the application
# Execute Main.java or run from terminal:
javac src/Main.java
java -cp src Main
```

Then simply follow the interactive command-line menu! 🎮

---

## 🏗️ Project Architecture

```
E-wallet_system/
├── src/
│   ├── Main.java                          # Application entry point
│   ├── model/
│   │   ├── Account.java                   # Account data model
│   │   └── EWalletSystem.java             # Core system logic
│   ├── service/
│   │   ├── AccountService.java            # Interface
│   │   ├── AdminService.java              # Interface
│   │   ├── AppService.java                # Interface
│   │   ├── ValidationService.java         # Interface
│   │   └── imp/                           # Implementations
│   │       ├── AccountServiceImp.java
│   │       ├── AdminServiceImp.java
│   │       ├── AppServiceImp.java
│   │       └── ValidationServiceImp.java
│   └── exception/
│       ├── EWalletException.java          # Base exception
│       └── MaxAttemptsExceededException.java
└── README.md
```

---

## 💡 How It Works

1. **User Authentication** 🔐
   - Create a new account with validation
   - Login with username/password
   - Limited failed attempt protection

2. **Fund Operations** 💰
   - Deposit money into your account
   - Withdraw funds safely
   - Transfer to other users instantly

3. **Account Management** 👤
   - View detailed account information
   - Update your password
   - Check complete transaction history

4. **Admin Controls** 👑
   - View all system accounts
   - Manage user accounts
   - System oversight and control

---

## ⚙️ Technical Stack

- **Language**: Java ☕
- **Architecture**: Service-Oriented Architecture (SOA)
- **Pattern**: Dependency Injection
- **Exception Handling**: Custom Exception Classes
- **Interface**: Command-Line Interface (CLI)

---

## 🔒 Security Features

✅ Password validation and encryption ready  
✅ Invalid login attempt limiting  
✅ Prevention of self-transfers  
✅ Overdraft protection  
✅ Transaction validation  
✅ Secure logout mechanism  

---

## 📝 Usage Example

```
Welcome to E-Wallet System!
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
1. Sign Up
2. Login
3. Exit

Enter your choice: 2
Enter username: john_doe
Enter password: ****
✓ Login successful!

Main Menu:
1. Deposit
2. Withdraw
3. Transfer
4. View Account Details
5. Logout
```

---

## 🎓 Learning Outcomes

This project demonstrates:
- Object-Oriented Programming (OOP) principles
- Interface and abstraction design patterns
- Exception handling and custom exceptions
- Input validation and error management
- CLI application development
- Service layer architecture

---

## 🤝 Contributing

Feel free to fork, modify, and enhance this project!

---

## 📄 License

This project is open source and available under the MIT License.

---

**Made with ❤️ for Java enthusiasts | Last Updated: April 2026**
