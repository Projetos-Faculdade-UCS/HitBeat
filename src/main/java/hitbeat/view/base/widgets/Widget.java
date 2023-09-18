package hitbeat.view.base.widgets;

import javafx.scene.Node;

/**
 * Classe abstracta que representa um widget.
 * @doc Um widget é um componente gráfico que pode ser adicionado a uma view.
 * 
 * @build Um widget é composto por um método build() que retorna um Node.
 * 
 * @build Um widget pode ser adicionado a uma view através do método build().
 * 
 * @build Um widget pode ser adicionado a outro widget através do método build().
 */
public abstract class Widget {
    public abstract Node build();
}
