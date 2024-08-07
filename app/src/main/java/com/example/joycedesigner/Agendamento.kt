package com.example.joycedesigner

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.example.joycedesigner.databinding.ActivityAgendamentoBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class Agendamento : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var data: String = ""
    private var hora: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome").toString()
        binding.tvTitleAgenda.text = "Bem-Vindo,$nome"

        val datePicker = binding.datePicker
        datePicker.setOnDateChangedListener{ _, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,monthOfYear)
            calendar.set(Calendar.DAY_OF_YEAR,dayOfMonth)

            var dia = dayOfMonth.toString()
            val mes: String

            if (dayOfMonth < 10){
                dia = "0$dayOfMonth"
            }
            if (monthOfYear < 10){
                mes = "" + (monthOfYear+1)
            }else{
                mes = (monthOfYear +1).toString()
            }

            data = "$dia / $mes / $year"
        }

        binding.timePicker.setOnTimeChangedListener{ _, hourOfDay, minute ->

            val minuto: String

            if(minute < 10){
                minuto = "0$minute"
            }else{
                minuto = minute.toString()
            }

            hora = "$hourOfDay:$minuto"
        }
        binding.timePicker.setIs24HourView(true)

        binding.btnAgendar.setOnClickListener{

            val servico1 = binding.servico1
            val servico2 = binding.servico2
            val servico3 = binding.servico3

            when{
                hora.isEmpty() -> {
                    mensagem(it,"Preencha o horário", "#FF0000")
                }
                hora < "8:00" && hora > "20:00" -> {
                    mensagem(it,"Estamos fechados - horário de atendimento das 08:00 as 20:00!", "#FF0000")
                }
                data.isEmpty() -> {
                    mensagem(it,"Coloque uma Data!", "#FF0000")
                }
                servico1.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    mensagem(it,"Agendamento realizado com sucesso!","FF03DAC5")
                }
                servico2.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    mensagem(it,"Agendamento realizado com sucesso!","FF03DAC5")
                }
                servico3.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    mensagem(it,"Agendamento realizado com sucesso!","FF03DAC5")
                }
                else -> {
                    mensagem(it,"Escolha um Serviço!", "#FF0000")
                }
            }
        }
    }
    private fun mensagem(view: View, mensagem: String, cor: String){
        val snackbar = Snackbar.make(view,mensagem,Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }
}