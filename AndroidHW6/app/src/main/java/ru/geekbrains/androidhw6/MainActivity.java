package ru.geekbrains.androidhw6;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import ru.geekbrains.androidhw6.fragments.AboutFragment;
import ru.geekbrains.androidhw6.fragments.NotesMainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotesMainFragment notesMainFragment = new NotesMainFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, notesMainFragment)
                .commit();

        initToolbar();
    }

    void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initDrawer(toolbar);
    }

    void initDrawer(Toolbar toolbar) {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView view = findViewById(R.id.nav_view);
        view.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.action_about:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .addToBackStack("")
                            .replace(R.id.main_container, new AboutFragment());
                    return true;
                case R.id.action_exit:
                    finish();
                    return true;
            }
            return false;
        });
    }
}