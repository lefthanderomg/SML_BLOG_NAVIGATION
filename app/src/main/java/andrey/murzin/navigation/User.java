package andrey.murzin.navigation;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String password;
    private String email;


    User(String password, String email) {
        this.password = password;
        this.email = email;
    }

    private User(Parcel in) {
        password = in.readString();
        email = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(password);
        dest.writeString(email);
    }
}
