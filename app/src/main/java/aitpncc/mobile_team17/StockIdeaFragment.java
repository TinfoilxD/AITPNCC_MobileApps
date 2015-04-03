package aitpncc.mobile_team17;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StockIdeaFragment extends Fragment
{
    String action;
    StockIdea mStockIdea;
    String url;
    String quote ="0";
    private static final String head = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%3D%22";
    private static final String footer = "%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        action = getActivity().getIntent().getStringExtra("action");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.stock_idea,container,false);

        final TextView datetime_textview = (TextView)v.findViewById(R.id.date_time);
        final TextView stock_sticker_textview = (TextView)v.findViewById(R.id.stock_sticker);
        final TextView description_textview = (TextView)v.findViewById(R.id.description);
        final Button get_quote_button = (Button)v.findViewById(R.id.get_quote);
        Button save_button = (Button) v.findViewById(R.id.save);


        if (action.equals("new"))
        {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            Date today = new Date();
            datetime_textview.setText(sdf.format(today));
            save_button.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View v)
                {
                    StockIdea stockIdea = new StockIdea();
                    stockIdea.setDatetime(datetime_textview.getText().toString());


                    stockIdea.setStockSticker(stock_sticker_textview.getText().toString());
                    stockIdea.setDescription(description_textview.getText().toString());

                    StockIdeaDBHelper stockIdeaDBHelper = new StockIdeaDBHelper(getActivity());
                    try
                    {
                        stockIdeaDBHelper.open();
                        if (stockIdeaDBHelper.insertStockIdea(stockIdea))
                        {
                            Intent i = new Intent(getActivity(),PastListActivity.class);
                            startActivity(i);
                        };
                        stockIdeaDBHelper.close();
                    } catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }
        else if (action.equals("edit"))
        {
            stock_sticker_textview.setEnabled(false);
            datetime_textview.setEnabled(false);
            int stockID = getActivity().getIntent().getIntExtra("stock_id",-1);
            try
            {
                StockIdeaDBHelper stockIdeaDBHelper = new StockIdeaDBHelper(getActivity());
                stockIdeaDBHelper.open();
                mStockIdea = stockIdeaDBHelper.getStockFromID(stockID);
                stockIdeaDBHelper.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            datetime_textview.setText(mStockIdea.getDatetime());
            stock_sticker_textview.setText(mStockIdea.getStockSticker());
            description_textview.setText(mStockIdea.getDescription());
            save_button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    StockIdea stockIdea = new StockIdea();
                    stockIdea.setStockID(mStockIdea.getStockID());
                    stockIdea.setDatetime(datetime_textview.getText().toString());
                    stockIdea.setStockSticker(stock_sticker_textview.getText().toString());
                    stockIdea.setDescription(description_textview.getText().toString());

                    StockIdeaDBHelper stockIdeaDBHelper = new StockIdeaDBHelper(getActivity());
                    try
                    {
                        stockIdeaDBHelper.open();
                        if (stockIdeaDBHelper.updateStockIdeaDescription(stockIdea))
                        {
                            Intent i = new Intent(getActivity(),PastListActivity.class);
                            startActivity(i);
                        };
                        stockIdeaDBHelper.close();
                    } catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
            });

            get_quote_button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    System.out.println(head  + mStockIdea.getStockSticker() + footer);
                    quote = head  + mStockIdea.getStockSticker() + footer;
                    new GetQuote().execute();
                }




            });
        }

        return v;
}
    private class GetQuote extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... params)
        {
            ServiceHandler sh = new ServiceHandler();
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
            System.out.println("return json = " + jsonStr);
            quote = jsonStr;
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            Toast.makeText(getActivity(),"quote = " + quote,Toast.LENGTH_LONG);
        }
    }
}
