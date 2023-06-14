package com.example.elperlanegra;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.example.elperlanegra.databinding.ActivityMainBinding;
import com.example.elperlanegra.modelos.UserModel;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private FirebaseAuth authUser;
    private FirebaseUser mUser;
    FirebaseDatabase db;
    Button btn_logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(binding.appBarMain.toolbar);

        authUser = FirebaseAuth.getInstance();
        mUser = authUser.getCurrentUser();
        db = FirebaseDatabase.getInstance();

        btn_logout = findViewById(R.id.btn_logout);
        //CLICK LISTENER PARA MÉTODO CERRAR SESIÓN
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authUser.signOut();
            }
        });

        //CUANDO EL USUARIO CIERRA SESIÓN VUELVE A WELCOME ACTIVITY
        authUser.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null){
                    Toast.makeText(MainActivity.this, "¡SESIÓN CERRADA!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                    finish();
                }
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_perfil, R.id.nav_carrito, R.id.nav_pedidos)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View headerView = navigationView.getHeaderView(0);
        TextView headerName = headerView.findViewById(R.id.nav_header_name);
        TextView headerMail = headerView.findViewById(R.id.nav_header_mail);
        ShapeableImageView headerImg = headerView.findViewById(R.id.nav_header_img);

        db.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel = snapshot.getValue(UserModel.class);

                        headerName.setText(userModel.getNombreAp());
                        headerMail.setText(userModel.getEmail());
                        Glide.with(MainActivity.this).load(userModel.getFotoPerfil()).into(headerImg);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        /*db.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel = snapshot.getValue(UserModel.class);

                        headerName.setText(userModel.getNombreAp());
                        headerMail.setText(userModel.getEmail());
                        Glide.with(MainActivity.this).load(userModel.getFotoPerfil()).into(headerImg);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /*private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.main);

        // Para mostrar el menú debajo de la Toolbar
        popupMenu.show();
    }*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_acerca:
                // Aquí va el código para la opción 1
                Intent intentAcerca = new Intent(getApplicationContext(), AcercaActivity.class);
                startActivity(intentAcerca);
                return true;
            case R.id.action_contactenos:
                // Aquí va el código para la opción 2
                Intent intentContactenos = new Intent(getApplicationContext(), ContactenosActivity.class);
                startActivity(intentContactenos);
                return true;
            case R.id.action_share:
                shareAppLink();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

///////////////////////////////https://play.google.com/store/apps/details?id=
    private void shareAppLink() {
        String appLink = "https://play.google.com/store/apps/details?id=net.comeandsee.thechosen&hl=es_EC&gl=US"; //+ getPackageName();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, appLink);
        startActivity(Intent.createChooser(intent, "Compartir en"));
    }
////////////////////////////////////////////
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}