package com.example.srivikashini.navigated;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;

public class addKid extends AppCompatActivity {
    EditText name, dob, regNo, classsec;
    String names, regNos, dobs,clasec;
    Button next;
    ImageButton propic;
    String image_str;
    private static final String TAG = "your activity name";
    Button datefixs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kid_main);
        name = (EditText) findViewById(R.id.editText);
        regNo = (EditText) findViewById(R.id.editText2);
        classsec = (EditText) findViewById(R.id.editText8);
        next = (Button) findViewById(R.id.button);
        propic = (ImageButton) findViewById(R.id.imageButton);
        datefixs = (Button)findViewById(R.id.button2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                names = name.getText().toString();
                regNos = regNo.getText().toString();
                dobs = datefixs.getText().toString();
                clasec = classsec.getText().toString();
                if (names.equals("") || regNos.equals("") || dobs.equals("")) {
                    Toast.makeText(addKid.this, "Enter the details", Toast.LENGTH_LONG).show();

                } else {

                    Intent mIntent = new Intent(addKid.this, kidscladdr.class);
                    BitmapDrawable drawable = (BitmapDrawable) propic.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();


                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    mIntent.putExtra("kidImage", byteArray);
                    mIntent.putExtra("kname", names);
                    mIntent.putExtra("kregno", regNos);
                    mIntent.putExtra("kclas",clasec);
                    mIntent.putExtra("kdob", dobs);
                    startActivity(mIntent);
                }
            }

        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri targetUri = data.getData();

            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
//                ByteArrayOutputStream boas = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, boas ); //bm is the bitmap object
//                byte[] byteArrayImage = boas .toByteArray();
//                String encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
                propic.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    void picSelect(View v)
    {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 0);
    }
    void selecdat(View vi){

            DatePickerDialog datePickerDialog;


                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(addKid.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                datefixs.setText(year + "-"
                                        + (monthOfYear + 1) + "-" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

    }

}