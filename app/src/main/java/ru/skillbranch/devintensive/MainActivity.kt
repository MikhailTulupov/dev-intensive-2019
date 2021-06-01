package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyBoard
import ru.skillbranch.devintensive.models.Bender
import java.util.*

const val KEY_STATUS = "STATUS"
const val KEY_QUESTION = "QUESTION"
const val KEY_ET_MESSAGE = "ET_MESSAGE"

class MainActivity : AppCompatActivity(), View.OnClickListener, TextView.OnEditorActionListener {

    lateinit var benderImage: ImageView
    lateinit var textTxt: TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn: ImageView
    lateinit var benderObj: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        messageEt.setOnEditorActionListener(this)
        messageEt.setText(savedInstanceState?.getString(KEY_ET_MESSAGE) ?: "")
        val status = savedInstanceState?.getString(KEY_STATUS) ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString(KEY_QUESTION) ?: Bender.Question.NAME.name
        benderObj = Bender(
            question = Bender.Question.valueOf(question),
            status = Bender.Status.valueOf(status)
        )
        Log.d("M_MainActivity", "onCreate")

        val (r, g, b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)

        textTxt.text = benderObj.askQuestion()
        sendBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.iv_send) {
            hideKeyBoard()
            sendMessage()
        }
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            hideKeyBoard()
            sendMessage()
            return true
        }
        return false
    }

    fun sendMessage() {
        val (phrase, color) = benderObj.listenAnswer(
            messageEt.text.toString()
        )
        messageEt.setText("")
        val (r, g, b) = color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
        textTxt.text = phrase
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(KEY_STATUS, benderObj.status.name)
        outState.putString(KEY_QUESTION, benderObj.question.name)
        outState.putString(KEY_ET_MESSAGE, et_message.text.toString())
        Log.d(
            "M_MainActivity",
            "onSaveInstanceState ${benderObj.status.name} ${benderObj.question.name}"
        )
    }
}