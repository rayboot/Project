package com.github.rayboot.project.api.models.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * author: rayboot  Created on 15/12/3.
 * email : sy0725work@gmail.com
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "AppDatabase";

    public static final int VERSION = 1;
}