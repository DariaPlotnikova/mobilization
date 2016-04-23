package test.mobi.mobilizationtest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.JsonReader;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ArtistList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<Artist> artists = new ArrayList<Artist>();
        RecyclerView recView = (RecyclerView)findViewById(R.id.artistListView);
        ArtistListAdapter adapter = new ArtistListAdapter(artists);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();

        new ParseTask().execute();

        recView.setAdapter(adapter);
        recView.setLayoutManager(layoutManager);
        recView.setItemAnimator(itemAnimator);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_artist_list, menu);
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


    private class ParseTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;

        /**
         * Получает JSON данные из внешнего источника
         */
        @Override
        protected String doInBackground(Void... params) {
            String resultJson = "";

            try {
                URL url = new URL("http://download.cdn.yandex.net/mobilization-2016/artists.json");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
                try {
                    List<Artist> artists = readMessagesArray(reader);
                    for (Artist a : artists){
                        resultJson += a.toJson() + ", ";
                        resultJson = resultJson.substring(0, resultJson.length()-2);    // Удаляет последнюю запятую
                    }
                }
                finally {
                    reader.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }

        /**
         *  Формирует список исполнителей
         */
        public List<Artist> readMessagesArray(JsonReader reader) throws IOException{
            List messages = new ArrayList();

            reader.beginArray();
            while (reader.hasNext()){
                messages.add(readMessage(reader));
            }
            reader.endArray();
            return messages;
        }

        /**
         * Считывает инфо об одном исполнителе
         */
        public Artist readMessage(JsonReader reader) throws IOException {
            long id = -1;
            String name = null;
            List style = null;    // func
            int songs = -1;
            int albums = -1;
            String link = null;
            String description = null;
            String coverSmall = null;
            String coverBig = null;

            reader.beginObject();
            while (reader.hasNext()){
                String key = reader.nextName();
                if (key.equals("id")){
                    id = reader.nextLong();
                } else if (key.equals("name")){
                    name = reader.nextString();
                } else if (key.equals("genres")){
                    style = readStringArray(reader);
                } else if (key.equals("tracks")){
                    songs = reader.nextInt();
                } else if (key.equals("albums")){
                    albums = reader.nextInt();
                } else if (key.equals("link")){
                    link = reader.nextString();
                } else if (key.equals("description")){
                    description = reader.nextString();
                } else if (key.equals("cover")){
                    String[] covers = readCovers(reader);
                    coverSmall = covers[0];
                    coverBig = covers[1];
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
            return new Artist(id, name, style, albums, songs, link, description, coverSmall, coverBig);
        }

        /**
         * Считывает стили исполнителя
         */
        public List readStringArray(JsonReader reader) throws IOException{
            List strings = new ArrayList();

            reader.beginArray();
            while (reader.hasNext()){
                strings.add(reader.nextString());
            }
            reader.endArray();
            return strings;
        }

        /**
         * Считывает обложки исполнителя
         */
        public String[] readCovers(JsonReader reader) throws IOException{
            String[] covers = new String[2];

            reader.beginObject();
            while (reader.hasNext()){
                String key = reader.nextName();
                if (key.equals("small")){
                    covers[0] = reader.nextString();
                } else if (key.equals("big")){
                    covers[1] = reader.nextString();
                }
                else {
                    reader.skipValue();
                }
            }
            reader.endObject();
            return covers;
        }

        /**
         * Выводит JSON данные в главое активити (список)
         */
        @Override
        protected void onPostExecute(String strJson){
            super.onPostExecute(strJson);

            JSONObject dataJson = null;
            try{
                dataJson = new JSONObject(strJson);
                JSONArray artists = dataJson.getJSONArray("");
                JSONObject artist = artists.getJSONObject(0);
                String name = "Имечко";//artist.getString("name");
                String style = "Стили";// artist.getString("genres");
                int songs = 9;//artist.getInt("tracks");
                int albums = 3;//artist.getInt("albums");

                ImageView imgView = (ImageView) findViewById(R.id.artistListItemImage);
                TextView nameView = (TextView) findViewById(R.id.artistListItemName);
                TextView styleView = (TextView) findViewById(R.id.artistListItemStyle);
                TextView worksView = (TextView) findViewById(R.id.artistListItemWorks);

                nameView.setText(name);
                styleView.setText(style);
                worksView.setText(songs + " песен, " + albums + " альбомов");

            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }

    }

}
