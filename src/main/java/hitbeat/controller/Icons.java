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
}
