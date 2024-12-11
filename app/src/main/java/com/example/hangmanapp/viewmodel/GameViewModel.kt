import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val _difficulty = MutableLiveData<String>()
    val difficulty: LiveData<String> get() = _difficulty

    fun setDifficulty(difficulty: String) {
        _difficulty.value = difficulty
    }
}
