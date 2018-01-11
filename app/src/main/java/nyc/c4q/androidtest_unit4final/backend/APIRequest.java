package nyc.c4q.androidtest_unit4final.backend;


import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by c4q on 1/10/18.
 */

public interface APIRequest {
    @GET("/operable/cog/master/priv/css-color-names.json")
    Call<ColorPOJO> getcolors();




}
