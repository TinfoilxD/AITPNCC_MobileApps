package aitpncc.mobile_team17;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * MainscreenFragment creates mainscreen fragment which contains the Add Stock Idea Button and the Past Stock Ideas button
 */

public class MainScreenFragment extends Fragment
{
    private Button mAddButton;
    private Button mPastButton;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_mainscreen,container,false);
        mAddButton = (Button)v.findViewById(R.id.button_addStockButton);
        mAddButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity(),StockIdeaActivity.class);
                i.putExtra("action", "new");
                getActivity().startActivity(i);
            }
        });
        mPastButton = (Button)v.findViewById(R.id.button_pastStockButton);
        mPastButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity(),PastListActivity.class);

                getActivity().startActivity(i);
            }
        });
        return v;
    }
}
