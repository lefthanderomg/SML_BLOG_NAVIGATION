package andrey.murzin.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;

public class Fragment_1 extends Fragment {

    public Fragment_1() {
    }

    public static Fragment_1 newInstance() {

        return new Fragment_1();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_next)
                .setOnClickListener(Navigation.createNavigateOnClickListener(R.id.fragment_2, null));

        view.findViewById(R.id.btn_next)
                .setOnClickListener(button -> {
                    Navigation.findNavController(button).navigate(R.id.action_fragment_1_to_fragment_2);
                });
    }


}
