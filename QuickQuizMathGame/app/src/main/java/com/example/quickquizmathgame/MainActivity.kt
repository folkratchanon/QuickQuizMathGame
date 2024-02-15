package com.example.quickquizmathgame


import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


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
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        init()
        btn_mode_back!!.setOnClickListener {
            var intent = Intent(this,StartActivity::class.java)
            startActivity(intent)
        }




        //easy Mode
        questionsList= ArrayList()
        questionsList.add(QuestionModel("9+6 = ?","13","15","14","11","15"))
        questionsList.add(QuestionModel("8+8 = ?","14","17","18","16","16"))
        questionsList.add(QuestionModel("3+9 = ? ","12","13","14","15","12"))
        questionsList.add(QuestionModel("5+7 = ?","12","13","14","15","12"))
        questionsList.add(QuestionModel("9+7 = ?","13","14","15","16","16"))
        questionsList.add(QuestionModel("8+3 = ? ","11","10","13","12","11"))
        questionsList.add(QuestionModel("6+7 = ?","14","13","12","11","13"))
        questionsList.add(QuestionModel("9-2 = ?","5","6","7","8","7"))
        questionsList.add(QuestionModel("7-6 = ?","0","1","3","2","1"))
        questionsList.add(QuestionModel("8-4 = ?","3","5","4","2","4"))
        questionsList.add(QuestionModel("9-5 = ?","2","4","6","1","4"))
        questionsList.add(QuestionModel("6-4 = ?","0","1","2","3","2"))
        questionsList.add(QuestionModel("4-2 = ? ","1","2","3","4","2"))
        questionsList.add(QuestionModel("8-5 = ?","2","4","1","3","3"))
        questionsList.add(QuestionModel("7-2 = ?","5","4","3","2","5"))
        questionsList.add(QuestionModel("6+3 = ?","7","6","8","9","9"))
        questionsList.add(QuestionModel("7+7 = ?","13","14","15","16","14"))
        questionsList.add(QuestionModel("8+5 = ?","13","14","23","33","13"))
        questionsList.add(QuestionModel("6-0 = ?","7","6","5","4","6"))
        questionsList.add(QuestionModel("3-1 = ?","0","1","2","3","2"))

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
        var duration:Long=TimeUnit.SECONDS.toMillis(5)


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