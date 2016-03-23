package com.github.rayboot.project.api.models.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * author: rayboot  Created on 15/12/3.
 * email : sy0725work@gmail.com
 */
@Table(database = AppDatabase.class)
public class DBSampleTable extends BaseModel {

    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
