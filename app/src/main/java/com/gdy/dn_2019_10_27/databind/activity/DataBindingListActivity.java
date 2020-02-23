package com.gdy.dn_2019_10_27.databind.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.gdy.dn_2019_10_27.BR;
import com.gdy.dn_2019_10_27.R;
import com.gdy.dn_2019_10_27.databind.model.UserViewModel;
import com.gdy.dn_2019_10_27.main.adapter.CommAdapter;

import java.util.ArrayList;
import java.util.List;

public class DataBindingListActivity extends AppCompatActivity {

    ListView listView;

    List<UserViewModel> datas;
 //https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576579548328&di=c019e8393cfccfa745783fe993dcafc7&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2019-11-13%2F5dcb758c7ede9.jpg");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data_binding_list);

        datas = new ArrayList<>();

        listView = findViewById(R.id.listView);

        for(int i=0;i<5;i++){
            UserViewModel model = new UserViewModel("王鸥","女","34","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576579548328&di=c019e8393cfccfa745783fe993dcafc7&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2019-11-13%2F5dcb758c7ede9.jpg");
            datas.add(model);
        }
        CommAdapter<UserViewModel> adapter = new CommAdapter<UserViewModel>(this,getLayoutInflater(),R.layout.item_data_bind,
                com.gdy.dn_2019_10_27.BR.userMV,
                datas);

        listView.setAdapter(adapter);
    }
}
