package andrey.murzin.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmnetCongratulation extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_congratulation, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvCongratulation = view.findViewById(R.id.tv_congratulation);
        if (getArguments() != null) {
//            String email = FragmnetCongratulationArgs.fromBundle(getArguments()).getEmail();
            String email = getArguments().getString("passwordd");
            tvCongratulation.setText(email);
        }
    }
}
