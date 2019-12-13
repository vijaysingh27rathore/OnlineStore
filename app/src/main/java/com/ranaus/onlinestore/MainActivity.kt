package com.ranaus.onlinestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            val loginUrl = "http://www.ranaus.com/Online_Store/login_users.php?email=" + email.text.toString() +
                    "&password=" + password.text.toString()

            val requestQueue = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(Request.Method.GET,loginUrl,Response.Listener { response ->
                if (response.equals("user exist"))
                {
                    Person.email = email.text.toString()
//                    Toast.makeText(this,response,Toast.LENGTH_LONG).show()
                    var loginIntent = Intent(this,HomeScreenActivity::class.java)
                    startActivity(loginIntent)
                }
                else
                {
                    var dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Alert")
                    dialogBuilder.setMessage(response)
                    dialogBuilder.create().show()
                }
            },Response.ErrorListener { error ->
                var dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Alert")
                dialogBuilder.setMessage(error.message)
                dialogBuilder.create().show()
            })

            requestQueue.add(stringRequest)
        }
    }
}
