package Javazord.Zordon;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener {
    private SpriteBatch spriteBatch;
    private FitViewport viewport;

    float gotaTempo;
    Vector2 touchPos;

    //Física
    Rectangle baldeRet;
    Rectangle gotaRet;

    //Assets
    Texture fundo;
    Texture balde;
    Sprite baldeSprite;
    Texture gota;
    Array<Sprite> gotaSprites;

    Sound gotaSom;
    Music musica;


    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8, 5);

        baldeRet = new Rectangle();
        gotaRet = new Rectangle();

        touchPos = new Vector2();

        fundo = new Texture("DropGame/background.png");
        balde = new Texture("DropGame/bucket.png");
        baldeSprite = new Sprite(balde);
        baldeSprite.setSize(1, 1);
        gota = new Texture("DropGame/drop.png");
        gotaSprites = new Array<>();

        gotaSom = Gdx.audio.newSound(Gdx.files.internal("DropGame/drop.mp3"));
        musica = Gdx.audio.newMusic(Gdx.files.internal("DropGame/music.mp3"));
        musica.setLooping(true);
        musica.setVolume(.5f);
        musica.play();
    }

    @Override
    public void render() {
        input();
        logic();
        draw();
    }

    private void input(){
        float speed = 4f;
        float delta = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            baldeSprite.translateX(speed * delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            baldeSprite.translateX(-speed * delta);
        }

        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);
            baldeSprite.setCenterX(touchPos.x);
        }
    }

    private void logic() {
        float cenaLarg = viewport.getWorldWidth();
        //float cenaAlt = viewport.getWorldHeight();
        float baldeLarg = baldeSprite.getWidth();
        float baldeAlt = baldeSprite.getHeight();
        float delta = Gdx.graphics.getDeltaTime();

        baldeSprite.setX(MathUtils.clamp(baldeSprite.getX(), 0, cenaLarg - baldeLarg));
        baldeRet.set(baldeSprite.getX(), baldeSprite.getY(), baldeLarg, baldeAlt);

        for (int i = gotaSprites.size - 1; i >= 0; i--){
            Sprite gotaSprite = gotaSprites.get(i);
            float gotaLarg = gotaSprite.getWidth();
            float gotaAlt = gotaSprite.getHeight();
            //Movimento
            gotaSprite.translateY(-2f * delta);
            //Física
            gotaRet.set(gotaSprite.getX(), gotaSprite.getY(), gotaLarg, gotaAlt);
            //Limpeza
            if (gotaSprite.getY() < - gotaAlt) gotaSprites.removeIndex(i);
            else if (baldeRet.overlaps(gotaRet)){
                gotaSprites.removeIndex(i);
                gotaSom.play();
            }
        }
        gotaTempo += delta;
        if (gotaTempo > 1f){
            gotaTempo = 0;
            newGota();
        }
    }

    private void draw(){
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        float cenaLarg = viewport.getWorldWidth();
        float cenaAlt = viewport.getWorldHeight();

        spriteBatch.draw(fundo, 0, 0, cenaLarg, cenaAlt);
        baldeSprite.draw(spriteBatch);

        for (Sprite gotaSprite : gotaSprites){
            gotaSprite.draw(spriteBatch);
        }
        spriteBatch.end();
    }

    private void newGota(){
        float gotaLarg = 1;
        float gotaAlt = 1;
        float cenaLarg = viewport.getWorldWidth();
        float cenaAlt = viewport.getWorldHeight();

        Sprite gotaSprite = new Sprite(gota);
        gotaSprite.setSize(gotaLarg, gotaAlt);
        gotaSprite.setX(MathUtils.random(0f, cenaLarg -gotaLarg));
        gotaSprite.setY(cenaAlt);
        gotaSprites.add(gotaSprite);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
    }

    public void pause(){

    }
    public void resume(){

    }
}
