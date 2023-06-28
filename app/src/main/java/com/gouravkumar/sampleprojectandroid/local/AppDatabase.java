package com.gouravkumar.sampleprojectandroid.local;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gouravkumar.sampleprojectandroid.local.dao.UserDetailDao;
import com.gouravkumar.sampleprojectandroid.model.UsersInfo;
import com.gouravkumar.sampleprojectandroid.local.dao.UsersListDao;

@Database(entities = UsersInfo.class, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_DB = "database.db";
    private static AppDatabase appDatabase;

    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, DATABASE_DB)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }

    public abstract UsersListDao getUsersListDao();

    public abstract UserDetailDao getUserDetailDao();
}
