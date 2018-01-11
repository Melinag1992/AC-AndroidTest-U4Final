package nyc.c4q.androidtest_unit4final;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nyc.c4q.androidtest_unit4final.backend.APIRequest;
import nyc.c4q.androidtest_unit4final.backend.ColorPOJO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ColorAdapter adapter;
    protected HashMap<String, String> colorDict;
    protected List<String> colorsList;
    String TAG = "color is ";
    private String purple;
    private String orange;
    private String brown;
    private String black;
    private String red;
    private String indigo;
    private String green;
    private String blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorDict = new HashMap<>();

        final String url = "https://raw.githubusercontent.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIRequest colorHexCode = retrofit.create(APIRequest.class);
        Call<ColorPOJO> colorResponse = colorHexCode.getcolors();
        colorResponse.enqueue(new Callback<ColorPOJO>() {
            @Override
            public void onResponse(Call<ColorPOJO> call, Response<ColorPOJO> response) {
                ColorPOJO color = new ColorPOJO();


                colorDict.put("purple", response.body().getPurple());
                colorDict.put("green", response.body().getGreen());
                colorDict.put("blue", response.body().getBlue());
                colorDict.put("red", response.body().getRed());
                colorDict.put("black", response.body().getBlack());
                colorDict.put("brown", response.body().getBrown());
                colorDict.put("orange", response.body().getOrange());
                colorDict.put("indigo", response.body().getIndigo());
                // TODO: adding all the colors and their values would be tedious, instead fetch it from the url below
                // https://raw.githubusercontent.com/operable/cog/master/priv/css-color-names.json

                colorsList = new ArrayList<>();
                String[] names = new String[]{"blue", "red", "purple", "indigo", "orange", "brown", "black", "green"};
                for (String n : names) colorsList.add(n);

                RecyclerView recyclerView = findViewById(R.id.rv);
                adapter = new ColorAdapter(colorsList, colorDict);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onFailure(Call<ColorPOJO> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case (R.id.info):
                InfoFragment infoFragment = new InfoFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.info_fragment, infoFragment);
                fragmentTransaction.commit();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

//    public void setRetrofit() {
//
//        final String url = "https://raw.githubusercontent.com/";
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(url)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        APIRequest colorHexCode = retrofit.create(APIRequest.class);
//        Call<ColorPOJO> colorResponse = colorHexCode.getcolors();
//        colorResponse.enqueue(new Callback<ColorPOJO>() {
//            @Override
//            public void onResponse(Call<ColorPOJO> call, Response<ColorPOJO> response) {
//                ColorPOJO color = new ColorPOJO();
//
//                black = response.body().getBlack();
//                orange = response.body().getOrange();
//                purple = response.body().getPurple();
//                brown = response.body().getBrown();
//                blue = response.body().getBlue();
//                orange = response.body().getOrange();
//                green = response.body().getGreen();
//                indigo = response.body().getIndigo();
//                red = response.body().getRed();
//
//
//                Log.d(TAG, response.body().getBlue());
//
//            }


//        });

//    }


//}


// TODO: Add options menu with the item "Info" which is always visible
// TODO: When "Info" menu item is clicked, display the fragment InfoFragment
// TODO: If InfoFragment is already visible and I click "Info", remove InfoFragment from the view.
// Link to creating options menu: https://github.com/C4Q/AC-Android/tree/v2/Android/Lecture-9-Menus-and-Navigation#anatomy-of-menu-xml





