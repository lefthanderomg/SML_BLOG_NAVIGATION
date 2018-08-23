package andrey.murzin.navigation;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class SplashFragment extends Fragment {

    private Handler handler;
    private Runnable runnable;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handler = new Handler();
        runnable = () -> {
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_fragment_main);
        };

        handler.postDelayed(runnable, 2000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null && runnable != null) handler.removeCallbacks(runnable);
    }
}
