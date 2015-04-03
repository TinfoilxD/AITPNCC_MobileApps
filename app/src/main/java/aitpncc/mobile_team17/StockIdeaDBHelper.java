package aitpncc.mobile_team17;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

/**

 */
public class StockIdeaDBHelper
{
    private SQLiteDatabase database;
    private DatabaseHandler dbHelper;

    public StockIdeaDBHelper(Context context)
    {
        dbHelper = new DatabaseHandler(context);
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close() throws SQLException
    {
        dbHelper.close();
    }

    public ArrayList<StockIdea> getAll()
    {
        ArrayList<StockIdea> stockIdeas = new ArrayList<StockIdea>();
        String selectQuery = "SELECT * FROM " + StockIdea.TABLE_NAME;
        try
        {
            Cursor cursor = database.rawQuery(selectQuery,null);
            if (cursor.moveToFirst())
            {
                do
                {
                    stockIdeas.add(cursorToStockIdea(cursor));
                }
                while
                        (cursor.moveToNext());
            }
        }
        catch (Exception e)
        {
            System.out.println("Error in getAll " + e);
            e.printStackTrace();
        }
        return stockIdeas;
    }

    private StockIdea cursorToStockIdea(Cursor cursor)
    {
        StockIdea stockIdea = new StockIdea();
        stockIdea.setDatetime(cursor.getString(cursor.getColumnIndex(StockIdea.COLUMN_DATE_TIME)));
        stockIdea.setDescription(cursor.getString(cursor.getColumnIndex(StockIdea.COLUMN_DESCRIPTION)));
        stockIdea.setStockID(cursor.getInt(cursor.getColumnIndex(StockIdea.COLUMN_STOCK_ID)));
        stockIdea.setStockSticker(cursor.getString(cursor.getColumnIndex(StockIdea.COLUMN_STOCK_STICKER)));
        return  stockIdea;
    }

    public StockIdea getStockFromID(int stockID)
    {
        StockIdea stockIdea = new StockIdea();
        String selectQuery = "SELECT * FROM " + StockIdea.TABLE_NAME + " WHERE " + StockIdea.COLUMN_STOCK_ID + " = " + Integer.toString(stockID);
        try
        {
            Cursor cursor = database.rawQuery(selectQuery, null);
            cursor.moveToFirst();
            stockIdea = cursorToStockIdea(cursor);
        }

        catch (Exception e)

        {
            e.printStackTrace();
        }
        return stockIdea;
    }

    public boolean insertStockIdea(StockIdea stockIdea)
    {
        boolean success = false;
        ContentValues cv = new ContentValues();
        try
        {
            cv.put(StockIdea.COLUMN_DATE_TIME, stockIdea.getDatetime());
            cv.put(StockIdea.COLUMN_DESCRIPTION, stockIdea.getDescription());
            cv.put(StockIdea.COLUMN_STOCK_STICKER, stockIdea.getStockSticker());
            if (database.insert(StockIdea.TABLE_NAME, null, cv)>0)
            {
                success = true;
            }
        }
        catch( Exception e)
        {
            e.printStackTrace();
        }
        return success;
    }

    public boolean updateStockIdeaDescription(StockIdea stockIdea)
    {
        boolean success = false;
        ContentValues cv = new ContentValues();
        int stockID = stockIdea.getStockID();
        System.out.println("stockID = " + stockID);
        try
        {
            cv.put(StockIdea.COLUMN_DESCRIPTION, stockIdea.getDescription());
            if(database.update(StockIdea.TABLE_NAME,cv,StockIdea.COLUMN_STOCK_ID + " = " + stockID ,null)>0)
            {
                success = true;
            };
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return success;
    }
}
