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

class Difficult : AppCompatActivity() {
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
        setContentView(R.layout.activity_difficult)
        supportActionBar?.hide()
        init()
        btn_mode_back!!.setOnClickListener {
            var intent = Intent(this,StartActivity::class.java)
            startActivity(intent)
        }




        //easy Mode
        questionsList= ArrayList()
        questionsList.add(QuestionModel("120/20*23 = ?","311","135","138","141","318"))
        questionsList.add(QuestionModel("448/28*41 = ? ","650","655","656","657","656"))
        questionsList.add(QuestionModel("512/32*13 = ?","204","208","213","215","208"))
        questionsList.add(QuestionModel("744/62*20 = ? ","240","241","242","243","240"))
        questionsList.add(QuestionModel("712/89*24 = ?","190","192","194","196","192"))
        questionsList.add(QuestionModel("715/55*19 = ?","241","243","245","247","247"))
        questionsList.add(QuestionModel("552/23*12 = ?","550","552","557","558","552"))
        questionsList.add(QuestionModel("861/41*17 = ?","353","355","357","361","357"))
        questionsList.add(QuestionModel("994/71*22 = ?","306","307","308","309","308"))
        questionsList.add(QuestionModel("759/69*30 = ?","310","320","330","340","330"))
        questionsList.add(QuestionModel("850/34*14 = ?","330","340","350","360","350"))
        questionsList.add(QuestionModel("990/99*55 = ?","545","550","555","560","550"))
        questionsList.add(QuestionModel("957/87*61 = ?","670","671","672","673","671"))
        questionsList.add(QuestionModel("924/42*25 = ?","530","550","570","590","550"))
        questionsList.add(QuestionModel("969/57*18 = ?","306","307","308","309","306"))
        questionsList.add(QuestionModel("836/76*61 = ?","671","675","677","680","671"))
        questionsList.add(QuestionModel("704/88*56 = ?","444","447","448","450","448"))
        questionsList.add(QuestionModel("696/87*47 = ?","376","379","385","387","376"))
        questionsList.add(QuestionModel("534/89*52 = ?","310","311","312","316","312"))
        questionsList.add(QuestionModel("648/54*62 = ?","742","744","746","748","744"))

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
        var duration:Long=TimeUnit.SECONDS.toMillis(40)


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