package com.tkusevic.CobeApp.common.utils;

import com.tkusevic.CobeApp.App;
import com.tkusevic.CobeApp.Data;
import com.tkusevic.CobeApp.data.model.User;

import java.util.List;

/**
 * Created by tkusevic on 16.01.2018..
 */

public class PringDataUtils {

    Data data = App.getData();

    public static String printUsers(Data data) {
        List<User> users = data.getUsers();
        String allUsers = "";
        for (User user : users) {
            allUsers += String.format("%d %s %s %s\n", user.getId(), user.getName(), user.getEmail());
        }
        return allUsers;
    }


}
