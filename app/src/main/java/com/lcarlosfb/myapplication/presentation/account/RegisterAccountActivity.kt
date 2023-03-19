package com.lcarlosfb.myapplication.presentation.account

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.lcarlosfb.myapplication.R
import com.lcarlosfb.myapplication.databinding.ActivityRegisterAccountBinding
import java.util.*


@Suppress("NAME_SHADOWING")
class RegisterAccountActivity : AppCompatActivity() {

	private lateinit var registerAccountBinding: ActivityRegisterAccountBinding
	private lateinit var registerAccountViewModel: RegisterAccountViewModel
	//val registerAccountViewModel : RegisterAccountViewModel by viewModels()


	private var date : String = ""
	private var cal = Calendar.getInstance()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		registerAccountBinding = ActivityRegisterAccountBinding.inflate(layoutInflater)
		registerAccountViewModel = ViewModelProvider(this)[RegisterAccountViewModel::class.java]
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
		/*registerAccountViewModel.edtError.observe(this){edtError->
			registerAccountBinding.edtName.setError(edtError)
		}*/

		val edtCampoNameObserver = androidx.lifecycle.Observer<String>{edtNameError->
			registerAccountBinding.edtName.setError(edtNameError)
		}
		registerAccountViewModel.edtNameError.observe(this,edtCampoNameObserver)

		val edtCampoEmailObserver = androidx.lifecycle.Observer<String>{edtEmailError->
			registerAccountBinding.edtEmail.setError(edtEmailError)
		}
		registerAccountViewModel.edtEmailError.observe(this,edtCampoEmailObserver)

		val edtCampoPasswordObserver = androidx.lifecycle.Observer<String>{edtPasswordError->
			registerAccountBinding.edtPassword.setError(edtPasswordError)
		}
		registerAccountViewModel.edtPasswordError.observe(this,edtCampoPasswordObserver)

		val edtCamporPasswordObserver = androidx.lifecycle.Observer<String>{edtrPasswordError->
			registerAccountBinding.edtRepeatPassword.setError(edtrPasswordError)
		}
		registerAccountViewModel.edtrPasswordError.observe(this,edtCamporPasswordObserver)

		val toastCamposObserver = androidx.lifecycle.Observer<String>{ toastError->
			Toast.makeText(	applicationContext,toastError,Toast.LENGTH_SHORT).show()
		}
		registerAccountViewModel.toastError.observe(this,toastCamposObserver)

		val verificarPasswordObserver = androidx.lifecycle.Observer<String>{ verificarPasswordError->
			registerAccountBinding.edtRepeatPassword.setError(verificarPasswordError)
		}
		registerAccountViewModel.verificarPasswordError.observe(this,verificarPasswordObserver)

		val salidaObserver = androidx.lifecycle.Observer<String>{ salida->
			registerAccountBinding.tvResponse.setText(salida)
		}
		registerAccountViewModel.salida.observe(this,salidaObserver)


		registerAccountBinding.btnSave.setOnClickListener {

			val name = registerAccountBinding.edtName.text.toString()
			val email = registerAccountBinding.edtEmail.text.toString()
			val password = registerAccountBinding.edtPassword.text.toString()
			val rPassword = registerAccountBinding.edtRepeatPassword.text.toString()
			val date = registerAccountBinding.edtDate.text.toString()
			val place = registerAccountBinding.actvPlace.text.toString()
			val genderF = registerAccountBinding.rbFemale.isChecked()
			val dance = registerAccountBinding.cbDance.isChecked()
			val cycling = registerAccountBinding.cbCycling.isChecked()
			val cinema = registerAccountBinding.cbCinema.isChecked()
			val travel = registerAccountBinding.cbTravel.isChecked()

			registerAccountViewModel.save(name,email,password,rPassword,date,
				place,genderF,dance,cycling,cinema,travel)
		}
	}
}

// Punto 2 de la version 1.0 terminado

//Punto 2 de la version 2.0 terminado