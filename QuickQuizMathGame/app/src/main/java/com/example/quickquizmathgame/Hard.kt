package com.example.quickquizmathgame

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.random.Random

class Hard : AppCompatActivity() {
    lateinit var questionsList:ArrayList<QuestionModel>
    private var index:Int = 0
    lateinit var questionModel: QuestionModel

    private var correctAnswerCount:Int=0
    private var wrongAnswerCount:Int=0

    lateinit var countDown:TextView
    lateinit var questions:TextView
    lateinit var option1:Button
    lateinit var option2:Button
    lateinit var option3:Button
    lateinit var option4:Button




    private var backPressedTime: Long = 0
    private var backToast: Toast? = null

    var btn_mode_back : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hard)
        supportActionBar?.hide()
        init()
        btn_mode_back!!.setOnClickListener {
            var intent = Intent(this,StartActivity::class.java)
            startActivity(intent)
        }



        //easy Mode
        questionsList= ArrayList()
        questionsList.add(QuestionModel("26+11*14 = ?","140","150","160","180","180"))
        questionsList.add(QuestionModel("300-18*12 = ?","64","74","84","94","84"))
        questionsList.add(QuestionModel("86+23*11 = ?","339","330","329","320","339"))
        questionsList.add(QuestionModel("279-15*15 = ? ","63","57","54","60","54"))
        questionsList.add(QuestionModel("53+16*14 = ?","269","270","276","277","277"))
        questionsList.add(QuestionModel("266-12*12 = ?","121","122","120","124","122"))
        questionsList.add(QuestionModel("98+18*10 = ?","280","279","278","277","278"))
        questionsList.add(QuestionModel("500-20*15 = ?","100","200","300","400","200"))
        questionsList.add(QuestionModel("36+13*18 = ?","260","270","280","290","270"))
        questionsList.add(QuestionModel("343-11*17 = ?","156","157","158","159","156"))
        questionsList.add(QuestionModel("80+14*16 = ?","300","304","309","312","304"))
        questionsList.add(QuestionModel("199-13*13 = ?","10","20","30","40","30"))
        questionsList.add(QuestionModel("59+19*12 = ?","285","287","289","290","287"))
        questionsList.add(QuestionModel("450-19*18 = ?","110","107","106","108","108"))
        questionsList.add(QuestionModel("41+15*22 = ?","380","376","374","371","371"))
        questionsList.add(QuestionModel("234-16*12 = ?","53","42","39","31","42"))
        questionsList.add(QuestionModel("72+24*11 = ?","324","329","334","336","336"))
        questionsList.add(QuestionModel("531-15*19 = ?","242","244","246","248","246"))
        questionsList.add(QuestionModel("64+21*24 = ?","550","555","564","568","568"))
        questionsList.add(QuestionModel("439-18*13 = ? ","202","204","205","206","205"))

        //questionsList.shuffle()
        questionModel= questionsList[index]

        setAllQuestions()

        countdown()










    }
    fun init(){
        countDown=findViewById(R.id.countdown)
        questions=findViewById(R.id.questions)
        option1=findViewById(R.id.option1)
        option2=findViewById(R.id.option2)
        option3=findViewById(R.id.option3)
        option4=findViewById(R.id.option4)
        btn_mode_back = findViewById(R.id.btn_mode_back)
    }


    fun countdown(){
        var duration:Long=TimeUnit.SECONDS.toMillis(20)


        object :CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                var sDuration:String= String.format(Locale.ENGLISH,
                    "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))

                countDown.text = sDuration

            }
            override fun onFinish() {
                index++
                if (index<10){
                    val list = listOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20)
                    val randomIndex = Random.nextInt(list.size);
                    //val randomElement = list[randomIndex]
                    questionModel=questionsList[randomIndex]
                    setAllQuestions()
                    resetBackground()
                    enableButton()
                    countdown()

                }
                else{

                    gameResult()

                }


            }



        }.start()



    }


    private fun correctAns(option: Button){
        option.background=getDrawable(R.drawable.right_bg)

        correctAnswerCount++



    }
    private fun wrongAns(option:Button){

        option.background=resources.getDrawable(R.drawable.wrong_bg)

        wrongAnswerCount++


    }

    private fun gameResult(){

        var intent=Intent(this,ResultActivity::class.java)

        intent.putExtra("correct",correctAnswerCount.toString())
        intent.putExtra("total","10")


        startActivity(intent)
    }



    private fun setAllQuestions() {
        questions.text=questionModel.question
        option1.text=questionModel.option1
        option2.text=questionModel.option2
        option3.text=questionModel.option3
        option4.text=questionModel.option4
    }
    private fun enableButton(){
        option1.isClickable=true
        option2.isClickable=true
        option3.isClickable=true
        option4.isClickable=true
    }
    private fun disableButton(){
        option1.isClickable=false
        option2.isClickable=false
        option3.isClickable=false
        option4.isClickable=false
    }
    private fun resetBackground(){
        option1.background=resources.getDrawable(R.drawable.option_bg)
        option2.background=resources.getDrawable(R.drawable.option_bg)
        option3.background=resources.getDrawable(R.drawable.option_bg)
        option4.background=resources.getDrawable(R.drawable.option_bg)
    }
    fun option1Clicked(view:View){
        disableButton()
        if(questionModel.option1==questionModel.answer){



            correctAns(option1)
            Toast.makeText(this,"True",Toast.LENGTH_SHORT).show()

        }
        else{
            wrongAns(option1)
            Toast.makeText(this,"Wrong", Toast.LENGTH_SHORT).show()
        }
    }

    fun option2Clicked(view:View){
        disableButton()
        if(questionModel.option2==questionModel.answer){



            correctAns(option2)
            Toast.makeText(this,"True",Toast.LENGTH_SHORT).show()

        }
        else{
            wrongAns(option2)
            Toast.makeText(this,"Wrong", Toast.LENGTH_SHORT).show()
        }
    }
    fun option3Clicked(view:View){
        disableButton()
        if(questionModel.option3==questionModel.answer){




            correctAns(option3)
            Toast.makeText(this,"True",Toast.LENGTH_SHORT).show()


        }
        else{
            wrongAns(option3)
            Toast.makeText(this,"Wrong", Toast.LENGTH_SHORT).show()
        }
    }
    fun option4Clicked(view:View){
        disableButton()
        if(questionModel.option4==questionModel.answer){



            correctAns(option4)
            Toast.makeText(this,"True",Toast.LENGTH_SHORT).show()

        }
        else{
            wrongAns(option4)
            Toast.makeText(this,"Wrong", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast?.cancel()
            finish()
        }

        else {
            backToast = Toast.makeText(baseContext, "DOUBLE PRESS TO QUIT GAME", Toast.LENGTH_SHORT)
            backToast?.show()
        }
        backPressedTime = System.currentTimeMillis()

    }




}