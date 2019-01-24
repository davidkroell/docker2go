package at.htl_villach.docker2go;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;


public class OverviewActivity extends AppCompatActivity implements Connection.onCommandStatusChangeListener {

    public TabInformation infoTab;
    public TabContainers containersTab;
    public TabImages imagesTab;
    public ProgressBar loadingIndicator;
    public Connection activeConnection;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_experiment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        loadingIndicator = findViewById(R.id.loadingIndicator);
        loadingIndicator.setVisibility(View.VISIBLE);

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
            Long position = extras.getLong(ConnectionActivity.KEY_CONN_ID, 0);
            activeConnection = Connection.findById(Connection.class, position);

            // increase times connected
            activeConnection.increaseTimesConnected();
            activeConnection.save();

            getSupportActionBar().setTitle(activeConnection.getUsername() + "@" + activeConnection.getHostname());
        } else {
            Snackbar.make(findViewById(R.id.constraintLayout), "Something went wrong", Snackbar.LENGTH_LONG);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        int currentTab = mViewPager.getCurrentItem();

        menu.clear();

        // inflate base menu
        inflater.inflate(R.menu.menu_overview, menu);
        switch (currentTab) {
            case 0:
                inflater.inflate(R.menu.menu_tab_info, menu);
                break;
            case 1:
                inflater.inflate(R.menu.menu_tab_containers, menu);
                break;
            case 2:
                inflater.inflate(R.menu.menu_tab_images, menu);
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.actionDisconnect:
                finish();
                break;
            case R.id.actionPruneImages:
                pruneImages();
                break;
            case R.id.actionPullImage:
                pullImage();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void pullImage() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();

        dialog.setTitle("Pull Image");
        dialog.setMessage(getResources().getString(R.string.pull_image_info));

        // inflate custom view in alertDialog
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_pull_image, null);
        dialog.setView(dialogView);

        AlertDialog.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String imgAndTag = ((EditText) dialogView.findViewById(R.id.editTextPullImage)).getText().toString();

                imgAndTag = "fromImage=" + imgAndTag;
                
                if (!imgAndTag.contains(":"))
                    imgAndTag += ":latest";

                try {
                    DockerCommandBuilder command = new DockerCommandBuilder()
                            .requestMethod("POST")
                            .apiEndpoint("/images/create")
                            .requestBody(imgAndTag);

                    activeConnection.executeCommand(OverviewActivity.this, command);
                    loadingIndicator.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    Snackbar.make(imagesTab.swipeRefreshLayout, e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        };

        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Pull", listener);
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Snackbar.make(imagesTab.swipeRefreshLayout,

                        "Image pull cancelled",
                        Snackbar.LENGTH_LONG).show();
            }
        });

        dialog.show();
    }

    private void pruneImages() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();

        AlertDialog.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DockerCommandBuilder command = new DockerCommandBuilder()
                        .requestMethod("POST")
                        .apiEndpoint("/images/prune");

                activeConnection.executeCommand(OverviewActivity.this, command);
                loadingIndicator.setVisibility(View.VISIBLE);
            }
        };

        dialog.setTitle("Warning!");
        dialog.setMessage("By proceeding, every untagged image will be removed.\nAre you sure?");
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", listener);
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Snackbar.make(imagesTab.swipeRefreshLayout,
                        "Image prune cancelled",
                        Snackbar.LENGTH_LONG).show();
            }
        });
        dialog.show();
    }

    @Override
    public void onCommandFinished(Command command) {
        loadingIndicator.setVisibility(View.GONE);
        if (command.exitedAsExpected())
            if (command.getApiEndpoint().equals("/images/prune"))
                Snackbar.make(imagesTab.swipeRefreshLayout,
                        "Successfully removed untagged images",
                        Snackbar.LENGTH_LONG).show();
            else if (command.getApiEndpoint().equals("/images/create"))
                Snackbar.make(imagesTab.swipeRefreshLayout,
                        "Successfully pulled image",
                        Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onAllCommandsFinished(CommandExecutionSummary ces) {
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

        public SectionsPagerAdapter(FragmentManager fm) {
            fragmentManager = fm;
            fragments = new Fragment[3];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            assert (0 <= position && position < fragments.length);
            FragmentTransaction trans = fragmentManager.beginTransaction();
            trans.remove(fragments[position]);
            trans.commit();
            fragments[position] = null;
        }

        @Override
        public Fragment instantiateItem(ViewGroup container, int position) {
            Fragment fragment = getItem(position);
            FragmentTransaction trans = fragmentManager.beginTransaction();
            trans.add(container.getId(), fragment, "fragment:" + position);
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

        public Fragment getItem(int position) {
            assert (0 <= position && position < fragments.length);
            if (fragments[position] == null) {
                switch (position) {
                    case 0:
                        if (infoTab == null) {
                            infoTab = new TabInformation();
                            Bundle arguments = new Bundle();
                            arguments.putInt(ConnectionActivity.KEY_CONN_ID, getIntent().getIntExtra(ConnectionActivity.KEY_CONN_ID, 0));
                            infoTab.setArguments(arguments);
                            infoTab.setRetainInstance(true);
                        }

                        return infoTab;
                    case 1:
                        if (containersTab == null) {
                            containersTab = new TabContainers();
                            Bundle arguments = new Bundle();
                            arguments.putInt(ConnectionActivity.KEY_CONN_ID, getIntent().getIntExtra(ConnectionActivity.KEY_CONN_ID, 0));
                            containersTab.setArguments(arguments);
                            containersTab.setRetainInstance(true);
                        }

                        return containersTab;
                    case 2:
                        if (imagesTab == null) {
                            imagesTab = new TabImages();
                            Bundle arguments = new Bundle();
                            arguments.putInt(ConnectionActivity.KEY_CONN_ID, getIntent().getIntExtra(ConnectionActivity.KEY_CONN_ID, 0));
                            imagesTab.setArguments(arguments);
                            imagesTab.setRetainInstance(true);
                        }

                        return imagesTab;
                    default:
                        return PlaceholderFragment.newInstance(position + 1);
                }
            }
            return fragments[position];
        }
    }
}
