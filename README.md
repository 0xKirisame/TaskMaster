# TaskMaster 🗂️

A simple Java-based Task Manager that supports creating, storing, retrieving, and managing tasks via the console. Built using core OOP principles and file I/O.

## 📌 Features

- Add, remove, and list tasks
- Mark tasks as completed
- View pending or completed tasks
- Save tasks to a `.txt` file
- Load tasks from a `.txt` file
- Designed using **LinkedList** for efficient operations

## 🧱 Tech Stack

- Java (Core)
- File I/O (BufferedReader/Writer)
- LinkedList data structure
- LocalDate for due dates

## 📂 Project Structure

- `Task.java` – Represents a single task
- `TaskManager.java` – Manages all task logic and file operations
- `Main.java` – Console UI and interaction

## 🧠 Concepts Used

- Object-Oriented Programming (encapsulation, composition)
- File handling (reading and writing `.txt`)
- Custom data parsing
- Basic error handling

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
