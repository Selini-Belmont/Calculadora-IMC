package br.com.calculadoraimc

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txvNome.text = acessoSharedPref!!.getString("nomeUsuario", "")

        btnMudarNome.setOnClickListener {
            startActivity(Intent(this, Cadastro::class.java))
        }

        fun CalcularIMC() : Float{
            var altura = sbAltura.progress.toFloat()
            var peso = sbMassa.progress.toFloat()

            var IMC = altura/peso.pow(2)
            IMC = round(IMC * 100)/100

            return IMC
        }
    }
}
