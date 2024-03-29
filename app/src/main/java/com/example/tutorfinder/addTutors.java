package com.example.tutorfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class addTutors extends AppCompatActivity {


    String locality;
    String class1;
    String subject;
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_21);


        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        locality = bundle.getString("Locality");
        class1 = bundle.getString("Class");
        subject = bundle.getString("Subject");




        DbHandler handler  = new DbHandler(this, "tutors", null, 1);



       handler.addTutor(new Tutors("Aayush", "Class 9", "Sector 45, Gurgaon", "30 Students got 99+","img1","img2","img3", "Science","1234567890","Somewhere-Nunya Business" ,"20 year old but 10 years+ experience","nice", "ggg","4⭐" ));
       handler.addTutor(new Tutors("Vihaan", "Class 9", "Sector 45, Gurgaon", "15 Students got 98+","img4","img2","img3", "Science","0987654321","Dharavi" ,"9 years+ experience","woww","ok" ,"4.5⭐"));
       handler.addTutor(new Tutors("Sara", "Class 10", "Sector 55, Gurgaon", "25 Students got 95+", "img5", "img6", "img7", "Mathematics", "9876543210", "123 Main Street", "15 years of experience", "Great tutor", "Awesome", "4.8⭐"));
       handler.addTutor(new Tutors("Rahul", "Class 11", "Sector 22, Delhi", "20 Students got 97+", "img8", "img9", "img10", "Physics", "8765432109", "456 Elm Street", "12 years of experience", "Highly recommended", "Excellent", "4.9⭐"));
       handler.addTutor(new Tutors("Neha", "Class 12", "Sector 18, Noida", "18 Students got 96+", "img11", "img12", "img13", "Chemistry", "7654321098", "789 Oak Avenue", "10 years of experience", "Patient and knowledgeable", "Good", "4.7⭐"));
       handler.addTutor(new Tutors("Amit", "Class 8", "Sector 62, Gurgaon", "22 Students got 94+", "img14", "img15", "img16", "Biology", "6543210987", "101 Pine Street", "8 years of experience", "Excellent teaching skills", "Amazing", "4.6⭐"));
       handler.addTutor(new Tutors("Priya", "Class 6", "Sector 12, Ghaziabad", "12 Students got 92+", "img17", "img18", "img19", "English", "5432109876", "303 Maple Avenue", "5 years of experience", "Dedicated and supportive", "Fantastic", "4.5⭐"));

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://mocki.io/v1/f82eb2cb-16b9-42ef-a961-67aaec614e5b", null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {

                    for (int i = 0; i < 2; i++) {
                        JSONObject obj = response.getJSONObject(i);
                        String Name = obj.getString("Name");
                        String Class1 = obj.getString("Class");
                        String Locality = obj.getString("Locality");
                        String PastResults = obj.getString("PastResults");
                        String Subject = obj.getString("Subject");
                        String PhoneNumber = obj.getString("PhoneNumber");
                        String Address = obj.getString("Address");
                        String TeachingExperience = obj.getString("TeachingExperience");
                        String Image1 = obj.getString("Image1");
                        String Image2 = obj.getString("Image2");
                        String Image3 = obj.getString("Image3");
                        String Rating = obj.getString("Rating");
                        String Review1 = obj.getString("Review1");
                        String Review2 = obj.getString("Review2");




                        Tutors o1 = new Tutors(Name,Class1,Locality,PastResults,Image1,Image2,Image3,Subject,PhoneNumber,Address,TeachingExperience,Review1, Review2, Rating );
//                        if(handler.contains(o1))
//
                        System.out.println(handler.contains(o1));
                        handler.addTutor(o1);
                        System.out.println(handler.contains(o1));
                        System.out.println(o1.Name);




                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Shubham", "Something went wrong");
                Toast.makeText(addTutors.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonArrayRequest);


        Cursor c = handler.getTutor(locality, class1, subject);
        ArrayList<Tutors> tutors = new ArrayList<>();

        int i = 0;
        if (c != null && c.moveToFirst()) {
            do {


                        Tutors o2 = new Tutors(
                                c.getString(0), c.getString(1), c.getString(2),
                                c.getString(3), c.getString(4), c.getString(5),
                                c.getString(6), c.getString(7), c.getString(8),
                                c.getString(9), c.getString(10), c.getString(11), c.getString(12), c.getString(13)
                        );
                        tutors.add(o2);
                        i += 1;



            } while (c.moveToNext());

            Tutors tutors1[] = new Tutors[tutors.size()];

            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            Activity2_1_1 c1 = new Activity2_1_1(tutors.toArray(tutors1));
            recyclerView.setAdapter(c1);
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        }

        else{
            Toast.makeText(addTutors.this, "No records exist", Toast.LENGTH_SHORT).show();
        }
        handler.close();


        }


    }

/*
    {
            "Name": "Karuna",
            "Class": "10",
                "Locality": "Sector 55, Gurgaon",
            "PastResults": "20 Students got 99+",
                "Image1": "https://randomuser.me/api/portraits/women/47.jpg",
            "Image1": "https://randomuser.me/api/portraits/women/47.jpg",
            "Image1": "https://randomuser.me/api/portraits/women/47.jpg",
            "Subject": "Mathematics",
            "PhoneNumber": 1992,
            "Address": "3-301 Sagavi Appartments",
            "TeachingExperience": "40+years experience"
            },
*/
