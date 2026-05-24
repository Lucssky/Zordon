/**
 * João Pedro Dias - 2025.2.0120.0007-2
 * 2026.1-CMP1223/C01 - Fundamentos de Programação
 * orientada a objetos
 */
package Javazord.Zordon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuPrincipal implements Screen {

    final AppEntry app;

    public MenuPrincipal(final AppEntry app) {
        this.app = app;
    }

    @Override
    public void render(float delta){
        ScreenUtils.clear(Color.BLACK);

        app.viewport.apply();
        app.batch.setProjectionMatrix(app.viewport.getCamera().combined);

        app.batch.begin();
        app.font.draw(app.batch, "Jogo de demonstração da disciplina de POO", 1, 1.5f);
        app.font.draw(app.batch, "Clique para começar!", 1, 1);
        app.batch.end();

        if (Gdx.input.isTouched()) {
            app.setScreen(new DropGameScreen(app));
            dispose();
        }
    }

    @Override
    public void resize(int lg, int alt){
        app.viewport.update(lg, alt, true);
    }

    @Override
    public void show(){

    }
    @Override
    public void hide(){

    }

    @Override
    public void pause(){

    }
    @Override
    public void resume(){

    }
    @Override
    public void dispose(){

    }
}
