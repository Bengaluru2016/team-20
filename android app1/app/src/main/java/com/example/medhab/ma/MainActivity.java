package com.example.medhab.ma;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.apache.http.NameValuePair;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class MainActivity extends ActionBarActivity {


    EditText d;
    Calendar myCalendar = Calendar.getInstance();
    private static final int REQUEST_CAMERA = 1;
    private static final int SELECT_FILE = 1;
    String id,name,gender,age,language,location,occupation,dob,contactNumber,idProof,parentIncome,fatherName,motherName,address,alternateContact,nativePlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        d=(EditText) findViewById(R.id.dDOB);
        d.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Button b=(Button)findViewById(R.id.SelectPhoto);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();

            }
        });



    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void connect(View v) throws IOException {
        EditText a = (EditText) findViewById(R.id.dID);
        EditText c = (EditText) findViewById(R.id.dName);
        RadioGroup r=(RadioGroup) findViewById(R.id.radioGender);
        int ind= r.getCheckedRadioButtonId();
        RadioButton rb=(RadioButton) findViewById(ind);
        EditText d = (EditText) findViewById(R.id.dAge);
        RadioGroup r1=(RadioGroup) findViewById(R.id.radioLanguage);
        int ind1= r1.getCheckedRadioButtonId();
        RadioButton rb1=(RadioButton) findViewById(ind1);
        EditText e = (EditText) findViewById(R.id.dOcc);
        EditText f = (EditText) findViewById(R.id.dDOB);
        EditText g = (EditText) findViewById(R.id.dPno);
        EditText h = (EditText) findViewById(R.id.dIDprf);
        EditText i = (EditText) findViewById(R.id.dPinc);
        EditText j = (EditText) findViewById(R.id.dMName);
        EditText k = (EditText) findViewById(R.id.dFName);
        EditText l = (EditText) findViewById(R.id.dAddress);
        EditText m = (EditText) findViewById(R.id.dNplc);
        EditText n = (EditText) findViewById(R.id.dAltcno);
        EditText o=(EditText) findViewById(R.id.dLocation);

        id = a.getText().toString();
        name = c.getText().toString();
        gender = rb.getText().toString();
        age=d.getText().toString();
        language=rb1.getText().toString();
        location=o.getText().toString();
        occupation=e.getText().toString();
        dob=f.getText().toString();
        contactNumber=g.getText().toString();
        idProof=h.getText().toString();
        fatherName=k.getText().toString();
        motherName=j.getText().toString();
        parentIncome=i.getText().toString();
        alternateContact=n.getText().toString();
        address=l.getText().toString();
        nativePlace=m.getText().toString();

        String urlParameters  = "id=a&name=c&gender=rb&age=d&language=rb1&location=0&occupation=e&dob=f&contactNumber=g&idProof=h&parentIncome=i&fatherName=k&motherName=j&address=l&alternateContact=n&nativePlace=m";
        final byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
        int    postDataLength = postData.length;
        String request        = "http://52.221.254.93:8080/api/v1/student/";
        URL url            = new URL( request );
        final HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        conn.setDoOutput( true );
        conn.setInstanceFollowRedirects( false );
        conn.setRequestMethod( "POST" );
        conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty( "charset", "utf-8");
        conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
        conn.setUseCaches( false );
        Thread nThread = new Thread() {
            public void run() {
                try(

                        DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
                    wr.write( postData );
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        };
        nThread.start();
//        try {
//
//            URL url = new URL("http://xtramile2k16/api/b1/student");
//            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setDoOutput(true);
//
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("firstParam", paramValue1));
//           // params.add(new BasicNameValuePair("secondParam", paramValue2));
//           // params.add(new BasicNameValuePair("thirdParam", paramValue3));
//
//            OutputStream os = conn.getOutputStream();
//            BufferedWriter writer = new BufferedWriter(
//                    new OutputStreamWriter(os, "UTF-8"));
//            writer.write(getQuery(params));
//            writer.flush();
//            writer.close();
//            os.close();
//
//            conn.connect();
//
//        } catch (MalformedURLException e) {}
//        catch (IOException e){}


    }

    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }


    private void selectImage() {
        final CharSequence[] items = { "Take Photo","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                }  else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            ImageView Im=(ImageView)findViewById(R.id.Image);
            if (requestCode == REQUEST_CAMERA) {

                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                Im.setImageBitmap(thumbnail);
                Log.d("done","done");

            }
            else
            {
                Log.d("undone","undone");
            }
        }
        else
        {
            Log.d("undone","undone");
        }



    }




DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };


    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        d.setText(sdf.format(myCalendar.getTime()));
    }

}




