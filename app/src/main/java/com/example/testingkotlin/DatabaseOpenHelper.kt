package com.example.testingkotlin

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class DatabaseOpenHelper:SQLiteAssetHelper {
    constructor(context:Context) : super(context, "Employee.s3db", null, 1)

}