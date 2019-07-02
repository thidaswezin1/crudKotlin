package com.example.testingkotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseAccess {

    lateinit var openHelper : SQLiteOpenHelper
    lateinit var database : SQLiteDatabase

    companion object {
         var instance:DatabaseAccess? = null

        fun getInstance(context:Context):DatabaseAccess{
            if (instance == null) {
                instance = DatabaseAccess(context)
            }
            return instance!!
        }
    }


    constructor(context: Context){
        this.openHelper = DatabaseOpenHelper(context)
    }



    fun open() {

        this.database = openHelper.writableDatabase
    }

    fun close() {
        if (database != null) {
            this.database.close()
        }
    }

    fun insertEmployee(e:Employee){
        var contentValues:ContentValues = ContentValues()
        contentValues.put("name",e.name)
        contentValues.put("email",e.email)
        contentValues.put("address",e.address)
        database.insert("employee_info",null,contentValues)
    }

    fun deleteEmployee(){
        database.delete("employee_info", null, null)
    }

    fun getAllEmployee() : List<Employee>{
        var list:List<Employee> = ArrayList<Employee>()
        val cursor = database.rawQuery("SELECT * from employee_info", null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            var e : Employee = Employee()
            e.name=(cursor.getString(1))
            e.email=(cursor.getString(2))
            e.address=(cursor.getString(3))
            list= listOf(e)
            cursor.moveToNext()
        }
        cursor.close()
        return list
    }

    fun updateEmployee(oldEmployee: Employee,newEmployee: Employee){
        var contentValues:ContentValues = ContentValues()
        contentValues.put("name",newEmployee.name)
        contentValues.put("email",newEmployee.email)
        contentValues.put("address",newEmployee.address)
        database.update("employee_info",contentValues,"name=?", arrayOf(oldEmployee.name))
    }
}