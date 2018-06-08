package at.htl_villach.docker2go;

import android.support.design.widget.TabLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class OverviewActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private Connection curConnection;

    private static TabInformation infoTab;
    private static TabContainers containersTab;
    private static TabImages imagesTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        // get intent data if item was clicked
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Integer position = extras.getInt(ConnectionActivity.KEY_POSITION, 0);
            curConnection = Connection.listAll(Connection.class).get(position);

            getSupportActionBar().setTitle(curConnection.getUsername() + "@" + curConnection.getHostname());
        }else{
            Snackbar.make(findViewById(R.id.constraintLayout), "Something went wrong", Snackbar.LENGTH_LONG);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.actionDisconnect) {
            // TODO close connection
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_overview, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends PagerAdapter {

        FragmentManager fragmentManager;
        Fragment[] fragments;

        public SectionsPagerAdapter(FragmentManager fm){
            fragmentManager = fm;
            fragments = new Fragment[3];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            assert(0 <= position && position < fragments.length);
            FragmentTransaction trans = fragmentManager.beginTransaction();
            trans.remove(fragments[position]);
            trans.commit();
            fragments[position] = null;
        }

        @Override
        public Fragment instantiateItem(ViewGroup container, int position){
            Fragment fragment = getItem(position);
            FragmentTransaction trans = fragmentManager.beginTransaction();
            trans.add(container.getId(),fragment,"fragment:"+position);
            trans.commit();
            return fragment;
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object fragment) {
            return ((Fragment) fragment).getView() == view;
        }

        public Fragment getItem(int position){
            assert(0 <= position && position < fragments.length);
            if(fragments[position] == null){
                switch (position) {
                    case 0:
                        if(infoTab == null) {
                            infoTab = new TabInformation();
                            Bundle arguments = new Bundle();
                            arguments.putInt(ConnectionActivity.KEY_POSITION, getIntent().getIntExtra(ConnectionActivity.KEY_POSITION, 0));
                            infoTab.setArguments(arguments);
                            //infoTab.setRetainInstance(true);
                        }

                        return infoTab;
                    case 1:
                        if(containersTab == null) {
                            containersTab = new TabContainers();
                            Bundle arguments = new Bundle();
                            arguments.putInt(ConnectionActivity.KEY_POSITION, getIntent().getIntExtra(ConnectionActivity.KEY_POSITION, 0));
                            containersTab.setArguments(arguments);
                            //infoTab.setRetainInstance(true);
                        }

                        return containersTab;
                    case 2:
                        if(imagesTab == null) {
                            imagesTab = new TabImages();
                            Bundle arguments = new Bundle();
                            arguments.putInt(ConnectionActivity.KEY_POSITION, getIntent().getIntExtra(ConnectionActivity.KEY_POSITION, 0));
                            imagesTab.setArguments(arguments);
                            //infoTab.setRetainInstance(true);
                        }

                        return imagesTab;
                    default:
                        return PlaceholderFragment.newInstance(position + 1);
                }
            }
            return fragments[position];
        }
    }

        /*public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    if(infoTab == null) {
                        infoTab = new TabInformation();
                        Bundle arguments = new Bundle();
                        arguments.putInt(ConnectionActivity.KEY_POSITION, getIntent().getIntExtra(ConnectionActivity.KEY_POSITION, 0));
                        infoTab.setArguments(arguments);
                        infoTab.setRetainInstance(true);
                    }

                    return infoTab;
                case 1:
                    if(containersTab == null) {
                        containersTab = new TabContainers();
                        Bundle arguments = new Bundle();
                        arguments.putInt(ConnectionActivity.KEY_POSITION, getIntent().getIntExtra(ConnectionActivity.KEY_POSITION, 0));
                        containersTab.setArguments(arguments);
                        infoTab.setRetainInstance(true);
                    }

                    return containersTab;
                case 2:
                    if(imagesTab == null) {
                        imagesTab = new TabImages();
                        Bundle arguments = new Bundle();
                        arguments.putInt(ConnectionActivity.KEY_POSITION, getIntent().getIntExtra(ConnectionActivity.KEY_POSITION, 0));
                        imagesTab.setArguments(arguments);
                        infoTab.setRetainInstance(true);
                    }

                    return imagesTab;
                default:
                    return PlaceholderFragment.newInstance(position + 1);
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }*/
}
