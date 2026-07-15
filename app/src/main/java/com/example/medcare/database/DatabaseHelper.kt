package com.example.medcare.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.medcare.models.User

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "MedCare.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_USERS = "users"

        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_PHONE = "phone"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createUsersTable = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT NOT NULL,
                $COLUMN_EMAIL TEXT UNIQUE NOT NULL,
                $COLUMN_PASSWORD TEXT NOT NULL,
                $COLUMN_PHONE TEXT NOT NULL
            )
        """.trimIndent()

        db.execSQL(createUsersTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun registerUser(user: User): Boolean {
        if (isEmailExists(user.email)) return false

        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, user.name)
            put(COLUMN_EMAIL, user.email)
            put(COLUMN_PASSWORD, user.password)
            put(COLUMN_PHONE, user.phone)
        }

        val result = db.insert(TABLE_USERS, null, values)
        db.close()

        return result != -1L
    }

    fun loginUser(email: String, password: String): Boolean {
        val db = readableDatabase

        val cursor = db.query(
            TABLE_USERS,
            null,
            "$COLUMN_EMAIL=? AND $COLUMN_PASSWORD=?",
            arrayOf(email, password),
            null,
            null,
            null
        )

        val success = cursor.count > 0
        cursor.close()
        db.close()

        return success
    }

    fun isEmailExists(email: String): Boolean {
        val db = readableDatabase

        val cursor = db.query(
            TABLE_USERS,
            arrayOf(COLUMN_ID),
            "$COLUMN_EMAIL=?",
            arrayOf(email),
            null,
            null,
            null
        )

        val exists = cursor.count > 0
        cursor.close()
        db.close()

        return exists
    }

    fun getUser(email: String): User? {
        val db = readableDatabase

        val cursor: Cursor = db.query(
            TABLE_USERS,
            null,
            "$COLUMN_EMAIL=?",
            arrayOf(email),
            null,
            null,
            null
        )

        var user: User? = null

        if (cursor.moveToFirst()) {
            user = User(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
                password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD)),
                phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE))
            )
        }

        cursor.close()
        db.close()

        return user
    }
}
