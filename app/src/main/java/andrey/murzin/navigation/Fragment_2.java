package andrey.murzin.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_2 extends Fragment {


    public Fragment_2() {
    }

    public static Fragment_2 newInstance() {

        return new Fragment_2();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_2, container, false);
    }


}
