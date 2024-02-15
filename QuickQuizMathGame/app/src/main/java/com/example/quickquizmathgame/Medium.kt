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

class Medium : AppCompatActivity() {
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
        setContentView(R.layout.activity_medium)
        supportActionBar?.hide()
        init()
        btn_mode_back!!.setOnClickListener {
            var intent = Intent(this,StartActivity::class.java)
            startActivity(intent)
        }




        //easy Mode
        questionsList= ArrayList()
        questionsList.add(QuestionModel("47-28 = ?","14","15","19","18","19"))
        questionsList.add(QuestionModel("99-33 = ? ","19","22","66","45","66"))
        questionsList.add(QuestionModel("52-36 = ?","14","15","16","17","16"))
        questionsList.add(QuestionModel("65-23 = ?","41","42","43","45","42"))
        questionsList.add(QuestionModel("88-19 = ?","59","49","69","79","69"))
        questionsList.add(QuestionModel("72-54 = ?","17","18","19","20","18"))
        questionsList.add(QuestionModel("26-15 = ?","12","13","14","11","11"))
        questionsList.add(QuestionModel("28+40 = ?","68","67","66","65","68"))
        questionsList.add(QuestionModel("37+19 = ?","45","44","57","56","56"))
        questionsList.add(QuestionModel("66+77 = ?","142","144","143","145","143"))
        questionsList.add(QuestionModel("29+29 = ?","58","59","56","57","58"))
        questionsList.add(QuestionModel("76+12 = ?","85","88","89","78","88"))
        questionsList.add(QuestionModel("25+56 = ?","81","82","83","84","81"))
        questionsList.add(QuestionModel("34+43 = ?","75","74","77","76","77"))
        questionsList.add(QuestionModel("22+89 = ?","110","111","112","113","111"))
        questionsList.add(QuestionModel("51+23 = ?","64","74","84","94","74"))
        questionsList.add(QuestionModel("44+81 = ?","120","123","125","127","125"))
        questionsList.add(QuestionModel("35+63 = ?","99","98","95","94","98"))
        questionsList.add(QuestionModel("67-26 = ?","42","41","43","44","41"))
        questionsList.add(QuestionModel("77-48 = ?","30","29","28","27","29"))

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
        var duration:Long=TimeUnit.SECONDS.toMillis(8)


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