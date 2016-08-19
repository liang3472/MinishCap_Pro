package com.example;


import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyDaoGenerator {

    public static final String PACKAGENAME = "com.coderstudio.tomliang.fe_webviewtool";

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, PACKAGENAME + ".bean");
        schema.setDefaultJavaPackageDao(PACKAGENAME + ".dao");

        // 一旦你拥有了一个 Schema 对象后，你便可以使用它添加实体（Entities）了。
        addHistory(schema);
        addFavorites(schema);

        new DaoGenerator().generateAll(schema, "app/src/main/java");
    }

    private static void addHistory(Schema schema) {
        Entity history = schema.addEntity("History");

        history.addStringProperty("url").primaryKey().unique().notNull();
        history.addIntProperty("count");
    }

    private static void addFavorites(Schema schema){
        Entity favorites = schema.addEntity("Favorites");

        favorites.addStringProperty("url").primaryKey().unique().notNull();
    }

}
