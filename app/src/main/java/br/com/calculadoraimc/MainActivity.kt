package br.com.calculadoraimc

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.annotation.RequiresApi
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var IMC : Float = 0.0f
        var Altura : Float = 0.0f
        var Massa = 0
        fun CalcularIMC(){
            IMC = (Massa / (Altura.pow(2)))
        }

        sbMassa.max = 200
        sbMassa.min = 40
        sbMassa.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                Massa = i
                txvMostraMassa.text = Massa.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                CalcularIMC()
            }
        })

        sbAltura.max = 250
        sbAltura.min = 150

        sbAltura.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, a: Int, b: Boolean) {

                Altura = a/100.toFloat()

                txvMostraAltura.text = Altura.toString()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                CalcularIMC()
            }
        })

        var nome = acessoSharedPref!!.getString("nomeUsuario", "")
        txvNome.text = "Olá, $nome."

        btnMudarNome.setOnClickListener {
            startActivity(Intent(this, Cadastro::class.java))
        }

        fun calcularSituacao(){
            var resultado = IMC

            if(resultado<17){
                txvSituacao.text = "Muito abaixo do peso."
                txvSituacao.setTextColor(Color.BLACK)
            } else if(resultado>=17 && resultado<=18.49){
                txvSituacao.text = "Abaixo do peso."
                txvSituacao.setTextColor(Color.BLUE)
            } else if(resultado>=18.5 && resultado<=24.99){
                txvSituacao.text = "Peso normal."
                txvSituacao.setTextColor(Color.GREEN)
            } else if(resultado>=25 && resultado<=29.99){
                txvSituacao.text = "Acima do peso."
                txvSituacao.setTextColor(Color.YELLOW)
            } else if(resultado>=30 && resultado<=34.99){
                txvSituacao.text = "Obesidade I"
                txvSituacao.setTextColor(Color.rgb(255,140,0))
            } else if(resultado>=35 && resultado<=39.99){
                txvSituacao.text = "Obesidade II (severa)"
                txvSituacao.setTextColor(Color.RED)
            } else {
                txvSituacao.text = "Obesidade III (mórbida)"
                txvSituacao.setTextColor(Color.rgb(92,51,23))
            }
        }

        btnCalcular.setOnClickListener {
            txvResultado.text = String.format("%.1f", IMC)
            calcularSituacao()
        }


    }
}
