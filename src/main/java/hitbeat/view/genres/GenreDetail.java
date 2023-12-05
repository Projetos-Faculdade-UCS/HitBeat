package hitbeat.view.genres;

import hitbeat.controller.genres.GenresController;
import hitbeat.model.Genre;
import hitbeat.view.base.widgets.generic_list.GenericTrackList;

public class GenreDetail extends GenericTrackList {
    private final GenresController genreController = new GenresController();

    public GenreDetail(Genre genre) {
        super();

        this.setTracksSupplier(() -> genreController.getTracks(genre));
    }
}
