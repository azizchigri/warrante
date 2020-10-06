package aziz.chigri.warrantev2.view;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import aziz.chigri.warrantev2.R;
import aziz.chigri.warrantev2.adapter.NextExpirationAdapter;
import aziz.chigri.warrantev2.adapter.TagViewPagerAdapter;
import aziz.chigri.warrantev2.model.Bill;
import aziz.chigri.warrantev2.model.TagItem;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<TagItem> tagItems;
    ArrayList<Bill> bills;
    BottomSheetBehavior sheetBehavior;
    NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tagItems = new ArrayList<>();
        tagItems.add(new TagItem("", "Cuisine", R.color.orange_red));
        tagItems.add(new TagItem("", "Salon", R.color.medium_violet_red));
        tagItems.add(new TagItem("", "Gaming", R.color.green));
        tagItems.add(new TagItem("", "Sport", R.color.greenYellow));
        tagItems.add(new TagItem("", "Sport2", R.color.light_sky_blue));
        tagItems.add(new TagItem("", "Sport3", R.color.dark_blue));
        tagItems.add(new TagItem("", "Sport4", R.color.violet));
        tagItems.add(new TagItem("", "Sport5", R.color.orange));
        tagItems.add(new TagItem("", "Sport6", R.color.orange));
        tagItems.add(new TagItem("", "Sport7", R.color.orange));
        tagItems.add(new TagItem("", "Sport8", R.color.orange));
        tagItems.add(new TagItem("", "Sport9", R.color.orange));
        tagItems.add(new TagItem("", "Sport10", R.color.orange));
        tagItems.add(new TagItem("", "Sport11", R.color.orange));
        tagItems.add(new TagItem("", "Sport12", R.color.orange));
        tagItems.add(new TagItem("", "Sport13", R.color.orange));
        tagItems.add(new TagItem("", "Sport14", R.color.orange));
        final ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new TagViewPagerAdapter(getSupportFragmentManager(), tagItems));
        configureBottomSheet();
        configureRecyclerView();
        this.configureToolBar();
        configureDrawerLayout();
        configureNavigationView();
    }

    private void configureToolBar(){
        this.toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    private void configureDrawerLayout() {
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configureBottomSheet(){
        final LinearLayout layoutBottomSheet = findViewById(R.id.bottom_sheet);

        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        //sheetBehavior.setPeekHeight(500);
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        sheetBehavior.setPeekHeight(size.y *2/5); // 40%
        sheetBehavior.setHalfExpandedRatio(0.40f);
        sheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);

        /**
         * bottom sheet state change listener
         * we are changing button text when sheet changed state
         * */
        sheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        sheetBehavior.setState(BottomSheetBehavior.STATE_DRAGGING);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        //btnBottomSheet.setText("Close Sheet");
                        int j = 0;
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        //btnBottomSheet.setText("Expand Sheet");
                        int k = 0;
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        int l = 0;
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        int m = 0;
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
/*        findViewById(R.id.btn_bottom_sheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    btnBottomSheet.setText("Close sheet");
                } else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    btnBottomSheet.setText("Expand sheet");
                }
            }
        });*/
    }

    private void configureRecyclerView(){
        // 3.1 - Reset list
        this.bills = new ArrayList<>();
        bills.add(new Bill("toto"));
        bills.add(new Bill("titi"));
        bills.add(new Bill("titi"));
        bills.add(new Bill("titi"));
        bills.add(new Bill("titi"));
        bills.add(new Bill("titi"));
        bills.add(new Bill("titi"));
        bills.add(new Bill("titi"));
        bills.add(new Bill("titi"));
        bills.add(new Bill("titi"));
        bills.add(new Bill("titi"));
        bills.add(new Bill("titi"));
        bills.add(new Bill("titi"));
        bills.add(new Bill("titi"));
        bills.add(new Bill("titi"));
        bills.add(new Bill("titi"));
        bills.add(new Bill("titi"));
        bills.add(new Bill("titi"));
        // 3.2 - Create adapter passing the list of users
        final NextExpirationAdapter adapter = new NextExpirationAdapter(this.bills);
        // 3.3 - Attach the adapter to the recyclerview to populate items
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        // 3.4 - Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // 4 - Handle Navigation Item Click
        int id = item.getItemId();

        switch (id){
            case R.id.activity_main_bills :
                break;
            case R.id.activity_main_drawer_profile:
                break;
            case R.id.activity_main_drawer_layout:
                break;
            default:
                break;
        }

        return true;
    }

    private void configureNavigationView(){
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}