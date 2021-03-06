package test.mobi.mobilizationtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static test.mobi.mobilizationtest.R.*;
import static test.mobi.mobilizationtest.R.string.artist_img;
import static test.mobi.mobilizationtest.R.string.works_in_list;

/**
 * Created by daria on 22.04.16.
 */
public class ArtistListAdapter extends RecyclerView.Adapter<ArtistListAdapter.ViewHolder> {

    private List<Artist> artists;

    public ArtistListAdapter(List<Artist> artists){
        this.artists = artists;
    }

    /**
     * Создание новых View и ViewHolder элементов списка
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout.artistlist_item, parent, false);
        return new ViewHolder(v);
    }

    /**
     * Заполнение виджетов данными из элемента artists[i]
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Artist curArtist = artists.get(position);
        int imageId = 0;
       // holder.image.setImageResource(imageId);                                                     // ВЫБИРАТЬ НУЖНЫЙ ID КАРТИНКИ
        holder.name.setText(curArtist.getName());
        holder.style.setText(curArtist.getStyleString());
        holder.works.setText(curArtist.getSongs() + "песен");
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }
    private void copy(Artist artist){
        int position = artists.indexOf(artist);
        Artist copy = artist.copy();
        artists.add(position + 1, copy);
        notifyItemInserted(position + 1);
    }
    private void delete(Artist artist){
        int position = artists.indexOf(artist);
        artists.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * Реализация класса ViewHolder, хранящего ссылки на виджеты.
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView style;
        private TextView works;     // Отображает количество альбомов и песен

        public ViewHolder(View itemView) {
            super(itemView);
            this.image = (ImageView) itemView.findViewById(id.artistListItemImage);
            this.name = (TextView) itemView.findViewById(id.artistListItemName);
            this.style = (TextView) itemView.findViewById(id.artistListItemStyle);
            this.works = (TextView) itemView.findViewById(id.artistListItemWorks);
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(layout.artistlist_item, viewGroup, false);

            return new ViewHolder(itemView);
        }

    }
}
