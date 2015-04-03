package aitpncc.mobile_team17;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PastListFragment extends Fragment
{
    StockIdeaListAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.stock_idea_list,container,false);
        final ListView listView =(ListView)v.findViewById(R.id.stock_idea_listview);
        ArrayList<StockIdea> stockIdeas = new ArrayList<>();
        StockIdeaDBHelper stockIdeaDBHelper = new StockIdeaDBHelper(getActivity());
        try
        {
            stockIdeaDBHelper.open();
            stockIdeas = stockIdeaDBHelper.getAll();
            stockIdeaDBHelper.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        adapter = new StockIdeaListAdapter(stockIdeas);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

                StockIdea stockIdea=((StockIdea)listView.getAdapter().getItem(position));
                Intent i = new Intent(getActivity(),StockIdeaActivity.class);

                i.putExtra("action","edit");
                i.putExtra("stock_id",stockIdea.getStockID());
                startActivity(i);
            }
        });
        return v;


    }
    private class StockIdeaListAdapter extends ArrayAdapter<StockIdea>
    {
        private int selectedItem = -1;
        public StockIdeaListAdapter(ArrayList<StockIdea> stockIdeas) {
            super(getActivity(), 0, stockIdeas);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            if (convertView == null)
            {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.stock_idea_item_list, null);
            }
            //display different color in odd and even rows

            StockIdea stockIdea = getItem(position);

            //display customer id
            TextView datetime_textview = (TextView) convertView.findViewById(R.id.date_time);
            datetime_textview.setText(stockIdea.getDatetime());

            //display customer name
            TextView stock_sticker_textview = (TextView) convertView.findViewById(R.id.stock_sticker);
            stock_sticker_textview.setText(stockIdea.getStockSticker());

            //display frequency
            TextView description_textview = (TextView) convertView.findViewById(R.id.description);
            description_textview.setText(stockIdea.getDescription());

            return convertView;
        }
    }
}
