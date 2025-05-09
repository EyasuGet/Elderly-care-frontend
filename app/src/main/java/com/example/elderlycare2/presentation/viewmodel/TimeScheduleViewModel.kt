package com.example.elderlycare2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elderlycare2.data.repository.ScheduleRepository
import com.example.elderlycare2.presentation.state.TimeScheduleEvent
import com.example.elderlycare2.presentation.state.TimeScheduleState
import com.example.elderlycare2.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeScheduleViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TimeScheduleState())
    val state: StateFlow<TimeScheduleState> = _state

    init {
        onEvent(TimeScheduleEvent.FetchQueues)
    }

    private fun onEvent(event: TimeScheduleEvent) {
        when (event) {
            is TimeScheduleEvent.FetchQueues -> {
                fetchTasks()
            }
        }
    }

    private fun fetchTasks() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            scheduleRepository.getTasks().collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                tasks = result.data,
                                error = null
                            )
                        }
                    }
                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }
}