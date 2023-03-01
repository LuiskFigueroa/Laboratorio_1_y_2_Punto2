package com.lcarlosfb.myapplication.presentation.account

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.internal.TextWatcherAdapter
import com.lcarlosfb.myapplication.R
import com.lcarlosfb.myapplication.databinding.ActivityMainBinding
import com.lcarlosfb.myapplication.databinding.ActivityRegisterAccountBinding
import java.util.*


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


			if (name.isEmpty() || email.isEmpty() || password.isEmpty() || rpassword.isEmpty() ||
				date.isEmpty() || place.isEmpty())
				Toast.makeText(applicationContext,"No debe dejar campos vacios",Toast.LENGTH_SHORT).show()

				if (name.isEmpty())
					registerAccountBinding.edtName.setError("Ingrese su Nombre")
				if (email.isEmpty())
					registerAccountBinding.edtEmail.setError("Ingrese su correo electronico")
				if (password.isEmpty())
					registerAccountBinding.edtPassword.setError("Ingrese contraseña")
				if (rpassword.isEmpty())
					registerAccountBinding.edtRepeatPassword.setError("Ingrese contraseña")
				if (date.isEmpty())
					registerAccountBinding.edtDate.error="Ingrese fecha de nacimiento"
				if (place.isEmpty())
					registerAccountBinding.actvPlace.error="Ingrese lugar de nacimiento"


			if (password != rpassword)
				Toast.makeText(applicationContext,"Las contraseñas no son iguales",Toast.LENGTH_SHORT).show()

			else{
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