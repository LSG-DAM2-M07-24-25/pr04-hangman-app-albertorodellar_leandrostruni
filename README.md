<div align="center">

# 🎲 Hangman Game 🎲

¡Hola! 👋  
Bienvenido a nuestro proyecto **Hangman Game**. Un clásico reinventado con una experiencia interactiva que combina diversión y desafío.  
Adivina las palabras antes de quedarte sin intentos y disfruta del diseño retro inspirado en los juegos pixel art. ¡Pon a prueba tu vocabulario y habilidades estratégicas!

</br>

---

</br>

### **Descripción funcional del juego**

</br>

### ***🏅 Objetivo:***  
Adivina la palabra oculta seleccionando letras. Cada error te acerca un paso más al final... ¡Intenta completar la palabra antes de que el hangman termine su dibujo!

</br>

### **🚀 Instrucciones Básicas:**

<p align="center">
    
• ***[Inicio del juego]*** Elige el nivel de dificultad (Fácil, Medio, Difícil).  
• ***[Selección de letras]*** Pulsa las letras del teclado en pantalla para adivinar la palabra oculta.  
• ***[Progreso]*** La palabra se muestra en formato "_" hasta que adivines correctamente cada letra.  
• ***[Intentos restantes]*** Tienes 6 intentos para adivinar la palabra. Cada error muestra un nuevo elemento del hangman.  
• ***[Final del juego]*** Ganas si completas la palabra antes de quedarte sin intentos; pierdes si el dibujo del hangman se completa.  
</p>

</br>

### **📝 Reglas del juego:**

<p align="center">
  
• ***Niveles de dificultad:***  
    **Fácil:** Palabras de 4-5 letras.  
    **Medio:** Palabras de longitud moderada.  
    **Difícil:** Palabras largas y complejas.  

• ***Límite de intentos:*** Solo puedes equivocarte 6 veces por partida.  

• ***Palabras dinámicas:*** Cada partida selecciona una palabra aleatoria de la lista según la dificultad elegida.  
</p>

---

</br>

### **✨ Características Principales:**

<p align="center">
  
• ***Diseño retro:*** Interfaz visual inspirada en pixel art.  
• ***Selección de dificultad:*** Escoge entre tres niveles de dificultad.  
• ***Progreso dinámico:*** La palabra oculta se actualiza al adivinar correctamente.  
• ***Teclado interactivo:*** Selección de letras con colores que indican estado (correcto/incorrecto).  
• ***Estadísticas al final del juego:*** Indica si ganaste o perdiste y cuántos intentos te sobraron.  
</p>
<br>

#### 🎮 Muestra del Juego 🎮

<div align="center">
  <img src="resources/Readme-Images/hangman_demo.gif" alt="Demo del juego Hangman" width="800">
</div>

</br>

---

</br>

### **⚙️ Descripción Técnica:**

<p align="center">
  
El proyecto está estructurado siguiendo la arquitectura MVVM (Model-View-ViewModel), facilitando la separación de lógica, datos y UI.  
</p>
<br>

<p align="center">
  
**🧩 Módulo Model:**  
• ***[WordRepository]*** Proporciona las palabras clasificadas por nivel de dificultad.  
• ***[Difficulty]*** Enumera los niveles de dificultad (Fácil, Medio, Difícil).  
</p>

<p align="center">
  
**🧠 Módulo ViewModel:**  
• ***[GameViewModel]*** Gestiona la lógica del juego, incluidos los intentos, las letras seleccionadas y la actualización del estado de la partida.  
</p>

<p align="center">
  
**🎨 Módulo View:**  
• ***[GameScreen]*** Pantalla principal donde se desarrolla el juego.  
• ***[MenuScreen]*** Pantalla para elegir nivel de dificultad.  
• ***[ResultScreen]*** Pantalla final que muestra el resultado de la partida.  
</p>

---

</br>

### 🛠️ Tecnologías y Herramientas 🛠️

</br>

<img alt="github" src="https://user-images.githubusercontent.com/25181517/192108374-8da61ba1-99ec-41d7-80b8-fb2f7c0a4948.png" width="80"/>  
<img alt="androidstudio" src="https://user-images.githubusercontent.com/25181517/192108895-20dc3343-43e3-4a54-a90e-13a4abbc57b9.png" width="80"/>
<img alt="android" src="https://user-images.githubusercontent.com/25181517/117269608-b7dcfb80-ae58-11eb-8e66-6cc8753553f0.png" width="80"/>
<img alt="kotlin" src="https://user-images.githubusercontent.com/25181517/185062810-7ee0c3d2-17f2-4a98-9d8a-a9576947692b.png" width="80"/>

<br>

---

</br>

### Integrantes del equipo: 
<p>
  Alberto Rodellar,
  Leandro Struni</br>
</p>

<table align="center">
  <tr>
    <td>
      <table align="center">
        <tr>
          <td align="center">
            <a href="https://github.com/LeanEmanuel">
              <img src="https://github.com/LeanEmanuel/Images/blob/main/Leandro.png" alt="Mini Leandro" width="80">
            </a>
          </td>
        </tr>
        <tr>
          <td>
            <a href="https://github.com/LeanEmanuel">
              <img src="https://img.shields.io/badge/LeanEmanuel-Git?style=flat&logo=github&logoColor=white&labelColor=black&color=50e520&label=GitHub" alt="Badge">
            </a>
          </td>
        </tr>
      </table>
    </td>
    <td>
      <table align="center">
        <tr>
          <td align="center">
            <a href="https://github.com/AlbertoRodellar">
              <img src="https://media.tenor.com/33I1sOQI3V4AAAAi/heimerdinger.gif" alt="Gif" width="80">
            </a>
          </td>
        </tr>
        <tr>
          <td>
            <a href="https://github.com/AlbertoRodellar">
              <img src="https://img.shields.io/badge/AlbertoRodellar-Git?style=flat&logo=github&logoColor=white&labelColor=black&color=50e520&label=GitHub" alt="Badge">
            </a>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
