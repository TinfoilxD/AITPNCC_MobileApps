package aitpncc.mobile_team17;


import android.support.v4.app.Fragment;

public class PastListActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {
        return new PastListFragment();
    }
}
