
/*
import androidx.lifecycle.ViewModel
import com.example.hangmanapp.model.Letter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AlphabetViewModel : ViewModel() {

    private val _alphabet = MutableStateFlow<List<Letter>>(emptyList())
    val alphabet: StateFlow<List<Letter>> = _alphabet

    init {
        loadAlphabet()
    }

    private fun loadAlphabet() {
        _alphabet.value = ('A'..'Z').map { Letter(it) }
    }

    fun onLetterClicked(letter: Letter) {
        _alphabet.value = _alphabet.value.map {
            if (it.char == letter.char) it.copy(pulsado = !it.pulsado) else it
        }
    }
}
*/