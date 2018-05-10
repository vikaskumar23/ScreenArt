package com.dragfoundation.screenart;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;

public class OrderPage extends AppCompatActivity {

    Spinner categorySpinner, paperSpinner, papertype;
    TextView category, weight,type,quantity,pro,wei,typ,qua,file,desc,description, choose;
    Button categoryContinue, weightContinue, typeContinue, quantityContinue, chooseContinue, extraContinue;

    EditText quant,extraDescription;

    SharedPreferences user;
    int quantifier;

    String filename;

    Uri uri;
    int mCategory,mtype,mweight;
    private static final int FILE_SELECT_CODE = 0;
    private int STORAGE_PERMISSION_CODE = 1;

    private boolean datachanged = false;
    String name,username;
    Button upload;

    int flag=0;

    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            datachanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        user = getApplicationContext().getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);

        username = user.getString("ID", null);

        upload=findViewById(R.id.upload);

        quant=findViewById(R.id.edit_quantity);

        category=findViewById(R.id.product_cat);
        weight=findViewById(R.id.product_weight);
        type=findViewById(R.id.product_type);
        quantity=findViewById(R.id.quant);
        choose=findViewById(R.id.choose);
        description=findViewById(R.id.description);

        extraDescription=findViewById(R.id.ext);

        pro=findViewById(R.id.pro);
        wei=findViewById(R.id.wei);
        typ=findViewById(R.id.typ);
        qua=findViewById(R.id.qua);
        file=findViewById(R.id.file);
        desc=findViewById(R.id.desc);



        categoryContinue=findViewById(R.id.categoryContinue);
        weightContinue=findViewById(R.id.weightContinue);
        typeContinue=findViewById(R.id.typeContinue);
        quantityContinue=findViewById(R.id.quantityContinue);
        chooseContinue=findViewById(R.id.fileContinue);
        extraContinue=findViewById(R.id.descContinue);

        category.setEnabled(false);
        weight.setEnabled(false);
        type.setEnabled(false);
        quantity.setEnabled(false);
        choose.setEnabled(false);
        description.setEnabled(false);

        categorySpinner = (Spinner) findViewById(R.id.spinner_product);
        paperSpinner = (Spinner) findViewById(R.id.spinner_paper);
        papertype=(Spinner) findViewById(R.id.spinner_paper1);
        setupSpinner1();
        setupSpinner2();
        setupSpinner3();

        categorySpinner.setOnTouchListener(mOnTouchListener);

        final ExpandableRelativeLayout expandable1 = (ExpandableRelativeLayout) findViewById(R.id.expandable_category);
       expandable1.setExpanded(true);

        final ExpandableRelativeLayout expandable2 = (ExpandableRelativeLayout) findViewById(R.id.expandable_weight);


        final ExpandableRelativeLayout expandable3 = (ExpandableRelativeLayout) findViewById(R.id.expandable_type);
       // expandable3.collapse();

        final ExpandableRelativeLayout expandable4 = (ExpandableRelativeLayout) findViewById(R.id.expandable_quantity);
        final ExpandableRelativeLayout expandable5 = (ExpandableRelativeLayout) findViewById(R.id.expandable_choose);
        final ExpandableRelativeLayout expandable6 = (ExpandableRelativeLayout) findViewById(R.id.expandable_desciption);
       // expandable4.collapse();

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandable1.toggle();
                expandable2.collapse();
                expandable3.collapse();
                expandable4.collapse();
                expandable5.collapse();
                expandable6.collapse();
            }
        });

        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandable2.toggle();
                expandable1.collapse();
                expandable3.collapse();
                expandable4.collapse();
                expandable5.collapse();
                expandable6.collapse();
            }
        });


        type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandable3.toggle();
                expandable1.collapse();
                expandable2.collapse();
                expandable4.collapse();
                expandable5.collapse();
                expandable6.collapse();
            }
        });

        quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandable4.toggle();
                expandable1.collapse();
                expandable2.collapse();
                expandable3.collapse();
                expandable5.collapse();
                expandable6.collapse();
            }
        });

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandable5.toggle();
                expandable1.collapse();
                expandable2.collapse();
                expandable3.collapse();
                expandable4.collapse();
                expandable6.collapse();
            }
        });






        categoryContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mCategory==0)
                {
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "Select Valid Category", Snackbar.LENGTH_LONG)
                            .setActionTextColor(getResources().getColor(android.R.color.holo_blue_bright))
                            .show();
                }else{
                    expandable2.expand();
                    expandable1.collapse();
                    category.setEnabled(true);
                    weight.setEnabled(true);
                    pro.setText("");
                    Drawable d = getResources().getDrawable(R.drawable.circle_complete);
                    pro.setBackground(d);
                }

            }
        });

        weightContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mweight==0)
                {
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "Select Valid Weight", Snackbar.LENGTH_LONG)
                            .setActionTextColor(getResources().getColor(android.R.color.holo_blue_bright))
                            .show();
                }else{
                    expandable2.collapse();
                    type.setEnabled(true);
                    expandable3.expand();
                    wei.setText("");
                    Drawable d = getResources().getDrawable(R.drawable.circle_complete);
                    wei.setBackground(d);
                }



            }
        });

        typeContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mtype==0)
                {
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "Select Valid Type", Snackbar.LENGTH_LONG)
                            .setActionTextColor(getResources().getColor(android.R.color.holo_blue_bright))
                            .show();
                }else{
                    expandable3.collapse();
                    quantity.setEnabled(true);
                    expandable4.expand();
                    typ.setText("");
                    Drawable d = getResources().getDrawable(R.drawable.circle_complete);
                    typ.setBackground(d);
                }


            }
        });


        quantityContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String a=quant.getText().toString().trim();
                if(a.isEmpty())
                {
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "Quantity can't be empty", Snackbar.LENGTH_LONG)
                            .setActionTextColor(getResources().getColor(android.R.color.holo_blue_bright))
                            .show();
                }else{
                    quantifier=Integer.parseInt(a);
                    if(quantifier<=0)
                    {
                        View parentLayout = findViewById(android.R.id.content);
                        Snackbar.make(parentLayout, "Enter Valid Quantity", Snackbar.LENGTH_LONG)
                                .setActionTextColor(getResources().getColor(android.R.color.holo_blue_bright))
                                .show();
                    }else{
                        expandable4.collapse();
                        choose.setEnabled(true);
                        expandable5.expand();
                        qua.setText("");
                        Drawable d = getResources().getDrawable(R.drawable.circle_complete);
                        qua.setBackground(d);
                    }
                }



            }
        });

        chooseContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(uri==null)
                {
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "Please Choose File", Snackbar.LENGTH_LONG)
                            .setActionTextColor(getResources().getColor(android.R.color.holo_blue_bright))
                            .show();
                }else{
                    expandable5.collapse();
                    description.setEnabled(true);
                    expandable6.expand();
                    file.setText("");
                    Drawable d = getResources().getDrawable(R.drawable.circle_complete);
                    file.setBackground(d);
                }



            }
        });

        extraContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitOrder();

            }
        });




        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(OrderPage.this,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    showFileChooser();
                } else {
                    requestStoragePermission();
                }


            }
        });

    }


    void submitOrder()
    {
        long time = System.currentTimeMillis();
        String extra=extraDescription.getText().toString();
        if(extra.isEmpty()){
            extra="No Comments";
        }


        OrderStat order = new OrderStat(0,username,time,quantifier,mCategory,mtype,mweight,filename,extra,null);
        //Toast.makeText(this, order.getFileName(), Toast.LENGTH_SHORT).show();
        Intent i=new Intent(OrderPage.this,Address.class);
       i.putExtra("create",order);
       String p=uri.toString();
        i.putExtra("path",p);
        startActivity(i);
    }

    private void setupSpinner1() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter productCategoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_category_options, android.R.layout.simple_spinner_item);

        categorySpinner.setSelected(true);
        // Specify dropdown layout style - simple list view with 1 item per line
        productCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("Visiting Cards")) {
                        mCategory = 1;
                    } else if (selection.equals("Letter Heads")) {
                        mCategory = 2;
                    } else if (selection.equals("Envelopes")) {
                        mCategory = 3;
                    } else if (selection.equals("Books")) {
                        mCategory = 4;
                    } else if (selection.equals("Marrige Cards")) {
                        mCategory = 5;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mCategory=0;
            }
        });

        // Apply the adapter to the spinner
        categorySpinner.setAdapter(productCategoryAdapter);







    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select File"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }



    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission Required")
                    .setMessage("This permission is needed to choose file from your device")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(OrderPage.this,
                                    new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showFileChooser();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }


 /*   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    uri = data.getData();
                    try {
                        path = FileUtils.getPath(this, uri);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(this, path, Toast.LENGTH_SHORT).show();
                   *//* String a = path.substring(path.lastIndexOf(".") + 1);
                    switch (a) {
                        case "cdr":
                        case "psd":
                        case "jpg":
                        case "png":
                        case "jpeg":
                        case "pdf": name=path.substring(path.lastIndexOf("/") + 1);
                                    upload.setText(name);
                                    flag=1;
                                    break;
                        default:flag=0;
                                path = null;
                                name="NULL";
                                upload.setText("SELECT FILE");
                                Toast.makeText(this, "File is not a valid Designing File", Toast.LENGTH_LONG).show();

                    }*//*

                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_SELECT_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    uri = data.getData();

                    String mimeType = getContentResolver().getType(uri);
                    if (mimeType == null) {
                        String path = FileView.getPath(this, uri);
                        if (path == null) {
                            filename = FileView.getName(uri.toString());
                        } else {
                            File file = new File(path);
                            filename = file.getName();
                        }
                    } else {
                        Uri returnUri = data.getData();
                        Cursor returnCursor = getContentResolver().query(returnUri, null, null, null, null);
                        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                        int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
                        returnCursor.moveToFirst();
                        filename = returnCursor.getString(nameIndex);
                        String size = Long.toString(returnCursor.getLong(sizeIndex));
                    }
                    File fileSave = getExternalFilesDir(null);
                    String sourcePath = getExternalFilesDir(null).toString();
                    try {
                        copyFileStream(new File(sourcePath + "/" + filename), uri,this);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Toast.makeText(this, filename, Toast.LENGTH_SHORT).show();

                String a = filename.substring(filename.lastIndexOf(".") + 1);
                switch (a) {
                    case "cdr":
                    case "psd":
                    case "jpg":
                    case "png":
                    case "jpeg":
                    case "pdf": name=filename.substring(filename.lastIndexOf("/") + 1);
                        upload.setText(name);
                        upload.setEnabled(false);
                        flag=1;
                        break;
                    default:flag=0;
                        uri = null;
                        name="NULL";
                        upload.setText("SELECT FILE");
                        Toast.makeText(this, "File is not a valid Designing File", Toast.LENGTH_LONG).show();

                }


            }
        }
    }


    private void copyFileStream(File dest, Uri uri, Context context)
            throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = context.getContentResolver().openInputStream(uri);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            is.close();
            os.close();
        }
    }









    private void setupSpinner2() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter paperCategoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_paper_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        paperCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        paperSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("100 gsm")) {
                        mweight = 1;
                    } else if (selection.equals("130 gsm")) {
                        mweight = 2;
                    } else if (selection.equals("170 gsm")) {
                        mweight = 3;
                    } else if (selection.equals("250 gsm")) {
                        mweight = 4;
                    } else if (selection.equals("270 gsm")) {
                        mweight = 5;
                    }else if (selection.equals("300 gsm")) {
                        mweight = 6;
                      }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mweight=0;
            }
        });

        // Apply the adapter to the spinner
        paperSpinner.setAdapter(paperCategoryAdapter);


    }


    private void setupSpinner3() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter paperCategoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_paper_type, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        paperCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        papertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("Gloss")) {
                        mtype = 1;
                    } else if (selection.equals("Matt")) {
                        mtype = 2;
                    } else if (selection.equals("Gumming")) {
                        mtype = 3;
                    } else if (selection.equals("PVC")) {
                        mtype = 4;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                        mtype=0;
            }
        });

        papertype.setAdapter(paperCategoryAdapter);


    }


    @Override
    public void onBackPressed() {
        if (!datachanged) {
            super.onBackPressed();
            return;
        }

        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };

        showUnsavedChangesDialog(discardButtonClickListener);


    }


    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        // Create an AlertDialog.Builder and set the messageright, and click listeners
        // for the postive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Discard your changes and quit ordering?");
        builder.setPositiveButton("Discard", discardButtonClickListener);
        builder.setNegativeButton("Keep Going", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
