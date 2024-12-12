package com.example.hangmanapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hangmanapp.R
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

    private val _remainingAttempts = MutableLiveData(6) //Contador de intentos
    val remainingAttempts: LiveData<Int> = _remainingAttempts

    private val _gameResult = MutableLiveData<String?>() //Resultado del juego (win/lose)
    val gameResult: LiveData<String?> = _gameResult

    private val _currentImage = MutableLiveData(R.drawable.hangman_image_0)
    val currentImage: LiveData<Int> = _currentImage


    /**
     * Inicializa el estado del ViewModel.
     *
     * - Llama a `resetLetterStates()` para asegurar que el estado de las letras
     *   esté correctamente inicializado al iniciar el ViewModel.
     */
    init {
        resetLetterStates()
    }

    
    //lista con las imagenes
    private val images = listOf(
        R.drawable.hangman_image_0, //Imagen inicial
        R.drawable.hangman_image_1,
        R.drawable.hangman_image_2,
        R.drawable.hangman_image_3,
        R.drawable.hangman_image_4,
        R.drawable.hangman_image_5,
        R.drawable.hangman_image_6 //Imagen final
    )


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
            "Easy" -> Difficulty.EASY
            "Medium" -> Difficulty.MEDIUM
            "Hard" -> Difficulty.HARD
            else -> return
        }

        val word = wordRepository.getWords(difficultyEnum).random()
        _currentWord.value = word.toCharArray().toList()
        _hiddenWord.value = word.map { '_' }
        _remainingAttempts.value = 6
        _gameResult.value = null
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
                    hidden[index] = char.uppercaseChar()
                }
            }
        }else{
            _remainingAttempts.value = (_remainingAttempts.value ?: 1) -1
            if (_remainingAttempts.value == 0){
                _gameResult.value  = "lose"
            }
        }

        //Actualizar imagen de fondo
        updateImage()

        //Actualiza el estado de la letra seleccionada
        letterStates[letter.uppercaseChar()] = isCorrect
        _letterStates.value = letterStates.toMap()

        _hiddenWord.value = hidden //Actualizar LiveData

        //Verifica si todas las letras están descubiertas
        if (!hidden.contains('_')) {
            _gameResult.value = "win"
        }

        //Log para depuración
        println("Estado de las letras actualizado: $letterStates")
    }


    /**
     * Actualiza la imagen actual del juego según los intentos restantes.
     *
     * La imagen se selecciona de la lista `images` basándose en el número de intentos usados.
     * Si el índice está fuera de rango, se utiliza una imagen predeterminada.
     */
    private fun updateImage() {
        val remaining = _remainingAttempts.value ?: 6
        _currentImage.value = images.getOrElse(6 - remaining) { R.drawable.hangman_image_0 }
    }


    /**
     * Restablece el contador de intentos y la imagen del juego a sus valores iniciales.
     *
     * - Los intentos se reinician a 6.
     * - La imagen se establece en la imagen inicial.
     */
    private fun resetAttempts() {
        _remainingAttempts.value = 6
        _currentImage.value = R.drawable.hangman_image_0
    }


    /**
     * Reinicia el estado del juego para una nueva partida.
     *
     * - Restablece el estado de las letras seleccionadas.
     * - Reinicia los intentos y la imagen inicial.
     * - Si `again` es `true`, carga una nueva palabra aleatoria.
     *
     * @param again Indica si se debe iniciar una nueva partida con una palabra aleatoria.
     */
    fun playAgain(again: Boolean){
        resetLetterStates()
        resetAttempts()
        if (again){
            loadRandomWord()
        }
    }
}
