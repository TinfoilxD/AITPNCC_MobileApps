package aitpncc.mobile_team17;


import android.support.v4.app.Fragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * MainScreenActivity hosts MainScreenFragment and copies the database from app to android databases
 *
 */
public class MainScreenActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {
        copyDataBase("team17db");
        return new MainScreenFragment();
    }

    private void copyDataBase(String dbname)  {
        try {

            // Open your local db as the input stream
            InputStream myInput = getAssets().open(dbname);


            final File dir = new File(getApplicationInfo().dataDir + "/databases");
            dir.mkdirs(); //create folders where write files

            final File db = new File(dir, dbname);

            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(db);
            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
            System.out.println("Database copied");


        }

        catch(Exception e)
        {
            System.out.println("Error in copyDatabase " + e);
            e.printStackTrace();
        }
    }
}
