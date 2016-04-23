package test.mobi.mobilizationtest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.EventLogTags;
import android.util.JsonReader;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArtistList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    /*    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    */

        new ParseTask().execute();

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
                URL url = new URL("download.cdn.yandex.net/mobilization-2016/artists.json");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
                try {
                    List<Artist> artists = readMessagesArray(reader);                                           // ПАРСИТЬ В СТРОКУ
                    for (Artist a : artists){
                        resultJson += " " + a.getName();
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
                reader.endObject();
            }
            return new Artist(id, name, style, songs, albums, link, description, coverSmall, coverBig);
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
         * Выводит JSON данные в активити
         */
        protected void onPostExecute(String strJson){

        }

    }

}
