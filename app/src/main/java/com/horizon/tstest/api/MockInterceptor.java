package com.horizon.tstest.api;

import android.util.Log;

import com.google.gson.Gson;
import com.horizon.tstest.models.BankDTO;
import com.horizon.tstest.models.DTO;
import com.horizon.tstest.models.LoansDTO;
import com.horizon.tstest.models.OfferDTO;
import com.horizon.tstest.models.ProvincesDTO;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/*By Phat La*/
public class MockInterceptor implements Interceptor {
    public static final String TAG = MockInterceptor.class.getSimpleName();

    //Methods
    private static final String M_GET  = "GET";
    private static final String M_POST = "POST";

    //
    private Gson gson = new Gson();

    //Loans Id will increase per request
    private int loansId = 0;

    //Sample Data
    private DTO offerData;
    private DTO provincesData;

    public MockInterceptor() {
        offerData     = createOfferSampleData();
        provincesData = createProvincesSampleData();
    }

    //
    private DTO createOfferSampleData(){
        //Sample bank data
        BankDTO bank = new BankDTO();
        bank.setName("Vietcombank");
        bank.setLogoURL("https://www.vietcombank.com.vn/images/logo30.png");

        //Sample offer data
        OfferDTO offer = new OfferDTO();
        offer.setMinAmount(30000000F);
        offer.setMaxAmount(100000000F);
        offer.setMinTenor(6);
        offer.setMaxTenor(18);
        offer.setInterestRate(19.99);
        offer.setBank(bank);

        return offer;
    }

    private DTO createProvincesSampleData(){
        //Provinces List
        List<String> provincesList = new ArrayList<>();
        provincesList.add("An Giang");
        provincesList.add("Bắc Giang");
        provincesList.add("Bắc Kạn");
        provincesList.add("Bạc Liêu");
        provincesList.add("Bắc Ninh");
        provincesList.add("Bà Rịa - Vũng Tàu");
        provincesList.add("Bến Tre");
        provincesList.add("Bình Định");
        provincesList.add("Bình Dương");
        provincesList.add("Bình Phước");

        //Sample provinces data
        ProvincesDTO provinces = new ProvincesDTO();
        provinces.setList(provincesList);
        provinces.setTotal(provincesList.size());

        return provinces;
    }

    //
    @Override
    public Response intercept(Chain chain) {

        //
        final Request request = chain.request();
        final URI uri = request.url().uri();
        final String method = request.method();

        //
        String jsonResponse = "";

        //
        switch (uri.getPath()){
            case "/offer": {
                if(method.equals(M_GET)){
                    jsonResponse = gson.toJson(offerData);

                    Log.i(TAG, "[API][GET]/offer:\n" + jsonResponse);
                }
            }
            break;

            case "/provinces": {
                if(method.equals(M_GET)){
                    jsonResponse = gson.toJson(provincesData);

                    Log.i(TAG, "[API][GET]/provinces:\n" + jsonResponse);
                }
            }
            break;

            case "/loans": {
                if(method.equals(M_POST)){
                    try{
                        final RequestBody body = request.body();
                        final Buffer buffer = new Buffer();

                        //
                        if (body != null) {
                            body.writeTo(buffer);

                            //Parse to object
                            LoansDTO loansData = gson.fromJson(buffer.readUtf8(), LoansDTO.class);

                            //
                            loansData.setId(++loansId); //Set ID

                            jsonResponse = gson.toJson(loansData);

                            Log.i(TAG, "[API][GET]/loans:\n" + jsonResponse);
                        }
                    } catch (Exception e){
                        Log.e(TAG, e.getMessage());
                    }
                }
            }
            break;

            //Undefined
            default: break;
        }

        //Build the response
        final Response response = new Response.Builder()
                .code(200)
                .message(jsonResponse)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), jsonResponse.getBytes()))
                .addHeader("content-type", "application/json")
                .build();

        //
        return response;
    }
}
