package com.moudy.alshafie.DataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.moudy.alshafie.DataBase.UserDatabaseHelper.Companion.TABLE_NAME

class FavoriteDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "favorite.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE favorite (" +
                    "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "  word TEXT NOT NULL," +
                    "  worda TEXT NOT NULL," +
                    "  wordae TEXT NOT NULL" +

                    ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // No need to implement this for now
    }

}
