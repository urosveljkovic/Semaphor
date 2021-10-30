package com.urkeev14.semaphor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _activeLight = MutableLiveData(redLight)
    private val _previousLight = MutableLiveData(redLight)
    private val activeLightDelayTime get() = _activeLight.value!!.nextLightDuration.times(1000L)

    val activeLight: LiveData<Light> = _activeLight

    init {
        startSemaphore()
    }

    private fun startSemaphore() = viewModelScope.launch {
        while (true) {
            if(_activeLight.value?.color == redLight.color){
                _activeLight.postValue(yellowLight)
                _previousLight.postValue(redLight)
            }else if (_activeLight.value?.color == yellowLight.color){
                if(_previousLight.value?.color == redLight.color){
                    _activeLight.postValue(greenLight)
                    _previousLight.postValue(greenLight)
                }else{
                    _activeLight.postValue(redLight)
                    _previousLight.postValue(redLight)
                }
            }else{
                _activeLight.postValue(yellowLight)
                _previousLight.postValue(greenLight)
            }
            delay(activeLightDelayTime)
        }
    }

}

private val redLight = Light(isActive = true, color = R.color.red, nextLightDuration = 1)
private val yellowLight = Light(isActive = false, color = R.color.yellow, nextLightDuration = 3)
private val greenLight = Light(isActive = false, color = R.color.green, nextLightDuration = 1)