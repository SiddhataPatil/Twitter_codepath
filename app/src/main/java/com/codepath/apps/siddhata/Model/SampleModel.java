package com.codepath.apps.siddhata.Model;

/**
 * Created by siddhatapatil on 10/8/17.
 */

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

/*
 * This is a temporary, sample model that demonstrates the basic structure
 * of a SQLite persisted Model object. Check out the ActiveAndroid wiki for more details:
 * https://github.com/pardom/ActiveAndroid/wiki/Creating-your-database-model
 *
 */
@Table(name = "items")
public class SampleModel extends Model {
    // Define table fields
    @Column(name = "name")
    private String name;

    public SampleModel() {
        super();
    }

    // Parse model from JSON
    public SampleModel(JSONObject object){
        super();

        try {
            this.name = object.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Getters
    public String getName() {
        return name;
    }

    // Record Finders
    public static com.codepath.apps.siddhata.Model.SampleModel byId(long id) {
        return new Select().from(com.codepath.apps.siddhata.Model.SampleModel.class).where("id = ?", id).executeSingle();
    }

    public static ArrayList<com.codepath.apps.siddhata.Model.SampleModel> recentItems() {
        return new Select().from(com.codepath.apps.siddhata.Model.SampleModel.class).orderBy("id DESC").limit("300").execute();
    }
}
