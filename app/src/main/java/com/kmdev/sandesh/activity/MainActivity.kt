package com.kmdev.sandesh.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

import com.kmdev.sandesh.R

class MainActivity : AppCompatActivity(), View.OnClickListener {


    var mEtQuotes: EditText? = null
    var mTvAddQuote: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mEtQuotes = findViewById(R.id.edittext_add_quotes) as EditText
        mTvAddQuote = findViewById(R.id.tv_add_quote) as TextView

        mTvAddQuote?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tv_add_quote->{

            }
        }

    }
}
