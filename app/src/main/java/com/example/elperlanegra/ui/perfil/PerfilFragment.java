package com.example.elperlanegra.ui.perfil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elperlanegra.R;
import com.example.elperlanegra.adaptadores.DatosActualesAdapter;
import com.example.elperlanegra.modelos.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class PerfilFragment extends Fragment {

    RecyclerView datosActuales_rec;

    FirebaseDatabase db;
    DatabaseReference usersRef;
    FirebaseAuth auth;
    FirebaseUser user;

    List<UserModel> userModelList;
    DatosActualesAdapter datosActualesAdapter;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        usersRef = db.getReference("Users").child(auth.getUid());


        datosActuales_rec = root.findViewById(R.id.datosActuales_rec);
        datosActuales_rec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        userModelList = new ArrayList<>();
        datosActualesAdapter = new DatosActualesAdapter(getActivity(),userModelList);
        datosActuales_rec.setAdapter(datosActualesAdapter);

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                userModelList.clear();
                for(DataSnapshot snapshot :
                        datasnapshot.getChildren()) {
                    UserModel user = datasnapshot.getValue(UserModel.class);
                    userModelList.add(user);
                    String nombre = user.getNombreAp();
                    String direccion = user.getDireccion();
                    String correo = user.getEmail();
                    String telefono = user.getTelefono();


                    String uid = datasnapshot.getKey();
                } datosActualesAdapter.notifyDataSetChanged(); ;

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Falló la lectura: " + error.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }


}