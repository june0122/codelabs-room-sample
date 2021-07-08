package com.june0122.roomwordsample

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository): ViewModel() {

    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }
}

class WordViewModelFactory(private val repository: WordRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        // instanceof는 특정 'Object'가 어떤 클래스/인터페이스를 상속/구현했는지를 체크하며
        // Class.isAssignableFrom()은 특정 'Class'가 어떤 클래스/인터페이스를 상속/구현했는지 체크한다.
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalStateException("Unknown ViewModel class")
    }
}
