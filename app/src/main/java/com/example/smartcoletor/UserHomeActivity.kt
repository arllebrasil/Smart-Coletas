package com.example.smartcoletor

import androidx.appcompat.app.AppCompatActivity
import  kotlinx.coroutines.*
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.annotation.RestrictTo.Scope
import com.beust.klaxon.Klaxon
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class UserHomeActivity : AppCompatActivity() {

    lateinit var spinerEstados:AutoCompleteTextView
    lateinit var spinerCidades:AutoCompleteTextView

    var estados: ArrayList<String> = ArrayList<String>()
    var cidades: ArrayList<String> = ArrayList<String>()

    var selectedEstado:String? = null
    var selectedCidade:String? = null

    lateinit var estadosAdapter:ArrayAdapter<String>
    lateinit var cidadesAdapter:ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_home)

        spinerEstados = findViewById(R.id.spiner_estados)
        spinerCidades = findViewById(R.id.spiner_cidades)

        estadosAdapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,estados)
        estadosAdapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,cidades)



        spinerEstados.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
            if (spinerCidades.text.toString().isNotBlank()){
                selectedEstado = estados.get(i)
                getCities();
            }
        })
    }



    fun getUFS() {
        if (selectedEstado == null) return;
        CoroutineScope(Dispatchers.IO).launch {
            val localidadesApiService = HttpClient()
            val response = localidadesApiService.get("https://servicodados.ibge.gov.br/api/v1/localidades/estados")
            val data = response.bodyAsText()
            estados = Klaxon().parse<ArrayList<String>>(data) as ArrayList<String>
            estadosAdapter.notifyDataSetChanged()
        }
    }

    fun getCities() {
        if (selectedCidade == null) return;
        CoroutineScope(Dispatchers.IO).launch {
            val localidadesApiService = HttpClient()
            val response = localidadesApiService.get("https://servicodados.ibge.gov.br/api/v1/localidades/estados/${selectedEstado}/cidades")
            val data = response.bodyAsText()
            cidades = Klaxon().parse<ArrayList<String>>(data) as ArrayList<String>
            cidadesAdapter.notifyDataSetChanged()
        }
    }
}