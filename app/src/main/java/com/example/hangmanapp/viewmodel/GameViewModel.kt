package com.example.hangmanapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hangmanapp.model.Difficulty
import com.example.hangmanapp.model.WordRepository

class GameViewModel() : ViewModel() {

    private val wordRepository: WordRepository = WordRepository()

    private val _difficulty = MutableLiveData<String>()
    val difficulty: LiveData<String> = _difficulty

    private val _currentWord = MutableLiveData<List<Char>>()
    val currentWord: LiveData<List<Char>> = _currentWord

    private val _hiddenWord = MutableLiveData<List<Char>>()
    val hiddenWord: LiveData<List<Char>> = _hiddenWord

    private val _letterStates = MutableLiveData<Map<Char, Boolean?>>()
    val letterStates: LiveData<Map<Char, Boolean?>> = _letterStates

    init {
        resetLetterStates()
    }


    /**
     * Resetea el estado de las letras seleccionadas.
     */
    private fun resetLetterStates() {
        _letterStates.value = ('A'..'Z').associateWith { null }
    }


    /**
     * Establece el nivel de dificultad del juego y carga una nueva palabra aleatoria acorde a la dificultad seleccionada.
     *
     * @param difficulty Nivel de dificultad en forma de cadena. Puede ser "Fácil", "Medio" o "Difícil".
     */
    fun setDifficulty(difficulty: String) {
        _difficulty.value = difficulty
        loadRandomWord()
    }

    /**
     * Carga una palabra aleatoria del repositorio según el nivel de dificultad actual.
     * Convierte la palabra en una lista de caracteres para su manipulación y crea una versión oculta representada con guiones bajos.
     *
     * Si la dificultad no es válida, no realiza ninguna acción.
     */
    private fun loadRandomWord() {
        val difficultyEnum = when (_difficulty.value) {
            "Fácil" -> Difficulty.EASY
            "Medio" -> Difficulty.MEDIUM
            "Difícil" -> Difficulty.HARD
            else -> return
        }

        val word = wordRepository.getWords(difficultyEnum).random()
        _currentWord.value = word.toCharArray().toList()
        _hiddenWord.value = word.map { '_' }
    }

    /**
     * Procesa la selección de una letra por parte del usuario e intenta revelar las coincidencias en la palabra oculta.
     *
     * @param letter Letra seleccionada por el usuario.
     *
     * Actualiza el estado de la palabra oculta (_hiddenWord) reemplazando los guiones bajos ('_') por la letra correspondiente
     * si está presente en la palabra original (_currentWord).
     */
    fun onLetterSelected(letter: Char) {
        val word = _currentWord.value ?: return
        val hidden = _hiddenWord.value?.toMutableList() ?: return
        val letterStates = _letterStates.value?.toMutableMap() ?: return

        //Verifica si la letra esta en la palabra
        val isCorrect = word.any() { it.equals(letter, ignoreCase = true) }


        //Actualiza la palabra oculta con la letra seleccionada si esta presente
        if (isCorrect) {
            word.forEachIndexed { index, char ->
                if (char.equals(letter, ignoreCase = true)) {
                    hidden[index] = char
                }
            }
        }

        //Actualiza el estado de la letra seleccionada
        letterStates[letter.uppercaseChar()] = isCorrect
        _letterStates.value = letterStates.toMap()

        _hiddenWord.value = hidden //Actualizar LiveData

        // Log para depuración
        println("Estado de las letras actualizado: $letterStates")
    }
}
