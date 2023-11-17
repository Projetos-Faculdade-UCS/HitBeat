package hitbeat.controller;

import hitbeat.view.base.widgets.SVGWidget;
import javafx.scene.paint.Color;

public class Icons {
    public SVGWidget getTracks() {
        return new SVGWidget("/hitbeat/svg/track-list.svg", 15, Color.WHITE);
    }

    public SVGWidget getGenres() {
        return new SVGWidget("/hitbeat/svg/genres.svg", 15, Color.WHITE);
    }

    public SVGWidget getHome() {
        return new SVGWidget("/hitbeat/svg/home.svg", 15, Color.WHITE);
    }

    public SVGWidget getArtists(){
        return new SVGWidget("/hitbeat/svg/artists.svg", 15, Color.WHITE);
    }

    public SVGWidget getPlaylists(){
        return new SVGWidget("/hitbeat/svg/playlist.svg", 15, Color.WHITE);
    }
    public SVGWidget getOptions(){
        return new SVGWidget("/hitbeat/svg/options.svg", 15, Color.WHITE);
    }

    public SVGWidget getFavorite(boolean isFavorite){
        if (isFavorite) {
            return new SVGWidget("/hitbeat/svg/heart-fill.svg", 15, Color.WHITE);
        }
        return new SVGWidget("/hitbeat/svg/heart.svg", 15, Color.WHITE);
    }
}
