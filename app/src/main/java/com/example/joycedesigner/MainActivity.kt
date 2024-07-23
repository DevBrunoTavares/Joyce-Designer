package com.example.joycedesigner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.joycedesigner.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val edtNome = binding.edtNome

        binding.btnAgendar.setOnClickListener{
            val nome = binding.edtNome.text.toString()

            if (nome ==""){

                Snackbar.make(
                    edtNome,"Preencha seu nome",
                    Snackbar.LENGTH_LONG
                )
                    .show()
            }else{
                navegarPraagendamento(nome)
            }
        }
    }

    private fun navegarPraagendamento(nome: String){
        val intent = Intent(this,Agendamento::class.java)
        intent.putExtra("nome",nome)
        startActivity(intent)
    }
}