package com.example.testingkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.view.View
import android.widget.Toast

class NextActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var db : DatabaseAccess
    var btnInsert:Button? = null
    lateinit var btnUpdate:Button
    lateinit var btnDelete:Button
    lateinit var btnView : Button
    lateinit var etName : EditText
    lateinit var etEmail : EditText
    lateinit var etAddress : EditText
    lateinit var tvName : TextView
    lateinit var tvEmail : TextView
    lateinit var tvAddress : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.next)
        btnInsert = findViewById(R.id.btnInsert)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
        btnView = findViewById(R.id.btnView)
        btnInsert!!.setOnClickListener(this)
        btnUpdate.setOnClickListener(this)
        btnDelete.setOnClickListener(this)
        btnView.setOnClickListener(this)

        etName = findViewById(R.id.editTextName)
        etEmail = findViewById(R.id.editTextEmail)
        etAddress = findViewById(R.id.editTextAddress)

        tvName = findViewById(R.id.tvName)
        tvEmail = findViewById(R.id.tvEmail)
        tvAddress = findViewById(R.id.tvAddress)

        this.db = DatabaseAccess.getInstance(applicationContext)

        //var dbaccess : DatabaseAccess = DatabaseAccess(applicationContext)
        //this.db = dbaccess.getInstance(applicationContext)
    }

    override fun onClick(v: View?) {
        if(v==btnInsert){
            insert()
            Toast.makeText(applicationContext,"DB Insert",Toast.LENGTH_SHORT).show()
        }
        else if (v==btnUpdate){
            update()
            Toast.makeText(applicationContext,"DB Update",Toast.LENGTH_SHORT).show()
        }
        else if (v==btnDelete){
            delete()
            Toast.makeText(applicationContext,"DB Delete",Toast.LENGTH_SHORT).show()
            tvName.setText("name")
            tvEmail.setText("email")
            tvAddress.setText("address")
        }
        else if (v==btnView){
            var list : List<Employee> = getLastEmployee()
            var e : Employee = list.get(list.size-1)
            tvName.setText(e.name)
            tvEmail.setText(e.email)
            tvAddress.setText(e.address)
        }

    }

    fun insert(){
        db.open()
        var employee:Employee = Employee()
        employee.name = etName.getText().toString()
        employee.email = etEmail.getText().toString()
        employee.address = etAddress.getText().toString()
        db.insertEmployee(employee)
        db.close()
    }

    fun update(){
        db.open()
        var newemp:Employee = Employee()
        newemp.name = etName.text.toString()
        newemp.email = etEmail.text.toString()
        newemp.address = etAddress.text.toString()

        var list : List<Employee> = db.getAllEmployee()
        var oldemp : Employee = list.get(list.size-1)
        db.updateEmployee(oldemp,newemp)
        db.close()
    }

    fun delete(){
        db.open()
        db.deleteEmployee()
        db.close()
    }

    fun getLastEmployee():List<Employee>{
        var list : List<Employee> = ArrayList<Employee>()
        db.open()
        list = db.getAllEmployee()
        db.close()
        return list
    }
}