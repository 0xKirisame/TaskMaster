# TaskMaster 🗂️

A simple Java-based Task Manager that supports creating, storing, retrieving, and managing tasks via the console. Built using core OOP principles and file I/O.

## 📌 Features

- Add, remove, and list tasks
- Mark tasks as completed
- View pending or completed tasks
- Save and load sessions
- Designed using **LinkedList** for efficient operations

## 🧱 Tech Stack

- Java (Core)
- File I/O (ObjectOutputStream/ObjectInputStream)
- LinkedList data structure
- LocalDate for due dates

## 📂 Project Structure

- `Task.java` – Represents a single task
- `TaskManager.java` – Manages all task logic and file operations
- `TaskMasterGUI.java` – Console UI and interaction
- `AES.java` – class used for AES-256 encryption (replaces XOR.java)

## 🧠 Concepts Used

- Object-Oriented Programming (encapsulation, composition)
- File handling
- Basic error handling
- Linked List

## 🔒 Security Note  
This project now uses **AES-256-CBC encryption** for task data protection.  
- Uses SHA-256 for key derivation from passwords
- Implements proper AES encryption with CBC mode and PKCS5 padding
- Files are encrypted/decrypted transparently during save/load operations
- **Note**: For production use, consider implementing random IVs and PBKDF2 for key derivation
  
## 🏁 Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/Engineer-Salman/TaskMaster.git

## 🚀 Future Plans  
- [x] Replace XOR with AES-256 + proper key derivation.  
- [ ] Add password salting (PBKDF2) for enhanced security.
- [ ] Implement random IVs for each encryption operation.
- [ ] Adding GUI
