package com.example.kotlinbtk_4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinbtk_4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    //SharedPreferences : Küçük veri saklama
    lateinit var sharedPreferences: SharedPreferences
    var alinanKullaniciAdi:String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPreferences = this.getSharedPreferences("com.example.kotlinbtk_4", Context.MODE_PRIVATE)

        alinanKullaniciAdi = sharedPreferences.getString("isim","")
        if (alinanKullaniciAdi==""){
            binding.textView2.text = "Kaydedilen İsim: "
        }
        else {
            binding.textView2.text = "Kaydedilen İsim: " + alinanKullaniciAdi
        }
    }

    fun kaydet(view: View) {
        var kullaniciIsmi = binding.editSimText.text .toString();
        kullaniciIsmi =kullaniciIsmi.replace(" ","")
        if (kullaniciIsmi.isEmpty()){
            Toast.makeText(this@MainActivity,"İsminizi boş bırakmayın",Toast.LENGTH_LONG).show()
        }
        else {
            sharedPreferences.edit().putString("isim",kullaniciIsmi).apply()
            Toast.makeText(this@MainActivity,"Kaydedildi",Toast.LENGTH_LONG).show()
            binding.textView2.text = "Kaydedilen İsim: " + kullaniciIsmi

        }

 }

    fun sil (view : View) {
        alinanKullaniciAdi = sharedPreferences.getString("isim","")
        if (alinanKullaniciAdi !=""){
            sharedPreferences.edit().remove("isim").apply()
        }
        binding.textView2.text = "Kaydedilen İsim: "
    }
}