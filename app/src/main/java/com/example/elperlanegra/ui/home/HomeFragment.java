package com.example.elperlanegra.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elperlanegra.R;
import com.example.elperlanegra.adaptadores.CategoryAdapter;
import com.example.elperlanegra.adaptadores.DesayunoAdapter;
import com.example.elperlanegra.adaptadores.PopularAdapter;
import com.example.elperlanegra.adaptadores.VerTodoAdapter;
import com.example.elperlanegra.databinding.FragmentHomeBinding;
import com.example.elperlanegra.modelos.CategoryModel;
import com.example.elperlanegra.modelos.DesayunoModel;
import com.example.elperlanegra.modelos.PopularModel;
import com.example.elperlanegra.modelos.VerTodoModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ScrollView scrollView;
    ProgressBar home_pb;

    //////SEARCH BAR VIEW/////////
    EditText search_box;
    private List<VerTodoModel> verTodoModelList;
    private  RecyclerView rv_search;
    private VerTodoAdapter verTodoAdapter;

    //RECYCLERVIEW
    RecyclerView popularRec, categoryRec, desayunoRec;

    //FIREBASE
    FirebaseFirestore db;

    //POPULAR ITEMS
    List<PopularModel> popularModelList;
    PopularAdapter popularAdapter;

    //CATEGORY ITEMS
    List<CategoryModel> categoryModelList;
    CategoryAdapter categoryAdapter;

    //DESAYUNO ITEMS
    List<DesayunoModel> desayunoModelList;
    DesayunoAdapter desayunoAdapter;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        db = FirebaseFirestore.getInstance();
        popularRec = root.findViewById(R.id.pop_rec);
        categoryRec = root.findViewById(R.id.cat_rec);
        desayunoRec = root.findViewById(R.id.desayuno_rec);
        scrollView = root.findViewById(R.id.sv_home1);
        home_pb = root.findViewById(R.id.home_pb);

        home_pb.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        //Popular items
        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popularModelList = new ArrayList<>();
        popularAdapter = new PopularAdapter(getActivity(), popularModelList);
        popularRec.setAdapter(popularAdapter);

        db.collection("ProductosPopulares")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularModel popularModel = document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdapter.notifyDataSetChanged();

                                home_pb.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Category items
        categoryRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        categoryModelList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getActivity(), categoryModelList);
        categoryRec.setAdapter(categoryAdapter);

        db.collection("Categorias")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoryModel categoryModel = document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Desayuno items
        desayunoRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        desayunoModelList = new ArrayList<>();
        desayunoAdapter = new DesayunoAdapter(getActivity(), desayunoModelList);
        desayunoRec.setAdapter(desayunoAdapter);

        db.collection("Desayunos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DesayunoModel desayunoModel = document.toObject(DesayunoModel.class);
                                desayunoModelList.add(desayunoModel);
                                desayunoAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //////SEARCH BAR VIEW/////////
        rv_search = root.findViewById(R.id.search_bar_rv);
        search_box = root.findViewById(R.id.search_box);
        verTodoModelList = new ArrayList<>();
        verTodoAdapter = new VerTodoAdapter(getContext(), verTodoModelList);
        rv_search.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_search.setAdapter(verTodoAdapter);
        rv_search.setHasFixedSize(true);
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().isEmpty()){
                    verTodoModelList.clear();
                    verTodoAdapter.notifyDataSetChanged();
                } else {
                    searchProduct(s.toString());
                }
            }
        });


        return root;
    }

    private void searchProduct(String type) {
        if (!type.isEmpty()){
            db.collection("AllProducts").whereEqualTo("tipo", type).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful() && task.getResult() != null){
                                verTodoModelList.clear();
                                verTodoAdapter.notifyDataSetChanged();
                                for (DocumentSnapshot doc : task.getResult().getDocuments()){
                                    VerTodoModel verTodoModel = doc.toObject(VerTodoModel.class);
                                    verTodoModelList.add(verTodoModel);
                                    verTodoAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
    }

}