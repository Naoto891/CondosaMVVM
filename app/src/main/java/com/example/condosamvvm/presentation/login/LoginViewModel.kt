package com.example.condosamvvm.presentation.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condosamvvm.domain.usecase.CheckUsuario
import com.example.condosamvvm.domain.usecase.GetIdPersonal
import com.example.condosamvvm.domain.usecase.GetIdPersonalInt
import com.example.condosamvvm.domain.usecase.GetIdSolicitante
import com.example.condosamvvm.domain.usecase.GetIdSolicitanteInt
import com.example.condosamvvm.domain.usecase.ObtenerUsuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getIdPersonal: GetIdPersonal,
    private val getIdSolicitante: GetIdSolicitante,
    private val checkUsuario: CheckUsuario,
    private val obtenerUsuario: ObtenerUsuario,
    private val getIdSolicitanteInt: GetIdSolicitanteInt,
    private val getIdPersonalInt: GetIdPersonalInt

): ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = enableLogin(email, password)
    }

    private fun enableLogin(email: String, password: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 3



    fun onLoginSelected(xd: (Int) -> Unit, xd2: (Int)-> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            val authenticated = checkUsuario(email.value!!, password.value!!)
            val idPersona = obtenerUsuario(email.value!!, password.value!!)


            if (authenticated) {
                if(getIdPersonal(idPersona!!)){
                    val idPersonal= getIdPersonalInt(idPersona)
                    xd(idPersonal!!)
                }
                if(getIdSolicitante(idPersona)){
                    val idSolicitante = getIdSolicitanteInt(idPersona)
                    xd2(idSolicitante!!)
                }

            }

            _isLoading.value = false
        }
    }




}