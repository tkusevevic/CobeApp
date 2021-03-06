package com.tkusevic.CobeApp.common.utils;

import com.tkusevic.CobeApp.ui.App;
import com.tkusevic.CobeApp.data.database.Data;
import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.data.model.Product;
import com.tkusevic.CobeApp.data.model.User;
import com.tkusevic.CobeApp.data.model.Worker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkusevic on 15.01.2018..
 */

public class DataUtils {

    public static void loadData() {
        Data data = App.getData();
        List<User> users = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<Worker> workers = new ArrayList<>();

        users.add(new User(1, "test@gmail.com", "test", "test"));
        users.add(new User(2, "user1@gmail.com", "user1", "password2"));
        users.add(new User(3, "user2@gmail.com", "user2", "password3"));
        data.addUsers(users);

        products.add(new Product(1, "Pan", 12, R.drawable.pan));
        products.add(new Product(2, "Laško točeno", 10, R.drawable.beer));
        products.add(new Product(3, "Laško", 10, R.drawable.lako));
        products.add(new Product(4, "Ožujsko", 12, R.drawable.ozujsko));
        products.add(new Product(5, "Karlovačko", 10, R.drawable.karlovacko));
        products.add(new Product(6, "Coca cola", 10, R.drawable.coca_cola));
        products.add(new Product(7, "Capuccino", 12, R.drawable.capucino));
        products.add(new Product(8, "kava s mlijekom", 10, R.drawable.coffe));
        products.add(new Product(9, "Espresso", 10, R.drawable.coffe));
        products.add(new Product(10, "Coca cola", 12, R.drawable.coca_cola));
        products.add(new Product(11, "Fanta", 11, R.drawable.fanta));
        products.add(new Product(12, "Sprite", 11, R.drawable.sprite));
        data.addProducts(products);

        workers.add(new Worker(1, 1200, "mirko", "mirkic", "konobar", "mirko@gmail.com", "mirko123"));
        workers.add(new Worker(2, 3500, "Anka", " Pantic", "konobar", "anka@gmail.com", "anka123"));
        workers.add(new Worker(3, 5000, "test", "vlasnik", "vlasnik", "test@test.com", "test"));
        data.addWorkers(workers);


    }
}
