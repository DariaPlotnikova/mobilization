package test.mobi.mobilizationtest;

import android.media.Image;

/**
 * Created by daria on 22.04.16.
 */
public class Artist {
    private Image image;
    private String name;
    private String style;
    private int albums;
    private int songs;

    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
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

    public Artist copy(){
        Artist copyArtist = new Artist();
        copyArtist.setImage(image);
        copyArtist.setName(name);
        copyArtist.setStyle(style);
        copyArtist.setAlbums(albums);
        copyArtist.setSongs(songs);
        return copyArtist;
    }
}
