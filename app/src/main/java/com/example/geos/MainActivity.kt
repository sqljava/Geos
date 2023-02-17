package com.example.geos

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import kotlin.random.Random

class MainActivity : AppCompatActivity(), OnClickListener {

    lateinit var savoltv : TextView
    lateinit var ochko : TextView
    lateinit var javob1 : TextView
    lateinit var javob2 : TextView
    lateinit var javob3 : TextView
    lateinit var javob4 : TextView

    lateinit var result : TextView
    lateinit var finishcard : CardView
    lateinit var btnha : Button
    lateinit var btnyoq : Button

    lateinit var rasm : ImageView

    var variant = arrayListOf<TextView>()

    var savollar = arrayListOf<Savol>()


    var togrijavob = 0

    var hozirgi = 0

    var hisob = 0



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savolqosh()
        ui()

        randomIndex()

        savolyoz()
        varianttanla()
        randomJavob()
        rasmqoy()




        hozirgi++


        javob1.setOnClickListener(this)
        javob2.setOnClickListener(this)
        javob3.setOnClickListener(this)
        javob4.setOnClickListener(this)

        btnha.setOnClickListener{
            finishcard.visibility = INVISIBLE
            hozirgi = 0
            randomIndex()
            hisob = 0
            ochko.text = "0"

            for (i in variant){
                i.isEnabled = true
            }
            randomIndex()
            savolyoz()
            varianttanla()
            randomJavob()
            rasmqoy()
        }

        btnyoq.setOnClickListener {
            finishcard.visibility = INVISIBLE
            hozirgi = 0
            randomIndex()
            for (i in variant){
                i.isEnabled = true
            }
            ochko.text = hisob.toString()
        }





    }

    fun ui(){
        savoltv = findViewById(R.id.savol)
        ochko = findViewById(R.id.ochko)
        javob1 = findViewById(R.id.javob1)
        javob2 = findViewById(R.id.javob2)
        javob3 = findViewById(R.id.javob3)
        javob4 = findViewById(R.id.javob4)
        rasm = findViewById(R.id.rasm)
        finishcard = findViewById(R.id.finishcard)
        result = findViewById(R.id.result)

        btnha = findViewById(R.id.ha)
        btnyoq = findViewById(R.id.yoq)

        variant.add(javob1)
        variant.add(javob2)
        variant.add(javob3)
        variant.add(javob4)



    }

    fun rasmqoy(){
        rasm.setImageResource(savollar[hozirgi].rasm)
    }

    fun randomIndex(){
        savollar.shuffle()
    }

    fun savolyoz(){

        savoltv.text = savollar[hozirgi].savol
    }

    fun varianttanla(){
        var ran = Random.nextInt(variant.size)

        variant[ran].text = savollar[hozirgi].javob
        togrijavob = ran

    }

    fun randomJavob(){


        var javoblar = arrayListOf<String>()
        for (i in savollar){
            javoblar.add(i.javob)
        }
        javoblar.removeAt(hozirgi)

        for (a in 0..variant.size-1){
            var w = Random.nextInt(javoblar.size)

            if (a==togrijavob){
                continue
            }else{
                variant[a].text = javoblar[w]
                javoblar.removeAt(w)
            }
        }
    }

    fun togrimi(javob: String){

        var savol = savoltv.text
        for (i in savollar){
            if (i.savol==savol){
                if (i.javob == javob){
                    hisob++
                    ochko.text = hisob.toString()+" Togri +1"
                }else{
                    hisob-=3
                    ochko.text = hisob.toString()+" Notogri -3"
                }
            }
        }
    }

    fun savolqosh(){

        savollar.add(Savol("Albaniya", "Tirana", R.drawable.albania))
        savollar.add(Savol("Avstriya", "Vena", R.drawable.avstria))
        savollar.add(Savol("Peru", "Lima", R.drawable.peru))
        savollar.add(Savol("Xitoy", "Pekin", R.drawable.xitoy))
        savollar.add(Savol("Tunis", "Tunis", R.drawable.tunis))
        savollar.add(Savol("Kipr", "Nikosiya", R.drawable.kipr))
        savollar.add(Savol("Korea", "Seul", R.drawable.korea))
    }

    override fun onClick(v: View?) {
        var javob = v as TextView
        togrimi(javob.text.toString())
        if (hozirgi>=savollar.size-1){
            finishgame()
        }else{
            hozirgi++
        }
        savolyoz()
        varianttanla()
        randomJavob()
        rasmqoy()
    }

    fun finishgame(){
        finishcard.visibility = VISIBLE
        result.text = hisob.toString()
        hozirgi=0

        for (i in variant){
            i.isEnabled = false
        }
    }



}