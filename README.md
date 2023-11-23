# HitBeat

Projeto para a disciplina "Projeto Temático I" da Universidade de Caxias do Sul

## Integrantes

* Bruno Gonçalves Martins
* Marco Aurélio da Rosa Haubrich
* Moacir Arnoldo Rannow

## Dependências

* Java 21.0.1
* JavaFx
* Maven
* libavformat-dev
* ffmpeg
* [MaterialFX](https://github.com/palexdev/MaterialFX/)

## Instruções para execução

* instalar dependencias de sistema

```bash
    sudo apt install ffmpeg
    sudo apt install libavformat-dev
```

* conferir que as dependências settadas no `pom.xml` estão funcionando corretamente (testado com vscode + `Java Extension Pack`)
* O projeto pode ser inicializado através da classe `src.main.java.hitbeat.App`

ps:: para utilizar corretamente a lib JavaFx é necessario que o projeto esteja modularizado. Este Projeto ainda não está modularizado. Para funconar foi criada uma `dummy class` que é responsavel por inicializar o JavaFx. Para ajustar utilizar esta [referência](https://edencoding.com/runtime-components-error/#:~:text=A%20“Runtime%20Components%20are%20Missing,modules%2C%20using%20command%20line%20arguments.)
