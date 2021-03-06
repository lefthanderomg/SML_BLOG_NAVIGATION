package andrey.murzin.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;

public class FragmentMain extends Fragment {

    public FragmentMain() {
    }

    public static FragmentMain newInstance() {

        return new FragmentMain();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_sign_up)
                .setOnClickListener(Navigation.createNavigateOnClickListener(R.id.fragment_sign_up, null));

        view.findViewById(R.id.btn_sign_in)
                .setOnClickListener(v->{
                    Navigation.findNavController(v).navigate(R.id.action_fragment_main_to_fragment_sign_in);
                });

        view.findViewById(R.id.btn_activity_graph)
                .setOnClickListener(v->{
                    Navigation.findNavController(v).navigate(R.id.action_fragment_main_to_nestedActivity);
                });

        view.findViewById(R.id.btn_nested_fragment)
                .setOnClickListener(v->{
                    Navigation.findNavController(v).navigate(R.id.action_fragment_main_to_nestedFragment);
                });

        view.findViewById(R.id.btn_error).setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.fragmentError, null));

        view.findViewById(R.id.btn_bottom).setOnClickListener(
                v -> {
                    Navigation.findNavController(v).navigate(R.id.action_fragment_main_to_bottomActvitity);
                });
    }


}
