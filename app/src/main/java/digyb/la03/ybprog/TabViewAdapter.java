package digyb.la03.ybprog;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by reksa on 17/12/2017.
 */

public class TabViewAdapter extends FragmentPagerAdapter {


    public TabViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0){
            fragment = new GenerateCodeFragment();
        } else if(position == 1){
            fragment = new ViewScoreFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position){
        String title = null;
        if (position == 0)
        {
            title = "Generate Code";
        }
        else if (position == 1)
        {
            title = "View Score";
        }
        return title;
    }
}