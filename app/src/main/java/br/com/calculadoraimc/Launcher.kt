package br.com.calculadoraimc

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager

var acessoSharedPref : SharedPreferences? = null

class Launcher : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_launcher)

        acessoSharedPref = getSharedPreferences("dados", Context.MODE_PRIVATE)

        Handler().postDelayed({
            if(acessoSharedPref!!.getString("nomeUsuario", null) == null){
                startActivity(Intent(this, Cadastro::class.java))
            }else {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }, 3000)
    }
}
