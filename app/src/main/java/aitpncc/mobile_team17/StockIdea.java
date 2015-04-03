package aitpncc.mobile_team17;

/**

 */
public class StockIdea
{
    public static String TABLE_NAME = "StockIdea";
    public static String COLUMN_STOCK_ID = "stock_id";
    public static String COLUMN_DATE_TIME = "datetime";
    public static String COLUMN_STOCK_STICKER = "stock_sticker";
    public static String COLUMN_DESCRIPTION = "description";

    private int stockID;
    private String datetime;
    private String stockSticker;
    private String description;

    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getStockSticker() {
        return stockSticker;
    }

    public void setStockSticker(String stockSticker) {
        this.stockSticker = stockSticker;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
