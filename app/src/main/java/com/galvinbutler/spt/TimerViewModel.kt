package com.galvinbutler.spt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {
    private val _time = MutableStateFlow(0)
    val time = _time.asStateFlow()

    private var job: Job? = null

    private fun isTicking(): Boolean {
        return job?.isActive ?: false
    }

    private fun start() {
        job = viewModelScope.launch {
            while (true) {
                delay(1000)
                _time.value++
            }
        }
    }

    private fun pause() {
        job?.cancel()
    }

    fun toggle() {
        if (isTicking()) {
            pause()
        } else {
            start()
        }
    }

    fun reset() {
        _time.value = 0
        job?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}