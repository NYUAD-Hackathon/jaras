package com.example.achkar.jaras;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static android.app.PendingIntent.getActivity;

/**
 * Created by Achkar on 15-04-11.
 */
public class SeePost extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class lstData {
        private
        String title;
        String date;
        int photo;
        String likes_num;
        String dislikes_num;

        public lstData() {
            title = "";
            date = "";
            photo = 0;
        }

        lstData(String n, String d, int p, String likes, String dislikes) {
            title = n;
            date = d;
            photo = p;
            likes_num = likes;
            dislikes_num = dislikes;
        }

        String getName() {
            return title;
        }

        String getDate() {
            return date;
        }

        int getPhoto() {
            return photo;
        }

        String getLikes() {
            return likes_num;
        }

        String getDislikes() {
            return dislikes_num;
        }
    }

    public static class CustomAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private ArrayList<lstData> objects;

        private class ViewHolder {
            TextView listTitles;
            TextView listData;
            ImageView listPhotos;
            TextView listLikes;
            TextView listDislikes;
        }

        public CustomAdapter(Context context, ArrayList<lstData> objects) {
            inflater = LayoutInflater.from(context);
            this.objects = objects;
        }

        public int getCount() {
            return objects.size();
        }

        public lstData getItem(int position) {
            return objects.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.list, null);
                holder.listTitles = (TextView) convertView.findViewById(R.id.post_title);
                holder.listData = (TextView) convertView.findViewById(R.id.post_body);
                holder.listPhotos = (ImageView) convertView.findViewById(R.id.post_image);
                holder.listLikes = (TextView) convertView.findViewById(R.id.post_like_text);
                holder.listDislikes = (TextView) convertView.findViewById(R.id.post_dislike_text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.listTitles.setText(objects.get(position).getName());
            holder.listData.setText(objects.get(position).getDate());
            holder.listPhotos.setImageResource(objects.get(position).getPhoto());
            holder.listLikes.setText(objects.get(position).getLikes());
            holder.listDislikes.setText(objects.get(position).getDislikes());
            return convertView;
        }
    }

    public class PlaceholderFragment extends Fragment {
        private ArrayAdapter<String> mForcastAdaptor;
        private ArrayAdapter<lstData> mForcastAdaptor2;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_see, container, false);

            Intent intent = getIntent();
            ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) intent.getSerializableExtra("messages_list");

            String[] data = {"ahmed", "mohamed", "ahmed", "mohamed", "ahmed", "mohamed", "ahmed", "mohamed", "ahmed", "mohamed","ahmed", "mohamed", "ahmed", "mohamed", "ahmed", "mohamed", "ahmed", "mohamed", "ahmed", "mohamed"};
            String[] upvotes = {"10", "57", "43", "23", "143", "3", "8", "32", "94", "12","10", "57", "43", "23", "143", "3", "8", "32", "94", "12"};
            String[] downvotes = {"3", "8", "23", "143", "32", "94", "12", "10", "57", "43","10", "57", "43", "23", "143", "3", "8", "32", "94", "12"};
            ArrayList<lstData> forcast2 = new ArrayList<lstData>();
            for (int i = 0; i < list.size() ; i++) {
                forcast2.add(new lstData((String) list.get(i).get("title"), (String) list.get(i).get("message"), R.drawable.ic_launcher, upvotes[i], downvotes[i]));
            }
                List<String> forecast = new ArrayList<String>(Arrays.asList(data));
                ListView listView1 = (ListView) rootView.findViewById(R.id.listview_forcast);
                mForcastAdaptor2 = new ArrayAdapter<lstData>(getActivity(), R.layout.list, R.id.list_item_photo_view, forcast2);
                CustomAdapter customAdapter = new CustomAdapter(getActivity(), forcast2);
                ListView listView = (ListView) rootView.findViewById(R.id.listview_forcast);
                listView.setAdapter(customAdapter);

                //listView.setAdapter(mForcastAdaptor2);
                return rootView;
            }
        }
    }






