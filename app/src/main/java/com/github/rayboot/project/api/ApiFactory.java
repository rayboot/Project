package com.github.rayboot.project.api;

/**
 * author: rayboot  Created on 15/11/27.
 * email : sy0725work@gmail.com
 */
public class ApiFactory {
    static Api api = null;

    public static Api getApi() {
        if (api == null) {
            api = new Api();
        }
        return api;
    }
}
