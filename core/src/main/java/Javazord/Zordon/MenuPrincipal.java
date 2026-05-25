/**
 * João Pedro Dias - 2025.2.0120.0007-2
 * 2026.1-CMP1223/C01 - Fundamentos de Programação
 * orientada a objetos
 */
package Javazord.Zordon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuPrincipal implements Screen {
    final AppEntrada app;
    private Stage stage;
    private Table raiz;


    public MenuPrincipal(final AppEntrada app) {
        this.app = app;
        stage = new Stage(app.sViewport);
        Gdx.input.setInputProcessor(stage);

        raiz = new Table();
        raiz.setFillParent(true);
        criaRaiz(raiz);
        stage.addActor(raiz);

        VerticalGroup scrollCont = new VerticalGroup();
        ScrollPane scroll = new ScrollPane(scrollCont, app.skin);
        scrollLayout(scroll, scrollCont);

    }
    private void criaRaiz(Table grid){

    }

    private void scrollLayout(ScrollPane scroll, VerticalGroup content){
        scroll.setFadeScrollBars(false);
        scroll.setFlickScroll(false);
        scroll.setScrollingDisabled(true, false);
        raiz.add(scroll).size(600f, 600f);

        TextButton balde = new TextButton("Balde", app.skin);
        balde.setHeight(150f);
        content.addActor(balde);
        balde.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                app.setScreen(new DropGameScreen(app));
            }
        });

        for (int i = 0; i < 6; i++) {
            TextButton teste = new TextButton("teste", app.skin);
            content.addActor(teste);
        }
        content.space(200f);
        content.grow();
        content.validate();
        scroll.validate();
    }


    @Override
    public void render(float delta){
        ScreenUtils.clear(Color.GRAY);

        app.fViewport.apply();
        app.batch.setProjectionMatrix(stage.getViewport().getCamera().combined);
        app.batch.begin();
            app.font.draw(app.batch, "Jogo de demonstração da disciplina de POO", 1, 1.5f);
            app.font.draw(app.batch, "Clique para começar!", 1, 1);
        app.batch.end();

        stage.getViewport().apply();
        stage.act();
        stage.draw();
//        if (Gdx.input.isTouched()) {
//            app.setScreen(new DropGameScreen(app));
//            dispose();
//        }
    }

    @Override
    public void resize(int lg, int alt){
        app.fViewport.update(lg, alt, true);
        stage.getViewport().update(lg, alt, true);
    }
    @Override
    public void show(){}
    @Override
    public void hide(){}
    @Override
    public void pause(){}
    @Override
    public void resume(){}
    @Override
    public void dispose(){
        stage.dispose();
    }
}
