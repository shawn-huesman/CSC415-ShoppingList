package com.example.csc415_shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.Intent
import android.widget.TextView
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.children

const val ITEM_REQUEST = 1

class MainActivity : AppCompatActivity()
{
    private val openItemViews = ArrayList<TextView>()
    private var items = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (view in findViewById<ViewGroup>(R.id.main_activity).children) {
            if (view is TextView && view !is Button) {
                openItemViews.add(view)
            }
        }

        if (savedInstanceState != null) {
            items = savedInstanceState.getStringArrayList("ItemsList")!!
            createItemList()
        }

        findViewById<Button>(R.id.add_item).setOnClickListener {
            startActivityForResult(Intent(this, SecondActivity::class.java), ITEM_REQUEST)
        }
    }

    private fun createItemList() {
        var i = 0

        for (item in items) {
            if (i < openItemViews.size) {
                openItemViews[i++].text = item
            }

            else {
                Toast.makeText(applicationContext, "List is full, can only hold 10 items at a time.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("ItemsList", items)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null && requestCode == ITEM_REQUEST && resultCode == RESULT_OK) {
            items.add(data.getStringExtra(EXTRA_ITEM)!!)
            createItemList()
        }
    }
}