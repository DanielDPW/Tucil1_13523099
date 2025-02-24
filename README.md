# IF2211 - Strategi Algoritma

<h3 align="center">IQ Puzzler Pro Solver</h3>
<p align="center">
Just do it!
<br/>
<a href="https://github.com/DanielDPW/Tucil1_13523099/tree/main/doc"><strong>Laporan »</strong></a>
<br />
</p>

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#features">Features</a></li>
        <li><a href="#built-with">Built With</a></li>
        <li><a href="#project-structure">Project Structure</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#how-to-use">How to Use</a></li>
      </ul>
    </li>
    <li><a href="#project-status">Project Status</a></li>
    <li><a href="#author">Author</a></li>
  </ol>
</details>

## About The Project

IQ Puzzler Pro Solver is a CLI/GUI-based program made using Java, which employs a brute-force approach in solving the game, *IQ Puzzler Pro*.

### Features

* Solves IQ Puzzler Pro using a brute-force approach
* Supports both CLI and GUI modes
* Reads input from files in a specified format
* Outputs solutions in both text (.txt) and image (.png) formats
* Provides execution time and iteration count for performance analysis
* Supports customizable save locations for solutions

### Built With

* **Java 23** – The core programming language used for solving IQ Puzzler Pro
* **Swing (javax.swing)** – GUI framework for the graphical interface
* **Java AWT (java.awt)** – Used for rendering UI components
* **JDK 23 (Java Development Kit)** – Required for compiling and running the program

### Project Structure
```ssh
.
├── README.md
├── bin
│   ├── Main.class
│   ├── MainGUI$FileDropHandler.class
│   ├── MainGUI.class
│   ├── board
│   │   ├── Board$1.class
│   │   └── Board.class
│   ├── image
│   │   ├── ImageGenerator$1.class
│   │   └── ImageGenerator.class
│   ├── parser
│   │   └── Parser.class
│   ├── piece
│   │   └── Piece.class
│   └── solver
│       └── Solver.class
├── doc
│   └── Tucil1_K2_13523099_Daniel Pedrosa Wu.pdf
├── input
│   ├── test1.txt
│   ├── test2.txt
│   ├── test3.txt
│   ├── test4.txt
│   ├── test5.txt
│   ├── test6.txt
│   ├── test7.txt
│   ├── test8.txt
│   ├── test9.txt
│   ├── test10.txt
│   ├── test11.txt
│   ├── test12.txt
│   ├── test13.txt
├── src
│   ├── Main.java
│   ├── MainGUI.java
│   ├── board
│   │   └── Board.java
│   ├── image
│   │   └── ImageGenerator.java
│   ├── parser
│   │   └── Parser.java
│   ├── piece
│   │   └── Piece.java
│   └── solver
│       └── Solver.java
└── test
    ├── sol1.png
    ├── sol1.txt
    ├── sol2.png
    ├── sol2.txt
    ├── sol3.png
    ├── sol3.txt
    ├── sol4.png
    ├── sol4.txt
    ├── sol5.png
    ├── sol5.txt
    ├── sol6.png
    ├── sol6.txt
    ├── sol7.png
    ├── sol7.txt
    ├── sol8.png
    ├── sol8.txt
```

## Getting Started

### Prerequisites
1. **Install Java Development Kit (JDK 23 or later)**
    ```sh
    java -version
    ```
    If Java is not installed, download and install it from:
    - [Oracle JDK 23](https://www.oracle.com/java/technologies/javase/jdk23-archive-downloads.html)
    - Or install OpenJDK 23:
      ```sh
      sudo apt install openjdk-23-jdk  # For Linux
      brew install openjdk@23          # For macOS
      ```

2. **Ensure you have Git installed**
    ```sh
    git --version
    ```
    If Git is not installed, install it using:
    ```sh
    sudo apt install git  # For Linux
    brew install git      # For macOS
    ```

### How to Use
1. **Clone the repository**
    ```sh
    git clone https://github.com/DanielDPW/Tucil1_13523099.git
    cd Tucil1_13523099
    ```

2. **Compile the Java source files**
    ```sh
    javac -d bin -sourcepath src src\Main.java src\board\Board.java src\parser\Parser.java src\piece\Piece.java src\solver\Solver.java src\image\ImageGenerator.java src\MainGUI.java       
    ```

3. **Run the program**
    - **For CLI mode:**
      ```sh
      cd bin
      java Main <filename in the input folder>
      ```
    - **For GUI mode:**
      ```sh
      cd bin
      java MainGUI
      ```

## Project Status

| No | Poin                                                 | Ya | Tidak |
|----|------------------------------------------------------|:--:|:-----:|
| 1  | Program berhasil dikompilasi tanpa kesalahan        | ✓  |       |
| 2  | Program berhasil dijalankan                         | ✓  |       |
| 3  | Solusi yang diberikan program benar dan mematuhi aturan permainan | ✓  |       |
| 4  | Program dapat membaca masukan berkas .txt serta menyimpan solusi dalam berkas .txt | ✓  |       |
| 5  | Program memiliki Graphical User Interface (GUI)     | ✓  |       |
| 6  | Program dapat menyimpan solusi dalam bentuk file gambar | ✓  |       |
| 7  | Program dapat menyelesaikan kasus konfigurasi custom | ✓  |       |
| 8  | Program dapat menyelesaikan kasus konfigurasi Piramida (3D) |    | ✓   |
| 9  | Program dibuat oleh saya sendiri                    | ✓  |       |

## Author
| NIM | Name |
| :---: | :---: |
| 13523099 | Daniel Pedrosa Wu |

