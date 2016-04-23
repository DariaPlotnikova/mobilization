package test.mobi.mobilizationtest;

import android.media.Image;

import java.util.List;
import java.util.Objects;

/**
 * Created by daria on 22.04.16.
 */
public class Artist {
    private long id;
    private String name;
    private List<String> style;
    private int albums;
    private int songs;
    private String link;
    private String description;
    private String coverSmall;
    private String coverBig;

    Artist(){
        this.id = -1;
        this.name = null;
        this.style = null;
        this.albums = -1;
        this.songs = -1;
        this.link = null;
        this.description = null;
        this.coverSmall = null;
        this.coverBig = null;
    }
    Artist(long id, String name, List style, int albums, int songs, String link,
           String description, String coverSmall, String coverBig){
        this.id = id;
        this.name = name;
        this.style = style;
        this.albums = albums;
        this.songs = songs;
        this.link = link;
        this.description = description;
        this.coverSmall = coverSmall;
        this.coverBig = coverBig;
    }

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

    public List getStyle() {
        return style;
    }
    public String getStyleString() {
        String styles = "";
        for (String s : style){
            styles += "\"" + s + "\",";
        }
        styles = styles.substring(0, styles.length()-1);
        return styles;
    }
    public void setStyle(List style) {
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

    public String toJson(){
        return ("{\"id\":"+this.id + ", \"name\":\""+this.name + "\", \"genres\":["+
                this.getStyleString()+"], \"tracks\":"+this.songs + ", \"albums\":"+this.albums +
                ",\"link\":\""+this.link + "\", \"description\":\""+this.description +
                "\", \"cover\":{\"small\":\""+this.coverSmall + "\" ,\"big\":\""+this.coverBig+"\"}}");
    }

}
