package com.lcarlosfb.myapplication.presentation.account

import androidx.core.util.PatternsCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class RegisterAccountViewModel : ViewModel() {
	//val edtError : MutableLiveData<Boolean> = MutableLiveData()

	val edtNameError: MutableLiveData<String> by lazy {
		MutableLiveData<String>()
	}
	val edtEmailError: MutableLiveData<String> by lazy {
		MutableLiveData<String>()
	}
	val edtPasswordError: MutableLiveData<String> by lazy {
		MutableLiveData<String>()
	}
	val edtrPasswordError: MutableLiveData<String> by lazy {
		MutableLiveData<String>()
	}
	val toastError: MutableLiveData<String> by lazy {
		MutableLiveData<String>()
	}
	val verificarPasswordError : MutableLiveData<String> by lazy{
		MutableLiveData<String>()
	}
	var salida : MutableLiveData<String> = MutableLiveData()

	fun save(name: String,email: String,password: String,rPassword: String,date: String,place: String,
		genderF : Boolean,dance : Boolean,cycling : Boolean,cinema : Boolean,travel : Boolean) {

		val passwordRegex = Pattern.compile(
			"^" +

					"(?=\\S+$)" +        //No admite espacios.
					".{4,}" +            //Minimo 4 caracteres.

					"$"
		)

		if (name.isEmpty()) {
			edtNameError.value = "Ingrese su Nombre"
		}
		if (email.isEmpty()) {
			edtEmailError.value = "Ingrese su correo electronico"
		}
		if (password.isEmpty()) {
			edtPasswordError.value = "Ingrese contraseña"
		}
		if (rPassword.isEmpty()) {
			edtrPasswordError.value = "Ingrese contraseña"
		}

		if (name.isEmpty() || email.isEmpty() || password.isEmpty() || rPassword.isEmpty() || date.isEmpty()
			|| place.isEmpty()){
			toastError.value = "Rellenar campos vacíos"
		}else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
			edtEmailError.value = "Ingrese un correo electronico valido"
			toastError.value = "Ingrese un correo electronico valido"
		}else if(!passwordRegex.matcher(password).matches() ){
			edtPasswordError.value = "La contraseña debe tener:\n" +
					" *Minimo 4 caracteres\n" +
					" *Sin espacios"
			toastError.value = "Contraseña invalida"
		}else if (password != rPassword) {
			verificarPasswordError.value = "Verificar contraseña"
			toastError.value = "Las contraseñas no son iguales"
		}else{
			val gender = if(genderF==true)
				"Femenino"
			else
				"Masculino"

			var hobbies = ""
			if (dance == true) hobbies += "Bailar"
			if (cycling == true) hobbies += " Ciclismo"
			if (cinema == true) hobbies += " Cine"
			if (travel == true) hobbies += " Viajar"

			salida.value = "Nombre: $name\nEmail: $email\nContraseña: $password\nGenero: $gender\nHobbies: $hobbies\nFecha nacimiento: $date\nLugar nacimiento: $place\n"
		}
	}
}