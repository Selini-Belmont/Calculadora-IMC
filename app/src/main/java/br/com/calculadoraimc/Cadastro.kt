package br.com.calculadoraimc

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro.*
import android.content.SharedPreferences

class Cadastro : AppCompatActivity() {

    var editorSharedPref : SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        editorSharedPref = acessoSharedPref!!.edit()

        btnProximo.setOnClickListener {
            if (edtNome.text.isEmpty()){
                Toast.makeText(this, "Preencha o campo corretamente", Toast.LENGTH_LONG).show()
            }else{
                editorSharedPref!!.putString("nomeUsuario", edtNome.text.toString())
                editorSharedPref!!.apply()

                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
}
