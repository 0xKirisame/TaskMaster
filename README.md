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
- `XOR.java` – class used for XOR encryption

## 🧠 Concepts Used

- Object-Oriented Programming (encapsulation, composition)
- File handling
- Basic error handling
- Linked List

 ## 🔒 "Security" Note  
This project uses **XOR encryption for educational purposes only**.  
- Why? To explore basic crypto concepts before upgrading to AES.  
- **Not for real-world use**—but hey, it’s a start!  

## 🏁 Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/Engineer-Salman/TaskMaster.git

## 🚀 Future Plans  
- [ ] Replace XOR with AES-256 + proper key derivation.  
- [ ] Add password salting (PBKDF2).
- [ ] Adding GUI
