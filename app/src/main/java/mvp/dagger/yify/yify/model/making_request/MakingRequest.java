package mvp.dagger.yify.yify.model.making_request;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

;


public class MakingRequest implements Parcelable {

    private static final String FIELD_STATUS = "status";
    private static final String FIELD_ERROR = "error";


    @SerializedName(FIELD_STATUS)
    private String mStatus;
    @SerializedName(FIELD_ERROR)
    private String mError;


    public MakingRequest() {

    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setError(String error) {
        mError = error;
    }

    public String getError() {
        return mError;
    }

    public MakingRequest(Parcel in) {
        mStatus = in.readString();
        mError = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MakingRequest> CREATOR = new Creator<MakingRequest>() {
        public MakingRequest createFromParcel(Parcel in) {
            return new MakingRequest(in);
        }

        public MakingRequest[] newArray(int size) {
            return new MakingRequest[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mStatus);
        dest.writeString(mError);
    }


}