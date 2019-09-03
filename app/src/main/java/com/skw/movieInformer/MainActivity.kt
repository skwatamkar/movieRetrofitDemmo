package com.skw.movieInformer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.skw.constants.AppConstants
import com.skw.model.movieModel
import com.skw.network.APIClient
import com.skw.network.APIServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity:AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
    }

    private fun loadData():Unit
    {


        val requestInterface = Retrofit.Builder()

//Set the API’s base URL//

            .baseUrl(AppConstants.BASE_URL)

//Specify the converter factory to use for serialization and deserialization//

            .addConverterFactory(GsonConverterFactory.create())

//Add a call adapter factory to support RxJava return types//

            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

//Build the Retrofit instance//

            .build().create(APIServices::class.java)

//Add all RxJava disposables to a CompositeDisposable//

        var myCompositeDisposable : CompositeDisposable = CompositeDisposable()

        myCompositeDisposable?.add(requestInterface.getMovieDetails("f2dfeace","morya", "full")

//Send the Observable’s notifications to the main UI thread//

            .observeOn(AndroidSchedulers.mainThread())

//Subscribe to the Observer away from the main UI thread//

            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse, this::handleError))

    }

    private fun handleResponse(movieModel: movieModel) {

        Toast.makeText(this, movieModel.toString(),Toast.LENGTH_LONG).show()
        

    }

    private fun handleError(throwable: Throwable)
    {

    }

}

