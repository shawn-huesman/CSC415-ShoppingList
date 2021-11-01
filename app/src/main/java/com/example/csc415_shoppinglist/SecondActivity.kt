package com.example.csc415_shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children

const val EXTRA_ITEM = "com.csc415.twoactivities.extra.ITEM"

class SecondActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        for (view in findViewById<ViewGroup>(R.id.second_activity).children) {
            if (view is Button) {
                view.setOnClickListener { getItem(it as Button) }
            }
        }
    }

    private fun getItem(view: Button) {
        val replyIntent = Intent()
        replyIntent.putExtra(EXTRA_ITEM, view.text)
        setResult(RESULT_OK, replyIntent)
        finish()
    }
}