package andrey.murzin.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.navigation.Navigation;

public class FragmentSignUp extends Fragment {


    public FragmentSignUp() {
    }

    public static FragmentSignUp newInstance() {

        return new FragmentSignUp();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText edEmail = view.findViewById(R.id.ed_email);
        EditText edPassword = view.findViewById(R.id.ed_password);
        Button button = view.findViewById(R.id.sign_up);

        button.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(edEmail.getText()) && !TextUtils.isEmpty(edPassword.getText())) {
                FragmentSignUpDirections.ActionFragmentSignUpToFragmnetCongratulation action
                        = FragmentSignUpDirections
                        .actionFragmentSignUpToFragmnetCongratulation("email", "password");
                Navigation.findNavController(view).navigate(action);
            }
        });
    }
}
