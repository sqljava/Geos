package com.example.geos

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity(), OnClickListener {

    lateinit var savoltv : TextView
    lateinit var ochko : TextView
    lateinit var javob1 : TextView
    lateinit var javob2 : TextView
    lateinit var javob3 : TextView
    lateinit var javob4 : TextView

    lateinit var rasm : ImageView



    var variant = arrayListOf<TextView>()

    var savollar = arrayListOf<Savol>()

    lateinit var indexlar : IntArray

    var togrijavob = 0

    var hozirgi = 0

    var hisob = 0



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savolqosh()
        ui()
        indexlar = IntArray(savollar.size){-1}
        randomIndex()

        savolyoz()
        varianttanla()
        randomJavob()

        rasm.setImageResource(savollar[indexlar[hozirgi]].rasm)





        javob1.setOnClickListener(this)
        javob2.setOnClickListener(this)
        javob3.setOnClickListener(this)
        javob4.setOnClickListener(this)



    }

    fun ui(){
        savoltv = findViewById(R.id.savol)
        ochko = findViewById(R.id.ochko)
        javob1 = findViewById(R.id.javob1)
        javob2 = findViewById(R.id.javob2)
        javob3 = findViewById(R.id.javob3)
        javob4 = findViewById(R.id.javob4)
        rasm = findViewById(R.id.rasm)

        variant.add(javob1)
        variant.add(javob2)
        variant.add(javob3)
        variant.add(javob4)



    }

    fun randomIndex(){

        //var arr = arrayListOf<Int>()

        for (i in 0..indexlar.size-1){
            var a = Random.nextInt(indexlar.size)

//            for (j in arr){
//                if (a!=j){
//                    arr.add(a)
//                    indexlar[i] = a
//                }
//            }
            indexlar[i] = a
        }
    }

    fun savolyoz(){

        savoltv.text = savollar[indexlar[hozirgi]].savol
    }

    fun varianttanla(){
        var ran = Random.nextInt(variant.size)

        variant[ran].text = savollar[indexlar[hozirgi]].javob
        togrijavob = ran

    }

    fun randomJavob(){


        var javoblar = arrayListOf<String>()
        for (i in savollar){
            javoblar.add(i.javob)
        }
        javoblar.removeAt(indexlar[hozirgi])

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
//        if (javob==savollar[indexlar[hozirgi]].javob){
//            ochko.text = "togri"
//        }else{
//            ochko.text = "notogri"
//        }

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

        savolyoz()
        varianttanla()
        randomJavob()



        rasm.animate().apply {
            duration = 500
            alpha(0.1f)
        }.withEndAction{



            rasm.animate().apply {
                duration = 500
                alpha(1f)}

        }.start()
        rasm.setImageResource(savollar[indexlar[hozirgi]].rasm)



        if (hozirgi<indexlar.size-1){
            hozirgi++
        }else{
            randomIndex()
            hozirgi=0
        }






    }
}