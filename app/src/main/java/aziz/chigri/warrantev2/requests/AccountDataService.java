package aziz.chigri.warrantev2.requests;

import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountDataService {

    @POST("/login")
    Call<ResponseBody> login(@Body JsonObject loginObject);
}
