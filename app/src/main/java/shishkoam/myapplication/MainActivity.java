package shishkoam.myapplication;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText txtData;
    private Context ctx;
    public static final String APP_DIR = "Army/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ctx = this;
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView text = (TextView) findViewById(R.id.text_view);

        try {
            text.setText(text.getText().toString() + '\n' + "Path with package to ExtSd: " + getBaseContext().getExternalFilesDir(null).getPath() );
        }catch (NullPointerException e){}

        try {
            text.setText(text.getText().toString() + '\n' + "\"Path to Ext_Sd: " + getExternalStorageDirectory().getAbsolutePath());
        }catch (NullPointerException e){}

        try {
            text.setText(text.getText().toString() + '\n' + "Path with package to Ext_Sd that not internal: " +getSecondaryStorage().getAbsolutePath());
        }catch (NullPointerException e){}


        txtData =(EditText) findViewById(R.id.edit_text_view);

        Button btnWriteSDFile = (Button) findViewById(R.id.write);
        btnWriteSDFile.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                try {
//                    File myFile = new File("/sdcard/mysdfile.txt");
//                    File myFile = new File(Environment.getExternalStorageDirectory()+"/mysdfile.txt");
//                    File myFile = new File("/mnt/sdcard/mysdfile.txt");

                    File myFile = new File(getSecondaryStorage().getAbsolutePath() + "/mysdfile.txt");

                    myFile.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
                    myOutWriter.append(txtData.getText());
                    myOutWriter.close();
                    fOut.close();
                    Toast.makeText(v.getContext(),"Done writing SD 'mysdfile.txt'", Toast.LENGTH_SHORT).show();
                    txtData.setText("");
                }
                catch (Exception e)
                {
                    Toast.makeText(v.getContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }


        });

        Button btnReadSDFile = (Button) findViewById(R.id.read);
        btnReadSDFile.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                try {
                    File myFile = new File(getSecondaryStorage().getAbsolutePath() +"/mysdfile.txt");
//                    File myFile = new File("/mnt/sdcard2/Android/data/shishkoam.myapplication/files/mysdfile.txt");

                    FileInputStream fIn = new FileInputStream(myFile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null)
                    {
                        aBuffer += aDataRow ;
                    }
                    txtData.setText(aBuffer);
                    myReader.close();
                    Toast.makeText(v.getContext(),"Done reading SD 'mysdfile.txt'",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnWriteSDFile2 = (Button) findViewById(R.id.write2);
        btnWriteSDFile2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                try {
                    File myFile = new File("/mnt/sdcard2/Android/data/shishkoam.myapplication/files/mysdfile.txt");


                    myFile.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
                    myOutWriter.append(txtData.getText());
                    myOutWriter.close();
                    fOut.close();
                    Toast.makeText(v.getContext(),"Done writing SD 'mysdfile.txt'", Toast.LENGTH_SHORT).show();
                    txtData.setText("");
                }
                catch (Exception e)
                {
                    Toast.makeText(v.getContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }


        });

        Button btnReadSDFile2 = (Button) findViewById(R.id.read2);
        btnReadSDFile2.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                try {
                    File myFile = new File("/mnt/sdcard2/Android/data/shishkoam.myapplication/files/mysdfile.txt");

                    FileInputStream fIn = new FileInputStream(myFile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null)
                    {
                        aBuffer += aDataRow ;
                    }
                    txtData.setText(aBuffer);
                    myReader.close();
                    Toast.makeText(v.getContext(),"Done reading SD 'mysdfile.txt'",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int getNumberOfFiles(File dir){
        try {
            return dir.listFiles().length;
        }catch (NullPointerException e){
            return -1;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public File getExternalStorageDirectory() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return getExternalStorageDirectoryPre19();
        } else {
            return getExternalStorageDirectoryV19();
        }
    }

    @TargetApi(19)
    public File getInternalAppPath() {
        if(Build.VERSION.SDK_INT >= 21) {
            File fl = getNoBackupPath();
            if(fl != null) {
                return fl;
            }
        }
        return ctx.getFilesDir();
    }

    @TargetApi(21)
    private File getNoBackupPath() {
        return ctx.getNoBackupFilesDir();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public File getExternalStorageDirectoryV19() {
        File location = getDefaultLocationV19();
            if(isWritable(location)) {
                return location;
            }
            File[] external = ctx.getExternalFilesDirs(null);
            if(external != null && external.length > 0 && external[0] != null) {
                location = external[0];
            } else {
                File[] obbDirs = ctx.getObbDirs();
                if(obbDirs != null && obbDirs.length > 0 && obbDirs[0] != null) {
                    location = obbDirs[0];
                } else {
                    location = getInternalAppPath();
                }
            }
        return location;
    }

    public File getDefaultLocationV19() {
        String location = getExternalStorageDirectoryPre19().getAbsolutePath();
        return new File(location);
    }


    public static boolean isWritable(File dirToTest) {
        boolean isWriteable = false;
        try {
            dirToTest.mkdirs();
            File writeTestFile = File.createTempFile("osmand_", ".tmp", dirToTest);
            isWriteable = writeTestFile.exists();
            writeTestFile.delete();
        } catch (IOException e) {
            isWriteable = false;
        }
        return isWriteable;
    }

    public File getExternalStorageDirectoryPre19() {
        String defaultLocation = Environment.getExternalStorageDirectory().getAbsolutePath();
        File rootFolder = new File(defaultLocation);
        return new File(rootFolder, APP_DIR);
    }

    public File getDefaultInternalStorage() {
        return new File(Environment.getExternalStorageDirectory(), APP_DIR);
    }

    @SuppressLint("NewApi")
    @Nullable
    public File getSecondaryStorage() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return getExternalStorageDirectoryPre19();
        } else {
            File[] externals = ctx.getExternalFilesDirs(null);
            for (File file : externals) {
                if (file != null && !file.getAbsolutePath().contains("emulated")) {
                    return file;
                }
            }
        }
        return null;
    }

}
