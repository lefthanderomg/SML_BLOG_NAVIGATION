package andrey.murzin.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.w3c.dom.Text;

import androidx.navigation.Navigation;

public class FragmentSignIn extends Fragment {


    public FragmentSignIn() {
    }

    public static FragmentSignIn newInstance() {

        return new FragmentSignIn();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText edEmail = view.findViewById(R.id.ed_email);
        EditText edPassword = view.findViewById(R.id.ed_password);
        view.findViewById(R.id.btn_sign_in).setOnClickListener(v->{
            if (!TextUtils.isEmpty(edEmail.getText()) && !TextUtils.isEmpty(edPassword.getText())) {
                Bundle bundle = new Bundle();
                bundle.putString("email", edEmail.getText().toString());
                bundle.putString("password", edPassword.getText().toString());

                Navigation.findNavController(view).navigate(R.id.fragmnet_congratulation, bundle);
            }
        });
    }
}
