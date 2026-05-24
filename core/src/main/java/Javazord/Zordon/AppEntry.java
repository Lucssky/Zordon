/**
 * João Pedro Dias - 2025.2.0120.0007-2
 * 2026.1-CMP1223/C01 - Fundamentos de Programação
 * orientada a objetos
 */
package Javazord.Zordon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class AppEntry extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    public FitViewport viewport;


    public void create(){
        batch = new SpriteBatch();
        font = new BitmapFont();
        viewport = new FitViewport(8,5);

        font.setUseIntegerPositions(false);
        font.getData().setScale( 3 * (viewport.getWorldHeight() / Gdx.graphics.getHeight()));

        this.setScreen(new MenuPrincipal(this));
    }

    public void render(){
        super.render();
    }

    public void dispose(){
        batch.dispose();
        font.dispose();
    }
}
