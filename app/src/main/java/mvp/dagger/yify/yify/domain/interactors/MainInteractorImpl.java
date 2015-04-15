package mvp.dagger.yify.yify.domain.interactors;

import java.util.HashMap;

import mvp.dagger.yify.yify.api.ApiClient;
import mvp.dagger.yify.yify.api.util.CancelableCallback;
import mvp.dagger.yify.yify.model.MovieListWrapper;
import mvp.dagger.yify.yify.domain.presenter.FinishListner;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by krishan on 9/4/15.
 */
public class MainInteractorImpl implements MainInteractor {


    CancelableCallback<MovieListWrapper> callback;


    @Override
    public void fetchData(final FinishListner listner) {
        ApiClient.getApiClient().getMovieList(new HashMap<String, String>(), callback = new CancelableCallback<>(new Callback<MovieListWrapper>() {
            @Override
            public void success(MovieListWrapper movieListWrapper, Response response) {
                listner.onSuccess(movieListWrapper);
            }

            @Override
            public void failure(RetrofitError error) {
                listner.onFailure(error);
            }
        }));
    }

    @Override
    public void destroy() {
        if (callback != null) {
            callback.cancel();
            callback = null;
        }

    }


}