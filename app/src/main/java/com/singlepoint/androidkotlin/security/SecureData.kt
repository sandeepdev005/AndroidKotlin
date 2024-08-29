package com.singlepoint.androidkotlin.security

import android.content.Context
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

/**
 *  To store sensitive data securely use
 *  1. Use Encrypted SharedPreferences
 *  2. Use Room Database with Encryption
 *
 *  implementation “androidx.security:security-crypto:1.0.0-rc02”
 * */

class SharedPreferenceClass(val context: Context) {

    fun encryptSharedPreference() {
        //EncryptedSharedPreferences
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val sharedPreference = EncryptedSharedPreferences.create(
            "Prefernnece_name", masterKeyAlias, context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }


    fun encryptRoomDB(){
        //Dependency to be used
        //implementation "net.zetetic:android-database-sqlcipher:4.4.3"
        val passphrase: ByteArray = SQLiteDatabase.getBytes("your-secret-password".toCharArray())
        val factory = SupportFactory(passphrase)

        val db = Room.databaseBuilder(
            context,
            YourDatabase::class.java, "encrypted-database"
        ).openHelperFactory(factory)
            .build()

    }

}
