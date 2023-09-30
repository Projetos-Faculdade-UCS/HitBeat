package hitbeat.controller;

import hitbeat.dao.BaseDAO;
import hitbeat.model.BaseModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class ModelController<T extends BaseModel> {
    private final BaseDAO<T> dao;

    public ModelController(BaseDAO<T> dao) {
        this.dao = dao;
    }

    public ObservableList<T> fetchAll() {
        return FXCollections.observableArrayList(dao.getAll());
    }
}
