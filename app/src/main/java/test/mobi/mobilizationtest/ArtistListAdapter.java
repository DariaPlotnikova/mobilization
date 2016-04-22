package test.mobi.mobilizationtest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

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
    public ArtistListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    /**
     * Заполнение виджетов данными из элемента artists[i]
     */
    @Override
    public void onBindViewHolder(ArtistListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
            image = (ImageView) itemView.findViewById(R.id.artistListItemImage);
            name = (TextView) itemView.findViewById(R.id.artistListItemName);
            style = (TextView) itemView.findViewById(R.id.artistListItemStyle);
            works = (TextView) itemView.findViewById(R.id.artistListItemWorks);
        }
    }
}
