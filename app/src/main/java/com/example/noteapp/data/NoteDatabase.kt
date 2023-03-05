package com.example.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.noteapp.model.Note
import com.example.util.DateConverter
import com.example.util.UUIDConvertor


@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class,UUIDConvertor::class)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}