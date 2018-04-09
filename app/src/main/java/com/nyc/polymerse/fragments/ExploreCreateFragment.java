package com.nyc.polymerse.fragments;


import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.nyc.polymerse.R;

import java.io.File;
import java.net.URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreCreateFragment extends Fragment {

    private Spinner langSpinner;
    private ImageView imageView;
    private View rootView;

    public ExploreCreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_explore_create, container, false);
        langSpinner = rootView.findViewById(R.id.explore_lang_spinner);
        imageView = rootView.findViewById(R.id.explore_create_image);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle extra = getArguments();
        if (extra!= null) {

            String path = extra.getString("pic_absolute_path", "");
            if (!path.equals("")) {
                File photo = new File(path);
                Uri photoImage = Uri.fromFile(photo);
                if (photoImage != null) {
                    imageView.setImageURI(photoImage);
                }
            }
//            Bitmap imageBitmap = (Bitmap) extra.get("data");
//            Bitmap scaledImageBitmap = Bitmap.createScaledBitmap(imageBitmap,400,400,false);
//            imageView.setImageBitmap(scaledImageBitmap);
//            encodeBitmapAndSaveToFirebase(imageBitmap);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }
        };



        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add("English");
        adapter.add("French");
        adapter.add("German");
        adapter.add("Italian");
        adapter.add("Japanese");
        adapter.add("Spanish");
        adapter.add("Mandarin");
        adapter.add("Hint to be displayed");

        langSpinner.setAdapter(adapter);
        langSpinner.setSelection(adapter.getCount());
    }

}
