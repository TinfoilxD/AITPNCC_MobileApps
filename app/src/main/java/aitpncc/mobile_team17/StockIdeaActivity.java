package aitpncc.mobile_team17;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

public class StockIdeaActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {

        return new StockIdeaFragment();
    }
}
