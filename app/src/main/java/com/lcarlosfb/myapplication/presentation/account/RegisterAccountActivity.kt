package com.lcarlosfb.myapplication.presentation.account

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.lcarlosfb.myapplication.R
import com.lcarlosfb.myapplication.databinding.ActivityRegisterAccountBinding
import java.util.*


@Suppress("NAME_SHADOWING")
class RegisterAccountActivity : AppCompatActivity() {

	private lateinit var registerAccountBinding: ActivityRegisterAccountBinding
	private var date : String = ""
	private var cal = Calendar.getInstance()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		registerAccountBinding = ActivityRegisterAccountBinding.inflate(layoutInflater)
		val view = registerAccountBinding.root
		setContentView(view)

		val dateSetListener =
			DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
				cal.set(Calendar.YEAR, year)
				cal.set(Calendar.MONTH, month)
				cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

				val format = "MM/dd/yy"
				val sdf = SimpleDateFormat(format, Locale.US)
				date = sdf.format(cal.time).toString()
				registerAccountBinding.edtDate.setText(date)
			}

		registerAccountBinding.edtDate.setOnClickListener {
			DatePickerDialog(
				this,
				dateSetListener,
				cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH)
			).show()
		}

		val place = resources.getStringArray(R.array.places)
		val adapter = ArrayAdapter(this, R.layout.list_place, place)
		with(registerAccountBinding.actvPlace){
			setAdapter(adapter)
		}

		registerAccountBinding.btnSave.setOnClickListener {

			var info = ""
			val name = registerAccountBinding.edtName.text.toString()
			val email = registerAccountBinding.edtEmail.text.toString()
			val password = registerAccountBinding.edtPassword.text.toString()
			val rpassword = registerAccountBinding.edtRepeatPassword.text.toString()
			val date = registerAccountBinding.edtDate.text.toString()
			val place = registerAccountBinding.actvPlace.text.toString()

			if (name.isEmpty()) {
				registerAccountBinding.edtName.setError("Ingrese su Nombre")
				Toast.makeText(	applicationContext,"Rellenar campos vacíos",Toast.LENGTH_SHORT).show()
			}else if (email.isEmpty()) {
				registerAccountBinding.edtEmail.setError("Ingrese su correo electronico")
				Toast.makeText(	applicationContext,"Rellenar campos vacíos",Toast.LENGTH_SHORT).show()
			}else if (password.isEmpty()) {
				registerAccountBinding.edtPassword.setError("Ingrese contraseña")
				Toast.makeText(	applicationContext,"Rellenar campos vacíos",Toast.LENGTH_SHORT).show()
			}else if (rpassword.isEmpty()) {
				registerAccountBinding.edtRepeatPassword.setError("Ingrese contraseña")
				Toast.makeText(	applicationContext,"Rellenar campos vacíos",Toast.LENGTH_SHORT).show()
			}else if (date.isEmpty()) {
				registerAccountBinding.edtDate.setError("Ingrese fecha de nacimiento")
				Toast.makeText(applicationContext, "Rellenar campos vacíos", Toast.LENGTH_SHORT).show()
			}else if (place.isEmpty()) {
				registerAccountBinding.actvPlace.setError("Ingrese lugar de nacimiento")
				Toast.makeText(applicationContext, "Rellenar campos vacíos", Toast.LENGTH_SHORT).show()
			}else if (password != rpassword){
				registerAccountBinding.actvPlace.setError(null)
				registerAccountBinding.edtDate.setError(null)
				registerAccountBinding.edtRepeatPassword.setError("Verificar contraseña")
				Toast.makeText(applicationContext,"Las contraseñas no son iguales",Toast.LENGTH_SHORT).show()

			}else{
				registerAccountBinding.actvPlace.setError(null)
				registerAccountBinding.edtDate.setError(null)
				val gender = if(registerAccountBinding.rbFemale.isChecked)
						"Femenino"
					else
						"Masculino"
				var hobbies = ""
				if (registerAccountBinding.cbCinema.isChecked) hobbies = "Cine"
				if (registerAccountBinding.cbDance.isChecked) hobbies += " Bailar"
				if (registerAccountBinding.cbCycling.isChecked) hobbies += " Ciclismo"
				if (registerAccountBinding.cbTravel.isChecked) hobbies += " Viajar"
				info += "Nombre: $name\nEmail: $email\nContraseña: $password\nGenero: $gender\nHobbies: $hobbies\n" +
						"Fecha nacimiento: $date\nLugar nacimiento: $place"
				registerAccountBinding.tvResponse.setText(info)
			}
		}
	}
}

// Punto 2 de la version 1.0 terminado