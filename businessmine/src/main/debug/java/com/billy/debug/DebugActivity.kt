package com.billy.debug

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.billy.android.component.user.R
import com.young.businessmine.TestActivity


class DebugActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_layout)
        Toast.makeText(this,"123",Toast.LENGTH_SHORT).show()
        var jump=Intent(this,TestActivity().javaClass)
        startActivity(jump)
    }

}
