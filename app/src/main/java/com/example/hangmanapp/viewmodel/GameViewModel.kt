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

    fun setDifficulty(difficulty: String) {
        _difficulty.value = difficulty
        loadRandomWord()
    }

    private fun loadRandomWord(){
        val difficultyEnum = when (_difficulty.value){
            "Fácil" -> Difficulty.EASY
            "Medio" -> Difficulty.MEDIUM
            "Difícil" -> Difficulty.HARD
            else -> return
        }

        val word = wordRepository.getWords(difficultyEnum).random()
        _currentWord.value= word.toCharArray().toList()
    }
}
