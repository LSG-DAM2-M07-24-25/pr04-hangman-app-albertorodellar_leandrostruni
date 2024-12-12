package com.example.hangmanapp.model

/**
 * Clase Word.
 *
 * Representa un repositorio de palabras categorizadas por niveles de dificultad.
 */
class WordRepository {
    private val words = mapOf(
        Difficulty.EASY to listOf(
            "java", "html", "css", "sql", "json",
            "ajax", "perl", "bash", "node", "ruby",
            "go", "vue", "sass", "yaml", "api",
            "dart", "nx", "pug", "zsh", "flask"
        ),
        Difficulty.MEDIUM to listOf(
            "kotlin", "laravel", "django", "angular", "reactjs",
            "express", "flutter", "android", "backend", "frontend",
            "webpack", "graphql", "docker", "mongodb", "sqlite",
            "swiftui", "electron", "firebase", "netlify", "tailwind"
        ),
        Difficulty.HARD to listOf(
            "javascript", "typescript", "frameworks", "microservice", "architecture",
            "intelligence", "debugging", "deserialization", "observability", "refactoring",
            "optimization", "virtualization", "kubernetes", "orchestration", "machinelearning",
            "scalability", "programming", "fullstack", "devops", "containerization"
        )
    )


    /**
     * Obtiene la lista de palabras correspondientes al nivel de dificultad especificado.
     *
     * @param difficulty El nivel de dificultad para el cual se desean obtener las palabras.
     *                    Puede ser uno de los siguientes valores: `EASY`, `MEDIUM`, `HARD`.
     * @return Una lista de palabras correspondientes al nivel de dificultad proporcionado.
     *         Si el nivel no tiene palabras asignadas, devuelve una lista vacía.
     */
    fun getWords(difficulty: Difficulty): List<String> {
        return words[difficulty] ?: emptyList()
    }

}