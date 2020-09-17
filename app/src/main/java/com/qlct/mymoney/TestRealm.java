package com.qlct.mymoney;

import android.content.Context;

import io.realm.Realm;

public class TestRealm {
    public static void Setup(Context context){
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
    }
}
