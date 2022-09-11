package com.example.dagger_hilt_mvvm_room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDao() : AppDao

    companion object {

        private var dbINSTANCE: AppDatabase? = null

        fun getAppDB(context: Context): AppDatabase {
            if (dbINSTANCE == null) {
                synchronized(AppDatabase::class) {
                    dbINSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "MyDb")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return dbINSTANCE!!
        }

        fun destroyInstance() {
            dbINSTANCE = null
        }
    }

}