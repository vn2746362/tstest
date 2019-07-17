package com.horizon.tstest.api;


import com.horizon.tstest.models.LoansDTO;
import com.horizon.tstest.models.OfferDTO;
import com.horizon.tstest.models.ProvincesDTO;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


/*By Phat La*/
public interface RESTService {

    @GET("/offer")
    Observable<OfferDTO> getOffer();

    @GET("/provinces")
    Observable<ProvincesDTO> getProvinces();

    @POST("/loans")
    Observable<LoansDTO> postLoans(@Body Object body); //Object means we can post LoansDTO and HashMap also
}
