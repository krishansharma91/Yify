package mvp.dagger.yify.yify.domain.presenter;

/**
 * Created by HP LAPTOP on 10-04-2015.
 */
public interface OnLoginFinishListner {
    void onSuccess();

    void onUserNameError();

    void onPasswordError();

    void onFailure(String error);
}
