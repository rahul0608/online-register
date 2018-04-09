package com.example.rahulverma.mobilebasedattendencesystem;

/**
 * Created by Rahulverma on 09/09/17.
 */

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;






public class ListAdapter extends ArrayAdapter<studentdetails> {


    private List<studentdetails> details;
    ArrayList<String> selectedStrings = new ArrayList<String>();
    StringBuilder checkedlist;
    JSONArray jarray;
    JSONObject j= new JSONObject();

    public ListAdapter(Context context, int resource, List<studentdetails> details,StringBuilder checkedlist,JSONArray j) {
        super(context, resource, details);
        this.details = details;
        this.checkedlist=checkedlist;
        this.jarray=j;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View v = convertView;

           /* TextView tt = null;
            TextView tt1 = null;
            TextView tt2 = null;
            TextView tt3 = null;*/
        TextView tt4 = null;
        CheckBox ch = null;

        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.studentlistrow, null);

            ch = (CheckBox) v.findViewById(R.id.checkbox);


                /*tt = (TextView) v.findViewById(R.id.age);
                tt1 = (TextView) v.findViewById(R.id.name);
                tt2 = (TextView) v.findViewById(R.id.city);
                tt3 = (TextView) v.findViewById(R.id.gender);*/
            tt4 = (TextView) v.findViewById(R.id.enroll);
        }

        studentdetails p = details.get(position);

        if (p != null) {

            if (ch != null) {
                ch.setText("" + p.getName());

            }
            if (tt4 != null) {
                tt4.setText("" + p.getEnroll_no());
            }


        }

        {
            ViewHolder holder;
            Log.v("ConvertView", String.valueOf(position));


            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.activity_markattendance, null);

            holder = new ViewHolder();

            holder.cb = ch;
            convertView.setTag(holder);


            holder.cb.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    CheckBox cb = (CheckBox) v;
                    studentdetails studentdetails = (studentdetails) cb.getTag();



                    if (cb.isChecked() == true  ) {
                        if(checkedlist.toString().contains(cb.getText().toString())==false) {
                            checkedlist.append(cb.getText() + ",");
                            Toast.makeText(getContext(), "Mark present:  " + cb.getText(), Toast.LENGTH_SHORT).show();
                            j=new JSONObject();

                            jarray.put(j);
                            try {
                                j.put("student_name",cb.getText().toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.v("jarray is",jarray.toString());

                        }



                    } else {

                        Toast.makeText(getContext(),
                                "Marked absent: " + cb.getText(),
                                Toast.LENGTH_SHORT).show();
                        studentdetails.setselected(cb.isChecked());
                        Log.v("marked absent", cb.getText().toString());
                      /*      if(cb.isChecked()==false){
                        for(int i=0;i<=jarray.length();i++) {
                            String unchecked = null;
                               if(cb.isChecked()==false)
                               {
                                    unchecked=cb.getText().toString();
                               }
                            try {
                                if (jarray.get(i).equals(unchecked)) ;

                                {
                                    jarray.remove(i);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.v("new jarray is", jarray.toString());
                        }
                        }*/

                    }


                }
            });

            studentdetails studentdetails = details.get(position);

            holder.cb.setText(studentdetails.getName());
            holder.cb.setChecked(studentdetails.isselected());
            holder.cb.setTag(studentdetails);


        }




        return v;

    }


}