package com.diegoferreiracaetano.data.local;

import com.diegoferreiracaetano.data.local.converters.DateConverter;
import com.diegoferreiracaetano.data.local.dog.DogDao;
import com.diegoferreiracaetano.data.local.dog.DogEntityLocal;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {
        DogEntityLocal.class,
}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class LocalDb extends RoomDatabase {
    abstract public DogDao dogDao();
}