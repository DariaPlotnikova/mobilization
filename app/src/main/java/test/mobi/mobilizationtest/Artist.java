package test.mobi.mobilizationtest;

import android.media.Image;

/**
 * Created by daria on 22.04.16.
 */
public class Artist {
    private long id;
    private String name;
    private String style;
    private int albums;
    private int songs;
    private String link;
    private String description;
    private String coverSmall;
    private String coverBig;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }
    public void setStyle(String style) {
        this.style = style;
    }

    public int getAlbums() {
        return albums;
    }
    public void setAlbums(int albums) {
        this.albums = albums;
    }

    public int getSongs() {
        return songs;
    }
    public void setSongs(int songs) {
        this.songs = songs;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverSmall() {
        return coverSmall;
    }
    public void setCoverSmall(String coverSmall) {
        this.coverSmall = coverSmall;
    }

    public String getCoverBig() {
        return coverBig;
    }
    public void setCoverBig(String coverBig) {
        this.coverBig = coverBig;
    }

    public Artist copy(){
        Artist copyArtist = new Artist();
        copyArtist.setId(id);
        copyArtist.setName(name);
        copyArtist.setStyle(style);
        copyArtist.setAlbums(albums);
        copyArtist.setSongs(songs);
        copyArtist.setLink(link);
        copyArtist.setDescription(description);
        copyArtist.setCoverSmall(coverSmall);
        copyArtist.setCoverBig(coverBig);
        return copyArtist;
    }
}
